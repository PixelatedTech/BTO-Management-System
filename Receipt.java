package Assignment;

public class Receipt {
	
	private String applicantName;
    private String nric;
    private int age;
    private String maritalStatus;
    private FlatType  flatType;
    private Project project; 
    

    
    public Receipt(Applicant applicant, FlatType flatType, Project project) {
        this.applicantName = applicant.getNRIC(); 
        this.nric = applicant.getNRIC();
        this.age = applicant.getAge();
        this.maritalStatus = applicant.getMaritalStatus();
        this.flatType = flatType;
        this.project = project;
    }
    
    
    public void printReceipt() {
        System.out.println("\n=== Flat Booking Receipt ===");
        System.out.println("Applicant NRIC: " + nric);
        System.out.println("Age: " + age);
        System.out.println("Marital Status: " + maritalStatus);
        System.out.println("Flat Type Booked: " + flatType);
        System.out.println("Project Details: " + project.toDisplayString(true));
        System.out.println("============================\n"); }
  
       
    

}
