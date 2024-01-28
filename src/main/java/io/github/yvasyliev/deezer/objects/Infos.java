package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.json.UploadTokenExpiresAtDeserializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Get the information about the API in the current country.
 */
@Data
public class Infos {
    /**
     * The current country ISO code.
     */
    @SerializedName("country_iso")
    private String countryIso;

    /**
     * The current country name.
     */
    @SerializedName("country")
    private String country;

    /**
     * Indicates if Deezer is open in the current country or not.
     */
    @SerializedName("open")
    private Boolean isOpen;

    /**
     * File upload token.
     */
    @SerializedName("upload_token")
    private String uploadToken;

    /**
     * Upload token expiration time.
     */
    @SerializedName("upload_token_lifetime")
    @JsonAdapter(UploadTokenExpiresAtDeserializer.class)
    private LocalDateTime uploadTokenExpiresAt;

    @SerializedName("has_podcasts")
    private Boolean hasPodcasts;

    /**
     * An array of available offers in the current country.
     */
    @SerializedName("offers")
    private List<Offer> offers;

    public boolean isUploadTokenExpired() {
        return LocalDateTime.now().isBefore(uploadTokenExpiresAt);
    }
}
