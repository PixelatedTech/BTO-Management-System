package Assignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// idk if this thing even works
// idk what i am doing at all

public class ProjectSearchCriteria {
	
	private List<String> nameKeywords;
    private List<String> neighborhoodKeywords;
    private List<FlatType> flatTypes;
    private Integer minPrice;
    private Integer maxPrice;

    
    // by opening dates or closing dates
    private String sortBy; 

    
    
    public ProjectSearchCriteria() {
        nameKeywords = new ArrayList<>();
        neighborhoodKeywords = new ArrayList<>();
        flatTypes = new ArrayList<>();
        sortBy = "opening"; // default will be sorting by opening date (earliest to latest)
    }
    
    
    // omg set/get methods are killer
    
    public List<String> getNameKeywords() { return nameKeywords; }
    public List<String> getNeighborhoodKeywords() { return neighborhoodKeywords; }
    public List<FlatType> getFlatTypes() { return flatTypes; }
    
    public Integer getMinPrice() { return minPrice; }
    public void setMinPrice(Integer minPrice) { this.minPrice = minPrice; }
    public Integer getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Integer maxPrice) { this.maxPrice = maxPrice; }
    
    public String getSortBy() { return sortBy;}
    public void setSortBy(String sortBy) {
        if (sortBy.equalsIgnoreCase("opening") || sortBy.equalsIgnoreCase("closing")) {
            this.sortBy = sortBy.toLowerCase();
        }
    }
    
    
    // MUST MUST need somehting to clear
    public void clear() {
        nameKeywords.clear();
        neighborhoodKeywords.clear();
        flatTypes.clear();
        minPrice = null;
        maxPrice = null;
        sortBy = "opening";
    }
    
    // setting up the filter projects fucntion
    
    public boolean matches(Project p) {
    	
    
        // Check name keywords: all keywords must appear in the project name.
    	// mm cannot handle spelling mistakes tho, i cant do anything abt it
    	
    	// -----------------------
    	// check 1: Project name
    	// -----------------------
    	if (!nameKeywords.isEmpty()) {
    	    boolean nameMatch = false;
    	    for (String keyword : nameKeywords) {
    	        if (p.getProjectName().toLowerCase().contains(keyword.toLowerCase())) {
    	            nameMatch = true;
    	            break;
    	        }
    	    }
    	    if (!nameMatch) {
    	        return false;
    	    }
    	}

        
        // -----------------------
    	// check 2: neighborhood 
    	// -----------------------
        
    	if (!neighborhoodKeywords.isEmpty()) {
    	    boolean neighMatch = false;
    	    for (String keyword : neighborhoodKeywords) {
    	        if (p.getNeighborhood().toLowerCase().contains(keyword.toLowerCase())) {
    	            neighMatch = true;
    	            break;
    	        }
    	    }
    	    if (!neighMatch) {
    	        return false;
    	    }
    	}
        
        // -----------------------
    	// check 3: FlatType 
    	// -----------------------
        
        // should fully booked flat be excluded for viewing?
        if (!flatTypes.isEmpty()) {
            boolean typeMatch = false;
            for (FlatType ft : flatTypes) {
            	
            	typeMatch = true;
            	
            	// should only show avaiable proejcts...., please check
//                if (p.getFlatUnitCount(ft) > 0) {
//                    typeMatch = true;
//                    break;
//                }
            	
            }
            if (!typeMatch) {
                return false;
            }
        } 
        
        // -----------------------
    	// check 4: Price Range 
    	// -----------------------
        
        // if user choose null or both then no fltering lol
        if (minPrice == null && maxPrice == null) {
            // No price filtering applied.
        	
        	
        } 
        
        
        // dont make sense if max is 0 right? 
        else if (maxPrice != null && maxPrice == 0) {
            System.out.println("Maximum price cannot be 0. Price filter not applied.");}
            
        // when the input make sense
        else if (minPrice != null && maxPrice != null) {
            boolean priceMatch = false;
            
            for (FlatType ft : FlatType.values()) {
                int price = p.getFlatPrice(ft);
                if (price >= minPrice && price <= maxPrice) {
                    priceMatch = true;
                    break;
                }
            }
            
         // Project does not meet the price filter.
            if (!priceMatch) {
                return false; 
            }
        }
        
        return true;

    }
    
    
}
