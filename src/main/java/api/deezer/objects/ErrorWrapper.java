package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

public class ErrorWrapper {

    @SerializedName("error")
    private Error error;

    public void setError(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}