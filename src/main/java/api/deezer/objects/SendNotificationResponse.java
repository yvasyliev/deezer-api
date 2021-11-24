package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Response to {@link api.deezer.requests.UserRequests#sendNotification(String)} request.
 */
public class SendNotificationResponse {
    /**
     * Whether request was successful.
     */
    @SerializedName("success")
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "BasicResponse{" +
                "success=" + success +
                '}';
    }
}
