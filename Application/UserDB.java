package project.xyz;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserDB implements IUserDB {
	//The directory of the input files. -Require adjustment-
	private static String directory = "/";
	//Indicate the end of file index for user_list.dat
	private static User dummy = new User("dummy","dummy");
	
	@Override
	public ArrayList<User> readUser() throws IOException, ClassNotFoundException {
		ArrayList<User> output = new ArrayList<>();
		String filePath = directory+"user_list.dat";
		FileInputStream fileIn = new FileInputStream(filePath);
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		User user = (User)objectIn.readObject();
		while (!user.getEmail().equals("dummy")) {
			output.add(0, user);
			user = (User)objectIn.readObject();
		}
		objectIn.close();
		fileIn.close();
		return output;
	}	
	@Override
	public void createUserFile() throws IOException {
		String filePath = directory+"user_list.dat";
		FileOutputStream fileOut = new FileOutputStream(filePath);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(dummy);
		objectOut.close();
		fileOut.close();
	}
	public void addUser(User user) throws IOException, ClassNotFoundException {
		ArrayList<User> users = readUser();
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