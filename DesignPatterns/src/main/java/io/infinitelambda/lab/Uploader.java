package io.infinitelambda.lab;

import java.io.File;

public interface Uploader {

    public UploadResult upload(File file, String contentType);

}
