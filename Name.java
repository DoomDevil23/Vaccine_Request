package Vaccine_Request;

public class Name {
    private String firstName, lastName;

    public void set_Name(String fName, String lName){
        firstName = fName;
        lastName = lName;
    }

    public String get_firstName(){
        return firstName;
    }

    public String get_lastName(){
        return lastName;
    }
}
