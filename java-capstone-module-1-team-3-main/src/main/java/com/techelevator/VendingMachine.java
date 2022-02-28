package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine<Static> {
    private BigDecimal balance = new BigDecimal("0.00");
    private Map<String, Item> slots = new TreeMap<>();

    private static final BigDecimal QUARTERS = new BigDecimal("0.25");
    private static final BigDecimal DIMES =  new BigDecimal("0.1");
    private static final BigDecimal NICKELS = new BigDecimal("0.05");

    public VendingMachine(){
    }
    public void restockMachine(String fileName) throws FileNotFoundException {
        File itemsFile = new File(fileName);
        if (itemsFile.exists()) {
            try (Scanner scanner = new Scanner(itemsFile)) {
                while (scanner.hasNextLine()) {
                    String itemLine = scanner.nextLine();
                    String[] newItemInfo = itemLine.split("\\|");
                    Item item = new Item(newItemInfo[1], newItemInfo[3], newItemInfo[0], new BigDecimal(newItemInfo[2]));
                    slots.put(newItemInfo[0], item);
                }
            }
        }
    }
    public Map<String, Item> getSlots() {
        return slots;
    }
    public boolean currentMoneyDeposited(BigDecimal moneyInput) throws FileNotFoundException {

        if (moneyInput.equals(new BigDecimal("1.00")) || moneyInput.equals(new BigDecimal("2.00")) ||
                moneyInput.equals(new BigDecimal("5.00")) || moneyInput.equals(new BigDecimal("10.00"))) {
            balance = balance.add(moneyInput);
            Logger logger = new Logger();
            logger.logToFile(logger.getDateTime()," FEED MONEY $" + moneyInput +" $"+ balance);
            return true;
        }
        return false;
    }
    public String selectProduct(String inputtedItem) throws FileNotFoundException {
        Item item = slots.get(inputtedItem);
        int count = item.getCount(0);
        String printOut = "";
        if (!inputtedItem.equals(item.getLocation())) {
            printOut = "invalid slot";
        }
        if (count == 0) {
            printOut = "Sold out";
        }
        if (count >= 1 && inputtedItem.equals(item.getLocation())) {
            int comparePrice = balance.compareTo(item.getPrice());
            if (comparePrice >= 0) {
                Logger logger = new Logger();
                logger.logToFile(logger.getDateTime()," " + item.getName() +" "+item.getLocation()+" $" + balance + " $" + balance.subtract(item.getPrice()));
                balance = balance.subtract(item.getPrice());
                item.setCount(--count);
                   printOut = item.getName() + ", " + item.getPrice() + ", " + balance;
                if (item.getType().equals("Chip")) {
                    return printOut += " Crunch, Crunch, Yum!";
                } else if (item.getType().equals("Candy")) {
                    return printOut += " Munch, Munch, Yum!";
                }else if (item.getType().equals("Drink")) {
                    return printOut += " Glug, Glug, Yum!";
                } else if (item.getType().equals("Gum")) {
                    return printOut += " Chew, Chew, Yum!";
                }

            } else {
                printOut = "Not Enough Money";
            }
        } return printOut;
    }
    public String returnChange(BigDecimal balance) throws FileNotFoundException {
        BigDecimal change = balance;
        BigDecimal[] quarterChange = change.divideAndRemainder(QUARTERS);
        String numQuarters = quarterChange[0].toString();
        BigDecimal[] dimeChange = quarterChange[1].divideAndRemainder(DIMES);
        String numDime = dimeChange[0].toString();
        BigDecimal nickelChange = dimeChange[1].divide(NICKELS, RoundingMode.DOWN);
        String numNickel = nickelChange.toString();
        Logger logger = new Logger();
        logger.logToFile(logger.getDateTime()," GIVE CHANGE  $" + balance + " $0.00");
        balance =BigDecimal.ZERO;
        String totalChange = numQuarters + " quarters " + numDime + " dimes " + numNickel + " nickels";
        return totalChange;
        }
    public BigDecimal getBalance() {
        return balance;
    }
}
