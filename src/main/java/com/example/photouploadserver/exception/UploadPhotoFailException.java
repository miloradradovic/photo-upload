package com.example.photouploadserver.exception;

public class UploadPhotoFailException extends RuntimeException {

    public UploadPhotoFailException() {
        super("Something went wrong while uploading the photo. Please try again later!");
    }
}
