package Assignment;
import java.util.List;
import java.util.ArrayList;

public class HDBManager extends User {
	
	static String RED = "\u001B[31m";
	static String CYAN = "\u001B[36m";
	static String RESET = "\u001B[0m";
	static String BOLD = "\u001B[1m";
	
	private static List<Applicant> applicantList = new ArrayList<>();
	
	public HDBManager(String nric, String password, int age, String maritalStatus) {
        super(nric, password, age, maritalStatus);
    }
	
	public static List<Applicant> getApplicants() {
        return applicantList;
    }
    
    public static void addApplicant(Applicant applicant) {
        applicantList.add(applicant);
    }

	/* ===============================
     * createProject() 
     * Add it to the project list
     *================================
     */
	public void createProject(List<Project> projects, String projectName, String neighborhood, 
            int twoRoomUnits, int threeRoomUnits,
            int twoRoomPrice, int threeRoomPrice,
            String applicationOpeningDate, String applicationClosingDate, boolean visible,
            int availableOfficerSlots)
	{
	
	    Project newProject = new Project(projectName, neighborhood, twoRoomUnits, threeRoomUnits,
	                           twoRoomPrice, threeRoomPrice, applicationOpeningDate, applicationClosingDate,
	                           this, visible, availableOfficerSlots);
	    projects.add(newProject);
	    System.out.println(CYAN + "Project \"" + projectName + "\" created successfully." + RESET);
	}
    
    
    
    
		    
 // Edit an existing project (only if this manager is the owner).
    public void editProject(Project project, String newNeighborhood, int newTwoRoomUnits, int newThreeRoomUnits) {
        project.setNeighborhood(newNeighborhood);
        project.setFlatUnitCount(FlatType.TWO_ROOM, newTwoRoomUnits);
        project.setFlatUnitCount(FlatType.THREE_ROOM, newThreeRoomUnits);
        System.out.println("Project \"" + project.getProjectName() + "\" updated successfully.");
    }
    
    
    
    
 // Delete a project from the central list.
    public void deleteProject(List<Project> projects, Project project) {
        if (projects.remove(project)) {
            System.out.println("Project \"" + project.getProjectName() + "\" deleted successfully.");
        } else {
            System.out.println(RED+"Project deletion failed."+RESET);
        }
    }
    
    
    public void toggleProjectVisibility(Project project) {
        project.setVisible(!project.isVisible());
        System.out.println("Project \"" + project.getProjectName() + "\" visibility is now " + project.isVisible());
    }
    
    // Approve an application by setting its status to SUCCESSFUL.
    public void approveApplication(Application application) {
        application.setStatus(ApplicationStatus.SUCCESSFUL);
        System.out.println("Application for project \"" + application.getProject().getProjectName() + "\" approved.");
    }
    
    
    public void rejectApplication(Application application) {
        application.setStatus(ApplicationStatus.UNSUCCESSFUL);
        System.out.println("Application for project \"" + application.getProject().getProjectName() + "\" rejected.");
    }
    
