package project.xyz;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TestUserDB implements IUserDB {
	//The directory of the input files. -Require adjustment-
	private String directory = "";
	//Indicate the end of file index for user_list.dat
	private User dummy = new User("dummy","dummy");
	private User test1 = new User("tester1","1234");
	private User test2 = new User("tester2","2345");
	private User test3 = new User("tester3","3456");
	
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
	
	public String printUserList(ArrayList<User> list) {
		String output = "";
		for (int i=0; i<list.size(); i++) {
			output += list.get(i).toString();
		}
		return output;
	}
	
	@Override
	public void createUserFile() throws IOException {
		String filePath = directory+"user_list.dat";
		FileOutputStream fileOut = new FileOutputStream(filePath);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(test1);
		objectOut.writeObject(test2);
		objectOut.writeObject(test3);
		objectOut.writeObject(dummy);
		objectOut.close();
		fileOut.close();
	}
}
