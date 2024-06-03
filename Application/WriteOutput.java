package project.xyz;

import java.io.*;
import java.util.*;

public class WriteOutput {

	//The directory of the input files. -Require adjustment-
	private static String directory = "/";
	//The end of file index for user_list.dat
	private static User dummy = new User("dummy","dummy");

	public static void createUserFile() throws IOException {
		String filePath = directory+"user_list.dat";
		FileOutputStream fileOut = new FileOutputStream(filePath);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(dummy);
		objectOut.close();
		fileOut.close();
	}
	
	public static void addUser(User user) throws IOException, ClassNotFoundException {
		ArrayList<User> users = ReadInput.userList();
		boolean isUpdated = false;
		int index = 0;
		for (int i=0; i<users.size(); i++) {
			if (users.get(i).getUserId()==user.getUserId()) {
				index = i;
				isUpdated = true;
				break;
			}
		}
		if (isUpdated) {
			users.remove(index);
			users.add(index, user);
		}
		else {
			users.add(user);
		}
		users.add(0, dummy);
		String filePath = directory+"user_list.dat";
		FileOutputStream fileOut = new FileOutputStream(filePath);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		int n = users.size(); 
		for (int i=1; i<=n; i++) {
			objectOut.writeObject(users.get(n-i));
		}
		objectOut.close();
		fileOut.close();
	}
}
