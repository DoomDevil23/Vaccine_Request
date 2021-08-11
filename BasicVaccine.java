package Vaccine_Request;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class BasicVaccine {

    private String company_name;
    private int company_id, batch_number, weeks=0;
    private LocalDate vaccine_date, next_vaccine_dose_date;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private boolean complation_status;

    /***************** CLASS CONSTRUCTOR *****************/
    public BasicVaccine(String company_name, int company_id, int batch_number, LocalDate vaccine_date, boolean complation_status){
        this.company_name = company_name;
        this. company_id = company_id;
        this.batch_number = batch_number;
        this.vaccine_date = vaccine_date;
        this.complation_status = complation_status;
    }

    /***************** FUNCTION RETRIEVING DATE FOR SECOND DOSE IF NEEDED */
    public LocalDate get_next_dose_date(){
        //USING FUNCTION PLUSWEEKS FROM LOCALDATE CLASS TO ADD WEEKS AND GET THE DATE FOR NEXT VACCINE
        next_vaccine_dose_date = vaccine_date.plusWeeks(get_weeks_next_dose(company_id));
        return next_vaccine_dose_date;
    }

    /***************** FUNCTION TO GET HOW MANY WEEKS TAKE FOR SECOND DOSE DEPENDING ON VACCINE LABORATORY *****************/
    public int get_weeks_next_dose(int id_company){
        switch(id_company){
            //4 WEEKS FOR PFIZER AND MODERNA
            case 1:
            case 3:
                weeks=4;
                break;
            //8 WEEKS FOR ASTRA-ZENICA
            case 2:
                weeks=8;
                break;
            //1 DOSE FOR JOHNSON&JOHNSON
            case 4:
                weeks=0;
                break;
        }
        return weeks;
    }

    /********** GETTERS **********/

    public String get_company_name(){
        return company_name;
    }

    public int get_company_id(){
        return company_id;
    }

    public int get_batch_number(){
        return batch_number;
    }

    public LocalDate get_vaccine_date(){
        return vaccine_date;
    }

    public boolean get_complation_status(){
        return complation_status;
    }
    
    /********** SETTERS **********/

    public void set_company_name(String company_name){
        this.company_name=company_name;
    }

    public void set_company_id(int company_id){
        this.company_id=company_id;
    }

    public void set_batch_number(int batch_number){
        this.batch_number=batch_number;
    }

    public void set_vaccine_date(LocalDate vaccine_date){
        this.vaccine_date=vaccine_date;
    }

    public void set_complation_status(boolean complation_status){
        this.complation_status=complation_status;
    }

    /********** METHOD TOSTRING SHOWING ATTRIBUTES ACCORDING TO PRINTING PARAMETERS GIVEN WITH COMMA SEPARATOR **********/
    public String toString(){
        //PRINTING FORMAT FOR JOHNSON&JOHNSON VACCINE
        if(complation_status){
            return company_id+","+company_name+","+batch_number+","+dtf.format(vaccine_date);
        }
        //PRINTING FORMAT FOR THE REST OF VACCINES
        else{
            return company_id+","+company_name+","+batch_number+","+dtf.format(vaccine_date)+","+get_weeks_next_dose(company_id)+",-";
        }
    }
}
