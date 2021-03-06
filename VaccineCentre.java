package Vaccine_Request;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

//import Vaccine_Request.ClientV;

public class VaccineCentre {
    private String name_of_centre, eircode;
    private List<ClientV> clientlist;

    /********** CLASS CONSTRUCTOR **********/
    public VaccineCentre(){
        name_of_centre = "Cork Vaccination Centre";
        eircode = "123456";

        clientlist = new ArrayList<ClientV>();
    }

    /********** GETTERS **********/

    public String get_name_of_centre(){
        return name_of_centre;
    }

    public String get_eircode(){
        return eircode;
    }

    public ClientV get_clientlist(){
        return clientlist;
    }

    /********* FUNCTION TO ADD NEW CLIENT TO CLIENTS LIST *********/
    public void add_client(ClientV cliente){
        clientlist.add(cliente);
    }

    /********* SHOWS ALL CLIENTS AND ATTRIBUTES IN CLIENTS LIST *********/
    public void show_all_clients(){
        //FORMATTING OUTPUT
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
        System.out.println("Clients");
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
        //LOOP FOR PRINTING EACH ELEMENT IN CLIENTLIST
        for(ClientV client : clientlist){
            System.out.println(client);
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
    }

    /********** FUNCTION FOR DELETING A CLIENT FROM CLIENTS LIST BY ID **********/
    public void remove_client(String id_client){
        int index = find_clientIndex(id_client);
        
        //WHEN INDEX IS -1 MEANS THE ID IS INVALID OR DOES NOT EXIST IN CLIENTS LIST
        if(index!=-1){
            clientlist.remove(index);
            System.out.println("Client deleted!");
        }
        else{
            System.out.println("The given code doesnt exist for a valid client on this vaccine centre!");
        }
    }

    /********* FUNCTION TO SEARCH A CLIENT INDEX IN LIST BY ID *********/
    public int find_clientIndex(String id_client){
        int i=0, index=-1;

        //LOOP FOR SEARCHING A CLIENT IN CLIENTS LIST BY ID
        for(ClientV client : clientlist){
            if(id_client.equalsIgnoreCase(client.get_idClient())){
                //IF FINDED THE INDEX IS STORED
                index=i;
            }
            i++;
        }
        
        return index;
    }

    //MAY BE REMOVED AND USE THE PREVIUS FUNCTION (?)
    public ClientV find_client(int index){
        return clientlist.get(index);
    }

    //THIS FUNCTION UPDATES A CLIENT WITH THE VACCINE DOSE DATA
    public void addvaccineclient(int index, ClientV objclientupdate){

        clientlist.set(index, objclientupdate);
    }

    /********* THIS FUNCTION LOADS CLIENTS DATA FROM A TXT FILE *********/
    public void load_client_data(File file, VaccineCentre objvaccinecentre){
        try{
            //OBJECT FROM BUFFEREDREADER CLASS THAT BUFFERE TXT FILE DATA
            BufferedReader br = new BufferedReader(new FileReader(file));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            //VARIABLE TO STORE LINES FROM TXT FILE
            String line;

            ClientV client=null;

            //LOOP FOR READING EACH LINE IN TXT FILE
            while((line=br.readLine()) != null){

                //CONDITION USED WHEN A CLIENT IS NOT VACCINATED. IN CASE TRUE IT USES CONSTRUCTOR WITHOUT VACCINE ATTRIBUTES
                if(split_txt_line(line, 5)==null){
                    client = new ClientV(split_txt_line(line, 3), split_txt_line(line, 4), split_txt_line(line, 2), split_txt_line(line, 1));
                }
                //IF CLIENT HAS BEEN VACCINATED, VACCINE ATTRIBUTES HAVE TO BE INCLUDED IN CONSTRUCTOR
                else{
                    //ATTRIBUE ONLY TRUE FOR JOHNSON&JOHNSON VACCINE
                    boolean complation_status = false;
                    if(split_txt_line(line, 5).equalsIgnoreCase("4")){
                        complation_status = true;
                    }
                    client = new ClientV(split_txt_line(line, 3), split_txt_line(line, 4), split_txt_line(line, 2), split_txt_line(line, 1), split_txt_line(line, 6), Integer.parseInt(split_txt_line(line, 5)), Integer.parseInt(split_txt_line(line, 7)), LocalDate.parse(split_txt_line(line, 8), formatter), complation_status);
                }
                
                //ADDING CLIENT TO CURRENT CLIENTS LIST FOR THE VACCINE CENTRE
                objvaccinecentre.add_client(client);
            }
            br.close();
        }
        catch(Exception e){}

    }

    /********* THIS FUNCTION SAVES CLIENTS DATA IN A TXT FILE *********/
    public void save_client_data(File file){
        try{
            FileWriter writer = new FileWriter(file);
            //LOOP FOR ADDING CLIENTS. ONE LINE FOR EACH CLIENT
            for(ClientV client : clientlist){
                writer.write(client.toString()+"\n");
            }
            writer.close();
        }
        catch(Exception e){}
    }

    
    /********* THIS METHOD RETURN A LIST WITH COMMA SEPARATORS INDEXES *********/
    public static ArrayList<Integer> get_positions(String line){
         //IN THIS LIST IS STORED ALL INDEXES FOR <<,>> THAT SEPARATES ATTRIBUTES
         ArrayList<Integer> positions = new ArrayList<Integer>();

         //THIS LOOP SEARCH EVERY <<,>> INDEX AND STORES IN <<POSITIONS>> LIST FOR USING LATER
         for (int i = -1; (i = line.indexOf(",", i + 1)) != -1; i++) {
             positions.add(i);
         }
         return positions;
    }

    /********* THIS FUNCTION IS FOR SPLITTING TXT LINES AND GETTING THE VALUES FOR CLASSES ATRIBUTES *********/
    public String split_txt_line(String line, int option){
        //VALUE TO RETURN EACH ATTRIBUTE
        String value=null;
        
       ArrayList<Integer> positions = get_positions(line);

        //**************VALIDATION FOR GETTING ATTRIBUTES FROM TEXT FILE LINES**************
        //FIRST ATTRIBUTE STARTS AT INDEX 0
        if(option==1){
            value = line.substring(0, positions.get(option-1));
        }
        //IN CASE THE CLIENT HAS NO VACCINE REGISTERED, A NULL VALUE IS SENT TO SPECIFY WICH CONSTRUCTOR HAS TO BE USED
        else if(positions.size()==4 && option==5){
            value=null;
        }
        //IN CASE CLIENT GOT JOHNSON&JOHNSON VACCINE THIS OPERATION IS USED TO GET THE LAST ATTRIBUTE IN LINE
        else if(positions.size()==7 && option==8){
            value=line.substring(positions.get(option-2)+1, line.length());
        }
        //DEFAULT CASE THAT GET ALL ATTRIBUTES STARTING FROM 2ND ATTRIBUTE TO LAST ATTRIBUTE FOR VACCINES DIFERENTS FROM JOHNSON&JOHNSON VACCINE
        else{
            value = line.substring(positions.get(option-2)+1, positions.get(option-1));
        }
        
        return value;
    }

    /********** THIS FUNCTION IS FOR SHOWING THE % OF CLIENTS THAT HAVE BEEN GIVEN EACH OF THE VACCINES TYPE **********/
    public void show_stats(){
        //COUNTERS
        int vaccine_count = 0, pfizer_count = 0, astra_count = 0, moderna_count = 0, johnson_count = 0;
        //LOOP FOR SEARCHING IN CLIENTS LIST
        for(ClientV client : clientlist){
            //CONDITION FOR ONLY INCLUDING VACCINATED CLIENTS IN STATS
            if(client.get_vaccine() != null){
                vaccine_count++;
                switch(client.get_vaccine().get_company_id()){
                    case 1:
                        pfizer_count++;
                        break;
                    case 2:
                        astra_count++;
                        break;
                    case 3:
                        moderna_count++;
                        break;
                    case 4:
                        johnson_count++;
                        break;
                }
            }
        }

        //FORMATTING INTEGERS INTO FLOATS TO GET PERCENTAGE STATS
        float pfizer_percentage = (pfizer_count*100.0f)/vaccine_count;
        float astra_percentage = (astra_count*100.0f)/vaccine_count;
        float moderna_percentage = (moderna_count*100.0f)/vaccine_count;
        float johnson_percentage = (johnson_count*100.0f)/vaccine_count;

        System.out.println("Clients for Pfizer vaccine: "+pfizer_percentage+"%");
        System.out.println("Clients for Astra-Zenica vaccine: "+astra_percentage+"%");
        System.out.println("Clients for Moderna vaccine: "+moderna_percentage+"%");
        System.out.println("Clients for Johnson&Johnson vaccine: "+johnson_percentage+"%");
        System.out.println("Clients vaccinated: "+vaccine_count);
    }

    /********** THIS FUNCTION IS FOR SHOWING CLIENTS EXPECTING SECOND DOSE **********/
    public void show_clients_due(){
        //LOOP FOR SEARCHING IN CLIENTS LIST
        for(ClientV client : clientlist){
            //CONDITION TO VALIDATE CLIENT HAS VACCINE DATA AND VACCINE IS DIFFERENT FROM JOHNSON&JOHNSON
            if(client.get_vaccine() != null && client.get_vaccine().get_company_id() != 4){
                System.out.println("Client: " + client.get_name() + " | Vaccine: " + client.get_vaccine().get_company_name() + " | Due Date: " + client.get_vaccine().get_next_dose_date());
            }
        }
    }
}
