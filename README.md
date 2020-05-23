# Student-project-database-and-management

This is one of my assignment subbmision for the course on **Data structures and Algorithms** which I took at ***IIT Delhi***.

## Problem Statement

Create a database of students and their projects. The projects have jobs and budgets. The jobs inturn have priorities. Implement such a database using:
- **Trie**: for implementing a telephone directory of students
- **Red Blck Tree**: for implementing an advanced directory with various other features of students like marks (different students can even have same name)
- **Priority Queue** using **Max Heap**: for implementing a marks storing system
- **Project Management**: Use above build directories to implement a project management system which executes projects taking into consideration their priority. Once a job in executed the budget of the related project will reduce. Execute the jobs till the budget of all the projects is finished.  

### Trie
Commands to implement:
- **INSERT**: It takes a Person name and phone number (in next line) as input and inserts that into the trie.
- **DELETE**: It takes a String as an input and deletes that from the trie.
- **SEARCH**: It takes a String as input and returns true or false, based on whether that word is present in trie or now.
- **MATCH**: It takes a String as an input, and return all words where the prefix is the entered String. Printing is done in a lexicographical order.
- **PRINTLEVEL**: Print the specified level in lexicographical order separated by comma and DO NOT print spaces.
- **PRINT**: Print all the LEVELS of the trie. The print format same as that of PRINTLEVEL.

### RB Tree
Commands to implement:
- **INSERT**: Insert a Person into the tree.
- **SEARCH**: Searches for a person in the tree.

### Priority Queue
Commands to implement:
- **INSERT** name marks: Insert the student in the tree. Student name and marks are give in the next line. Students name will be unique.
- **EXTRACTMAX**: Extract the student with highest marks and print it. Extract operations also removes this from the max-heap.

### Project Management
Commands to implement:
- **USER**: Create the user with given user name.
- **PROJECT**: Create a project. NAME PRIORITY BUDGET
- **JOB**: Create a job. NAME PROJECT USER RUNTIME
- **QUERY**: Return the status of the Job queried.
- **ADD**: Increase the budget of the project. PROJECT BUDGET
- **EMPTY_LINE**: Let the scheduler execute a single JOB.

## References
- Prof. Subodh Kumar, IIT Delhi (http://www.cse.iitd.ernet.in/~kumarsandeep/ta/col106/assignment4/main.html)
