package Receipt.AddOns.Interfaces;

import Store.PurchasedItems;

//the type of added info to a BasicReceipt
//(e.g., greetings, rebates, coupons)
public interface AddOn {

    public boolean applies(PurchasedItems items);
    public String getLines();

}