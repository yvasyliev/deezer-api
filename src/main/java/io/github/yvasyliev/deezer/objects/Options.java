package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.Duration;

/**
 * Get the user's options.
 */
@Data
public class Options {
    /**
     * If the user can stream on the platform.
     */
    @SerializedName("streaming")
    private Boolean streaming;

    /**
     * The streaming duration of the user.
     */
    @SerializedName("streaming_duration")
    private Duration streamingDuration;

    /**
     * The user can listen to the music in offline mode.
     */
    @SerializedName("offline")
    private Boolean canListenOffline;

    /**
     * The HQ can be activated.
     */
    @SerializedName("hq")
    private Boolean canActivateHd;

    /**
     * Displays ads.
     */
    @SerializedName("ads_display")
    private Boolean isAdsDisplay;

    /**
     * Activates audio ads.
     */
    @SerializedName("ads_audio")
    private Boolean isAdsAudio;

    /**
     * If the user reached the limit of linked devices.
     */
    @SerializedName("too_many_devices")
    private Boolean isTooManyDevices;

    /**
     * If the user can subscribe to the service.
     */
    @SerializedName("can_subscribe")
    private Boolean canSubscribe;

    /**
     * The limit of radio skips. 0 = no limit.
     */
    @SerializedName("radio_skips")
    private Integer radioSkips;

    /**
     * Lossless is available.
     */
    @SerializedName("lossless")
    private Boolean isLosslessAvailable;

    /**
     * Allows to display the preview of the tracks.
     */
    @SerializedName("preview")
    private Boolean isPreviewAllowed;

    /**
     * Allows to stream the radio.
     */
    @SerializedName("radio")
    private Boolean isRadioAllowed;
}
