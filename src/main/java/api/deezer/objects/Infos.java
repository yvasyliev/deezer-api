package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Get the information about the API in the current country
 */
public class Infos {
    /**
     * The current country ISO code
     */
    @SerializedName("country_iso")
    private String countryIso;

    /**
     * The current country name
     */
    @SerializedName("country")
    private String country;

    /**
     * Indicates if Deezer is open in the current country or not
     */
    @SerializedName("open")
    private Boolean isOpen;

    @SerializedName("upload_token")
    private String uploadToken;

    /**
     * An array of available offers in the current country
     */
    @SerializedName("offers")
    private List<Offer> offers;

    public String getCountryIso() {
        return countryIso;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    @Override
    public String toString() {
        return "Infos{" +
                "countryIso='" + countryIso + '\'' +
                ", country='" + country + '\'' +
                ", open=" + isOpen +
                ", offers=" + offers +
                ", uploadToken='" + uploadToken + +'\'' +
                '}';
    }
}
