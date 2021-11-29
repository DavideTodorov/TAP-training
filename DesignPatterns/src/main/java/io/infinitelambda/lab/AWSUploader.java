package io.infinitelambda.lab;

import java.io.File;

public class AWSUploader implements Uploader {
    @Override
    public UploadResult upload(File file, String contentType) {
        System.out.println("Uploading to AWS.");
        return new UploadResult(file.getName(), file.getAbsolutePath(), contentType);
    }
}
