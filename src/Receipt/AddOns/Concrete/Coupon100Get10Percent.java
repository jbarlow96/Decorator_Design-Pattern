package Receipt.AddOns.Concrete;

import Receipt.AddOns.Interfaces.Coupon;
import Store.PurchasedItems;

public class Coupon100Get10Percent implements Coupon {

    @Override
    public boolean applies(PurchasedItems items) {
        return items.getTotalCost() >= 100;
    }

    @Override
    public String getLines() {
        StringBuilder sb = new StringBuilder("");
        sb.append("BEST BUY COUPON: 10% off next purchase\n");
        sb.append("Good until 12/31/2019");
        return sb.toString();
    }

}
