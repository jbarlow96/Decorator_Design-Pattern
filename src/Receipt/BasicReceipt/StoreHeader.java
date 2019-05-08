package Receipt.BasicReceipt;

public class StoreHeader {

    private String street_addr;
    private String zip_code;
    private String city;
    private String state_code;
    private String phone_num;
    private String store_num; // e.g., #1004

    public StoreHeader(String street_addr, String zip_code, String city, String state_code, String phone_num, String store_num) {
        super();
        this.street_addr = street_addr;
        this.zip_code = zip_code;
        this.state_code = state_code;
        this.phone_num = phone_num;
        this.store_num = store_num;
        this.city = city;
    }

    public String getStateCode() {
        return state_code;
    }

    public String toString() {
        String addr = street_addr + ", " + city + ", " + state_code + " " + zip_code;
        String firstLine = String.format("\n%-20s %40s\n", "BEST BUY", "Store # " + store_num);
        String secondLine = String.format("%-40s %20.40s\n", addr, phone_num);
        return firstLine + secondLine;
    }
}
