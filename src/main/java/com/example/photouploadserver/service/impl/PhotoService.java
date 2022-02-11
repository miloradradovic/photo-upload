package com.example.photouploadserver.service.impl;

import com.example.photouploadserver.exception.DeletePhotoFailException;
import com.example.photouploadserver.exception.UploadPhotoFailException;
import com.example.photouploadserver.service.IPhotoService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class PhotoService implements IPhotoService {

    private static final String ROOT_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator +
        "photos";
    private final Path rootLocation = Paths.get(ROOT_PATH);

    @Override
    public boolean uploadPhoto(MultipartFile file) {
        try {
            if (getPhotoByName(file.getOriginalFilename()) != null) {
                Files.delete(Paths.get(ROOT_PATH + File.separator + file.getOriginalFilename()));
            }
            Files.createDirectories(rootLocation);
            Files.copy(file.getInputStream(), this.rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            return true;
        } catch (Exception e) {
            throw new UploadPhotoFailException();
        }
    }

    @Override
    public String getPhotoByName(String name) {
        Path destination = Paths.get(ROOT_PATH + File.separator + name);// retrieve the image by its name
        try {
            byte[] bytes = IOUtils.toByteArray(destination.toUri());
            return Base64Utils.encodeToString(bytes);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void delete(String name) {
        try {
            Files.delete(Paths.get(ROOT_PATH + File.separator + name));
        } catch (IOException e) {
            throw new DeletePhotoFailException();
        }
    }
}
