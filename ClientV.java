package Vaccine_Request;

import java.time.LocalDate;

public class ClientV{
    private String idClient, phoneClient;
    private Name name;
    private BasicVaccine vaccine;

    //CONSTRUCTOR USED WHEN A CLIENT IS VACCINATED
    public ClientV(String idClient, String phoneClient, String fName, String lName, String company_name, int company_id, int batch_number, LocalDate vaccine_date, boolean complation_status){
        this.idClient = idClient;
        this.phoneClient = phoneClient;
        name = new Name(fName, lName);
        vaccine = new BasicVaccine(company_name, company_id, batch_number, vaccine_date, complation_status);
        
    }

    //CONSTRUCTOR USED WHEN CLIENT IS REGISTERED FOR FIRST TIME
    public ClientV(String idClient, String phoneClient, String fName, String lName){
        this.idClient=idClient;
        this.phoneClient=phoneClient;
        name = new Name(fName, lName);
    }

    public String get_idClient(){
        return idClient;
    }

    public String get_phoneClient(){
        return phoneClient;
    }

    public Name get_name(){
        return name;
    }

    public String get_firstName(){
        return name.get_firstName();
    }

    public String get_lastName(){
        return name.get_lastName();
    }

    public BasicVaccine get_vaccine(){
        return vaccine;
    }

    public String toString(){
        //FORMAT IS UP TO CLIENT VACCINATION STATUS
        //WHEN VACCINATED, VACCINE ATTRIBUTES ARE SHOWEN
        if(vaccine!=null){
                return name+","+get_idClient()+","+get_phoneClient()+","+vaccine;           
        }
        //ELSE ONLY BASIC CLIENT ATTRIBUTES ARE SHOWEN
        else{
            return name+","+get_idClient()+","+get_phoneClient()+", -";
        }
    }
}
