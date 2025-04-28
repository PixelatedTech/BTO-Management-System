package Assignment;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Applicant extends User{
	
	static String RED = "\u001B[31m";
	static String RESET = "\u001B[0m";
	static String CYAN = "\u001B[36m";
	
	private Application application;
	private List<Enquiry> enq;
	
	// construct
	
	public Applicant(String nric, String password,  int age, String maritalStatus) {
		super(nric, password, age, maritalStatus);
		this.enq = new ArrayList<>();
	}
	
	
	public Application getApplication() {
		return application;
	}
	
	public void setApplication(Application application) {
		this.application = application;
	}
	
	public List<Enquiry> getEnq() {
		return enq;
		
	}
	
	public void addEnq(Enquiry e) {
		enq.add(e);
	}
	
	
	// getApplicationStatuss()
	public void viewApplicationStatus() {
	    if (application == null) {
	        System.out.println(RED + "Sorry, you have no application." + RESET);
	    } else {
	        Project p = application.getProject();
	        System.out.println("\n=== Your BTO PROJECT Application ===");
	        // Full project info (no visibility flag needed here)
	        System.out.println("Project:      " + p.getProjectName());
	        System.out.println("Neighborhood: " + p.getNeighborhood());
	        System.out.println("Flat Type:    " + application.getFlatType());
	        System.out.println("Status:       " + CYAN + application.getStatus() + RESET);
	        System.out.println("=============================\n");
	    }
	}
	
	

	public void withdrawApplication() {
		
		if(application == null) {
			System.out.println(RED+"Sorry, you have no application to withdraw"+RESET);
		}
		
		else {
            if(application.getStatus() == ApplicationStatus.SUCCESSFUL) {
                application.setStatus(ApplicationStatus.WITHDRAWAL_REQUESTED);
                System.out.println("Withdrawal request submitted for " + application.getProject().getProjectName());
            }
            else {
                application.setStatus(ApplicationStatus.WITHDRAWN);
                System.out.println("Withdrawn SUCCESSFUL for " + application.getProject().getProjectName());
            }
		}
	}
	
	// Manage enquiries: if an enquiry already has a reply, editing/deletion is not allowed.
	
	// got this question. of there is a rpely to an enq?? can the applicant be aple to still edit and delete it?? does it make sense
	// i dont think that will be a good design to implement.
	// lets add a checker and prevent appliu=cant to edit and delete a enq if there is a reply to it.
	
    public void manageEnquiries() {
        if(enq.isEmpty()){
            System.out.println(RED+"No enquiries found."+RESET);
            return;
        }
        
        System.out.println("\nYour Enquiries:");
        System.out.println("------------------");
        
        
        for (int i = 0; i < enq.size(); i++) {
            System.out.println((i+1) + ". " + enq.get(i));
        }
        
        System.out.println("Options:");
        System.out.println("[1] ▶ Edit Enquiry");
        System.out.println("[2] ▶ Delete Enquiry");
        System.out.println("[3] ▶ Cancel");
        System.out.print("Enter choice: ");
        
        
        String option = BTOManagementSystem.scanner.nextLine();
        
        
        switch(option) {
        
        
            case "1": // create a index for edit purpose
                System.out.print("Enter enquiry number to edit: ");
                int editIndex = Integer.parseInt(BTOManagementSystem.scanner.nextLine());
                
                // validation for out of range 
                if(editIndex < 1 || editIndex > enq.size()){
                    System.out.println(RED+"Invalid index."+RESET);
                    return;
                }
                
                
                Enquiry toEdit = enq.get(editIndex - 1);
                
                // prevents edit action , can remove the null. we not passing in null anymore
                if(toEdit.getReply() != null && !toEdit.getReply().isEmpty()){
                    System.out.println(RED+"Cannot edit enquiry that has been replied to."+RESET);
                    return;
                }
                
  
                System.out.print("Enter new enquiry text: ");
                String newText = BTOManagementSystem.scanner.nextLine();
                
             // Get current timestamp and format it
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String timestamp = now.format(formatter);
                
                
                toEdit.setText(newText + " (Updated at: " + timestamp + ")");
                System.out.println("Enquiry updated.");
                break;
                
                
                
            case "2":
                System.out.print("Enter enquiry number to delete: ");
                int deleteIndex = Integer.parseInt(BTOManagementSystem.scanner.nextLine());
                
                // validation for out of range 
                if(deleteIndex < 1 || deleteIndex > enq.size()){
                    System.out.println(RED+"Invalid index."+RESET);
                    return;
                }
                
                
                Enquiry toDelete = enq.get(deleteIndex - 1);
                if(toDelete.getReply() != null && !toDelete.getReply().isEmpty()){
                    System.out.println(RED+"Cannot delete enquiry that has been replied to."+RESET);
                    return;
                }
                
                // delete the enq
                enq.remove(deleteIndex - 1);
                System.out.println("Enquiry deleted.");
                break;
                
                
            default:
                System.out.println(RED+"Cancelled."+RESET);
        }
    }
	
	
	
	@Override
    public String formatProjectDisplay(Project project) {
		
        // For Singles, show only 2-Room count with the price
		// we not checking the age. although we say "available" 
		// we not showing the visible status. 
		
		
        if (this.getMaritalStatus().equalsIgnoreCase("Single")) {
            return "Project: " + project.getProjectName() +
                   ", Neighborhood: " + project.getNeighborhood() +
                   ", 2-Room Units: " + project.getFlatUnitCount(FlatType.TWO_ROOM) +
                   ", 2-Room Price: " + project.getFlatPrice(FlatType.TWO_ROOM) +
                   ", Opening Date: " + project.getApplicationOpeningDate()+
                   ", Closing Date: " + project.getApplicationClosingDate();
            
        } else {  // Married
            return "Project: " + project.getProjectName() +
                   ", Neighborhood: " + project.getNeighborhood() +
                   ", 2-Room Units: " + project.getFlatUnitCount(FlatType.TWO_ROOM) +
                   ", 2-Room Price: " + project.getFlatPrice(FlatType.TWO_ROOM) +
                   ", 3-Room Units: " + project.getFlatUnitCount(FlatType.THREE_ROOM) +
                   ", 3-Room Price: " + project.getFlatPrice(FlatType.THREE_ROOM)+
                   ", Opening Date: " + project.getApplicationOpeningDate()+
                   ", Closing Date: " + project.getApplicationClosingDate();
        }
    }
	
	

}
