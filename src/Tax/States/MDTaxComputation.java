package Tax.States;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Store.PurchasedItems;
import Tax.TaxComputation;

public class MDTaxComputation extends TaxComputation {


    @Override
    public double computeTax(PurchasedItems items, Date receiptDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(receiptDate);

        if(taxHoliday(receiptDate)) {
            return 0.0;
        }
        return items.getTotalCost() * getTaxRate(receiptDate);
    }

    @Override
    public boolean taxHoliday(Date receiptDate) {
        Calendar receiptCalendar = Calendar.getInstance();
        receiptCalendar.setTime(receiptDate);
        int receiptYear = receiptCalendar.get(Calendar.YEAR);

        Date taxHolidayStartdate = new  GregorianCalendar(receiptYear, Calendar.AUGUST, 12).getTime();
        Date taxHolidayEnddate = new  GregorianCalendar(receiptYear, Calendar.AUGUST, 18).getTime();

        if( !receiptDate.before(taxHolidayStartdate) && !receiptDate.after(taxHolidayEnddate)) {
            return true;
        }
        return false;
    }

    @Override
    public double getTaxRate(Date receiptDate) {
        if(taxHoliday(receiptDate))
            return 0.0;
        else
            return 0.06;
    }
}