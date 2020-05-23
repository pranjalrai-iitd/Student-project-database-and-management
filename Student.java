package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;
    public int priority=0;

    public Student(String trim, int parseInt) {
    	name=trim;
    	marks=parseInt;
    }

    @Override
    public int compareTo(Student student) {
        if(marks>student.marks)
        	return 1;
        else if(marks<student.marks)
        	return -1;
        else 
        	return 0;
        
    }

    public String getName() {
        return name;
    }
    public int getMarks() {
    	return marks;
    }
    public String toString() {
    	String ret= "Student{name='"+getName()+"', marks="+String.valueOf(getMarks())+"}";
    	return ret;
    }
}
