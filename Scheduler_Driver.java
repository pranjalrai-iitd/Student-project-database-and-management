package ProjectManagement;

import PriorityQueue.MaxHeap;
import PriorityQueue.PriorityQueueDriverCode;
import Trie.Trie;
import Trie.TrieInterface;
import Trie.TrieNode;
import RedBlack.RBTree;
import RedBlack.RedBlackNode;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Scheduler_Driver extends Thread implements SchedulerInterface {


    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
      
        File file;
        if (args.length == 0) {
            URL url = PriorityQueueDriverCode.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }
    
    MaxHeap<Job> maxheap=new MaxHeap<Job>();
    Trie<Project> trie=new Trie<Project>();
    RBTree<String,User> rbt=new RBTree<String,User>();
    int globaltime=0;
    ArrayList<Job> c=new ArrayList<Job>();
    ArrayList<Job> r=new ArrayList<Job>();



    public void execute(File file) throws IOException {

 //       URL url = Scheduler_Driver.class.getResource("INP");  //  COMMENT OUT AT THE END >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 //       file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                    handle_project(cmd);
                    break;
                case "JOB":
                    handle_job(cmd);
                    break;
                case "USER":
                    handle_user(cmd[1]);
                    break;
                case "QUERY":
                    handle_query(cmd[1]);
                    break;
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }


        run_to_completion();

        print_stats();

    }




    @Override
    public void run() {
        while(maxheap.getSize()!=0)
        schedule();
    }
    
    


    @Override
    public void run_to_completion() {
    	run();

    }

    @Override
    public void handle_project(String[] cmd) {
    	Project project=new Project(cmd[1],cmd[2],cmd[3]);
    	trie.insert(project.getName(), project);
    	System.out.println("Creating project");

    }

    @Override
    public void handle_job(String[] cmd) {
    	TrieNode<Project> p=trie.search(cmd[2]);
    	if(p==null) {
    		System.out.println("Creating job");
    		System.out.println("No such project exists. "+cmd[2]);
    	}
    	else if(p!=null) {
    		Project prj=p.getValue();
    		RedBlackNode<String, User> pr=rbt.search(cmd[3]);
    		if(pr==null) {
    			System.out.println("Creating job");
    			System.out.println("No such user exists: "+cmd[3]);
    		}
    		else if(pr!=null) {
    			User u=pr.getValue();
    	    	Job job=new Job(cmd[1],prj,u,cmd[4]);
    	    	maxheap.insert(job);
    	    	r.add(job);
    	    	System.out.println("Creating job");
    		}
    	}
}

    @Override
    public void handle_user(String name) {
    	User user=new User(name);
    	rbt.insert(user.getName(), user);
    	System.out.println("Creating user");
    	

    }

    @Override
    public void handle_query(String key) {
    	System.out.println("Querying");
    	int ret=0;
    	for(int i=0; i< r.size(); i++) {
    		if(r.get(i).getName().compareTo(key)==0)
    			ret=1;
    	}
    	for(int i=0; i< c.size(); i++) {
    		if(c.get(i).getName().compareTo(key)==0)
    			ret=-1;
    	}
    	if(ret==1) {
    		System.out.println(key+": NOT FINISHED");
    	}
    	if(ret==-1) {
    		System.out.println(key+": COMPLETED");
    	}
    	if(ret==0) {
    		System.out.println(key+": NO SUCH JOB");
    	}
    		

    }

    @Override
    public void handle_empty_line() {
    	System.out.println("Running code");
    	System.out.println("    "+"Remaining jobs: "+(maxheap.getSize()));
    	handle();
    	System.out.println("Execution cycle completed");
    }
    public void handle() {
    	
    	if(maxheap.getSize()!=0) {
    	Job job=maxheap.extractMax();
    	TrieNode<Project> n=trie.search((job.getProject()).getName());
    	Project p=n.getValue();
    	System.out.println("    "+"Executing: "+job.getName()+" from: "+p.getName());
    	if(Integer.parseInt(p.getBudget())>=Integer.parseInt(job.getTime())) {
    		for(int i=0; i< r.size(); i++) {
    			if(r.get(i)==job) {
    				Job temp=r.remove(i);
    				c.add(temp);
    			}
    		}
    		globaltime=globaltime+Integer.parseInt(job.getTime());
    		job.G=String.valueOf(globaltime);
    		int budget=Integer.parseInt(p.budget)-Integer.parseInt(job.getTime());
    		p.budget=String.valueOf(budget);
    		System.out.println("    "+"Project: "+p.getName()+" budget remaining: "+p.getBudget());
    	}
    	else if(Integer.parseInt(p.getBudget())<Integer.parseInt(job.getTime())) {
    		System.out.println("    "+"Un-sufficient budget.");
    		handle();
    	}
    	}
    	
    }

    @Override
    public void handle_add(String[] cmd) {
 
    	TrieNode<Project> t=trie.search(cmd[1]);
    	Project p =t.getValue();
    	int temp=Integer.parseInt(p.budget)+Integer.parseInt(cmd[2]);
    	p.budget=String.valueOf(temp);
    	for(int i=0; i<r.size(); i++) {
    		if(r.get(i).getProject()==p) {
    			Job j=r.get(i);
    			maxheap.insert(j);
    		}
    	}
    	System.out.println("Adding Budget");

    }

    @Override
    public void print_stats() {
    	System.out.println("--------------STATS--------------");
    	System.out.println("Total jobs done: "+c.size());
    	for(int i=0; i<c.size();i++) {
    		Job job=c.get(i);
    		System.out.println("Job{user='"+job.getUser().getName()+"', project='"+job.getProject().getName()+"', jobstatus=COMPLETED, execution_time="+job.getTime()+", end_time="+job.getG()+", name='"+job.getName()+"'}");
    	    
    	}
    	System.out.println("----------------------");
    	System.out.println("Unfinished jobs:");
    	int a=r.size();
    	for(int i=0; i<r.size();i++) {
    		Job job=r.get(i);
    		System.out.println("Job{user='"+job.getUser().getName()+"', project='"+job.getProject().getName()+"', jobstatus=REQUESTED, execution_time="+job.getTime()+", end_time=null , name='"+job.getName()+"'}");
   	}
    	System.out.println("Total unfinished jobs: "+a);
    	System.out.println("---------------STATS DONE---------------");

    }
    @Override
    public void schedule() {
    	System.out.println("Running code");
    	System.out.println("    "+"Remaining jobs: "+(maxheap.getSize()));
    		handle();
    	System.out.println("System execution completed");

    }
}
