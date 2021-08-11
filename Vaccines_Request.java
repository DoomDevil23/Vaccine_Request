package Vaccine_Request;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.File;


public class Vaccines_Request{
    public static void main(String[] args){
        VaccineCentre objvaccinecentre = new VaccineCentre();
        int choice;

        //A NEW FILE IS CREATED IF NOT EXIST. ELSE THIS DOES NOTHING, BUT INITIALIZE THE VARIABLE
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

        //LOOP FOR KEEPING INSIDE MENU UNTIL QUIT OPTION IS SELECTED
        do{
            choice=0;
            System.out.println("Cork Vaccination Center");
            System.out.println("===================");
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
                //CAST READLINE INTO INTEGER TO USE SWITCH
                choice = Integer.parseInt(System.console().readLine());
                clearScreen();
            }
            //CATCH FOR NON INTEGER INPUTS
            catch(Exception e){
                clearScreen();
                System.out.println("Enter a value between 0 - 9");
                System.out.println("Press Enter to continue.");
                System.console().readLine();
                clearScreen();
            }

            switch(choice){
                //CASE 1 FOR ADDING CLIENTS TO VACCINE CENTRE
                case 1:
                    ClientV objClientV;

                    clearScreen();
                    
                    System.out.println("Enter Clients First Name:");
                    String firstName = System.console().readLine();

                    System.out.println("Enter Clients Last Name:");
                    String lastName = System.console().readLine();

                    System.out.println("Enter Clients ID:");
                    String idClient = System.console().readLine();

                    System.out.println("Enter Clients Phone:");
                    String phoneClient = System.console().readLine();

                    objClientV = new ClientV(idClient, phoneClient, firstName, lastName);
                    objvaccinecentre.add_client(objClientV);
                    
                    System.out.println("Client has been added. Press Enter to continue.");
                    System.console().readLine();
                    
                    clearScreen();
                    break;

                //CASE 2 FOR REMOVING CLIENTS FROM VACCINE CENTRE CLIENTS LIST
                case 2: 
                    clearScreen();
                    System.out.println("Enter client id code to remove:");
                    String id_client = System.console().readLine();
                    objvaccinecentre.remove_client(id_client);
                    System.out.println("Press Enter to continue.");
                    System.console().readLine();
                    clearScreen();
                    break;

                //CASE 3 FOR RECORDING A SPECIFIC CLIENTS FIRST VACCINATION
                case 3:
                    int company_id=0, batch_number=0;
                    String company_name="";
                    LocalDate vaccine_date = LocalDate.now();
                    boolean complation_status = false;

                    clearScreen();

                    System.out.println("Enter client code to find:");
                    String idclient = System.console().readLine();
                    
                    int index = objvaccinecentre.find_clientIndex(idclient);
                    if(index==-1){
                        System.out.println("This client doesnt exist!");
                    }
                    else{
                        //READING THE CODE FOR SELECTED VACCINE
                        //LOOP UNTIL SELECT A VALID VALUE
                        while(!(company_id<=4) || !(company_id>=1)){
                            System.out.println("1 - Pfizer");
                            System.out.println("2 - Astra-Zenica");
                            System.out.println("3 - Moderna");
                            System.out.println("4 - Jhonson&Johnson");
                            System.out.println("Enter vaccine code number:");
                            
                            try{
                                company_id = Integer.parseInt(System.console().readLine());
                                
                                //VALIDATING COMPANY_ID IN RIGHT RANGE
                                if(company_id>4 || company_id<1){
                                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                                    System.out.println("Invalid value of:"+company_id+". Enter a number between 1-4");
                                }
                            }
                            //ERROR THROWS WHEN INPUT IS NOT INTEGER VALUE
                            catch(Exception e){
                                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                                System.out.println("Enter a number between 1-4");
                            }
                        }

                        //SELECTING COMPANY NAME ACCORDING TO COMPANY CODE
                        switch (company_id) {
                            case 1:
                                company_name="Pfizer";
                                break;

                            case 2:
                                company_name="Astra-Zenica";
                                break;

                            case 3:
                                company_name="Moderna";
                                break;

                            case 4:
                                company_name="Johnson&Johnson";
                                complation_status=true;
                                break;
                        }

                        //LOOP FOR GETTING RIGHT BATCH NUMBER
                        while(batch_number==0){
                            System.out.println("Enter batch number for this vaccine:");
                            try{
                                batch_number = Integer.parseInt(System.console().readLine());
                            }
                            catch(Exception e){
                                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                                System.out.println("Batch number is incorrect. Please try again with a valid batch number.");
                            }
                        }
                        ClientV objclientactual = objvaccinecentre.find_client(index);
                        ClientV objclientupdate = new ClientV(objclientactual.get_idClient(), objclientactual.get_phoneClient(), objclientactual.get_firstName(), objclientactual.get_lastName(), company_name, company_id, batch_number, vaccine_date, complation_status);
                        objvaccinecentre.addvaccineclient(index, objclientupdate);
                    }

                    break;

                //CASE 4 FOR SHOWING ALL THE CLIENTS FOR THIS VACCINE CENTRE
                case 4:
                    clearScreen();
                    objvaccinecentre.show_all_clients();
                    System.out.println("This are the registered clients for this vaccine centre. Press Enter to continue.");
                    System.console().readLine();
                    clearScreen();
                    break;
               
                //CASE 5 FOR SHOWING STATISTICS OF CLIENTS GIVEN EACH VACCINE TYPE
                case 5:
                    clearScreen();
                    objvaccinecentre.show_stats();
                    System.out.println("Press Enter to continue");
                    System.console().readLine();
                    clearScreen();
                    break;

                //CASE 6 FOR SHOWING CLIENTS DUE A SECOND VACCINE AND THE DATE DUE
                case 6:
                    clearScreen();
                    objvaccinecentre.show_clients_due();
                    System.out.println("Press Enter to continue");
                    System.console().readLine();
                    clearScreen();
                    break;

                //CASE FOR LOADING CLIENTS DATA SAVED IN A TEXT FILE
                case 7:
                    clearScreen();
                    objvaccinecentre.load_client_data(file, objvaccinecentre);
                    System.out.println("Clients have been uploaded from txt file!");
                    System.out.println("Press Enter to continue");
                    System.console().readLine();
                    clearScreen();
                    break;

                //CASE FOR SAVING CLIENTS DATA IN A TXT FILE
                case 8:
                    clearScreen();
                    objvaccinecentre.save_client_data(file);
                    System.out.println("The clients have been saved!");
                    System.out.println("Press Enter to continue.");
                    System.console().readLine();
                    clearScreen();
                    break;
            }

        }while(choice != 9);
    }

    /********** FUNCTION FOR WIPING OLD DATA FROM SCREEN **********/
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}