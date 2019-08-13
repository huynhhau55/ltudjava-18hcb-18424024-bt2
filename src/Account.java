import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Account {
	String user;
	String pass;
	public Account(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	 @Override
	    public String toString() {
	        return user + pass;
	 }
	 public static List<Account> readAccounts (String fileName){
		 List<Account> accounts = new ArrayList<Account>();
		 Path pathToFile = Paths.get(fileName);
		 try(BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.UTF_8)){
			 String line = br.readLine();
			 while(line != null) {
				 String[] attributes = line.split(";");
				 Account account = createAccount(attributes);
				 accounts.add(account);
				 line = br.readLine();
			 }
			 br.close();
		 }
		 
		 catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return accounts;
	}

	private static Account createAccount(String[] metadata) {
		String user = metadata[0];
		String pass = metadata[1];
		return new Account(user,pass);
	}

	
}
