package Receipt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Receipt.AddOns.Interfaces.*;
import Receipt.BasicReceipt.*;
import Store.PurchasedItems;
import Tax.CollectionOfTaxComputations;
import Tax.TaxComputation;

public class ReceiptFactory {
    private StoreHeader store_header; // contains street_addr, zip_code, state_code, phone num, Store num
    private HashMap<String, TaxComputation> allTaxComputationMap; // Tax computation objs (for each state)
    private AddOn[] addOns; // secondary heading, rebate and coupon add-ons (hardcoded here)
    private TaxComputation currentTaxComputationObj;
    private final String STORE_INFO_FILE_NAME = "storeInfo.txt";
    private final String ADD_ON_FILE_NAME = "addOns.txt";

    public ReceiptFactory() { // constructor

        // Populates array of TaxComputation objects and array of AddOn objects (as if downloaded from the BestBuy web site).
        allTaxComputationMap = getTaxComputationObjects();
        addOns = getAddOns();

        // Reads config file to create and save StoreHeader object (store_num, street_addr, etc.) to be used on all receipts.
        store_header = getStoreHeaderFromConfig();

        // Based on the state code (e.g., “MD”) creates and stores appropriate StateComputation object to be used on all receipts.
        currentTaxComputationObj =    getTaxComputationMethodFromHeader();
    }

    private TaxComputation getTaxComputationMethodFromHeader() {
        return allTaxComputationMap.get(store_header.getStateCode());
    }

    private StoreHeader getStoreHeaderFromConfig() {
        String fileName = STORE_INFO_FILE_NAME;
        BufferedReader br = null;
        HashMap<String,String> map = new HashMap<String,String>();
        try {
            // Read the csv file
            br = new BufferedReader(new FileReader(fileName));

            String line = "";
            // Read to skip the header
            br.readLine();
            // Read from the second line
            while ((line = br.readLine()) != null) {
                String[] strings = line.split(",");

                if (strings.length == 2) {
                    map.put(strings[0], strings[1]);
                }
            }
        } catch (

                Exception ee) {
            ee.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ie) {
                System.out.println("Error occurred while closing the BufferedReader");
                ie.printStackTrace();
            }
        }

        String street = map.get("street");
        String city = map.get("city");
        String state = map.get("state");
        String zip = map.get("zip");
        String phone = map.get("phone");
        String storeNumber = map.get("storeNumber");

        return new StoreHeader(street, zip , city, state, phone, storeNumber);
    }

    private AddOn[] getAddOns() {

        String fileName = ADD_ON_FILE_NAME;
        int numActiveAddons = 0;
        String[] strings;

        BufferedReader br = null;
        HashMap<String,Boolean> map = new HashMap<String,Boolean>();
        try {
            // Reading the csv file
            br = new BufferedReader(new FileReader(fileName));

            String line = "";
            // Read to skip the header
            if( (line = br.readLine()) != null) {
                strings = line.split(",");
                numActiveAddons =  Integer.parseInt(strings[1]) ;
            }
            // Reading from the second line
            while ((line = br.readLine()) != null) {
                strings = line.split(",");

                if (strings.length == 2) {
                    if (strings[1].equals("1")) {
                        strings[1] = "true";
                    }
                    map.put(strings[0], Boolean.parseBoolean(strings[1]));
                }
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ie) {
                System.out.println("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }

        AddOn[] addOnsToReturn2 = new AddOn[numActiveAddons];
        ArrayList<AddOn> addOnsToReturn3 = new ArrayList<AddOn>();
        int i = 0;
        Class addOnClass;
        AddOn addOnToAdd;
        String pkg = "Receipt.AddOns.Concrete.";

        for(String addOnName: map.keySet()) {
            if(map.get(addOnName)) {
                try {
                    addOnClass = Class.forName(pkg + addOnName);
                    addOnToAdd = (AddOn) addOnClass.newInstance();

                    addOnsToReturn3.add(addOnToAdd);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        AddOn[] addOnsToReturn4 = addOnsToReturn3.toArray(new AddOn[0]);
        return addOnsToReturn4;
    }

    private HashMap<String, TaxComputation> getTaxComputationObjects() {
        // TODO Auto-generated method stub
        return CollectionOfTaxComputations.getMapOfTaxComputations();
    }

    public Receipt getReceipt(PurchasedItems items, Date date) {
        Receipt receiptToReturn;
        // Sets the current date of the BasicReceipt.
        BasicReceipt basicReceipt = new BasicReceipt(items, date);

        // Sets StoreHeader object of the BasicReceipt (by call to SetStoreHeader of BasicReceipt)
        basicReceipt.setStoreHeader(store_header);

        // Sets the TaxComputation object of the BasicReceipt (by call to the setTaxComputation of BasicReceipt).
        basicReceipt.setTaxComputation(currentTaxComputationObj);

        receiptToReturn = basicReceipt;

        // Traverses over all AddOn objects, calling the applies method of each.
        // If an AddOn object applies, then determines if the AddOn is of type SecondaryHeader, Rebate, or Coupon.
        // If of type SecondaryHeader, then creates a PreDecorator for other AddOn. If of type Rebate or Coupon, then creates a PostDecorator.
        // Links in the decorator object based on the Decorator design pattern.
        for(AddOn a: addOns) {
            if(a.applies(items)) {
                if(a instanceof SecondaryHeading) {
                    receiptToReturn = new PreDecorator(receiptToReturn, a);
                }
                else if(a instanceof Rebate) {
                    receiptToReturn = new PostDecorator(receiptToReturn, a);
                }
                else if(a instanceof Coupon){
                    receiptToReturn = new PostDecorator(receiptToReturn, a);
                }
            }
        }

        // Returns decorated BasicReceipt object as type Receipt.
        return receiptToReturn;
    }
}
