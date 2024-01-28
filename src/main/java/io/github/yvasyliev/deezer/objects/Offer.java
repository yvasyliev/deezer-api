package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.net.URL;

/**
 * An offer object.
 */
@Data
public class Offer {
    /**
     * Offer ID.
     */
    @SerializedName("id")
    private Long id;

    /**
     * Offer name.
     */
    @SerializedName("name")
    private String name;

    /**
     * Offer amount.
     */
    @SerializedName("amount")
    private String amount;

    /**
     * Offer currency.
     */
    @SerializedName("currency")
    private String currency;

    /**
     * Offer display amount.
     */
    @SerializedName("displayed_amount")
    private String displayedAmount;

    /**
     * PDF terms of use.
     */
    @SerializedName("tc")
    private URL tc;

    /**
     * HTML terms of use.
     */
    @SerializedName("tc_html")
    private URL tcHtml;

    /**
     * Text terms of use.
     */
    @SerializedName("tc_txt")
    private URL tcTxt;

    /**
     * Trial period.
     */
    @SerializedName("try_and_buy")
    private Integer tryAndBuy;
}
