package Assignment;

public class FlatTypeDetail {
	
	private String type;  // e.g., "2-Room", "3-Room"
    private int count;
    
    public FlatTypeDetail(String type, int count) {
        this.type = type;
        this.count = count;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public String toString() {
        return type + ": " + count;
    }
    
    
    

}
