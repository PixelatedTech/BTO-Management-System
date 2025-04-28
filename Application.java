package Assignment;

public class Application {
	
	private Applicant applicant;
	private Project project;
	private FlatType flatType;
	private ApplicationStatus status;  //enum
	
	// construct
	
	public Application(Applicant applicant, Project project, FlatType  flatType, ApplicationStatus status) {
		this.applicant = applicant;
        this.project = project;
        this.flatType = flatType;
        this.status = status;
	}
	
	public Applicant getApplicant() { return applicant; }
    public Project getProject() { return project; }
    public FlatType getFlatType() { return flatType; }
    public void setFlatType(FlatType flatType) { this.flatType = flatType; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Application by " + applicant.getNRIC() +
               " for project " + project.getProjectName() +
               " | Flat Type: " + flatType.name() +
               " | Status: " + status;
    }
	

}
