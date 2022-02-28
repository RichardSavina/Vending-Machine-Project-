package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	private String[] menuOptions = MAIN_MENU_OPTIONS;
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {
		VendingMachine vendingMachine = new VendingMachine();

		try {
			vendingMachine.restockMachine("vendingmachine.csv");
		} catch (FileNotFoundException e) {
			System.out.println("File not found"+ e.getMessage());
		}
		boolean exit = false;
		while (!exit) {
			String choice = (String) menu.getChoiceFromOptions(menuOptions);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println("I'm in option 1");
//				System.out.println(vendingMachine.getSlots());
				displayItems(vendingMachine);
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				System.out.println("I'm in option 2");
				menuOptions = PURCHASE_MENU_OPTIONS;

				// do purchase
			}else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				exit = true;
			}else if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
				try {
					Scanner moneyScanner = new Scanner(System.in);
					System.out.println("Please Deposit Money:");
					String inputtedMoney = moneyScanner.nextLine();
					BigDecimal moneyInput = new BigDecimal(inputtedMoney);
					if (!vendingMachine.currentMoneyDeposited(moneyInput)) {
						System.out.println("Invalid Money Amount" + moneyInput);
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid Amount " + e.getMessage());
				}
			}else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
				displayItems(vendingMachine);
				System.out.println("Please enter slot number");
				Scanner selectedItem = new Scanner(System.in);
				String inputtedItem = selectedItem.nextLine();
				String outPut = null;
				try {
					outPut = vendingMachine.selectProduct(inputtedItem);
				} catch (NullPointerException e) {
					System.out.println("Invalid input " + e.getMessage());
				}
				System.out.println(outPut);
				System.out.println("Current Balance: " + vendingMachine.getBalance());
			}else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){
				System.out.println(vendingMachine.returnChange(vendingMachine.getBalance()));
				menuOptions = MAIN_MENU_OPTIONS;


			}
		}
	}

	private void displayItems(VendingMachine vendingMachine) {
		Map<String,Item> slots = vendingMachine.getSlots();
//
		for (Map.Entry<String, Item> entry : slots.entrySet()) {
			System.out.println(entry.getKey()+" " + entry.getValue().getName()+ " "+ entry.getValue().getPrice()+ " " + entry.getValue().getCount(0));
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
