package Vaccine_Request;

import java.sql.Date;
import java.time.LocalDateTime;

public class ClientV{
    private String idClient, phoneClient;
    private Name name;
    private BasicVaccine vaccine;

    public ClientV(String idClient, String phoneClient, String fName, String lName, String company_name, int company_id, int batch_number, LocalDateTime vaccine_date, boolean complation_status){
        this.idClient = idClient;
        this.phoneClient = phoneClient;
        name = new Name(fName, lName);
        vaccine = new BasicVaccine(company_name, company_id, batch_number, vaccine_date, complation_status);
        
    }

    public String get_idClient(){
        return idClient;
    }

    public String get_phoneClient(){
        return phoneClient;
    }

    public String toString(){
        return name+","+get_idClient()+","+get_phoneClient()+","+vaccine;
    }
}
