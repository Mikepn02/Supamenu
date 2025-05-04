package com.mikepn.supamenubackend.v1.standalone;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${application.security.file.uploads.photos-output-path}")
    private String fileUploadPath;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public String saveFile(
            @NonNull MultipartFile sourceFile,
            @NonNull UUID menuId
    ) {
        final String fileUploadSubPath = "items" + File.separator + menuId;
        return uploadFile(sourceFile, fileUploadSubPath, menuId);
    }

    private String uploadFile(
            @NonNull MultipartFile sourceFile,
            @NonNull  String fileUploadSubPath, UUID menuId) {

        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("Failed to create targetFolder");
                return null;
            }
        }

        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String fileName = UUID.randomUUID().toString();
        String targetFilePath = finalUploadPath + File.separator + fileName + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);

        try {
            Files.write(targetPath, sourceFile.getBytes());
            log.info("File saved to " + targetFilePath);

            String normalizedFilePath = ("uploads" + File.separator + "items" + File.separator + menuId.toString()
                    + File.separator + fileName + "." + fileExtension).replace("\\", "/");

            return normalizedFilePath;

        } catch (IOException e) {
            log.error("File was not saved", e);
        }
        return null;
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return filename.substring(lastDotIndex + 1).toLowerCase();
    }
}
