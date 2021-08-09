package Vaccine_Request;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

//import Vaccine_Request.ClientV;

public class VaccineCentre {
    private String name_VaccineCentre, eircode_VaccineCentre;
    private List<ClientV> clientlist;

    //CONSTRUCTOR
    public VaccineCentre(){
        name_VaccineCentre = "Cork Vaccination Centre";
        eircode_VaccineCentre = "123456";

        clientlist = new ArrayList<ClientV>();
    }

    //FUNCTION TO ADD NEW CLIENT TO CLIENTS LIST
    public void add_client(ClientV cliente){
        clientlist.add(cliente);
    }

    //SHOWS ALL CLIENTS AND ATTRIBUTES IN CLIENTS LIST
    public void show_all_clients(){
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
        System.out.println("Clients");
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
        for(ClientV client : clientlist){
            System.out.println(client);
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
    }

    //FUNCTION FOR DELETING A CLIENT FROM CLIENTS LIST BY ID
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

    //function to search a client by id
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

    public ClientV find_client(int index){
        return clientlist.get(index);
    }

    //THIS FUNCTION UPDATES A CLIENT WITH THE VACCINE DOSE DATA
    public void addvaccineclient(int index, ClientV objclientupdate){

        clientlist.set(index, objclientupdate);
    }

    //THIS FUNCTION LOADS CLIENTS DATA FROM A TXT FILE
    public void load_client_data(File file, VaccineCentre objvaccinecentre){
        try{
            //OBJECT FROM BUFFEREDREADER CLASS THAT BUFFERE TXT FILE DATA
            BufferedReader br = new BufferedReader(new FileReader(file));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            //VARIABLE TO STORE LINES FROM TXT FILE
            String line;

            ClientV client=null;

            //**********LOOP FOR READING EACH LINE IN TXT FILE**********
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
                    client = new ClientV(split_txt_line(line, 3), split_txt_line(line, 4), split_txt_line(line, 2), split_txt_line(line, 1), split_txt_line(line, 6), Integer.parseInt(split_txt_line(line, 5)), Integer.parseInt(split_txt_line(line, 7)), LocalDateTime.parse(split_txt_line(line, 8).concat(" 00:00"), formatter), complation_status);
                }
                
                //ADDING CLIENT TO CURRENT CLIENTS LIST FOR THE VACCINE CENTRE
                objvaccinecentre.add_client(client);
            }
        }
        catch(Exception e){}

    }

    //THIS FUNCTION SAVES CLIENTS DATA IN A TXT FILE
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

    //THIS FUNCTION IS FOR SPLITTING TXT LINES AND GETTING THE VALUES FOR CLASSES ATRIBUTES
    public String split_txt_line(String line, int option){
        //VALUE TO RETURN EACH ATTRIBUTE
        String value=null;
        
        //IN THIS LIST IS STORED ALL INDEXES FOR <<,>> THAT SEPARATES ATTRIBUTES
        List<Integer> positions = new ArrayList<Integer>();

        //THIS LOOP SEARCH EVERY <<,>> INDEX AND STORES IN <<POSITIONS>> LIST FOR USING LATER
        for (int i = -1; (i = line.indexOf(",", i + 1)) != -1; i++) {
            positions.add(i);
        }

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
}
