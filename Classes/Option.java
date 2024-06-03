import java.util.Scanner;

public class Option{

    public static void main(String[] args) {
        
        System.out.println("test1");
        Scanner input = new Scanner(System.in);
        boolean a = false;
        System.out.println("Test");

        while(!(a)){
     
            System.out.println("Enter 1 to buy a ticket\nEnter 2 to see all availible seats");
            int answer = input.nextInt();

            if(answer == 1){
     
                System.out.println("Pause place 1");
                a = true;
            }
            else if(answer == 2){
                
                System.out.println("Here are a list of availible seats");
                a = true;
            }
            else{
                System.out.println("Please enter a valid option");
            }
       }
    }
}