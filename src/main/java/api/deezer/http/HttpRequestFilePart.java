package api.deezer.http;

/**
 * A file to upload as {@code multipart/form-data}.
 */
public class HttpRequestFilePart {
    /**
     * Form field name.
     */
    private final String name;

    /**
     * File data.
     */
    private final byte[] value;

    /**
     * Filename.
     */
    private String filename;

    /**
     * Form field content type.
     */
    private String contentType;

    /**
     * Content encoding.
     */
    private String contentTransferEncoding;

    public HttpRequestFilePart(final String name, final byte[] value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Creates an instance of {@link HttpRequestFilePart} with a PNG image as a value.
     *
     * @param name  form field name.
     * @param value image data.
     * @return instance of {@link HttpRequestFilePart}.
     */
    public static HttpRequestFilePart png(final String name, final byte[] value) {
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