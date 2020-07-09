package onboardingemployee.crudoperations.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    private long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String address;
    private long phonenumber;
    private String currentorganisation;
    public Employee() {
   super();
    }
 
    public Employee(String firstName, String lastName, String emailId ,String address,long phonenumber,String currentorganisation) {
    	super();
    	
         this.firstName = firstName;
         this.lastName = lastName;
         this.emailId = emailId;
         this.address = address;
         this.phonenumber = phonenumber;
         this.currentorganisation = currentorganisation;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    
    public String getfirstName() {
        return firstName;
    }
    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }
 
    
    public String getlastName() {
        return lastName;
    }
    public void setlastName(String lastName) {
        this.lastName = lastName;
    }
 
    
    public String getemailId() {
        return emailId;
    }
    public void setemailId(String emailId) {
        this.emailId = emailId;
    }

   
    public String getaddress() {
        return address;
    }
    public void setaddress(String address) {
    	this.address = address;
    }
    public long getphonenumber() {
        return phonenumber;
    }
    public void setphonenumber(long phonenumber) {
    	this.phonenumber = phonenumber;
    }
    public String getcurrentorganisation() {
        return currentorganisation;
    }
    public void setcurrentorganisation(String currentorganisation) {
        this.currentorganisation = currentorganisation;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
       +",address="+address+ ",phonenumber=" +phonenumber+ ",currentorganisation="+currentorganisation+"]";
    }
 
}