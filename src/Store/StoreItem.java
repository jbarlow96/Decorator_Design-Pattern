package Store;

public class StoreItem {

    private String itemCode; // e.g., 3010
    private String itemDescription; // e.g., Dell Laptop
    private String itemPrice;

    public StoreItem(String itemCode, String itemDescription, String itemPrice) {
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public StoreItem(StoreItem orginal) {
        this.itemCode = orginal.itemCode;
        this.itemDescription = orginal.itemDescription;
        this.itemPrice = orginal.itemPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
