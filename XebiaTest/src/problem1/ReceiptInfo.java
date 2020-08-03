package problem1;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReceiptInfo {

	private List<ProductInfo> productsList = null;
	private double total;
	private double saleTax;
	
	
	public void processReceipt(String inputFile) {
		try {
			Scanner input = new Scanner(System.in);
			File file = new File(inputFile);
			input = new Scanner(file);
			productsList = new ArrayList<ProductInfo>();
			while (input.hasNextLine()) {
				String line = input.nextLine();				
				int atIndex = line.lastIndexOf("at");

				if (atIndex != -1) {
					
					String[] words = line.split(" ");
					int quantity = Integer.parseInt(words[0]);
					float price = Float.parseFloat((line.substring(atIndex + 2).trim()));
					String name = line.substring(1, atIndex);
										
					boolean isImported = line.contains("imported");
					String[] exemptedItems = new String[] {"book", "chocolate", "pills"};
					int exemptedItemIndex = checkItemFromArray(line, exemptedItems);
					String exemptedType = null;
					if (exemptedItemIndex != -1) {
						exemptedType = exemptedItems[exemptedItemIndex];
					}
					
					ProductInfo product = null;
					if (isImported) {
						if (exemptedType != null) {
							if (exemptedType == "book") {
								product = new ProductInfo(quantity, name, price, ItemType.IMPORTED_BOOK);
							} else if (exemptedType == "chocolate") {
								product = new ProductInfo(quantity, name, price, ItemType.IMPORTED_FOOD);
							} else if (exemptedType == "pills") {
								product = new ProductInfo(quantity, name, price, ItemType.IMPORTED_MEDICAL);
							}
						} else {
							product = new ProductInfo(quantity, name, price, ItemType.IMPORTED_OTHERS);
						}
					} else {
						if (exemptedType != null) {
							if (exemptedType == "book") {
								product = new ProductInfo(quantity, name, price, ItemType.BOOK);
							} else if (exemptedType == "chocolate") {
								product = new ProductInfo(quantity, name, price, ItemType.FOOD);
							} else if (exemptedType == "pills") {
								product = new ProductInfo(quantity, name, price, ItemType.MEDICAL);
							}
						} else {
							product = new ProductInfo(quantity, name, price, ItemType.OTHERS);
						}
					}

					productsList.add(product);
				} else {
					System.out.println("Input receipt not proper");
				}
			}
			input.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	public void calculateSalesAndTotal() {
		BigDecimal totalSum = new BigDecimal("0");
		BigDecimal totalTaxSum = new BigDecimal("0");
		BigDecimal salesTaxPercent = new BigDecimal(".1");
		BigDecimal importTaxPercent = new BigDecimal(".05");
		total = 0;
		saleTax = 0;

		for (int i = 0; i < productsList.size(); i++) {
			totalTaxSum = BigDecimal.valueOf(0);
			BigDecimal originalPrice = new BigDecimal(String.valueOf(productsList.get(i).getPrice()));
			totalSum = totalSum.add(originalPrice);

			if (productsList.get(i).isSalesTaxable()) {				
				BigDecimal salesTax = salesTaxPercent.multiply(originalPrice);
				salesTax = round(salesTax, BigDecimal.valueOf(0.05), RoundingMode.UP);
				totalTaxSum = totalTaxSum.add(salesTax);
			}

			if (productsList.get(i).isImportedTaxable()) {
				BigDecimal importTax = importTaxPercent.multiply(originalPrice);
				importTax = round(importTax, BigDecimal.valueOf(0.05), RoundingMode.UP);
				totalTaxSum = totalTaxSum.add(importTax);
			}

			productsList.get(i).setPrice(totalTaxSum.floatValue() + productsList.get(i).getPrice());

			saleTax = saleTax + totalTaxSum.doubleValue();
			totalSum = totalSum.add(totalTaxSum);
		}
		
		saleTax = roundToTwoDecimals(saleTax);
		total = totalSum.doubleValue();
	}
	

	public static int checkItemFromArray(String line, String[] items) {
		int index;
		for (int i = 0; i < items.length; i++) {
			index = line.indexOf(items[i]);
			if (index != -1) {
				return i;
			}
		}
		return -1;
	}
	
	
	public static BigDecimal round(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
		BigDecimal divided = value.divide(increment, 0, roundingMode);
		BigDecimal result = divided.multiply(increment);
		result.setScale(2, RoundingMode.UNNECESSARY);
		return result;
	}


	public double roundToTwoDecimals(double d) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		return Double.valueOf(decimalFormat.format(d));
	}

	public void printFinalReceipt() {
		for (int i = 0; i < productsList.size(); i++) {
			System.out.println(productsList.get(i).getQuantity() + productsList.get(i).getName() + ": " + productsList.get(i).getPrice());
		}
		System.out.printf("Sales Tax: %.2f\n", saleTax);
		System.out.println("Total: " + total);
	}

}
