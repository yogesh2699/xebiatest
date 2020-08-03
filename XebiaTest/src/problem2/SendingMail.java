package problem2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SendingMail {

	Map<String, String> map = new HashMap<String, String>();
	Set<String> set = new HashSet<String>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter rows in 2D array: ");
		int rows = Integer.parseInt(br.readLine());

		System.out.print("Enter columns in 2D array: ");
		int columns = Integer.parseInt(br.readLine());

		String friendsArray[][] = new String[rows][columns];

		System.out.print("Enter array elements : ");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print("Enter element at [" + i + "][" + j + "] :");
				friendsArray[i][j] = br.readLine().trim();
			}
		}

		System.out.print("\nData you entered : \n");
		for (String[] x : friendsArray) {
			for (String y : x) {
				System.out.print(y + "        ");
			}
			System.out.println();
		}

		System.out.print("Enter input email address : ");
		String emailAddress = br.readLine().trim();

		System.out.print("Enter message to send : ");
		String message = br.readLine().trim();

		SendingMail s = new SendingMail();
		s.sendMail(emailAddress, message, friendsArray);

	}

	void sendMail(String emailAddress, String message, String[][] friendsArray) {

		for (String[] friends : friendsArray) {
			map.put(friends[0].trim().toLowerCase(), friends[1].trim().toLowerCase());
		}

		if(map.containsKey(emailAddress.trim())) {
			callRecursive(emailAddress, message, map);	
		} else {
			System.out.println("No friends associated with entered email.");
		}
		
	}

	void callRecursive(String emailAddress, String message, Map<String, String> map) {

		set.add(emailAddress.trim().toLowerCase());

		String[] emailFriends;
		if (map.containsKey(emailAddress.trim())) {
			emailFriends = map.get(emailAddress.trim()).split(",");

			for (String email : emailFriends) {
				if (!set.contains(email.trim())) {
					sendMail(email, message);
					set.add(email.trim().toLowerCase());

					if (map.containsKey(email.trim())) {
						callRecursive(email, message, map);
					}
				}
			}
		}

	}

	void sendMail(String emailAddress, String message) {
		System.out.println(message + " sent to " + emailAddress.trim());
	}

}
