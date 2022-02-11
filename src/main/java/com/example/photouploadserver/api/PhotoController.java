package com.example.photouploadserver.api;

import com.example.photouploadserver.dto.PhotoDTO;
import com.example.photouploadserver.service.impl.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file) {
        photoService.uploadPhoto(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<PhotoDTO> getByName(@PathVariable String name) {
        String photo = photoService.getPhotoByName(name + ".png");
        return new ResponseEntity<>(new PhotoDTO(photo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        photoService.delete(name + ".png");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
