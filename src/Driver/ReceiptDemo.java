// Authors: Jude Barlow, David Hanlon

package Driver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Receipt.Receipt;
import Receipt.ReceiptFactory;
import Store.AvailableItems;
import Store.PurchasedItems;

public class ReceiptDemo {

    public static void main(String[] args) {
        System.out.println("Welcome to Best Buy!");

        // Creates a Date object (either from Java API -- automatically if not specifically chosen -- or date entered by user)
        Date date = new  GregorianCalendar().getTime();

        // Creates a PurchasedItems object (selections made by user)
        PurchasedItems purchasedItems = new PurchasedItems();


        // Creates a ReceiptFactory object.
        ReceiptFactory receiptFactory = new ReceiptFactory();

        // Creates AvailableItems objects
        AvailableItems availableItems = new AvailableItems();


        String prompt = "\n1 - Start New Receipt\n2 - Add an Item\n3 - Display Receipt\n4 - Exit";
        Scanner in = new Scanner(System.in);

        int selection;
        boolean quit = false;

        do {
            System.out.println(prompt);
            System.out.println("\nEnter Here (1, 2, 3, or 4): ");
            try {
                selection = in.nextInt();
            } catch (Exception e) {
                selection = 5;
            }
            switch (selection) {
                case 1:// Start New Receipt

                    System.out.println("\nStarting new Receipt");

                    // Prompts user for current date
                    System.out.println("Enter the number of the month for your Receipt(enter 1 for January, 2 for February, and so forth...): ");
                    int month = Integer.parseInt( in.next() ) - 1; // this is because January is 0 in java api
                    System.out.println("Enter the day of the month for your Receipt(1, 2, 3, etc.): ");
                    int dayOfMonth = in.nextInt();
                    System.out.println("Enter the year for your Receipt(2019, 2018, 2017, etc.): ");
                    int year = in.nextInt();

                    date = new  GregorianCalendar(year, month, dayOfMonth).getTime();// Date(2017, 7, 19);
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(date);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
                    dateFormat.setTimeZone(cal.getTimeZone());
                    System.out.println("Receipt date set to " + dateFormat.format(cal.getTime()) );
                    System.out.println("\nEnter 2 to add an item");

                    // Creates a PurchasedItems object (selections made by user)
                    purchasedItems = new PurchasedItems();


                    // Constructs a ReceiptFactory object.
                    receiptFactory = new ReceiptFactory();

                    break;
                case 2: // Add Items

                    System.out.println("Select an item to add\n");

                    // print available items
                    System.out.println(availableItems.getformattedItemsForPrinting());

                    // Prompts user for items to purchase, storing each in PurchasedItems.

                    System.out.println("Enter the item code of the item you would like to add:");
                    String itemCode = in.next();
                    String itemCodeToAdd = itemCode;
                    if( purchasedItems.addItem(itemCodeToAdd) ) {
                        System.out.println("\nItem " + itemCodeToAdd + " was added to the order.");
                    }
                    else {
                        System.out.println("\nItem " + itemCodeToAdd + " is not a valid item.");
                    }

                    System.out.println("\nEnter 2 to add another item or Enter 3 to display Receipt.");

                    break;
                case 3: // Display Receipt

                    // Calls the getReceipt method of the factory to obtain constructed Receipt.
                    Receipt receipt = receiptFactory.getReceipt(purchasedItems, date);

                    // Prints Receipt by call to method prtReceipt.
                    receipt.prtReceipt();
                    break;
                case 4:
                    quit = true;
                    System.out.println("Exiting.");
                    in.close();
                    break;
                default:
                    System.out.println("Invalid selection. Enter 1, 2, 3, or 4");
            }
        } while (!quit);
    }
}
