package Objects;

/**
 * Created by Adam Hayes on 4/24/2017.
 */
public class OrderInfoHandler {
    private String shippingName;
    private String shippingAddress;
    private String shippingAddressLine2;
    private String shippingCity;
    private int shippingZip;
    private String shippingState;
    private String billingName;
    private String billingAddress;
    private String billingAddressLine2;
    private String billingCity;
    private int billingZip;
    private String billingState;

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddressLine2() {
        return shippingAddressLine2;
    }

    public void setShippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public int getShippingZip() {
        return shippingZip;
    }

    public void setShippingZip(int shippingZip) {
        this.shippingZip = shippingZip;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setBillingName(String billingName){ this.billingName = billingName; }

    public String getBillingName() { return billingName; }

    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public int getBillingZip() {
        return billingZip;
    }

    public void setBillingZip(int billingZip) {
        this.billingZip = billingZip;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public void shippingAsBilling() {
        billingName = shippingName;
        billingAddress = shippingAddress;
        billingAddressLine2 = shippingAddressLine2;
        billingCity = shippingCity;
        billingState = shippingState;
        billingZip = shippingZip;
    }


}
