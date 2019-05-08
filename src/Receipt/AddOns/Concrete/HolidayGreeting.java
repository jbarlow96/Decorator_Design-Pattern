package Receipt.AddOns.Concrete;

import Receipt.AddOns.Interfaces.SecondaryHeading;
import Store.PurchasedItems;

public class HolidayGreeting implements SecondaryHeading {

    public boolean applies(PurchasedItems items) {
        return true; // SecondaryHeading decorators always applied
    }

    public String getLines() {
        return "\t* Happy Holidays from Best Buy *";
    }

}
