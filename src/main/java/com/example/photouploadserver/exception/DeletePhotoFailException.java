package com.example.photouploadserver.exception;

public class DeletePhotoFailException extends RuntimeException {

    public DeletePhotoFailException() {
        super("Something went wrong while deleting the photo. Please try again later!");
    }
}
