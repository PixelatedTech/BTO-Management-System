package Assignment;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class BTOManagementSystem {
	
	// all the colors and options
	static String RED = "\u001B[31m";
	static String CYAN = "\u001B[36m";
	static String RESET = "\u001B[0m";
	static String PURPLE = "\u001B[35m"; 
	static String BOLD = "\u001B[1m";
	
	static Scanner scanner = new Scanner(System.in);

    // need to load projects, applicants, managers,officers. 
    static List<User> users = new ArrayList<>();
    static List<Project> projects = new ArrayList<>();
    
    
    public static void main(String[] args){

    	// LOAD THE DATA.
        initializeSampleData(); 
        
        // BTO BANNER
        System.out.println("\n██████╗ ████████╗ ██████╗     ███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗███╗   ███╗███████╗███╗   ██╗████████╗");
        System.out.println("██╔══██╗╚══██╔══╝██╔═══██╗    ████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝");
        System.out.println("██████╔╝   ██║   ██║   ██║    ██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ");
        System.out.println("██╔══██╗   ██║   ██║   ██║    ██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ");
        System.out.println("██████╔╝   ██║   ╚██████╔╝    ██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ");
        System.out.println("╚═════╝    ╚═╝    ╚═════╝     ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ");
        System.out.println("\nWelcome to the BTO Management System!\n");
        

        
        
        // THIS IS WAYYYY TO BORING TO SEEEEEEE
        // LETS TRY TO IMPORT SMTH COOL 
        while (true) {
        	System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("       》 BTO MANAGEMENT SYSTEM 《           ");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            //System.out.println("[1] ▶ Login                 ");
            System.out.print("[1] ▶ ");
            String text = "login"; 
            technoAnimation(text); 
            System.out.println(); 
            System.out.print("[2] ▶ ");
            text = "Exit"; 
            technoAnimation(text); 
            System.out.println(); 
            System.out.print("Enter choice: ");
            
            
            String choice = scanner.nextLine();
            
            switch (choice) {
            case "1":
                login();
                break;
            case "2":
                System.out.println(RED +  "Exiting system. Goodbye!" + RESET);
                return;
            default:
            	System.out.println(RED + "Invalid choice Enter. Try again." + RESET);
	        }
	    }
	}
    
    private static void initializeSampleData() {
    	
    	// applicant data from the excel file, lazy to write fucntion to convert 
    	Applicant applicantJohn   = new Applicant("S1234567A", "password", 35, "Single");
        Applicant applicantSarah  = new Applicant("T7654321B", "password", 40, "Married");
        Applicant applicantGrace  = new Applicant("S9876543C", "password", 37, "Married");
        Applicant applicantJames  = new Applicant("T2345678D", "password", 30, "Married");
        Applicant applicantRachel = new Applicant("S3456789E", "password", 25, "Single");

        users.add(applicantJohn);
        users.add(applicantSarah);
        users.add(applicantGrace);
        users.add(applicantJames);
        users.add(applicantRachel);
        
        //  HDBManager's static methods:
        HDBManager.addApplicant(applicantJohn);
        HDBManager.addApplicant(applicantSarah);
        HDBManager.addApplicant(applicantGrace);
        HDBManager.addApplicant(applicantJames);
        HDBManager.addApplicant(applicantRachel);
        
        
        // Officers
        HDBOfficer officerDaniel = new HDBOfficer("T2109876H", "password", 36, "Single");
        HDBOfficer officerEmily = new HDBOfficer("S1111111A", "password", 30, "Married");
        HDBOfficer officerDavid = new HDBOfficer("T1234567J", "password", 29, "Married");
        
        users.add(officerDaniel);
        users.add(officerEmily);
        users.add(officerDavid);
        
        
        // manager
        HDBManager managerJessica = new HDBManager("S5678901G", "password", 26, "Married");
        HDBManager managerMichael = new HDBManager("T8765432F", "password", 36, "Single");
        HDBManager managerAlice = new HDBManager("S1122334K", "password", 30, "Married");
        HDBManager managerBob   = new HDBManager("T4433221M", "password", 45, "Single");
        
        users.add(managerJessica);
        users.add(managerMichael);
        users.add(managerAlice);
        users.add(managerBob);
        

        // projects
        Project acaciaBreeze = new Project("Acacia Breeze", "Yishun", 2, 3, 350000, 450000, "15/2/2025", "20/5/2025", managerJessica, true, 3);
        Project lemon = new Project("Lemon", "Yishun", 0, 3, 350000, 450000, "15/2/2025", "20/5/2025", managerMichael, true, 3);
        Project appleOrchard = new Project("Apple", "Boon Lay", 1, 2, 400000, 500000, "10/2/2025", "18/5/2025", managerAlice, true, 3);
        Project bananaBay = new Project("Banana", "Jurong", 5, 0, 300000, 0, "20/2/2025", "01/3/2025", managerBob, false, 3);
        Project mangoMeadows = new Project("Mango", "Clementi", 3, 3, 380000, 470000, "18/2/2025", "22/3/2025", managerMichael, false, 3);

        projects.add(acaciaBreeze);
        projects.add(lemon);
        projects.add(appleOrchard);
        projects.add(bananaBay);
        projects.add(mangoMeadows);
        

        
        
        // setting the pre-requisite
        officerDaniel.setRegisteredOfficer(true);
        officerDaniel.setRegistrationApproved(true);
        officerDaniel.setHandlingProject(acaciaBreeze);

        officerEmily.setRegisteredOfficer(true);
        officerEmily.setRegistrationApproved(true);
        officerEmily.setHandlingProject(appleOrchard);
        
        
        // Pre-set applicantJohn's application as SUCCESSFUL for Acacia Breeze.
//        Application appJohn = new Application(applicantJohn, acaciaBreeze, FlatType.TWO_ROOM, ApplicationStatus.SUCCESSFUL);
//        applicantJohn.setApplication(appJohn);
//        acaciaBreeze.addApplication(appJohn);
        
        
    }
    
    
    // printing menu header cus i need we need user NRIC and date
    // right now only applicants is using it
    // need to add it to officer and manager
    private static void printMenuHeader(User user) {
        System.out.println(PURPLE + "NRIC: " + user.getNRIC() + " | Date: " + LocalDate.now() + RESET);
    }
    
   
    

    /* ===============================
     * Just some basic login routine 
     * check NRIC format and password
     *================================
     */
    
    private static void login() {
        System.out.print("Enter NRIC (S/TxxxxxxxA-Z): ");
        String nric = scanner.nextLine();
        
        if (!User.validateNRIC(nric.toUpperCase())) {
            System.out.println(RED + "Invalid NRIC format. Must start with either S/T, followed by 7 digits, ending with a letter." + RESET);
            return;
        }
        
        // if is it correct...
        // ask password
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        
        // finding the right user.
        User loggedInUser = null;
        // check against applicants, officer, managers ( all users) 
        for (User u : users) {
            if (u.getNRIC().equalsIgnoreCase(nric) && u.getPassword().equals(password)) {
                loggedInUser = u;
                break;
            }
        }
        
        // wrong password 
        if (loggedInUser == null) {
            System.out.println(RED + "Login failed. Check NRIC or password." + RESET);
            return;
        }
        
        // unfortunately we have no name..., so NRIC ig
        System.out.println(CYAN + "\nLogin successful.\nWelcome, " + loggedInUser.getNRIC() + "!" + RESET);

        // link to the correct menu options
        if (loggedInUser instanceof HDBManager) {
            managerMenu((HDBManager) loggedInUser);
        } else if (loggedInUser instanceof HDBOfficer) {
            officerMenu((HDBOfficer) loggedInUser);
        } else if (loggedInUser instanceof Applicant) {
            applicantMenu((Applicant) loggedInUser);
        }
    }
    
    
    /* ===============================
     * Applicant Menu 
     *================================
     */
    
    private static void applicantMenu(Applicant user) {
        while (true) {
        	System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("         》   Applicant Menu   《         ");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            printMenuHeader(user);
            System.out.println("[1] ▶ View Available Projects");
            System.out.println("[2] ▶ Apply for a Project");
            System.out.println("[3] ▶ View Application Status");
            System.out.println("[4] ▶ Request Withdrawal");
            System.out.println("[5] ▶ Submit an Enquiry");
            System.out.println("[6] ▶ View/Edit/Delete Enquiries");
            System.out.println("[7] ▶ Change Password");
            System.out.println("[8] ▶ Logout");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
            
            
            case "1": // [1] ▶ View Available Projects
            	// checked code
                viewAvailableProjects(user);
                break;
                
            case "2": // [2] ▶ Apply for a Project"
            	// checked code
                applyForProject(user);
                break;
                
                
            case "3": // [3] ▶ View Application Status
            	// checked code
                user.viewApplicationStatus();
                break;
                
                
            case "4": // [4] ▶ Request Withdrawal
            	// checked code
                user.withdrawApplication();
                break;
                
                
            case "5": // [5] ▶ Submit an Enquiry
            	// checked code
                submitEnquiry(user);
                break;
                
                
            case "6": // [6] ▶ View/Edit/Delete Enquiries
            	// checked code 
                user.manageEnquiries();
                break;
                
               
            case "7": // [7] ▶ Change Password
            	// checked code
                changePassword(user);
                break;
               
            // damn it i need a animation for this
            case "8": // [8] ▶ Logout
            	System.out.print(CYAN);
            	technoAnimation("Logging out...");
            	System.out.print(RESET);
            	System.out.println();
            	 
                //System.out.println(CYAN+"Logging out...\n"+RESET);
                return;
                
                
            default:
                System.out.println(RED+"Invalid choice."+RESET);
	        }
	    }
	}
    
    
    
    /* ===============================
     * Officer MAIN Menu
     *================================
     */
    
    private static void officerMenu(HDBOfficer user) {
    	
        while (true) {
        	System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("         》   HDB Officer Menu   《         ");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            printMenuHeader(user);

            // Applicant  operations
            System.out.println("[1] ▶ View Available Projects");
            System.out.println("[2] ▶ Apply for a Project");
            System.out.println("[3] ▶ View Application Status");
            System.out.println("[4] ▶ Request Withdrawal");
            System.out.println("[5] ▶ Submit an Enquiry");
            System.out.println("[6] ▶ View/Edit/Delete Enquiries");
            
            System.out.println("[7] ▶ Register for a Project");
            System.out.println("[8] ▶ View Registration Status");
            System.out.println("[9] ▶ My Project");
            
            System.out.println("[10] ▶ Change Password");
            System.out.println("[11] ▶ Logout");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1": // [1] ▶ View Available Projects
                	// checked code
                    viewAvailableProjects(user);
                    break;
                    
                case "2": // [2] ▶ Apply for a Project
                	//checked code
                    applyForProject(user);
                    break;
                    
                case "3": // [3] ▶ View Application Status"
                	// checked code
                    user.viewApplicationStatus();
                    break;
                    
                    
                case "4": // [4] ▶ Request Withdrawal
                	// checked code
                	user.withdrawApplication();
                    break;
                    
                    
                case "5": // [5] ▶ Submit an Enquiry
                	// checked code
                    submitEnquiry(user);
                    break;
                    
                    
                case "6": // [6] ▶ View/Edit/Delete Enquiries
                	//  checked code
                	user.manageEnquiries();
                    break;
                    
                case "7": // "[7] ▶ Register for a Project"
                	// checked code
                    registerForProject(user);
                    break;
                    
                case "8": // [8] ▶ View Registration Status
                	// checked code
                    System.out.println("Registration Status: " + user.getRegistrationStatus());
                    if (user.getHandlingProject() != null) {
                        System.out.println("Handling Project: " + user.getHandlingProject().getProjectName());
                    }
                    break;
                    
                    
                case "9": // [9] ▶ My Project
                	// checked code
                    if (user.getHandlingProject() == null || !user.isRegistrationApproved()) {
                        System.out.println(RED+"You are not assigned to any project."+RESET);
                    } else {
                    	// give it to the sub project menu
                        myProjectSubMenu(user);
                    }
                    break;
                    
                    
                case "10": // [10] ▶ Change Password
                	// checked code
                    changePassword(user);
                    break;
                    
                case "11": // [11] ▶ Logout
                	System.out.print(CYAN);
                	technoAnimation("Logging out...");
                	System.out.print(RESET);
                	System.out.println();
                    return;
                    

                default:
                    System.out.println(RED+"Invalid choice."+RESET);

            }
        }
    }
    
    
    

    /* ===============================
     * Officer PROJECT SUB  Menu
     * only appear only if they have a approved project
     *================================
     */
    
    private static void myProjectSubMenu(HDBOfficer user) {
        while (true) {
            
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("         》   My Project Menu   《         ");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("[1] ▶ View All Applications");
            System.out.println("[2] ▶ Process Booking for an Applicant");
            System.out.println("[3] ▶ Reply to an Enquiry");
            System.out.println("[4] ▶ Generate Receipt for an Applicant");
            System.out.println("[5] ▶ Back to Main");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
            
	            case "1": // "[1] ▶ View All Applications"
	            	// checked code
	                user.viewAllApplications();
	                break;
	            
            
                case "2": // [2] ▶ Process Booking for an Applicant"
                	// checked code
                    System.out.print("Enter applicant NRIC to process booking: ");
                    String appNRIC = scanner.nextLine();
                    
                    user.processBooking(appNRIC);
                    break;
                    
                case "3": // [3] ▶ Reply to an Enquiry
                	// checked code
                    user.replyToEnquiry();
                    break;
                    
                    
                case "4": // [4] ▶ Generate Receipt for an Applicant
                	// checked code
                    user.generateReceipt();
                    break;
                
                case "5": // [5] ▶ Back to Main
                	// checked code
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    
    // ==============================
    // Manager Menu - MAIN , this guy need how many menus ah
    // ==============================
    
    private static void managerMenu(HDBManager user) {
        while (true) {            
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("         》   HDB Manager Menu   《         ");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            
            System.out.println("[1] ▶ Create Project"); // project management (1)
            System.out.println("[2] ▶ View All Projects (with filter)"); // project management (1)
            System.out.println("[3] ▶ View/Edit/Delete Projects");// project management (1)
            System.out.println("[4] ▶ Application/Registration Management"); // sub menu (2)
            System.out.println("[5] ▶ Generate Applicant Booking Report (with filter)"); // sub menu reporting (3)
            System.out.println("[6] ▶ Enquiry Management"); // sub menu enquiry (4)
            System.out.println("[7] ▶ Change Password");
            System.out.println("[8] ▶ Logout");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
            
                case "1": // [1] ▶ Create Project
                	// CJECKED CODE
                    createProject(user);
                    break;
                    
                    
                case "2":
                	// CHEAKED CODE
                	// [2] ▶ View All Projects 
                	viewAllProjectsWithFilter(user);
                    break;
                    
                    
                    
                case "3": //[3] ▶ View/Edit/Delete Projects
                	//checked code
                	manageProject(user);
                    break;
                    
                /// new menu
  
                case "4": // [4] ▶ Application/Registration Management"
                	applicationRegistrationManagementMenu(user);
                    break;
                    
                    
                    
            // [5] ▶ Generate Applicant Booking Report (with filter)
                	

                case "5": {
                    // 1) Fetch all applicants
                    List<Applicant> allApplicants = HDBManager.getApplicants();
                    if (allApplicants.isEmpty()) {
                        System.out.println(RED + "No applicants found." + RESET);
                        break;
                    }

                    // 2) Offer filter menu
                  //System.out.println("║▓▒░ FILTER PROJECTS ░▒▓║");
            		System.out.println();
            		System.out.println(BOLD);
            		System.out.println(MID);
                    System.out.println(">>   FILTER REPORTS   <<");
                    System.out.println("=============================");
                    System.out.println(RESET);
                    
                    
                    System.out.println("[1] No filter (all)");
                    System.out.println("[2] By marital status");
                    System.out.println("[3] By flat type");
                    System.out.println("[4] By project name");
                    System.out.println("[5] By application status");
                    System.out.print("Choose filter: ");
                    String filterChoice = scanner.nextLine().trim();

                    // 3) Copy into working list
                    List<Applicant> reportList = new ArrayList<>(allApplicants);

                    switch (filterChoice) {
                        case "2":
                            System.out.print("Enter marital status (Single/Married): ");
                            String ms = scanner.nextLine().trim();
                            reportList.removeIf(a -> !a.getMaritalStatus().equalsIgnoreCase(ms));
                            break;
                        case "3":
                            System.out.print("Enter flat type (TWO_ROOM/THREE_ROOM): ");
                            String ft = scanner.nextLine().trim().toUpperCase();
                            reportList.removeIf(a -> {
                                Application ap = a.getApplication();
                                return ap == null || !ap.getFlatType().name().equals(ft);
                            });
                            break;
                        case "4":
                            System.out.print("Enter project name: ");
                            String pn = scanner.nextLine().trim();
                            reportList.removeIf(a -> {
                                Application ap = a.getApplication();
                                return ap == null || !ap.getProject().getProjectName().equalsIgnoreCase(pn);
                            });
                            break;
                        case "5":
                            System.out.print("Enter application status (PENDING/SUCCESSFUL/etc.): ");
                            String st = scanner.nextLine().trim().toUpperCase();
                            reportList.removeIf(a -> {
                                Application ap = a.getApplication();
                                return ap == null || !ap.getStatus().name().equals(st);
                            });
                            break;
                        default:
                            // no filter
                    }

                    // 4) Print the report
                    System.out.println(CYAN + "\n---     Applicant Booking Report       ---" + RESET);
                    for (Applicant a : reportList) {
                        Application ap = a.getApplication();
                        if (ap != null) {
                            System.out.printf(
                                "Applicant: %s | Age: %d | Marital: %s | Flat: %s | Project: %s | Status: %s%n",
                                a.getNRIC(),
                                a.getAge(),
                                a.getMaritalStatus(),
                                ap.getFlatType(),
                                ap.getProject().getProjectName(),
                                ap.getStatus()
                            );
                        } else {
                            System.out.printf(
                                "Applicant: %s | Age: %d | Marital: %s | No booking found%n",
                                a.getNRIC(),
                                a.getAge(),
                                a.getMaritalStatus()
                            );
                        }
                    }
                    break;
                }


               
                    
                    
                case "6":
                    System.out.println("Enquiry Management");
                    handleEnquiryManagement(user);
                    break;

                case "7":
                    changePassword(user);
                    break;
                case "8":
                	System.out.print(CYAN);
                	technoAnimation("Logging out...");
                	System.out.print(RESET);
                	System.out.println();
                    //System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    /* ===============================
     * registerForProject()
     * the officer cannot REGISTER for the same project the officer APPLY for!
     * officer cannot register more than one project
     *================================
     */
    
    private static void registerForProject(HDBOfficer officer) {
    	
    	
        // 1) If already being registered, just do ahead and show details and return
        Project current = officer.getHandlingProject();
        if (officer.isRegisteredOfficer() && current != null) {
            System.out.println(RED + "You are already registered for a project:" + RESET);
           // hmmm 
            System.out.println("  " + current.toDisplayString(true));
            return;
        }

        // prompt for the project name to help register
        System.out.print("Enter project name to register as officer: ");
        String projectName = scanner.nextLine().trim();
        Project target = findProjectByName(projectName);

        if (target == null) {
            System.out.println(RED + "Project not found." + RESET);
            return;
        }

        // Preventing registering on a project that have been already applied to case 1 
        if (officer.getApplication() != null
         && officer.getApplication().getProject().equals(target)) {
            System.out.println(RED +
                "You have already applied for this project as an applicant. " +
                "You cannot register as an officer for it." +
                RESET);
            return;
        }

        // If everything is ok
        // mark officer registration as pending.
        officer.setRegisteredOfficer(true);
        officer.setRegistrationApproved(false);
        officer.setHandlingProject(target);
        System.out.println(CYAN + 
            "Officer registration submitted for \"" + 
            target.getProjectName() + 
            "\". Pending manager approval." + 
            RESET);
    }
    
    

    
    /* ===============================
     * viewAvailableProjects()
     * Available for applicant and Officer
     * 1. visible status can only been seen by officer and manager 
     * 2. single who can apply, cannot see 3 room details == good design 
     *================================
     */
    
    private static void viewAvailableProjects(User user) {
    	
    	
    	
        System.out.println(BOLD);
        System.out.println(">>   AVAILABLE PROJECTS   <<");
        System.out.println("=============================");
        System.out.println(RESET);
        
        boolean found = false;
        
        if (user instanceof HDBOfficer || user instanceof HDBManager) {

            for (Project p : projects) {
                System.out.println(user.formatProjectDisplay(p));
                found = true;
            }
        } 
        
        
        else { 

        	
            List<Project> availableProjects = new ArrayList<>();
            List<Project> fullyBookedProjects = new ArrayList<>();
            
            for (Project p : projects) {
                if (p.isVisible()) {
                    if (user.getMaritalStatus().equalsIgnoreCase("Single")) {
                   
                        if (p.getFlatUnitCount(FlatType.TWO_ROOM) > 0) {
                            availableProjects.add(p);
                        } else {
                            fullyBookedProjects.add(p);
                        }
                    } else if (user.getMaritalStatus().equalsIgnoreCase("Married")) {
                       
                        if (p.getFlatUnitCount(FlatType.TWO_ROOM) > 0 || p.getFlatUnitCount(FlatType.THREE_ROOM) > 0) {
                            availableProjects.add(p);
                        } else {
                            fullyBookedProjects.add(p);
                        }
                    }
                }
            }
            
            // Print available projects first lol
            if (!availableProjects.isEmpty()) {
                System.out.println("Available Projects:");
                for (Project p : availableProjects) {
                    System.out.println(user.formatProjectDisplay(p));
                    found = true;
                }
            }
            
            // Then only print fully booked projects.
            if (!fullyBookedProjects.isEmpty()) {
                System.out.println(CYAN + "--- FULLY BOOKED ---" + RESET);
                for (Project p : fullyBookedProjects) {
                    System.out.println(user.formatProjectDisplay(p));
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println(RED + "No available projects matching your criteria." + RESET);
        }
        
        /// UPDATE NEW VERSION
        /// LETS DO FILTERING 
       
        System.out.print("Would you like to apply filter settings? (Y/N): ");
        String answer = scanner.nextLine().trim();
        if (answer.equalsIgnoreCase("Y")) {
            filterProjects(user, savedProjectSearchCriteria);
        }
    }


    
    /* ===============================
     * applyForProject()
     * 1. check if they can even apply.
     * 2. Auto book 2 room for single
     *================================
     */
    
    
    private static void applyForProject(Applicant user) {
    	
    	if((user.getAge() >= 35 && user.getMaritalStatus() == "Single") || (user.getAge() >= 21 && user.getMaritalStatus() == "Married")) {
    		
	        System.out.print("Enter project name to apply for: ");
	        String projectName = scanner.nextLine();
	        
	        
	        Project target = findProjectByName(projectName);
	        
	        // officer cannot apply for a project when it is not visible right. 
	        // should i split it?
	        if (target == null || !target.isVisible()) {
	            System.out.println(RED + "Project not found or not visible." + RESET);
	            return;
	        }
	        
	        // If the user is an officer, ensure better not registered for the same project.
	        if (user instanceof HDBOfficer) {
	            HDBOfficer officer = (HDBOfficer) user;
	            if (officer.getHandlingProject() != null && officer.getHandlingProject().equals(target)) {
	                System.out.println(RED + "You are already registered as an officer for this project and cannot apply as an applicant." + RESET);
	                return;
	            }
	        }
	        
	        // cannot apply for more than one
	        Application app = user.getApplication();
	        if (app != null) {
	            ApplicationStatus s = app.getStatus();
	            if (s == ApplicationStatus.PENDING
	             || s == ApplicationStatus.SUCCESSFUL
	             || s == ApplicationStatus.BOOKED
	             || s == ApplicationStatus.WITHDRAWAL_REQUESTED) {
	                System.out.println(RED + "You have already applied for a project." + RESET);
	                return;
	            }
	            // otherwise (UNSUCCESSFUL or WITHDRAWN) we let them apply again
	        }
	        
	        
	        // another factor to consider is that singles cannot have the option to choose three room. 
	        FlatType flatType;
	        
	        // For Single applicants aged 35 or above, auto-book as TWO_ROOM.
	        if (user.getMaritalStatus().equalsIgnoreCase("Single") && user.getAge() >= 35) {
	            
	            
	            // making sure user can book when there are slots
	            if(target.getFlatUnitCount(FlatType.TWO_ROOM) == 0){
	            	System.out.println(RED+ " All of the two room have been fully booked for this project" + RESET);
	            	return;
	            }
	            
	            flatType = FlatType.TWO_ROOM;
	            System.out.println(CYAN + "As a single applicant aged 35 or above, you are automatically booked for a 2-Room flat." + RESET);
	            
	        } else if (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21) {
	        	
	        	// now we will give them a choice
	        	System.out.println("Select a flat type to apply for:");
	            System.out.println("[1] Two Room");
	            System.out.println("[2] Three Room");
	            System.out.println("[3] Exit");
	            System.out.print("Enter your choice: ");
	            
	            int choice;
	            
	            // validation 
	            try {
	                choice = Integer.parseInt(scanner.nextLine());
	            } catch (NumberFormatException e) {
	                System.out.println(RED+ "Invalid input. Please enter a number."+ RESET);
	                return;
	            }
	
	            switch (choice) {
	                case 1:
	                	// making sure user can book when there are slots
	                	if (target.getFlatUnitCount(FlatType.TWO_ROOM) == 0) {
	                		System.out.println(RED+ " All of the two room have been fully booked for this project" + RESET);
	                		return;
	                	}
	                	flatType = FlatType.TWO_ROOM;
	           
	                    break;
	                    
	                case 2:
	                	if (target.getFlatUnitCount(FlatType.THREE_ROOM) == 0) {
	                		System.out.println(RED+ " All of the three room have been fully booked for this project" + RESET);
	                		return;
	                	}
	                	flatType = FlatType.THREE_ROOM;
	                    
	                    break;
	                    
	                case 3:
	                    System.out.println(RED + "Exiting application process." + RESET);
	                    return;
	                    
	                default:
	                    System.out.println(RED + "Invalid choice." + RESET);
	                    return;
	            }
	        } else {
	            System.out.println(RED + "You do not meet the age requirements to apply for a project." + RESET);
	            return;
	        }
	        
	        Application newApp = new Application(user, target, flatType, ApplicationStatus.PENDING);
	        // UPDATE 
	        user.setApplication(newApp);
	        target.addApplication(newApp);
	        System.out.println("Application submitted successfully (Status: PENDING).");
	        System.out.println("\n=== Your BTO PROJECT Application ===");
	        System.out.println("  Project:      " + target.getProjectName());
	        System.out.println("  Neighborhood: " + target.getNeighborhood());
	        System.out.println("  Flat Type:    " + flatType);
	        System.out.println("=============================\n");
	        System.out.println();
	    }
    	
    	else
    	{
    		System.out.println(RED + "Sorry currently you are not eligible to apply for a project" + RESET);
    	}
    } 

    
    
    
    /* ===============================
     * submitEnquiry()
     * get the text (enq)
     * add into the list
     * No test validation needed.
     * DO I NEED TIMESTAMP! HELP
     *================================
     */
    
    private static void submitEnquiry(Applicant user) {
    	// get the string and add into the list
        System.out.print("Enter enquiry text: ");
        String text = scanner.nextLine();
        
     // Get current timestamp and format it
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        
        String textT = text + " (Submitted at: " + timestamp + ")";
        
        Enquiry e = new Enquiry(textT);
        user.addEnq(e);
        System.out.println("Enquiry submitted.");
    }
    
    

    
    // should it be a searable interface?
    private static Project findProjectByName(String name) {
        for (Project p : projects) {
            if (p.getProjectName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    
    
    /* ===============================
     * changePassword()
     * haha get them to enter current password 
     * call setpassword() in user, universal 
     *================================
     */
    
    private static void changePassword(User user) {
    	
        System.out.print("Enter current password: ");
        String current = scanner.nextLine();
        
        if (!user.getPassword().equals(current)) {
            System.out.println(RED+"Incorrect current password."+RESET);
            return;
        }
        
        System.out.print("Enter new password: ");
        String newPass = scanner.nextLine();
        user.setPassword(newPass);
        System.out.println(CYAN+"Password changed successfully."+RESET);
    }
    
    
    /* ===============================
     * createProject() 
     * Create projects
     *================================
     */
    
    private static void createProject(HDBManager manager) {
    	
    // validation 
    	DateTimeFormatter time = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate current = LocalDate.now();
        for (Project p : projects) {
            if (p.getManager().equals(manager)) {
                LocalDate start = LocalDate.parse(p.getApplicationOpeningDate(), time);
                LocalDate end   = LocalDate.parse(p.getApplicationClosingDate(), time);
                // if today is inside [start, end]
                if (!current.isBefore(start) && !current.isAfter(end)) {
                    System.out.println(RED +
                        "You already have an active project \"" + p.getProjectName() +
                        "\" (applications open " + start.format(time) +
                        " to " + end.format(time) + ").\n" +
                        "Cannot create a new one until its application period ends." +
                        RESET);
                    return;
                }
            }
        }
        
        
        String projectName;
        
        
        // 1. Project name
        /// it have be unique
        // cannot take empty also, not null
        while (true) {
            System.out.print("Enter project name: ");
            projectName = scanner.nextLine().trim();
            if (projectName.isEmpty()) {
                System.out.println(RED + "Project name cannot be empty." + RESET);
                continue;
            }
            boolean exists = false;
            for (Project p : projects) {
                if (p.getProjectName().equalsIgnoreCase(projectName)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                System.out.println(RED + "Error: A project with that name already exists." + RESET);
            } else {
                break;
            }
        }

        // 2. Neighborhood (cant be null)
        String neighborhood;
        while (true) {
            System.out.print("Enter neighborhood (e.g., Yishun, Boon Lay): ");
            neighborhood = scanner.nextLine().trim();
            if (neighborhood.isEmpty()) {
                System.out.println(RED + "Neighborhood cannot be empty." + RESET);
            } else {
                break;
            }
        }

        // 3. 2-Room units
        // what if it is negative also
        // what if it is over a threshold?
        // what if it is a single number
        int twoRoomUnits;
        while (true) {
            System.out.print("Enter Number of 2-Room units: ");
            String line = scanner.nextLine().trim();
            try {
                twoRoomUnits = Integer.parseInt(line);
                if (twoRoomUnits < 0) {
                    System.out.println(RED + "Value must be non negative." + RESET);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid number for 2-Room units." + RESET);
            }
        }

        // 4. 3-Room units
        int threeRoomUnits;
        while (true) {
            System.out.print("Enter Number of 3-Room units: ");
            String line = scanner.nextLine().trim();
            try {
                threeRoomUnits = Integer.parseInt(line);
                if (threeRoomUnits < 0) {
                    System.out.println(RED + "Value must be non negative." + RESET);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid number for 3-Room units." + RESET);
            }
        }

        // 5. 2-Room price
        int twoRoomPrice;
        while (true) {
            System.out.print("Enter 2-Room price: ");
            String line = scanner.nextLine().trim();
            try {
                twoRoomPrice = Integer.parseInt(line);
                if (twoRoomPrice < 0) {
                    System.out.println(RED + "Value must be non negative." + RESET);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid number for 2-Room price." + RESET);
            }
        }

        // 6. 3-Room price
        int threeRoomPrice;
        while (true) {
            System.out.print("Enter 3-Room price: ");
            String line = scanner.nextLine().trim();
            try {
                threeRoomPrice = Integer.parseInt(line);
                if (threeRoomPrice < 0) {
                    System.out.println(RED + "Value must be non negative." + RESET);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid number for 3-Room price." + RESET);
            }
        }

        // 7. Officer slots
        int slots;
        while (true) {
            System.out.print("Enter available officer slots: ");
            String line = scanner.nextLine().trim();
            try {
                slots = Integer.parseInt(line);
                if (slots < 0) {
                    System.out.println(RED + "Value must be non negative." + RESET);
                    continue;
                }
                
                if (slots > 10) {
                    System.out.println(RED + "Slot cannot be more than 10" + RESET);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid number for officer slots." + RESET);
            }
        }

        // 8. Dates
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate today = LocalDate.now();

        LocalDate openDate;
        while (true) {
            System.out.print("Enter application opening date (d/M/yyyy): ");
            String s = scanner.nextLine().trim();
            try {
                openDate = LocalDate.parse(s, dtf);
                if (openDate.isBefore(today)) {
                    System.out.println(RED + "Opening date must be today or in the future." + RESET);
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println(RED + "Invalid format. Please use d/M/yyyy." + RESET);
            }
        }

        LocalDate closeDate;
        while (true) {
            System.out.print("Enter application closing date (d/M/yyyy): ");
            String s = scanner.nextLine().trim();
            try {
                closeDate = LocalDate.parse(s, dtf);
                if (!closeDate.isAfter(openDate)) {
                    System.out.println(RED + "Closing date must be after opening date." + RESET);
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println(RED + "Invalid format. Please use d/M/yyyy." + RESET);
            }
        }
        
        
        boolean visible;
        while (true) {
            System.out.print("Should the project be visible? (true/false): ");
            String v = scanner.nextLine().trim();
            if (v.equalsIgnoreCase("true")) {
                visible = true;
                break;
            } else if (v.equalsIgnoreCase("false")) {
                visible = false;
                break;
            } else {
                System.out.println(RED + "Please enter 'true' or 'false'." + RESET);
            }
        }
        

        // 9. Final confirmation
        System.out.println("\nPlease confirm the project details:");
        
        System.out.println("  Name:           " + projectName);
        System.out.println("  Neighborhood:   " + neighborhood);
        System.out.println("  2-Room units:   " + twoRoomUnits  + "    " +  twoRoomPrice);
        System.out.println("  3-Room units:   " + threeRoomUnits  + "    " + threeRoomPrice);
        System.out.println("  Opens:          " + openDate.format(dtf));
        System.out.println("  Closes:         " + closeDate.format(dtf));
        System.out.println("  Officer slots:  " + slots);
        System.out.println("  Visible:        " + visible);
        System.out.print("Create this project? (Y/N): ");

        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("Y")) {
            manager.createProject(
                projects,
                projectName,
                neighborhood,
                twoRoomUnits,
                threeRoomUnits,
                twoRoomPrice,
                threeRoomPrice,
                openDate.format(dtf),
                closeDate.format(dtf),
                visible,   
                slots
            );
            // System.out.println(CYAN + "Project created successfully." + RESET);
        } else {
            System.out.println(RED + "Project creation cancelled." + RESET);
        }
    }




    
    /* ===============================
     * manageProject()
     * the manager can view/edit and delete projects
     *================================
     */
    
    private static void manageProject(HDBManager manager) {
    	
    	System.out.println("\n--- All Projects ---");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + projects.get(i).toDisplayString(true));
        }
        System.out.println("[E] Exit");
        System.out.print("Select a project by number: ");
        String input = scanner.nextLine();
        
        if (input.equalsIgnoreCase("E"))
            return;
        
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid input." + RESET);
            return;
        }
        
        
        if (index < 1 || index > projects.size()) {
            System.out.println(RED + "Invalid selection." + RESET);
            return;
        }
        
        Project selectedProject = projects.get(index - 1);
        while (true) {
        	
        	
            System.out.println("\n--- Options for " + selectedProject.getProjectName() + " ---");
            System.out.println("[1] ▶ Edit Project");
            System.out.println("[2] ▶ Delete Project");
            System.out.println("[3] ▶ Toggle Visibility");
            System.out.println("[4] ▶ Back");
            System.out.print("Enter your choice: ");
            String opt = scanner.nextLine();
            switch (opt) {
            
            
                case "1": // [1] ▶ Edit Project
                	// checked code
                	// i dont add the toggle here
                	editProjectDetails(manager, selectedProject);
                	break;
 
                case "2": // [2] ▶ Delete Project
                	// checked code
                    manager.deleteProject(projects, selectedProject);
                    break;
                    
                case "3": // ▶ Toggle Visibility
                    System.out.println(PURPLE + "Current Visibility: " + selectedProject.isVisible() + RESET);
                    System.out.print("Enter new Visibility (true/false, or press Enter to keep current): ");
                    String newVis = scanner.nextLine().trim();
                    if (!newVis.isEmpty()) {
                        boolean desiredVis;
                        try {
                            desiredVis = Boolean.parseBoolean(newVis);
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input for visibility." + RESET);
                            break;
                        }

                        // Only enforce the “one active project” rule when turning ON
                        if (desiredVis) {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
                            LocalDate today = LocalDate.now();
                            boolean conflict = false;

                            for (Project other : projects) {
                                if (other.getManager().equals(manager)
                                    && other != selectedProject
                                    && other.isVisible()) 
                                {
                                    LocalDate start = LocalDate.parse(other.getApplicationOpeningDate(), dtf);
                                    LocalDate end   = LocalDate.parse(other.getApplicationClosingDate(), dtf);
                              
                                    if (!today.isBefore(start) && !today.isAfter(end)) {
                                        System.out.println(RED +
                                            "Cannot make \"" + selectedProject.getProjectName() +
                                            "\" visible: This will result in a conflict.\""  + RESET);
                                        conflict = true;
                                        break;
                                    }
                                }
                            }

                            if (conflict) {
                                break;
                            }
                        }

                        // No conflict (or turning OFF), so apply the change
                        selectedProject.setVisible(desiredVis);
                        System.out.println("Visibility updated to: " + desiredVis);
                    } else {
                        System.out.println("Visibility remains unchanged.");
                    }
                    break;

                    
                    
                case "4": // [4] ▶ Back
                	// checked code
                    return;
                    
                default:
                    System.out.println(RED+"Invalid choice."+RESET);
            }
        }
    }
    
    
    /* ===============================
     * editProjectDetails()
     * the manager can edit all the fields of the projects.
     * even if the project name changes, it will nto affect as the referance will remain the same
     *================================
     */
    
    private static void editProjectDetails(HDBManager manager, Project project) {
    	
        System.out.println("\n>>> Editing Project Details <<<");

        // Edit Project Name
        System.out.println(PURPLE + "Current Project Name: " + project.getProjectName() + RESET);
        System.out.print("Enter new project name (or press Enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            project.setProjectName(newName);
            System.out.println("Project name updated to: " + newName);
        }

        // Edit Neighborhood
        System.out.println(PURPLE + "Current Neighborhood: " + project.getNeighborhood() + RESET);
        System.out.print("Enter new neighborhood (or press Enter to keep current): ");
        String newNeighborhood = scanner.nextLine().trim();
        if (!newNeighborhood.isEmpty()) {
            project.setNeighborhood(newNeighborhood);
            System.out.println("Neighborhood updated to: " + newNeighborhood);
        }

        // Edit 2-Room Units
        System.out.println(PURPLE + "Current 2-Room Units: " + project.getFlatUnitCount(FlatType.TWO_ROOM) + RESET);
        System.out.print("Enter new number of 2-Room units (or press Enter to keep current): ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                int newTwoUnits = Integer.parseInt(input);
                project.setFlatUnitCount(FlatType.TWO_ROOM, newTwoUnits);
                System.out.println("2-Room units updated to: " + newTwoUnits);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for 2-Room units. Value not changed.");
            }
        }

        // Edit 3-Room Units
        System.out.println(PURPLE + "Current 3-Room Units: " + project.getFlatUnitCount(FlatType.THREE_ROOM) + RESET);
        System.out.print("Enter new number of 3-Room units (or press Enter to keep current): ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                int newThreeUnits = Integer.parseInt(input);
                project.setFlatUnitCount(FlatType.THREE_ROOM, newThreeUnits);
                System.out.println("3-Room units updated to: " + newThreeUnits);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for 3-Room units. Value not changed.");
            }
        }

        // Edit 2-Room Price
        System.out.println(PURPLE + "Current 2-Room Price: " + project.getFlatPrice(FlatType.TWO_ROOM) + RESET);
        System.out.print("Enter new 2-Room price (or press Enter to keep current): ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                int newTwoPrice = Integer.parseInt(input);
                project.setFlatPrice(FlatType.TWO_ROOM, newTwoPrice);
                System.out.println("2-Room price updated to: " + newTwoPrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for 2-Room price. Value not changed.");
            }
        }

        // Edit 3-Room Price
        System.out.println(PURPLE + "Current 3-Room Price: " + project.getFlatPrice(FlatType.THREE_ROOM) + RESET);
        System.out.print("Enter new 3-Room price (or press Enter to keep current): ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                int newThreePrice = Integer.parseInt(input);
                project.setFlatPrice(FlatType.THREE_ROOM, newThreePrice);
                System.out.println("3-Room price updated to: " + newThreePrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for 3-Room price. Value not changed.");
            }
        }
        
        // Edit Application Opening Date
        // need to add validation for the date
        System.out.println(PURPLE + "Current Application Opening Date: " + project.getApplicationOpeningDate() + RESET);
        System.out.print("Enter new Application Opening Date (or press Enter to keep current): ");
        String newOpenDate = scanner.nextLine().trim();
        if (!newOpenDate.isEmpty()) {
            project.setApplicationOpeningDate(newOpenDate);
            System.out.println("Application Opening Date updated to: " + newOpenDate);
        }
       
        

        // Edit Application Closing Date
        System.out.println(PURPLE + "Current Application Closing Date: " + project.getApplicationClosingDate() + RESET);
        System.out.print("Enter new Application Closing Date (or press Enter to keep current): ");
        String newCloseDate = scanner.nextLine().trim();
        if (!newCloseDate.isEmpty()) {
            project.setApplicationClosingDate(newCloseDate);
            System.out.println("Application Closing Date updated to: " + newCloseDate);
        }
        
        
        
        // Edit Available Officer Slots
        System.out.println(PURPLE + "Current Available Officer Slots: " + project.getAvailableOfficerSlots() + RESET);
        System.out.print("Enter new Available Officer Slots (or press Enter to keep current): ");
        String newSlotsStr = scanner.nextLine().trim();
        if (!newSlotsStr.isEmpty()) {
            try {
                int newSlots = Integer.parseInt(newSlotsStr);
                project.setAvailableOfficerSlots(newSlots);
                System.out.println("Available Officer Slots updated to: " + newSlots);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for officer slots.");
            }
        }
        
    }
    
    
    
    /* ===============================
     * viewAllProjectsWithFilter()
     * it shows ALL of the projects
     * shows the filter
     *================================
     */
    
    private static void viewAllProjectsWithFilter(HDBManager manager) {
        // PRINTS ALL THE PROEJCTS
        System.out.println("\n--- All Projects ---");
        for (Project p : projects) {
            System.out.println(p.toDisplayString(true));
        }
        
        // Ask if the manager wants to filter the list to ONLY their own projects.
        System.out.print("Would you like to filter and view only the projects you have created? (Y/N): ");
        String response = scanner.nextLine().trim();
        if (response.equalsIgnoreCase("Y")) {
            System.out.println("\n--- Your Projects ---");
            boolean found = false;
            for (Project p : projects) {
                if (p.getManager().equals(manager)) {
                    System.out.println(p.toDisplayString(true));
                    found = true;
                }
            }
            if (!found) {
                System.out.println(RED+"You have not created any projects."+RESET);
            }
        }
        
        /// UPDATE NEW VERSION
        /// LETS DO FILTERING 
       
        System.out.print("Would you like to apply filter settings? (Y/N): ");
        String answer = scanner.nextLine().trim();
        if (answer.equalsIgnoreCase("Y")) {
            filterProjects(manager, savedProjectSearchCriteria);
        }
    }


    private static void viewAllProjects() {
        System.out.println("\n--- All Projects ---");
        for (Project p : projects) {
            System.out.println(p);
        }
    }
    
    
    private static void applicationRegistrationManagementMenu(HDBManager manager) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
        	
        	System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("   》  Application/Registration Management Menu  《   ");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            
            System.out.println("\n--- Application/Registration Management Menu ---");
            System.out.println("[1] ▶ View Pending/Approved Officer Registrations");
            System.out.println("[2] ▶ Approve/Reject Officer Registration");
            System.out.println("[3] ▶ Approve/Reject Applicant BTO Applications");
            System.out.println("[4] ▶ Approve/Reject Withdrawal Requests");
            System.out.println("[5] ▶ Back");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {

                case "1":
                    manager.viewOfficerRegistrations(projects, users);
                    break;

                case "2": {
                    // 1) Find all projects this manager owns
                    List<Project> myProjects = new ArrayList<>();
                    for (Project p : projects) {
                        if (p.getManager().equals(manager)) {
                            myProjects.add(p);
                        }
                    }
                    if (myProjects.isEmpty()) {
                        System.out.println(RED + "You have no projects to manage." + RESET);
                        break;
                    }

                    // 2) Letting the manager pick one of their projects
                    System.out.println("\nYour projects:");
                    for (int i = 0; i < myProjects.size(); i++) {
                        System.out.printf("  [%d] %s%n", i + 1, myProjects.get(i).getProjectName());
                    }
                    System.out.print("Select project number (or E to cancel): ");
                    String projInput = scanner.nextLine().trim();
                    if (projInput.equalsIgnoreCase("E")) break;

                    int projIdx;
                    try {
                        projIdx = Integer.parseInt(projInput) - 1;
                        if (projIdx < 0 || projIdx >= myProjects.size()) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid project selection." + RESET);
                        break;
                    }
                    Project project = myProjects.get(projIdx);

                    // 3) Split officers into approved vs pending for that project
                    List<HDBOfficer> approved = new ArrayList<>();
                    List<HDBOfficer> pending  = new ArrayList<>();
                    for (User u : users) {
                        if (u instanceof HDBOfficer) {
                            HDBOfficer off = (HDBOfficer) u;
                            if (project.equals(off.getHandlingProject())) {
                                if (off.isRegistrationApproved()) approved.add(off);
                                else                           pending.add(off);
                            }
                        }
                    }

                    // 4) Show approved officers
                    System.out.println(CYAN + "\nAlready approved officers:" + RESET);
                    if (approved.isEmpty()) {
                        System.out.println("  (none)");
                    } else {
                        for (HDBOfficer off : approved) {
                            System.out.println("  • " + off.getNRIC());
                        }
                    }

                    // 5) Show pending registrations
                    System.out.println(CYAN + "\nPending officer registrations:" + RESET);
                    if (pending.isEmpty()) {
                        System.out.println("  (no pending requests)");
                        break;
                    }
                    for (int i = 0; i < pending.size(); i++) {
                        System.out.printf("  [%d] %s%n", i + 1, pending.get(i).getNRIC());
                    }
                    System.out.print("Enter officer number to process (or E to cancel): ");
                    String offInput = scanner.nextLine().trim();
                    if (offInput.equalsIgnoreCase("E")) break;

                    int offIdx;
                    try {
                        offIdx = Integer.parseInt(offInput) - 1;
                        if (offIdx < 0 || offIdx >= pending.size()) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid officer selection." + RESET);
                        break;
                    }
                    
                    
                    HDBOfficer officer = pending.get(offIdx);

                    // 6) Approve or reject
                    System.out.print("Approve (A) or Reject (R)? ");
                    String decision = scanner.nextLine().trim().toUpperCase();
                    boolean approve = decision.equals("A");
                    manager.handleOfficerRegistration(project, officer, approve);

                    break;
                }


                // Case 3:
                case "3": {
                    // 1) Find all projects this manager owns
                    List<Project> myProjects = new ArrayList<>();
                    for (Project p : projects) {
                        if (p.getManager().equals(manager)) {
                            myProjects.add(p);
                        }
                    }
                    if (myProjects.isEmpty()) {
                        System.out.println(RED + "You have no projects to manage." + RESET);
                        break;
                    }

                    // 2) Let the manager pick one
                    System.out.println("\nYour projects:");
                    for (int i = 0; i < myProjects.size(); i++) {
                        System.out.printf("  [%d] %s%n", i + 1, myProjects.get(i).getProjectName());
                    }
                    System.out.print("Enter project number (or E to cancel): ");
                    String projInput = scanner.nextLine().trim();
                    if (projInput.equalsIgnoreCase("E")) break;
                    int projIdx;
                    try {
                        projIdx = Integer.parseInt(projInput) - 1;
                        if (projIdx < 0 || projIdx >= myProjects.size()) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid selection." + RESET);
                        break;
                    }
                    Project project = myProjects.get(projIdx);

                    // 3) Collect pending applications
                    List<Application> pendingApps = new ArrayList<>();
                    for (Application app : project.getApplications()) {
                        if (app.getStatus() == ApplicationStatus.PENDING) {
                            pendingApps.add(app);
                        }
                    }
                    if (pendingApps.isEmpty()) {
                        System.out.println(RED + "No pending applications for \""
                                           + project.getProjectName() + "\"." + RESET);
                        break;
                    }

                    // 4) Display pending applications
                    System.out.println("\nPending BTO applications:");
                    for (int i = 0; i < pendingApps.size(); i++) {
                        Application a = pendingApps.get(i);
                        System.out.printf("  [%d] %s | Flat: %s%n",
                                          i + 1,
                                          a.getApplicant().getNRIC(),
                                          a.getFlatType());
                    }
                    System.out.print("Select application number (or E to cancel): ");
                    String appInput = scanner.nextLine().trim();
                    if (appInput.equalsIgnoreCase("E")) break;
                    int appIdx;
                    try {
                        appIdx = Integer.parseInt(appInput) - 1;
                        if (appIdx < 0 || appIdx >= pendingApps.size()) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid selection." + RESET);
                        break;
                    }
                    String applicantNRIC = pendingApps.get(appIdx).getApplicant().getNRIC();

                    // 5) Approve or reject
                    System.out.print("Approve (A) or Reject (R)? ");
                    String decision = scanner.nextLine().trim().toUpperCase();
                    boolean approve = decision.equals("A");

                    // 6) Delegate to your existing logic (sets SUCCESSFUL or UNSUCCESSFUL) :contentReference[oaicite:2]{index=2}&#8203;:contentReference[oaicite:3]{index=3}
                    manager.processBTOApplication(project, applicantNRIC, approve);
                    break;
                }


                // Case 4: 
                /// this might have issue
                /// need to print all the withdrawal request before asking for NRIC...
                /// check later ok
                case "4": {
                    // 1) Collect all withdrawal‐requested applications under this manager
                    List<Application> withdrawRequests = new ArrayList<>();
                    for (Project p : projects) {
                        if (p.getManager().equals(manager)) {
                            for (Application app : p.getApplications()) {
                                if (app.getStatus() == ApplicationStatus.WITHDRAWAL_REQUESTED) {
                                    withdrawRequests.add(app);
                                }
                            }
                        }
                    }
                    if (withdrawRequests.isEmpty()) {
                        System.out.println(RED + "No withdrawal requests found for your projects." + RESET);
                        break;
                    }

                    // 2) Display them
                    System.out.println("\nPending withdrawal requests:");
                    for (int i = 0; i < withdrawRequests.size(); i++) {
                        Application app = withdrawRequests.get(i);
                        System.out.printf("  [%d] %s | Project: %s | Flat: %s%n",
                            i + 1,
                            app.getApplicant().getNRIC(),
                            app.getProject().getProjectName(),
                            app.getFlatType());
                    }

                    // 3) Pick one (or cancel)
                    System.out.print("Enter request number to process (or E to cancel): ");
                    String sel = scanner.nextLine().trim();
                    if (sel.equalsIgnoreCase("E")) break;
                    int idx;
                    try {
                        idx = Integer.parseInt(sel) - 1;
                        if (idx < 0 || idx >= withdrawRequests.size()) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid selection." + RESET);
                        break;
                    }
                    String applicantNRIC = withdrawRequests.get(idx).getApplicant().getNRIC();

                    // 4) Approve or reject
                    System.out.print("Approve withdrawal (A) or Reject (R)? ");
                    String decision = scanner.nextLine().trim().toUpperCase();
                    boolean approve = decision.equals("A");

                    // 5) Delegate to your existing handler :contentReference[oaicite:0]{index=0}&#8203;:contentReference[oaicite:1]{index=1}
                    manager.handleWithdrawalRequests(projects, applicantNRIC, approve);
                    break;
                }


                // Case 5
                case "5":
                    return;

                default:
                    System.out.println(RED + "Invalid choice. Please enter [1-5]." + RESET);
            }
        }
    }
    
    
    
    public static Project findProjectByName(List<Project> projects, String name) {
        for (Project p : projects) {
            if (p.getProjectName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    //NRIC Officer
    public static HDBOfficer findOfficerByNRIC(List<User> users, String nric) {
        for (User user : users) {
            if (user instanceof HDBOfficer && user.getNRIC().equals(nric)) {
                return (HDBOfficer) user;
            }
        }
        return null;
    }
    
    /* ===============================
     * Application/Registration Management Menu
     * 
     *================================
     */

//    private static void applicationRegistrationManagementMenu(HDBManager manager) {
//        while (true) {
//        	
//        	
//            System.out.println("\n--- Application/Registration Management Menu ---");
//            System.out.println("[1] ▶ View Pending/Approved Officer Registrations");
//            System.out.println("[2] ▶ Approve/Reject Officer Registration");
//            System.out.println("[3] ▶ Approve/Reject Applicant BTO Applications");
//            System.out.println("[4] ▶ Approve/Reject Withdrawal Requests");
//            System.out.println("[5] ▶ Back");
//            System.out.print("Enter your choice: ");
//            
//            
//            String choice = scanner.nextLine();
//            switch (choice) {
//            
//            
//                case "1":
//                    // Display officer registrations for projects managed by this manager.
//                    for (Project p : projects) {
//                        if (p.getManager().equals(manager)) {
//                            System.out.println("Project: " + p.getProjectName());
//                            // For simplicity, iterate over all users and list officers registered for this project.
//                            for (User u : users) {
//                                if (u instanceof HDBOfficer) {
//                                    HDBOfficer off = (HDBOfficer) u;
//                                    if (off.isRegisteredOfficer() && off.getHandlingProject() != null &&
//                                            off.getHandlingProject().equals(p)) {
//                                        System.out.println("   Officer NRIC: " + off.getNRIC() + " | Status: " + off.getRegistrationStatus());
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    break;
//                    
//                    
//                    
//                case "2":
//                	
//                	
//                    System.out.print("Enter project name: ");
//                    String projName = scanner.nextLine();
//                    Project proj = findProjectByName(projName);
//                    
//                    // dont need to ask for the project. its only filter by project manager projects
//                    
//                    if (proj != null && proj.getManager().equals(manager)) {
//                        System.out.print("Enter officer NRIC to approve/reject: ");
//                        String officerNRIC = scanner.nextLine();
//                        HDBOfficer officer = findOfficerByNRIC(officerNRIC);
//                        if (officer != null) {
//                            System.out.print("Approve (A) or Reject (R)?: ");
//                            String decision = scanner.nextLine();
//                            if (decision.equalsIgnoreCase("A")) {
//                                manager.approveOfficerRegistration(officer, proj);
//                            } else if (decision.equalsIgnoreCase("R")) {
//                                officer.setRegisteredOfficer(false);
//                                officer.setRegistrationApproved(false);
//                                officer.setHandlingProject(null);
//                                System.out.println("Officer registration rejected.");
//                            } else {
//                                System.out.println("Invalid decision.");
//                            }
//                        } else {
//                            System.out.println("Officer not found.");
//                        }
//                    }
//                    break;
//                    
//                    
//                    
//                    
//                case "3":
//                    System.out.print("Enter project name: ");
//                    String projNameApp = scanner.nextLine();
//                    Project projApp = findProjectByName(projNameApp);
//                    if (projApp != null && projApp.getManager().equals(manager)) {
//                        System.out.print("Enter applicant NRIC to approve/reject application: ");
//                        String applicantNRIC = scanner.nextLine();
//                        Application app = findApplicationByNRIC(projApp, applicantNRIC);
//                        if (app != null) {
//                            System.out.print("Approve (A) or Reject (R)?: ");
//                            String decision = scanner.nextLine();
//                            if (decision.equalsIgnoreCase("A")) {
//                                manager.approveApplication(app);
//                            } else if (decision.equalsIgnoreCase("R")) {
//                                manager.rejectApplication(app);
//                            } else {
//                                System.out.println("Invalid decision.");
//                            }
//                        } else {
//                            System.out.println("Application not found.");
//                        }
//                    }
//                    break;
//                case "4":
//                    System.out.println("Withdrawal request management not fully implemented.");
//                    break;
//                case "5":
//                    return;
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        }
//    }
    
    
    
    
    /* ===============================
     * generateApplicantBookingReport()
     * 
     *================================
     */
    
    private static void generateApplicantBookingReport(HDBManager manager) {
    	
        System.out.println("\n--- Applicant Booking Report ---");
        
        System.out.print("Enter filter criteria (e.g., Married, TWO_ROOM, etc.) or leave blank for all: ");
        
        String filter = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        for (Project p : projects) {
            for (Application app : p.getApplications()) {
                if (app.getStatus() == ApplicationStatus.BOOKED) {
                    String reportLine = "Applicant: " + app.getApplicant().getNRIC() +
                                        " | Flat Type: " + app.getFlatType().name() +
                                        " | Project: " + p.getProjectName() +
                                        " | Age: " + app.getApplicant().getAge() +
                                        " | Marital Status: " + app.getApplicant().getMaritalStatus();
                    if (filter.isEmpty() || reportLine.toLowerCase().contains(filter)) {
                        System.out.println(reportLine);
                        found = true;
                    }
                }
            }
        }
        
        if (!found) {
            System.out.println("No bookings found matching the criteria.");
        }   
        
    }
    
    
    public static void handleEnquiryManagement(HDBManager manager) {
    	
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Enquiry Management ---");
            System.out.println("[1] ▶ View all enquiries for all applicants");
            System.out.println("[2] ▶ View & reply to enquiries for one of your projects");
            System.out.println("[3] ▶ Back");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // 1) View every enquiry in the system
                    for (User u : BTOManagementSystem.users) {
                        if (u instanceof Applicant) {
                            EnquiryManager.viewAllApplicantEnquiries((Applicant) u);
                        }
                    }
                    break;

                case "2":
                    // 2a) Show only your projects
                    List<Project> myProjects = new ArrayList<>();
                    for (Project p : projects) {
                        if (p.getManager().equals(manager)) {
                            myProjects.add(p);
                        }
                    }
                    if (myProjects.isEmpty()) {
                        System.out.println(RED + "You have no projects." + RESET);
                        break;
                    }
                    System.out.println("\nSelect one of your projects:");
                    for (int i = 0; i < myProjects.size(); i++) {
                        System.out.printf("  [%d] %s%n", i + 1, myProjects.get(i).getProjectName());
                    }
                    System.out.print("Project number (or E to cancel): ");
                    String projInput = scanner.nextLine().trim();
                    if (projInput.equalsIgnoreCase("E")) break;
                    int projIdx;
                    try {
                        projIdx = Integer.parseInt(projInput) - 1;
                        if (projIdx < 0 || projIdx >= myProjects.size()) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        System.out.println(RED + "Invalid selection." + RESET);
                        break;
                    }
                    Project chosen = myProjects.get(projIdx);

                    // 2b) Find applicants on that project who have enquiries
                    List<Applicant> applicantsWithEnq = new ArrayList<>();
                    for (Application app : chosen.getApplications()) {
                        Applicant a = app.getApplicant();
                        if (!a.getEnq().isEmpty() && !applicantsWithEnq.contains(a)) {
                            applicantsWithEnq.add(a);
                        }
                    }
                    if (applicantsWithEnq.isEmpty()) {
                        System.out.println(RED + "No enquiries for project \"" 
                                           + chosen.getProjectName() + "\"." + RESET);
                        break;
                    }

                    // 2c) Let manager pick which applicant to reply to
                    System.out.println("\nApplicants with enquiries:");
                    for (int i = 0; i < applicantsWithEnq.size(); i++) {
                        System.out.printf("  [%d] %s%n", i + 1, applicantsWithEnq.get(i).getNRIC());
                    }
                    System.out.print("Applicant number (or E to cancel): ");
                    String appInput = scanner.nextLine().trim();
                    if (appInput.equalsIgnoreCase("E")) break;
                    int appIdx;
                    try {
                        appIdx = Integer.parseInt(appInput) - 1;
                        if (appIdx < 0 || appIdx >= applicantsWithEnq.size()) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        System.out.println(RED + "Invalid selection." + RESET);
                        break;
                    }
                    // 2d) Delegate to EnquiryManager
                    EnquiryManager.viewAndReplyApplicantEnquiries(applicantsWithEnq.get(appIdx));
                    break;

                case "3":
                    // Go back
                    return;

                default:
                    System.out.println(RED + "Invalid choice." + RESET);
            }
        }
    }

    
    
    
    // this is somehting i really wnat to do
 // Function that handles the techno animation
    public static void technoAnimation(String text) {
        // Loop through each character in the string
        for (int i = 0; i < text.length(); i++) {
        	
        	System.out.print(text.charAt(i)); 

            // Add some delay between each letter for animation effect
            try {
                Thread.sleep(100);  // Delay in milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    
    
// _____________________________________________________________________________________________________________
// FILTER 
// _____________________________________________________________________________________________________________    
    
    // too presave the filter setting whyyy
    private static ProjectSearchCriteria savedProjectSearchCriteria = new ProjectSearchCriteria();
    
    // hmm lets set color for the filter menu
    static String MID =  "\u001B[38;2;61;49;99m" ; 
    static String YELLOW = "\u001B[33m"; 
    
    
    
    // lets do filter menu 
    //ProjectSearchCriteria criteria
    
    private static void filterProjects(User user, ProjectSearchCriteria criteria) {
    	
    	while(true) {
    		
    		//System.out.println("║▓▒░ FILTER PROJECTS ░▒▓║");
    		System.out.println();
    		System.out.println(BOLD);
    		System.out.println(MID);
            System.out.println(">>   FILTER PROJECTS   <<");
            System.out.println("=============================");
            System.out.println(RESET);
            
    		System.out.println(MID + "[1] » Project Name" + RESET);
    		System.out.println(MID + "[2] » Neighborhood" + RESET);
    		System.out.println(MID + "[3] » Flat Type" + RESET);
    		System.out.println(MID + "[4] » Set Price Range" + RESET);
    		System.out.println(MID + "[5] » Sort Date" + RESET);
    		System.out.println(MID + "[6] » View Filtered Projects" + RESET);
    		System.out.println(MID + "[7] » View My Filters" + RESET);
    		System.out.println(MID + "[8] » Clear Filters" + RESET);
    		System.out.println(MID + "[9] » Exit" + RESET);
    		
    		System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            
            // lets go
            switch(choice) {
            
            case "1":
            	
            	// if have name as a current filter, need to show
            	if (!criteria.getNameKeywords().isEmpty()) {
                    System.out.println(CYAN + "Current project name filters: " + criteria.getNameKeywords() + RESET);
                }
            	
            	// what if user suddenly dont want name as a filter
            	System.out.print("Enter a project name keyword to add (or press Enter to skip): ");
                String nameKeyword = scanner.nextLine().trim();
                if (!nameKeyword.isEmpty()) {
                    criteria.getNameKeywords().add(nameKeyword);
                    System.out.println(CYAN + "Added project name keyword: " + nameKeyword + RESET);
                } else if (nameKeyword.isEmpty()) {System.out.println(CYAN + "Skipping..."+ RESET);}
                break;
          
                
            case "2":
            	if (!criteria.getNeighborhoodKeywords().isEmpty()) {
                    System.out.println(CYAN + "Current neighborhood filters: " + criteria.getNeighborhoodKeywords() + RESET);
                }
            	
            	System.out.print("Enter a neighborhood keyword to add (or press Enter to skip): ");
                String neighKeyword = scanner.nextLine().trim();
                if (!neighKeyword.isEmpty()) {
                    criteria.getNeighborhoodKeywords().add(neighKeyword);
                    System.out.println(CYAN + "Added neighborhood keyword: " + neighKeyword + RESET);
                }
                break;
            	
            case "3":
                // Show existing filters
                if (!criteria.getFlatTypes().isEmpty()) {
                    System.out.println(CYAN + "Current flat type filters: " + criteria.getFlatTypes() + RESET);
                }

                // single cannot filter 3 room right, it will break huhhhh
                boolean isSingle = (user instanceof Applicant)
                    && ((Applicant)user).getMaritalStatus().equalsIgnoreCase("Single");

                System.out.println(CYAN + "Select a flat type to add:" + RESET);
                System.out.println("[1] TWO_ROOM");
                if (!isSingle) {
                    System.out.println("[2] THREE_ROOM");
                } else {
                	// idk if i need to ask message 
                    System.out.println("");
                }
                System.out.println("Press Enter to skip.");
                System.out.print("Your choice: ");
                
                String option = scanner.nextLine().trim();
                if (!option.isEmpty()) {
                    switch (option) {
                        case "1":
                            criteria.getFlatTypes().add(FlatType.TWO_ROOM);
                            System.out.println(CYAN + "Added flat type: TWO_ROOM" + RESET);
                            break;
                        case "2":
                            if (isSingle) {
                                System.out.println(RED + "Cannot add THREE_ROOM filter as a Single applicant." + RESET);
                            } else {
                                criteria.getFlatTypes().add(FlatType.THREE_ROOM);
                                System.out.println(CYAN + "Added flat type: THREE_ROOM" + RESET);
                            }
                            break;
                        default:
                            System.out.println(CYAN + "Invalid option. No flat type added." + RESET);
                    }
                }
                break;

                
                
            case "4":
            	
            	// hmm need to none if it is empty?
            	// cus it can only hold one value
            	// best to show none right???
            	
            	System.out.println(CYAN + "Current Price Range: " + RESET +
            	        (criteria.getMinPrice() != null ? criteria.getMinPrice() : "none") +
            	        " - " +
            	        (criteria.getMaxPrice() != null ? criteria.getMaxPrice() : "none"));
            
            	// THIS ONE NEEEDDD EXCEPTION HANDLING 
            	System.out.print("Enter minimum price (or press Enter to skip): ");
                String minPriceStr = scanner.nextLine().trim();
                Integer minPrice = null;
                if (!minPriceStr.isEmpty()) {
                    try {
                        minPrice = Integer.parseInt(minPriceStr);
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid input for minimum price. Skipping update." + RESET);
                    }
                }
                
                
                System.out.print("Enter maximum price (or press Enter to skip): ");
                String maxPriceStr = scanner.nextLine().trim();
                Integer maxPrice = null;
                if (!maxPriceStr.isEmpty()) {
                    try {
                        maxPrice = Integer.parseInt(maxPriceStr);
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid input for maximum price. Skipping update." + RESET);
                    }
                }
                
                
                // chekcing everything 
                if (minPrice != null && maxPrice != null) {
                	
                    if (maxPrice == 0) {
                        System.out.println(RED + "Maximum price cannot be 0. Pricing filter not applied." + RESET);
                    } else {
                        criteria.setMinPrice(minPrice);
                        criteria.setMaxPrice(maxPrice);
                        System.out.println(CYAN + "Price range updated to: " + minPrice + " - " + maxPrice + RESET);
                    }
                } 
                
                // NOT NULL 
                else if (minPrice != null) {
                    criteria.setMinPrice(minPrice);
                    System.out.println(CYAN + "Minimum price updated to: " + minPrice + RESET);
                } 
                
                
                // MAX CANT BE 0, DOE SNOT MAKE SENSE
                else if (maxPrice != null) {
                    if (maxPrice == 0) {
                        System.out.println(RED + "Maximum price cannot be 0. Price filter not applied." + RESET);
                    } else {
                        criteria.setMaxPrice(maxPrice);
                        System.out.println(CYAN + "Maximum price updated to: " + maxPrice + RESET);
                    }
                }
                break;
                
                
            case "5":
            	
            	// instead of opening and closing sort, combine cases
            	System.out.println(MID + "Select sort order:" + RESET);
                System.out.println(MID + "[1] Sort by Opening Date (Earliest to Latest)" + RESET);
                System.out.println(MID + "[2] Sort by Closing Date (Soonest to Latest)" + RESET);
                System.out.print("Your choice: ");
                String sortChoice = scanner.nextLine().trim();
                if (sortChoice.equals("1")) {
                    criteria.setSortBy("opening");
                    System.out.println(CYAN + "Sort order set to Opening Date." + RESET);
                } else if (sortChoice.equals("2")) {
                    criteria.setSortBy("closing");
                    System.out.println(CYAN + "Sort order set to Closing Date." + RESET);
                } else {
                    System.out.println(RED + "Invalid selection. No changes made."+ RESET);
                }
                break;
                
            	
            case "6":
                // Create a DateTimeFormatter to parse dates (assumed format "d/M/yyyy")
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate today = LocalDate.now();
                
                // Filter projects based on savedProjectSearchCriteria.
                List<Project> filteredProjects = new ArrayList<>();
                for (Project p : projects) {
                    if (savedProjectSearchCriteria.matches(p)) {
                        filteredProjects.add(p);
                    }
                }
                
                // Sort the filteredProjects list based on sort order.
                if (savedProjectSearchCriteria.getSortBy().equals("opening")) {
                    filteredProjects.sort((p1, p2) -> {
                        try {
                            LocalDate d1 = LocalDate.parse(p1.getApplicationOpeningDate(), dtf);
                            LocalDate d2 = LocalDate.parse(p2.getApplicationOpeningDate(), dtf);
                            return d1.compareTo(d2);
                        } catch(Exception e) {
                            return 0;
                        }
                    });
                } else if (savedProjectSearchCriteria.getSortBy().equals("closing")) {
                    filteredProjects.sort((p1, p2) -> {
                        try {
                            LocalDate d1 = LocalDate.parse(p1.getApplicationClosingDate(), dtf);
                            LocalDate d2 = LocalDate.parse(p2.getApplicationClosingDate(), dtf);
                            return d1.compareTo(d2);
                        } catch(Exception e) {
                            return 0;
                        }
                    });
                }
                
                System.out.println(CYAN + "\n--- Filtered Projects ---" + RESET);
                if (filteredProjects.isEmpty()) {
                    System.out.println(RED + "No projects match your filter criteria." + RESET);
                } else {
                    for (Project proj : filteredProjects) {
                        String icon = "";
                        if (savedProjectSearchCriteria.getSortBy().equals("opening")) {
                            try {
                                LocalDate openDate = LocalDate.parse(proj.getApplicationOpeningDate(), dtf);
                                long daysUntilOpen = java.time.temporal.ChronoUnit.DAYS.between(today, openDate);
                                if (daysUntilOpen >= 0 && daysUntilOpen <= 7) {
                                    icon = " " + YELLOW + "★" + RESET;
                                }
                            } catch (Exception e) {
                                // Date parsing error; ignore icon.
                            }
                        } else if (savedProjectSearchCriteria.getSortBy().equals("closing")) {
                            try {
                                LocalDate closeDate = LocalDate.parse(proj.getApplicationClosingDate(), dtf);
                                long daysUntilClose = java.time.temporal.ChronoUnit.DAYS.between(today, closeDate);
                                if (daysUntilClose >= 0 && daysUntilClose <= 7) {
                                    icon = " " + YELLOW + "⚠️" + RESET;
                                }
                            } catch (Exception e) {
                                // Date parsing error; ignore icon.
                            }
                        }
                        System.out.println(user.formatProjectDisplay(proj) + icon);
                    }
                }
                break;
                
                
            	
//            	// lets show filter projects
//                List<Project> filteredProjects = new ArrayList<>();
//                for (Project p : projects) {
//                    if (savedProjectSearchCriteria.matches(p)) {
//                        filteredProjects.add(p);
//                    }
//                }
//                
//                System.out.println(ORANGE + "\n--- Filtered Projects ---" + RESET);
//                if (filteredProjects.isEmpty()) {
//                    System.out.println(RED + "No projects match your filter criteria." + RESET);
//                } else {
//                    for (Project proj : filteredProjects) {
//                        System.out.println(proj.toDisplayString(true));
//                    }
//                }
//                break;
                
                /// LETSS ADD ICONNNNSSS
                /// LIKE IF THE SORT IS OPENING, STAR THE INBETERRN < 1 WEEK
                /// LIKE IF THE SORT IS CLOSING, URGENT THE < 1 WEEK
                
                
                
            case "7":
            	// thank god this is easier 

                System.out.println(CYAN + "\n--- Current Filter Settings ---" + RESET);
                System.out.println("Project Name Keywords: " + savedProjectSearchCriteria.getNameKeywords());
                System.out.println("Neighborhood Keywords: " + savedProjectSearchCriteria.getNeighborhoodKeywords());
                System.out.println("Flat Types: " + savedProjectSearchCriteria.getFlatTypes());
                
                if (savedProjectSearchCriteria.getMinPrice() != null && savedProjectSearchCriteria.getMaxPrice() != null) {
                    System.out.println("Price Range: " + savedProjectSearchCriteria.getMinPrice() + " - " + savedProjectSearchCriteria.getMaxPrice());
                } 
                
                else {
                    System.out.println("Price Range: Not Set");
                }
                
                System.out.println("Sort Order: " + savedProjectSearchCriteria.getSortBy());
                break;
                
            case "8":
                savedProjectSearchCriteria.clear();
                System.out.println(CYAN + "All filter settings have been cleared." + RESET);
                break;

            case "9":
            	// hmm add animation to it later
                System.out.println(CYAN + "Exiting filter menu." + RESET);
                return; 
                
            default:
                System.out.println(RED + "Invalid choice. Please try again." + RESET);
                

            }
            
            System.out.println(); // for Spacing
            
    	}
    }
  
}



    
    
    
    
                
            
            
    
    
    
  
    
    


