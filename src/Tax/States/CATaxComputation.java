package Tax.States;

import java.util.Date;

import Store.PurchasedItems;
import Tax.TaxComputation;

public class CATaxComputation extends TaxComputation {


    @Override
    public double computeTax(PurchasedItems items, Date receiptDate) {
        return items.getTotalCost() * getTaxRate(receiptDate);
    }

    @Override
    public boolean taxHoliday(Date receiptDate) {
        return false;
    }

    @Override
    public double getTaxRate(Date receiptDate) {
        return 0.075;
    }
}