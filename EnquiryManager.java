package Assignment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EnquiryManager{
    //view all of the current enquiries
    public static void viewAllApplicantEnquiries(Applicant applicant){
        List<Enquiry> enquiries = applicant.getEnq();
        if (enquiries == null || enquiries.isEmpty()) {
            //System.out.println("No enquiries found for applicant " + applicant.getNRIC() + ".");
            return;
        }
        System.out.println("Enquiries for applicant " + applicant.getNRIC() + ":");
        System.out.println("-----------------------------");
        for (int i = 0; i < enquiries.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + enquiries.get(i));
        }
    }
    // reply enquiries
    public static void viewAndReplyApplicantEnquiries(Applicant applicant){
        List<Enquiry> enquiries = applicant.getEnq();
        if (enquiries == null || enquiries.isEmpty()) {
            //System.out.println("No enquiries found for applicant " + applicant.getNRIC() + "."); (remove if dont want to loop through applicants with no enquiries)
            return;
        }
        
        System.out.println("Applicant NRIC: " + applicant.getNRIC() + ":");
        System.out.println("-----------------------------");
        for (int i = 0; i < enquiries.size(); i++) {
            System.out.println(i+1 + ". " + enquiries.get(i));
        }
        System.out.println();
        // Choose which enquiries to reply to
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter enquiry number to reply (or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 0) {
            System.out.println("Reply cancelled.");
            return;
        }
        if (choice < 1 || choice > enquiries.size()) {
            System.out.println("Invalid enquiry number.");
            return;
        }
        
        Enquiry selectedEnquiry = enquiries.get(choice - 1);
        // Prevent replying if a reply already exists.
        if (selectedEnquiry.getReply() != null && !selectedEnquiry.getReply().isEmpty()) {
            System.out.println("This enquiry already has a reply.");
            return;
        }
        
        System.out.print("Enter your reply: ");
        String replyMessage = scanner.nextLine();
        
        // Timestamp in "YYYY-MM-DD HH:mm" format.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = dtf.format(LocalDateTime.now());
        
        // Build the formatted reply string.
        String formattedReply = replyMessage + " (Submitted at: " + timestamp + ")";
        
        // Save the reply in the enquiry.
        selectedEnquiry.setReply(formattedReply);
        
        System.out.println("Reply sent for enquiry: " + selectedEnquiry);
    }
}

