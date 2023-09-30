package tn.Shamash.Pfe.serviceImpl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "uploads/";

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }


    public Resource downloadFile(String fileName) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Failed to load file: " + fileName);
        }
    }

}
