public class Userid{
    private String Name;
    private int Age;
    private int PhoneNumber;
    private String email;
    private String Password;
    private static int userId = 1;

    public Userid(String NameIn , int AgeIn , int PhoneNumberIn , String emailIn, String PasswordIn){
        Name = NameIn;
        Age = AgeIn;
        PhoneNumber = PhoneNumberIn;
        email= emailIn;
        Password = PasswordIn;
        userId++;
    }
    public String getName(){
        return Name;
    }
    public int getAge(){
        return Age;
    }
    public int getphoneNum(){
        return PhoneNumber;
    }
    public String getEmail(){
        return email;
    }
    public String getpassword(){
        return Password;
    }

    public String toString() {
        return "UserId{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                ", PhoneNumber=" + PhoneNumber +
                ", email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                ", UserID='"  + userId+'\'' + 
                '}';
    }

}