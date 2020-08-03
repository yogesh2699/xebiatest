package problem1;

public class SalesCalculator {

	public static void main(String[] args) {
		ReceiptInfo r = new ReceiptInfo();

		r.processReceipt("input.txt");
		r.calculateSalesAndTotal();

		System.out.println("Output 1:\n");
		r.printFinalReceipt();

		System.out.println("\n------------------------\n");

		r.processReceipt("input2.txt");
		r.calculateSalesAndTotal();

		System.out.println("Output 2:\n");
		r.printFinalReceipt();

		System.out.println("\n------------------------\n");

		r.processReceipt("input3.txt");
		r.calculateSalesAndTotal();

		System.out.println("Output 3:\n");
		r.printFinalReceipt();
	}

}
