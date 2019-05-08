package Receipt.BasicReceipt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Receipt.Receipt;
import Store.PurchasedItems;
import Tax.TaxComputation;

public class BasicReceipt implements Receipt {

    private StoreHeader store_header; // street address, state code, phone number, Store number
    private TaxComputation tc;
    private Date date; // may also be a String type
    private PurchasedItems items;

    public BasicReceipt(PurchasedItems items, Date date) { // Date may also be a String type
        this.items = items;
        this.date = date;
    }

    public void setStoreHeader(StoreHeader h) {
        store_header = h;
    }

    public void setTaxComputation(TaxComputation tc) {
        this.tc = tc;
    }

    public String getStateCode() {
        return store_header.getStateCode();
    }

    public boolean addItem(int itemNumber) {
        return false;
    }

    public void prtReceipt() {
        System.out.println(store_header.toString());

        DateFormat dateFormat = new SimpleDateFormat("M/dd/yy h:mm a");
        System.out.println(dateFormat.format(date) + "\n");

        System.out.printf("%-20s %40s\n", "Item", "Price($)");
        System.out.println(items.getformattedItemsForPrinting());

        Double subtotal = items.getTotalCost();
        System.out.printf("\n%-20.40s %40.2f\n", "Subtotal", subtotal);

        Double tax = tc.computeTax(items, date);
        System.out.printf("\n%-20.40s %40.2f\n", getStateCode() +" Sales Tax (" + tc.getTaxRate(date) * 100 + "%)", tax);

        Double grandTotal = subtotal + tax;
        System.out.printf("\n%-20.40s %40.2f\n\n", "Total Sale", grandTotal);
    }
}
