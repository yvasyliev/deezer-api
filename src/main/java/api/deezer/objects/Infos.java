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
    private String country_iso;

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

    /**
     * An array of available offers in the current country
     */
    @SerializedName("offers")
    private List<Offer> offers;

    public String getCountry_iso() {
        return country_iso;
    }

    public void setCountry_iso(String country_iso) {
        this.country_iso = country_iso;
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

    @Override
    public String toString() {
        return "Infos{" +
                "country_iso='" + country_iso + '\'' +
                ", country='" + country + '\'' +
                ", open=" + isOpen +
                ", offers=" + offers +
                '}';
    }
}
