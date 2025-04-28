package Assignment;
import java.util.*;

public class Project {
	
	private String projectName;
    private String neighborhood;
    private int[] flatUnitCounts; // should enum or new class? this is annoying
    private int[] flatPrices; 
    
    private String applicationOpeningDate;
    private String applicationClosingDate;
    private HDBManager manager;
    private boolean visible;
    public int availableOfficerSlots;
    private List<Application> applications;
    
    public Project(String projectName, String neighborhood, int twoRoomUnits, int threeRoomUnits,int twoRoomPrice, int threeRoomPrice,
            String applicationOpeningDate, String applicationClosingDate, HDBManager manager,
            boolean visible, int availableOfficerSlots) {
    	
    	
		this.projectName = projectName;
		this.neighborhood = neighborhood;
		flatUnitCounts = new int[FlatType.values().length];
		flatPrices = new int[FlatType.values().length];

	    flatUnitCounts[FlatType.TWO_ROOM.ordinal()] = twoRoomUnits;
	    flatUnitCounts[FlatType.THREE_ROOM.ordinal()] = threeRoomUnits;
	    flatPrices[FlatType.TWO_ROOM.ordinal()]     = twoRoomPrice;
	    flatPrices[FlatType.THREE_ROOM.ordinal()]     = threeRoomPrice;

		this.applicationOpeningDate = applicationOpeningDate;
		this.applicationClosingDate = applicationClosingDate;
		this.manager = manager;
		this.visible = visible;
		this.availableOfficerSlots = availableOfficerSlots;
		this.applications = new ArrayList<>();
    }
    
    public int getFlatUnitCount(FlatType type) {
        return flatUnitCounts[type.ordinal()];
    }
    
    public void setFlatUnitCount(FlatType type, int count) {
        flatUnitCounts[type.ordinal()] = count;
    }
    
    // idk if there was any mention about prices, but a manager have to create a project. 
    public int getFlatPrice(FlatType type) {
        return flatPrices[type.ordinal()];
    }

    public void setFlatPrice(FlatType type, int price) {
        flatPrices[type.ordinal()] = price;
    }
    
    
    
    public String getProjectName() {
        return projectName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }
    
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    
    public String getApplicationOpeningDate() {
        return applicationOpeningDate;
    }
    
    public void setApplicationOpeningDate(String applicationOpeningDate) {
        this.applicationOpeningDate = applicationOpeningDate;
    }
    

    public String getApplicationClosingDate() {
        return applicationClosingDate;
    }
    
    public void setApplicationClosingDate(String applicationClosingDate) {
        this.applicationClosingDate = applicationClosingDate;
    }
    
    public HDBManager getManager() {
        return manager;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getAvailableOfficerSlots() {
        return availableOfficerSlots;
    }
    
    public void setAvailableOfficerSlots(int availableOfficerSlots) {
        this.availableOfficerSlots = availableOfficerSlots;
    }

    public void addApplication(Application app) {
        applications.add(app);
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
 
    
    
    
    public List<Application> getApplications() { return applications; }
    
 // This method displays full project info, including the visibility flag.
    public String toDisplayString(boolean includeVisibility) {
        StringBuilder sb = new StringBuilder();
        sb.append("Project: ").append(projectName)
          .append(", Neighborhood: ").append(neighborhood);

        for (FlatType type : FlatType.values()) {
            sb.append(", ").append(type.name())
              .append(": Units=").append(getFlatUnitCount(type))
              .append(", Price=").append(getFlatPrice(type));
        }
        if (includeVisibility) {
            sb.append(", Visible: ").append(visible);
        }
        return sb.toString();
    }

    
    
    @Override
    public String toString() {
        return toDisplayString(true);
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Project: ").append(projectName)
//          .append(", Neighborhood: ").append(neighborhood)
//          .append(", Flat Units: ");
//        // Iterate over all defined flat types and append counts
//        for (FlatType type : FlatType.values()) {
//            sb.append(type.name()).append(": ").append(getFlatUnitCount(type)).append(" ");
//        }
//        sb.append(", Visible: ").append(visible);
//        return sb.toString();
//    }
}
