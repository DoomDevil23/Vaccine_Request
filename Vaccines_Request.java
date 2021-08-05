package Vaccine_Request;

import java.io.IOException;
import java.io.File;

public class Vaccines_Request{
    public static void main(String[] args){
        int choice = 0;

        File file = new File("Resources/records.txt");

        try{
            if(file.createNewFile()){
                System.out.println("File has been created.");
            }
            else{
                System.out.println("File already exists.");
            }
        }
        catch(IOException er){
            
        }

        do{
            System.out.println("1 - Add a Client for this Vaccine Centre");
            System.out.println("2 - Remove a Client at this Vaccine Centre");
            System.out.println("3 - Record a Clients First Vaccination");
            System.out.println("4 - Display details of all Clients (incuding their vaccine details)");
            System.out.println("5 - Display overall vaccine stats (the percent of clients given each vaccine type)");
            System.out.println("6 - Show the name of clients due a second vaccine and the date due");
            System.out.println("7 - Load ClientInformation from a text file");
            System.out.println("8 - Save Client information to a text file");
            System.out.println("9 - Quit");
            System.out.print("Enter choice:");
            
            try{
                choice = Integer.parseInt(System.console().readLine());
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            catch(Exception er){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Enter a value between 0 - 9");
            }

        }while(choice != 9);
    }

    
}