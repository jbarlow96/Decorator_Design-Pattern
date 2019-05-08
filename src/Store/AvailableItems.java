package Store;

import java.util.ArrayList;

public class AvailableItems {

    private ArrayList<StoreItem> items;

    public AvailableItems() {
        items = new ArrayList<StoreItem>();
        loadAllStoreItems();
    }

    private void loadAllStoreItems() {

        addItem(new StoreItem("0050", "Nintendo Switch", "300.00"));
        addItem(new StoreItem("0100", "Portable Charger", "40.00"));
        addItem(new StoreItem("0150", "Apple Watch", "480.00"));
        addItem(new StoreItem("0200", "Apple Keyboard", "100.00"));
        addItem(new StoreItem("1000", "PS4 Pro", "400.00"));
        addItem(new StoreItem("2000", "Apple Macbook Pro", "1200.00"));
        addItem(new StoreItem("3000", "TV", "150.00"));

    }

    private void addItem(StoreItem item) {
        items.add(item);
    }

    public boolean containsItem(String itemCode) {
        for(StoreItem item: items) {
            if(itemCode.equals(item.getItemCode())) {
                return true;
            }
        }
        return false;
    }

    public StoreItem getItem(String itemCode) {
        for(StoreItem item: items) {
            if(itemCode.equals(item.getItemCode())) {
                return new StoreItem(item);
            }
        }
        return null;
    }

    public String getformattedItemsForPrinting() {
        StringBuilder sb = new StringBuilder("");
        sb.append( String.format( "%-20.40s %-20.40s %40.40s\n", "Item Code", "Name", "Price" ) );
        for(StoreItem item:items) {
            sb.append( String.format( "%-20.40s %-20.40s %40.2f\n", item.getItemCode(), item.getItemDescription(), Double.parseDouble(item.getItemPrice())));
        }
        return sb.toString();
    }
}