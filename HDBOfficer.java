package Assignment;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class HDBOfficer extends Applicant{
	
	static String CYAN = "\u001B[36m";
	
	private boolean registeredOfficer;
	private boolean registrationApproved;
    private Project handlingProject;
    
    public HDBOfficer(String nric, String password, int age, String maritalStatus) {
        super(nric, password, age, maritalStatus);
        this.registeredOfficer = false;
        this.registrationApproved = false;
        this.handlingProject = null;
    }
    
    public boolean isRegisteredOfficer() { return registeredOfficer; }
    public void setRegisteredOfficer(boolean val) { this.registeredOfficer = val; }
    
    public boolean isRegistrationApproved() { return registrationApproved;}
    public void setRegistrationApproved(boolean registrationApproved) { this.registrationApproved = registrationApproved;}

    public Project getHandlingProject() { return handlingProject; }
    public void setHandlingProject(Project handlingProject) { this.handlingProject = handlingProject; }
    
    
    public String getRegistrationStatus() {
        if (!registeredOfficer) {
            return "Not Registered";
        } else if (!registrationApproved) {
            return "Pending Approval";
        } else {
            return "Approved";
        }
    }
    
    public void viewAllApplications() {
    	
    	// extra validation 
        if (handlingProject == null) {
            System.out.println(RED+"You are not handling any project."+RESET);
            return;
        }
        
        // ELSE:
        System.out.println("Applications for project: " + handlingProject.getProjectName());
        for (Application app : handlingProject.getApplications()) {
            System.out.println(app);
        }
        
    }
    
    // Process booking for an applicant:
    // – Update the available flat count for chosen type.
    // – Change application status from SUCCESSFUL to BOOKED.
    // – Update the applicant's application to record the chosen flat type.
    public void processBooking(String applicantNRIC) {
    	
    	// extra validation 
        if (handlingProject == null) {
            System.out.println(RED+"You are not assigned to any project."+RESET);
            return;
        }
        
        
        Application targetApp = null;
        for (Application app : handlingProject.getApplications()) {
            if (app.getApplicant().getNRIC().equalsIgnoreCase(applicantNRIC)) {
                targetApp = app;
                break;
            }
        }
        
        
        // CANNOT FIND NRIC
        if (targetApp == null) {
            System.out.println(RED+"Application for NRIC " + applicantNRIC + " not found."+RESET);
            return;
        }
        
        // validate when status is not successful
        if (targetApp.getStatus() != ApplicationStatus.SUCCESSFUL) {
            System.out.println(RED+"Application is not in SUCCESSFUL state."+RESET);
            return;
        }
        
        if (targetApp.getStatus() == ApplicationStatus.BOOKED) {
            System.out.println(RED +"Application is alreday booked."+RESET);
            return;
        }
        
        
        // Use the flat type that the applicant chose when applying
        FlatType chosenType = targetApp.getFlatType();
        int availableUnits = handlingProject.getFlatUnitCount(chosenType);
        
        if (availableUnits <= 0) {
            System.out.println(RED+"No available units for " + chosenType.name()+RESET);
            return;
        }
        
        // Print current project details (without visibility flag)
        System.out.println("Current project details: " + handlingProject.toDisplayString(false));
        
        
        // Deduct one from total .
        
        handlingProject.setFlatUnitCount(chosenType, availableUnits - 1);
        targetApp.setStatus(ApplicationStatus.BOOKED);
        // (The targetApp already has its flat type set.)
        System.out.println(CYAN+ " Current: Booking processed for applicant " + applicantNRIC + " for " + chosenType.name() + " flat."+ RESET);
        
     // Print updated project details after booking
        System.out.println("Project details after booking: " + handlingProject.toDisplayString(false));
        
        
    }
    
    
    
    /* ===============================
     * replyToEnquiry()
     * get all the enquiry with NRIC
     * narrow it down with NRIC 
     * then reply to selected enquiy
     *================================
     */
    
    
    public void replyToEnquiry() {
    	
    	// extra validation 
        if (handlingProject == null) {
            System.out.println("You are not handling any project.");
            return;
        }
        
     // STEP 1
     // First, print all enquiry from applicants in the same project +  NRIC.
        System.out.println("All Enquiries for project " + handlingProject.getProjectName() + ":");
        System.out.println("-----------------------------------------------------------------");
        
        boolean hasAnyEnquiry = false;
        
        for (Application app : handlingProject.getApplications()) {
            List<Enquiry> enquiries = app.getApplicant().getEnq();
            if (!enquiries.isEmpty()) {
                System.out.println("Applicant NRIC: " + app.getApplicant().getNRIC());
                for (int i = 0; i < enquiries.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + enquiries.get(i));
                }
                hasAnyEnquiry = true;
            }
        }
        
        if (!hasAnyEnquiry) {
            System.out.println(RED+"No enquiries found for any applicant."+RESET);
            return;
        }
        
        System.out.print("Enter the applicant NRIC to narrow down enquiries: ");
        String targetNRIC = BTOManagementSystem.scanner.nextLine();
        
        // STEP 2:
        // nARROW DOWN
        
        Application targetApp = null;
        for (Application app : handlingProject.getApplications()) {
            if (app.getApplicant().getNRIC().equalsIgnoreCase(targetNRIC)) {
                targetApp = app;
                break;
            }
        }
        if (targetApp == null) {
            System.out.println(RED+"No application found for applicant " + targetNRIC +RESET);
            return;
        }
        
        List<Enquiry> applicantEnquiries = targetApp.getApplicant().getEnq();
        if (applicantEnquiries.isEmpty()) {
            System.out.println(RED+"No enquiries found for applicant " + targetNRIC + RESET);
            return;
        }
        
        System.out.println("Enquiries for applicant " + targetNRIC + ":");
        for (int i = 0; i < applicantEnquiries.size(); i++) {
            System.out.println((i + 1) + ". " + applicantEnquiries.get(i));
        }
        
        // STEP 3:
        // display all the enquiry
        
        System.out.print("Enter the enquiry number you wish to reply to: ");
        
        
        int enquiryIndex;
        try {
            enquiryIndex = Integer.parseInt(BTOManagementSystem.scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(RED+"Invalid input."+RESET);
            return;
        }
        
        
        if (enquiryIndex < 1 || enquiryIndex > applicantEnquiries.size()) {
            System.out.println(RED+"Invalid enquiry number."+RESET);
            return;
        }
        
        
        Enquiry selectedEnquiry = applicantEnquiries.get(enquiryIndex - 1);
        if (selectedEnquiry.getReply() != null && !selectedEnquiry.getReply().isEmpty()) {
            System.out.println(RED+"This enquiry has already been replied to."+RESET);
            return;
        }
        
        System.out.print("Enter your reply: ");
        String replyMsg = BTOManagementSystem.scanner.nextLine();
        
        // Capture current timestamp and format it
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        
        String replyMsgT = replyMsg + " (Replied at: " + timestamp + ")";
        selectedEnquiry.setReply(replyMsgT);
        System.out.println(CYAN +"Reply sent successfully."+ RESET);

    }
    
    

    public void generateReceipt() {
    	
        // extra validation
        if (handlingProject == null) {
            System.out.println(RED + "You are not handling any project." + RESET);
            return;
        }
        
        // print all the  booked applications (with applicant NRIC and flat type)
        System.out.println("\n--- Booked Applications for " + handlingProject.getProjectName() + " ---");
        
        boolean foundBooked = false;
        
        
        for (Application app : handlingProject.getApplications()) {
            if (app.getStatus() == ApplicationStatus.BOOKED) {
                System.out.println("Applicant NRIC: " + app.getApplicant().getNRIC() 
                                   + " | Flat Type: " + app.getFlatType().name());
                foundBooked = true;
            }
        }
        
        if (!foundBooked) {
            System.out.println(RED + "No booked applications found for your project." + RESET);
            return;
        }
        
        // Ask officer to enter the applicant NRIC
        System.out.print("Enter applicant NRIC to generate receipt: ");
        String applicantNRIC = BTOManagementSystem.scanner.nextLine();
        
        // Find the booked application for the given applicant NRIC.
        Application targetApp = null;
        for (Application app : handlingProject.getApplications()) {
            if (app.getApplicant().getNRIC().equalsIgnoreCase(applicantNRIC)
                    && app.getStatus() == ApplicationStatus.BOOKED) {
                targetApp = app;
                break;
            }
        }
        
        if (targetApp == null) {
            System.out.println(RED + "No booked application found for applicant " + applicantNRIC + RESET);
            return;
        }
        
        // Create and print the receipt.
        Receipt receipt = new Receipt(targetApp.getApplicant(), targetApp.getFlatType(), targetApp.getProject());
        receipt.printReceipt();
    }
    
    
    
    
    @Override
    public String formatProjectDisplay(Project project) {
        // For officer, show all project details including the visibility flag.
        return project.toDisplayString(true); 
    }
    
    
    

}
