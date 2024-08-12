package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.demo.domain.Item;
import com.example.demo.domain.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.requsetDTO.ItemInsertRequestDTO;
import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.responseDTO.ItemDetailDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<String>  uploadToS32(List<MultipartFile> files, String nickname) {
        List<String> fileNames = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // 최적의 스레드 풀 크기

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String current_date = now.format(dateTimeFormatter);

        try {
            AtomicInteger count = new AtomicInteger(1); // 고유한 파일 번호를 생성하기 위한 AtomicInteger

            CompletableFuture<?>[] futures = files.stream()
                    .map(file -> CompletableFuture.runAsync(() -> {
                        try {
                            String fileName = nickname + current_date + count.getAndIncrement();

                            ObjectMetadata metadata = new ObjectMetadata();

                            metadata.setContentLength(file.getSize());
                            metadata.setContentType(file.getContentType());

                            amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);
                            System.out.println("사진 URL" + amazonS3.getUrl(bucket, fileName));
                            fileNames.add(fileName);
                        } catch (IOException e) {
                            // 에러 처리
                        }
                    }, executor))
                    .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(futures).join(); // 모든 작업이 완료될 때까지 대기
        } finally {
            executor.shutdown(); // ExecutorService 종료
        }

        return fileNames;
    }


    @Transactional
    public String insertItem(ItemInsertRequestDTO requestDTO, LoginResponseDTO loginDTO, List<MultipartFile> files) {
        List<User> user = userRepository.findByNickName(loginDTO.getNickName());

        Item item = Item.builder()
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .stockQuantity(requestDTO.getStockQuantity())
                .description(requestDTO.getDescription())
                .build();

        item.setUser(user.get(0));
        itemRepository.save(item);

        if(files != null) {
            List<String> fileNames = uploadToS32(files, user.get(0).getNickName());
            items.setPhotoName(fileNames.get(0));

            for(String fileName : fileNames){
                Item item = Item.createPhoto(fileName, item);
                itemRepository.save(item);
            }
        }

        return "good";
    }

    @Transactional
    public ItemDetailDTO showDetail(Long itemId, Long userId){
        Item item = itemRepository.findById(itemId).orElse(null);

        User user = userRepository.findById(userId).orElse(null);

        return ItemDetailDTO.builder()
                .title(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .description(item.getDescription())
                .witer(user.getNickName())
                .build();
    }
    @Transactional
    public List<Item> findItemList(){
        return itemRepository.findAll();
    }

}
