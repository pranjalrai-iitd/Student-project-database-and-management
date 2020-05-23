package ProjectManagement;

public class Job implements Comparable<Job> {
	public String name;
	public Project project;
	public String G;
	public User user;
	public String runtime;
	
	public Job(String name, Project project, User user, String runtime) {
		this.name=name;
		this.project=project;
		this.user=user;
		this.runtime=runtime;
		G=String.valueOf(0);
	}

    @Override
    public int compareTo(Job job) {
    Project p=job.getProject();
    int s=Integer.parseInt(p.getPriority());
    int t=Integer.parseInt(project.getPriority());
    if(s<t)
    	return 1;
    else if(s>t)
    	return -1;
    else 
    	return 0;
    }
    
    public String getName() {
    	return name;
    }
    
    public Project getProject() {
    	return project;
    }
    
    public User getUser() {
    	return user;
    }
    
    public String getTime() {
    	return runtime;
    }
    
    public String getG() {
    	return G;
    }
}