package problem3;

import java.util.Scanner;

public class GeneratePalindrome {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter input string: ");
		String inputString = sc.nextLine();

		String palindromeString = createPalindrome(inputString);
		System.out.println("Palindrome for " + inputString + " is: " + palindromeString);
		sc.close();
	}

	public static String createPalindrome(String input) {
		String newChar = "";
		int i = 0;
		while (!checkIfPalindrome(input + newChar)) {
			newChar = input.charAt(i) + newChar;
			i++;
		}
		return input + newChar;
	}

	public static boolean checkIfPalindrome(String input) {
		StringBuffer buffer = new StringBuffer(input);
		if (buffer.reverse().toString().equals(input)) {
			return true;
		}
		return false;
	}

}
