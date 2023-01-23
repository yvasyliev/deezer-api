package api.deezer.http;

public class HttpRequestFilePart {

    private String name = null;
    private String filename = null;
    private String contentType = null;
    private String contentTransferEncoding = null;
    private byte[] value = null;

    public HttpRequestFilePart(final String name, final byte[] value) {
        this.name = name;
        this.value = value;
    }

    public static HttpRequestFilePart image(final String name, final byte[] value){
        HttpRequestFilePart image = new HttpRequestFilePart(name, value);
        image.setContentType("image/png");
        image.setFilename("image.png");
        return image;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentTransferEncoding() {
        return contentTransferEncoding;
    }

    public void setContentTransferEncoding(String contentTransferEncoding) {
        this.contentTransferEncoding = contentTransferEncoding;
    }

    public byte[] getValue() {
        return value;
    }

}