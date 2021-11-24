package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Get the user's options
 */
public class Options {
    /**
     * If the user can stream on the platform
     */
    @SerializedName("streaming")
    private Boolean streaming;

    /**
     * the streaming duration of the user
     */
    @SerializedName("streaming_duration")
    private Integer streamingDuration;

    /**
     * The user can listen to the music in offline mode
     */
    @SerializedName("offline")
    private Boolean canListenOffline;

    /**
     * The HQ can be activated
     */
    @SerializedName("hq")
    private Boolean canActivateHd;

    /**
     * Displays ads
     */
    @SerializedName("ads_display")
    private Boolean isAdsDisplay;

    /**
     * Activates audio ads
     */
    @SerializedName("ads_audio")
    private Boolean isAdsAudio;

    /**
     * If the user reached the limit of linked devices
     */
    @SerializedName("too_many_devices")
    private Boolean isTooManyDevices;

    /**
     * If the user can subscribe to the service
     */
    @SerializedName("can_subscribe")
    private Boolean canSubscribe;

    /**
     * The limit of radio skips. 0 = no limit
     */
    @SerializedName("radio_skips")
    private Integer radioSkips;

    /**
     * Lossless is available
     */
    @SerializedName("lossless")
    private Boolean isLosslessAvailable;

    /**
     * Allows to display the preview of the tracks
     */
    @SerializedName("preview")
    private Boolean isPreviewAllowed;

    /**
     * Allows to stream the radio
     */
    @SerializedName("radio")
    private Boolean isRadioAllowed;

    public Boolean getStreaming() {
        return streaming;
    }

    public void setStreaming(Boolean streaming) {
        this.streaming = streaming;
    }

    public Integer getStreamingDuration() {
        return streamingDuration;
    }

    public void setStreamingDuration(Integer streamingDuration) {
        this.streamingDuration = streamingDuration;
    }

    public Boolean getCanListenOffline() {
        return canListenOffline;
    }

    public void setCanListenOffline(Boolean canListenOffline) {
        this.canListenOffline = canListenOffline;
    }

    public Boolean getCanActivateHd() {
        return canActivateHd;
    }

    public void setCanActivateHd(Boolean canActivateHd) {
        this.canActivateHd = canActivateHd;
    }

    public Boolean getIsAdsDisplay() {
        return isAdsDisplay;
    }

    public void setIsAdsDisplay(Boolean isAdsDisplay) {
        this.isAdsDisplay = isAdsDisplay;
    }

    public Boolean getIsAdsAudio() {
        return isAdsAudio;
    }

    public void setIsAdsAudio(Boolean isAdsAudio) {
        this.isAdsAudio = isAdsAudio;
    }

    public Boolean getIsTooManyDevices() {
        return isTooManyDevices;
    }

    public void setIsTooManyDevices(Boolean isTooManyDevices) {
        this.isTooManyDevices = isTooManyDevices;
    }

    public Boolean getCanSubscribe() {
        return canSubscribe;
    }

    public void setCanSubscribe(Boolean canSubscribe) {
        this.canSubscribe = canSubscribe;
    }

    public Integer getRadioSkips() {
        return radioSkips;
    }

    public void setRadioSkips(Integer radioSkips) {
        this.radioSkips = radioSkips;
    }

    public Boolean getIsLosslessAvailable() {
        return isLosslessAvailable;
    }

    public void setIsLosslessAvailable(Boolean isLosslessAvailable) {
        this.isLosslessAvailable = isLosslessAvailable;
    }

    public Boolean getIsPreviewAllowed() {
        return isPreviewAllowed;
    }

    public void setIsPreviewAllowed(Boolean isPreviewAllowed) {
        this.isPreviewAllowed = isPreviewAllowed;
    }

    public Boolean getIsRadioAllowed() {
        return isRadioAllowed;
    }

    public void setIsRadioAllowed(Boolean isRadioAllowed) {
        this.isRadioAllowed = isRadioAllowed;
    }

    @Override
    public String toString() {
        return "Options{" +
                "streaming=" + streaming +
                ", streamingDuration=" + streamingDuration +
                ", offline=" + canListenOffline +
                ", hq=" + canActivateHd +
                ", adsDisplay=" + isAdsDisplay +
                ", adsAudio=" + isAdsAudio +
                ", tooManyDevices=" + isTooManyDevices +
                ", canSubscribe=" + canSubscribe +
                ", radioSkips=" + radioSkips +
                ", lossless=" + isLosslessAvailable +
                ", preview=" + isPreviewAllowed +
                ", radio=" + isRadioAllowed +
                '}';
    }
}
