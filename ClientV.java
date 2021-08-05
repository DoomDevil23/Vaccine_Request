package Vaccine_Request;

public class ClientV extends Name {
    private String idClient, phoneClient;

    public void set_Data(String idClient, String phoneClient, String fName, String lName){
        this.idClient = idClient;
        this.phoneClient = phoneClient;
        super.set_Name(fName, lName);
    }

    public String get_idClient(){
        return idClient;
    }

    public String get_phoneClient(){
        return phoneClient;
    }

    public String toString(){
        return get_lastName()+","+get_firstName()+","+get_idClient()+","+get_phoneClient();
    }
}
