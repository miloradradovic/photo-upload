package com.example.photouploadserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface IPhotoService {

    boolean uploadPhoto(MultipartFile file);
    String getPhotoByName(String name);
    void delete(String name);
}
