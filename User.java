package Assignment;

abstract class User {
	
	protected String nric; // primary key
	protected String password;
	protected int age;
	protected String maritalStatus;

	
	// construct
	
	public User(String nric, String password,  int age, String maritalStatus) {
		
		this.nric = nric;
		this.password = password;
		this.age = age;
		this.maritalStatus = maritalStatus;
		
	}
	
	
	public String getNRIC() {
		return nric;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public int getAge() {
		return age;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static boolean validateNRIC(String nric) {
		return nric.matches("^[ST]\\d{7}[A-Z]$");
	}
	
	
// the project view have to be diff for single, married, (officer, manager)
	
	public abstract String formatProjectDisplay(Project project);
	
}



//class diagram
//- nric : String
//- password : String
//- maritalStatus: String
//- age: int

//+ getNRIC()
//+ gatPassword()
//+ getMaritalStatus()
//+ getAge()

//+ setPassword(String password)
//+ validatENRIC(String nric)
