package com.example.auxiliary.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {
    @Value("${file.root.path}")
    private String fileRootPath;

    @PostMapping("/file/upload")
    public String fileUpload(@RequestParam("file") MultipartFile[] files) {
        String filePath = "";
        List<String> fileNames = new ArrayList<>();
        // 多文件上传
        for (MultipartFile file : files) {
            // 上传简单文件名
            String originalFilename = System.currentTimeMillis()+file.getOriginalFilename().replace(StringPool.SPACE,StringPool.EMPTY);
            fileNames.add(originalFilename);
            // 存储路径
            filePath = new StringBuilder(fileRootPath)
                .append(originalFilename)
                .toString();
            try {
                // 保存文件
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileNames.stream().collect(Collectors.joining(StringPool.COMMA));
    }
}