    // Approve an officer registration for a project if there are available slots.
    public void approveOfficerRegistration(HDBOfficer officer, Project project) {
        if (project.getAvailableOfficerSlots() > 0) {
            officer.setRegisteredOfficer(true);
            officer.setHandlingProject(project);
            project.setAvailableOfficerSlots(project.getAvailableOfficerSlots() - 1);
            System.out.println("Officer " + officer.getNRIC() + " registration approved for project \"" + project.getProjectName() + "\".");
        } else {
            System.out.println("No available officer slots for project \"" + project.getProjectName() + "\".");
        }
    }
    
    
    // manager sub menu (1)
    public void viewOfficerRegistrations(List<Project> projects, List<User> users) {
        System.out.println("\n--- Officer Registrations for Your Projects ---");
        boolean hasProjects = false;

        for (Project project : projects) {
            if (project.getManager().equals(this)) {
                hasProjects = true;
                System.out.println(CYAN + "\nProject: " + project.getProjectName() + RESET);
                
                List<HDBOfficer> officersInProject = new ArrayList<>();

                for (User user : users) {
                    if (user instanceof HDBOfficer) {
                        HDBOfficer officer = (HDBOfficer) user;
                        if (officer.getHandlingProject() != null &&
                                officer.getHandlingProject().equals(project)) {
                            officersInProject.add(officer);
                        }
                    }
                }

                if (officersInProject.isEmpty()) {
                    System.out.println("   No officer registrations for this project.");
                } else {
                    for (HDBOfficer officer : officersInProject) {
                        String status = officer.isRegistrationApproved() ? "Approved" : "Pending";
                        System.out.printf("   Officer NRIC: %s | Status: %s%n",
                                 officer.getNRIC(), status);
                    }
                }
            }
        }

        if (!hasProjects) {
            System.out.println(RED + "You are not managing any projects." + RESET);
        }
    }
    
    
    // manager sub menu (2)
    public void handleOfficerRegistration(Project project, HDBOfficer officer, boolean isApprove) {
        if (!project.getManager().equals(this)) {
            System.out.println(RED + "Error: You are not the manager of this project." + RESET);
            return;
        } else if (officer.isRegistrationApproved()) {
            System.out.println(RED + "Error: You are already the officer of this project or other project." + RESET);
            return;
        } else {
            if (isApprove) {
                if (project.getAvailableOfficerSlots() > 0) {
                    officer.setRegistrationApproved(true);
                    officer.setHandlingProject(project);
                    project.setAvailableOfficerSlots(project.getAvailableOfficerSlots() - 1);
                    System.out.printf(CYAN + "Officer %s approved for project %s.%n" + RESET,
                            officer.getNRIC(), project.getProjectName());
                } else {
                    System.out.println(RED + "No available officer slots in this project." + RESET);
                }
            } else {
                officer.setRegistrationApproved(false);
                officer.setHandlingProject(null);
                System.out.printf(CYAN + "Officer %s registration rejected.%n" + RESET, officer.getNRIC());
            }
        }
    }
    
    
    // manager sub menu (3)
    public void processBTOApplication(Project project, String applicantNRIC, boolean isApprove) {
        if (!project.getManager().equals(this)) {
            System.out.println(RED + "Error: Unauthorized access to project." + RESET);
            return;
        }

        for (Application app : project.getApplications()) {
            if (app.getApplicant().getNRIC().equals(applicantNRIC)) {
                if (isApprove) {
                    app.setStatus(ApplicationStatus.SUCCESSFUL);
                    System.out.printf(CYAN + "Application by %s approved.%n" + RESET, applicantNRIC);
                } else {
                    app.setStatus(ApplicationStatus.UNSUCCESSFUL);
                    System.out.printf(CYAN + "Application by %s rejected.%n" + RESET, applicantNRIC);
                }
                return;
            }
        }

        System.out.println(RED + "Application not found for NRIC: " + applicantNRIC + RESET);
    }
    
    // manager sub menu (4) 
    
    public void handleWithdrawalRequests(List<Project> projects, String applicantNRIC, boolean isApprove) {
        boolean foundRequest = false;

        for (Project project : projects) {
            if (project.getManager().equals(this)) {
                for (Application app : project.getApplications()) {
                    if (app.getApplicant().getNRIC().equals(applicantNRIC) &&
                            app.getStatus() == ApplicationStatus.WITHDRAWAL_REQUESTED) {
                        foundRequest = true;
                        if (isApprove) {
                            app.setStatus(ApplicationStatus.WITHDRAWN);
                            System.out.printf(CYAN + "Withdrawal by %s approved.%n" + RESET, applicantNRIC);
                        } else {
                            app.setStatus(ApplicationStatus.SUCCESSFUL);
                            System.out.printf(CYAN + "Withdrawal by %s rejected.%n" + RESET, applicantNRIC);
                        }
                    }
                }
            }
        }

        if (!foundRequest) {
            System.out.println(RED + "No withdrawal request found for NRIC: " + applicantNRIC + RESET);
        }
    }
    
    
    
    @Override
    public String formatProjectDisplay(Project project) {
        return project.toDisplayString(true);
    }
    
    

}
