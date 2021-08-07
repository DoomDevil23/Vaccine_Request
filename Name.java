package Vaccine_Request;

public class Name {
    private String firstName, lastName;

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String get_firstName(){
        return firstName;
    }

    public String get_lastName(){
        return lastName;
    }

    public String toString(){
        return lastName+","+firstName;
    }
}
