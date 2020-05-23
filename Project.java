package ProjectManagement;


public class Project {
	String name;
	String priority;
	String budget;
	
	public Project(String name, String priority, String budget) {
		this.name=name;
		this.priority=priority;
		this.budget=budget;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public String getBudget() {
		return budget;
	}

}
