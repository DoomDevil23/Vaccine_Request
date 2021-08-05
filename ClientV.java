package Vaccine_Request;

public class ClientV {
    private String idClient, phoneClient;

    public void set_Data(String idClient, String phoneClient){
        this.idClient = idClient;
        this.phoneClient = phoneClient;
    }

    public String get_idClient(){
        return idClient;
    }

    public String get_phoneClient(){
        return phoneClient;
    }
}
