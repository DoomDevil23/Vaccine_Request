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
                    clearScreen();
                    option_one(objvaccinecentre);
                    clearScreen();
                    break;

                //CASE 2 FOR REMOVING CLIENTS FROM VACCINE CENTRE CLIENTS LIST
                case 2: 
                    clearScreen();
                    option_two(objvaccinecentre);
                    clearScreen();
                    break;

                //CASE 3 FOR RECORDING A SPECIFIC CLIENTS FIRST VACCINATION
                case 3:
                    clearScreen();
                    option_three(objvaccinecentre);
                    clearScreen();
                    break;

                //CASE 4 FOR SHOWING ALL THE CLIENTS FOR THIS VACCINE CENTRE
                case 4:
                    clearScreen();
                    option_four(objvaccinecentre);
                    clearScreen();
                    break;
               
                //CASE 5 FOR SHOWING STATISTICS OF CLIENTS GIVEN EACH VACCINE TYPE
                case 5:
                    clearScreen();
                    option_five(objvaccinecentre);
                    clearScreen();
                    break;

                //CASE 6 FOR SHOWING CLIENTS DUE A SECOND VACCINE AND THE DATE DUE
                case 6:
                    clearScreen();
                    option_six(objvaccinecentre);
                    clearScreen();
                    break;

                //CASE FOR LOADING CLIENTS DATA SAVED IN A TEXT FILE
                case 7:
                    clearScreen();
                    option_seven(file, objvaccinecentre);
                    clearScreen();
                    break;

                //CASE FOR SAVING CLIENTS DATA IN A TXT FILE
                case 8:
                    clearScreen();
                    option_eight(file, objvaccinecentre);
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

    /********** FUNCTION FOR MANAGING OPTION 1 IN MENU. THIS FUNCTION READS CLIENT DATA AND SAVE IT IN THEIR CLASS OBJECTS **********/
    public static void option_one(VaccineCentre objvaccinecentre){
        System.out.println("Enter Clients First Name:");
        String firstName = System.console().readLine();

        System.out.println("Enter Clients Last Name:");
        String lastName = System.console().readLine();

        System.out.println("Enter Clients ID:");
        String idClient = System.console().readLine();

        System.out.println("Enter Clients Phone:");
        String phoneClient = System.console().readLine();

        ClientV objClientV = new ClientV(idClient, phoneClient, firstName, lastName);
        objvaccinecentre.add_client(objClientV);
        
        System.out.println("Client has been added. Press Enter to continue.");
        System.console().readLine();
    }

    /********** FUNCTION FOR MANAGING OPTION 2 IN MENU. THIS FUNCTION READS CLIENT ID AND SENDS IT TO VACCINE_CENTRE CLASS TO DELETE IT IF EXIST **********/
    public static void option_two(VaccineCentre objvaccinecentre){
        System.out.println("Enter client id code to remove:");
        String id_client = System.console().readLine();
        objvaccinecentre.remove_client(id_client);
        System.out.println("Press Enter to continue.");
        System.console().readLine();
    }

    /********** FUNCTION FOR MANAGING OPTION 3 IN MENU. THIS FUNCTION READS CLIENT ID AND ADDS DATA FOR A NEW VACCINATION COMPLETED **********/
    public static void option_three(VaccineCentre objvaccinecentre){
        //NEEDED ATTRIBUTES FOR REGISTERING A VACCINATION
        int company_id=0, batch_number=0, index;
        String company_name="";
        LocalDate vaccine_date = LocalDate.now();
        boolean complation_status = false;

        //READING CLIENT ID
        System.out.println("Enter client code to find:");
        String idclient = System.console().readLine();
        
        //LOOKING AND GETTING CLIENT INDEX IN LIST IF CLIENT ID EXISTS IN CLIENTS LIST
        index = objvaccinecentre.find_clientIndex(idclient);
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

            //CLIENT VACCINE INFORMATION UPDATE
            ClientV objclientactual = objvaccinecentre.find_client(index);
            ClientV objclientupdate = new ClientV(objclientactual.get_idClient(), objclientactual.get_phoneClient(), objclientactual.get_firstName(), objclientactual.get_lastName(), company_name, company_id, batch_number, vaccine_date, complation_status);
            objvaccinecentre.addvaccineclient(index, objclientupdate);

            System.out.println("Successful vaccination!");
            System.out.println("Press Enter to continue");
            System.console().readLine();
        }

    }

    /********** FUNCTION FOR MANAGING OPTION 4 IN MENU. THIS FUNCTION USES VACCINE_CENTRE CLASS TO SHOW ALL REGISTERED CLIENTS FOR THE CURRENT VACCINE CENTRE **********/
    public static void option_four(VaccineCentre objvaccinecentre){
        objvaccinecentre.show_all_clients();
        System.out.println("This are the registered clients for this vaccine centre. Press Enter to continue.");
        System.console().readLine();
    }

    /********** FUNCTION FOR MANAGING OPTION 5 IN MENU. THIS FUNCTION USES VACCINE_CENTRE CLASS TO SHOW VACCINATED CLIENTS STATS **********/
    public static void option_five(VaccineCentre objvaccinecentre){
        objvaccinecentre.show_stats();
        System.out.println("Press Enter to continue");
        System.console().readLine();
    }

    /********** FUNCTION FOR MANAGING OPTION 6 IN MENU. THIS FUNCTION USES VACCINE_CENTRE CLASS TO SHOW CLIENTS NEEDING A SECOND DOSE **********/
    public static void option_six(VaccineCentre objvaccinecentre){
        objvaccinecentre.show_clients_due();
        System.out.println("Press Enter to continue");
        System.console().readLine();
    }

    /********** FUNCTION FOR MANAGING OPTION 7 IN MENU. THIS FUNCTION USES VACCINE_CENTRE CLASS TO LOAD CLIENTS FROM A TXT FILE **********/
    public static void option_seven(File file, VaccineCentre objvaccinecentre){
        objvaccinecentre.load_client_data(file, objvaccinecentre);
        System.out.println("Clients have been uploaded from txt file!");
        System.out.println("Press Enter to continue");
        System.console().readLine();
    }

    /********** FUNCTION FOR MANAGING OPTION 8 IN MENU. THIS FUNCTION USES VACCINE_CENTRE CLASS TO SAVE CLIENTS INTO A TXT FILE **********/
    public static void option_eight(File file, VaccineCentre objvaccinecentre){
        objvaccinecentre.save_client_data(file);
        System.out.println("The clients have been saved!");
        System.out.println("Press Enter to continue.");
        System.console().readLine();
    }
}