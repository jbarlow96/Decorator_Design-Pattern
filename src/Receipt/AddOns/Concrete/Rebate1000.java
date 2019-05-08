package Receipt.AddOns.Concrete;

import Receipt.AddOns.Interfaces.Rebate;
import Store.PurchasedItems;

public class Rebate1000 implements Rebate {

    public boolean applies(PurchasedItems items) {
        return items.containsItem("1000");
    }

    public String getLines() {
        return "Mail-in Rebate for Item #1000\n\n" + "Name:\n" + "Address:\n" + "Mail to: Best Buy Rebates, P.O. Box 1400, Orlando, FL";
    }

}
