package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * An offer object
 */
public class Offer {
    /**
     * Offer ID
     */
    @SerializedName("id")
    private Integer id;

    /**
     * Offer name
     */
    @SerializedName("name")
    private String name;

    /**
     * Offer amount
     */
    @SerializedName("amount")
    private String amount;

    /**
     * Offer currency
     */
    @SerializedName("currency")
    private String currency;

    /**
     * Offer display amount
     */
    @SerializedName("displayed_amount")
    private String displayedAmount;

    @SerializedName("tc")
    private String tc;

    @SerializedName("tc_html")
    private String tcHtml;

    @SerializedName("tc_txt")
    private String tcTxt;

    /**
     * Trial period
     */
    @SerializedName("try_and_buy")
    private Integer tryAndBuy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDisplayedAmount() {
        return displayedAmount;
    }

    public void setDisplayedAmount(String displayedAmount) {
        this.displayedAmount = displayedAmount;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTcHtml() {
        return tcHtml;
    }

    public void setTcHtml(String tcHtml) {
        this.tcHtml = tcHtml;
    }

    public String getTcTxt() {
        return tcTxt;
    }

    public void setTcTxt(String tcTxt) {
        this.tcTxt = tcTxt;
    }

    public Integer getTryAndBuy() {
        return tryAndBuy;
    }

    public void setTryAndBuy(Integer tryAndBuy) {
        this.tryAndBuy = tryAndBuy;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", displayedAmount='" + displayedAmount + '\'' +
                ", tc='" + tc + '\'' +
                ", tcHtml='" + tcHtml + '\'' +
                ", tcTxt='" + tcTxt + '\'' +
                ", tryAndBuy=" + tryAndBuy +
                '}';
    }
}
