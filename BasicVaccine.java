package Vaccine_Request;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class BasicVaccine {

    private String company_name;
    private int company_id, batch_number, weeks=0;
    private LocalDate vaccine_date, next_vaccine_dose_date;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private boolean complation_status;

    public BasicVaccine(String company_name, int company_id, int batch_number, LocalDate vaccine_date, boolean complation_status){
        this.company_name = company_name;
        this. company_id = company_id;
        this.batch_number = batch_number;
        this.vaccine_date = vaccine_date;
        this.complation_status = complation_status;
    }

    public LocalDate get_next_dose_date(){
        if(complation_status){
            next_vaccine_dose_date = null;
        }
        else{
            next_vaccine_dose_date = vaccine_date.plusWeeks(get_weeks_next_dose(company_id));
        }
        return next_vaccine_dose_date;
    }

    public int get_weeks_next_dose(int id_company){
        switch(id_company){
            case 1:
            case 3:
                weeks=4;
                break;
            case 2:
                weeks=8;
                break;
            case 4:
                weeks=0;
                break;
        }
        return weeks;
    }

    public int get_company_id(){
        return company_id;
    }

    public String get_company_name(){
        return company_name;
    }
    
    public String toString(){
        if(complation_status){
            return company_id+","+company_name+","+batch_number+","+dtf.format(vaccine_date);
        }
        else{
            return company_id+","+company_name+","+batch_number+","+dtf.format(vaccine_date)+","+get_weeks_next_dose(company_id)+",-";
        }
    }
}
