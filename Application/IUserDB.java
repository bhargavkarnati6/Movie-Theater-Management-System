package project.xyz;

import java.io.IOException;
import java.util.*;

public interface IUserDB {
	public ArrayList<User> readUser() throws IOException, ClassNotFoundException;
	public void createUserFile() throws IOException;
}
