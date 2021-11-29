package io.infinitelambda.lab;

public class UploadResult {
    private String name;
    private String url;
    private String contentType;

    public UploadResult(String name, String url, String contentType) {
        this.name = name;
        this.url = url;
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
