package Vaccine_Request;

import java.time.LocalDateTime;

public class TwoDoseVaccine extends BasicVaccine{
    private int min_weeks_between_dosed;
    private LocalDateTime actual_second_dose_date;

    public TwoDoseVaccine(){
        super();
    }
}