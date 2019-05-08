package Tax;

import java.util.Date;

import Store.PurchasedItems;

public abstract class TaxComputation {
    public abstract double computeTax(PurchasedItems items, Date receiptDate);
    public abstract boolean taxHoliday(Date receiptDate);
    public abstract double getTaxRate(Date receiptDate);
}
