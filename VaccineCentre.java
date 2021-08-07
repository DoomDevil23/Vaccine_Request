package Vaccine_Request;

import java.util.ArrayList;
import java.util.List;

//import Vaccine_Request.ClientV;

public class VaccineCentre {
    private String name_VaccineCentre, eircode_VaccineCentre;
    private List<ClientV> clientlist;

    public VaccineCentre(){
        name_VaccineCentre = "Cork Vaccination Centre";
        eircode_VaccineCentre = "123456";

        clientlist = new ArrayList<ClientV>();
    }

    public void add_client(ClientV cliente){
        clientlist.add(cliente);
    }

    public void show_all_clients(){
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
        System.out.println("Clients");
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
        for(ClientV client : clientlist){
            System.out.println(client);
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
    }

    public void remoe_client(String id_client){
        int i = 0;
        boolean deleted = false;

        for(ClientV client : clientlist){
            if(id_client.equalsIgnoreCase(client.get_idClient())){
                clientlist.remove(i);
                deleted=true;
            }
            i++;
        }
        if(deleted){
            System.out.println("Client deleted!");
        }
        else{
            System.out.println("The given code doesnt exist for a valid client on this vaccine centre!");
        }
    }

    public void find_client(String id_client){
        int i=0, index=-1;

        for(ClientV client : clientlist){
            if(id_client.equalsIgnoreCase(client.get_idClient())){
                index=i;
            }
            i++;
        }
        if(index==-1){
            System.out.println("This client doesnt exist!");
        }
        else{
            System.out.println(clientlist.get(index));
        }
        //return index;
    }

    //funcion de prueba
    public ClientV get_client(int index){
        return clientlist.get(index);
    }
}
