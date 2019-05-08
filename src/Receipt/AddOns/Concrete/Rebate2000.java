package Receipt.AddOns.Concrete;

import Receipt.AddOns.Interfaces.Rebate;
import Store.PurchasedItems;

public class Rebate2000 implements Rebate {

    public boolean applies(PurchasedItems items) {
        return items.containsItem("2000");
    }

    public String getLines() {
        return "$100 REBATE    ITEM 2000    Apple Macbook Pro\n\nMail-in Rebate for Item #2000\n" + "Name:\n" + "Address:\n\n" +
                "Mail to: Best Buy Rebates, P.O. Box 1400, Orlando, FL\n" +
                "\nPlease allow 3-4 weeks for processing";
    }

}
