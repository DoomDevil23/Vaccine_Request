package Vaccine_Request;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BasicVaccine {

    private String company_name;
    private int company_id, batch_number;
    private LocalDateTime vaccine_date;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private boolean complation_status;

    public BasicVaccine(String company_name, int company_id, int batch_number, LocalDateTime vaccine_date, boolean complation_status){
        this.company_name = company_name;
        this. company_id = company_id;
        this.batch_number = batch_number;
        this.vaccine_date = vaccine_date;
        this.complation_status = complation_status;
    }
    
    public String toString(){
        return company_id+","+company_name+","+batch_number+","+dtf.format(vaccine_date);
    }
}
