package io.infinitelambda.lab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || args.length > 2) {
            throw new IllegalArgumentException("Not enough parameters");
        }
        File file = new File(args[1]);
        Path path = file.toPath();
        String contentType = null;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uploader uploader = null;
        if (args[0].toLowerCase().equals("aws")){
            uploader = new AWSUploader();
        }else if (args[0].toLowerCase().equals("azure")){
            uploader = new AzureUploader();
        }else {
            System.out.println("No such cloud provider. Please try again.");
            return;
        }

        UploadResult upload = uploader.upload(file, contentType);

        System.out.println("Upload finished");
    }
}
