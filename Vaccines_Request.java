package Vaccine_Request;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.File;


public class Vaccines_Request{
    public static void main(String[] args){
        VaccineCentre objvaccinecentre = new VaccineCentre();
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
                choice = Integer.parseInt(System.console().readLine());
                clearScreen();
            }
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

                case 2:
                    //CASE 2 FOR REMOVING CLIENTS FROM VACCINE CENTRE CLIENTS LIST
                    clearScreen();
                    System.out.println("Enter client id code to remove:");
                    String id_client = System.console().readLine();
                    objvaccinecentre.remove_client(id_client);
                    System.out.println("Press Enter to continue.");
                    System.console().readLine();
                    clearScreen();
                    break;

                case 3:
                    //CASE 3 FOR RECORDING A SPECIFIC CLIENTS FIRST VACCINATION
                    int company_id=0, batch_number=0;
                    String company_name="";
                    LocalDateTime vaccine_date = LocalDateTime.now();
                    boolean complation_status = false;

                    clearScreen();

                    System.out.println("Enter client code to find:");
                    String idclient = System.console().readLine();
                    
                    int index = objvaccinecentre.find_clientIndex(idclient);
                    if(index==-1){
                        System.out.println("This client doesnt exist!");
                    }
                    else{
                        //reading the code for selected vaccine
                        //loop until select a valid value
                        while(!(company_id<=4) || !(company_id>=1)){
                            System.out.println("1 - Pfizer");
                            System.out.println("2 - Astra-Zenica");
                            System.out.println("3 - Moderna");
                            System.out.println("4 - Jhonson&Johnson");
                            System.out.println("Enter vaccine code number:");
                            
                            try{
                                company_id = Integer.parseInt(System.console().readLine());
                                
                                //validating company_id in right range
                                if(company_id>4 || company_id<1){
                                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                                    System.out.println("Invalid value of:"+company_id+". Enter a number between 1-4");
                                }
                            }
                            //error throws when input is not integer value
                            catch(Exception e){
                                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                                System.out.println("Enter a number between 1-4");
                            }
                        }

                        //selecting company name according to company code
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

                case 4:
                    //CASE 4 FOR SHOWING ALL THE CLIENTS FOR THIS VACCINE CENTRE
                    clearScreen();
                    objvaccinecentre.show_all_clients();
                    System.out.println("This are the registered clients for this vaccine centre. Press Enter to continue.");
                    System.console().readLine();
                    clearScreen();
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    //CASE FOR LOADING CLIENTS DATA SAVED IN A TEXT FILE
                    clearScreen();
                    objvaccinecentre.load_client_data(file, objvaccinecentre);
                    break;

                case 8:
                    //CASE FOR SAVING CLIENTS DATA IN A TXT FILE
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

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}