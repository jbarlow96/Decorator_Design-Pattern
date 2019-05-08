package Store;

import java.util.ArrayList;

public class PurchasedItems {

    private ArrayList<StoreItem> items;
    private AvailableItems availableItems;

    public PurchasedItems() {
        items = new ArrayList<StoreItem>();
        availableItems = new AvailableItems();
    }

    public void addItem(StoreItem item) {
        items.add(item);
    }

    public boolean addItem(String itemCode) {
        if(availableItems.containsItem(itemCode)) {
            items.add( availableItems.getItem(itemCode) );
            return true;
        }
        else
            return false;
    }

    public double getTotalCost() {
        double sum = 0;
        for(StoreItem item: items) {
            sum += Double.parseDouble(item.getItemPrice());
        }
        return sum;
    }

    public boolean containsItem(String itemCode) {
        for(StoreItem item: items) {
            if(itemCode.equals(item.getItemCode())) {
                return true;
            }
        }
        return false;
    }

    public String getformattedItemsForPrinting() {
        StringBuilder sb = new StringBuilder("");
        for(StoreItem item:items) {
            sb.append( String.format( "%-20.40s %40.2f\n", item.getItemDescription(), Double.parseDouble( item.getItemPrice() ) ) );
        }
        return sb.toString();
    }
}
