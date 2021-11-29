package io.infinitelambda.lab;

import java.io.File;

public class AzureUploader implements Uploader {

    @Override
    public UploadResult upload(File file, String contentType) {
        System.out.println("Uploading to Azure.");
        return new UploadResult(file.getName(), file.getAbsolutePath(), contentType);
    }
}
