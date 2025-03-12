import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main w2 = new Main();

        System.out.println("Homework: ");
        w2.homework();
    }


    public void compulsory() {
        Student[] students = new Student[10];
        Student s1 = new Student("Nume", LocalDate.of(2000,10,10), "120");
        Student s2 = new Student("Nume2", LocalDate.of(2004, 12, 13), "121" );
        Student s3 = new Student("Nume3", LocalDate.of(2003, 3, 12), "122");
        Student s4 = new Student("Nume4", LocalDate.of(2002, 6, 21), "123");

    }

    public void homework(){
        Student[] students = new Student[10];
        int studentsCount = 0;
        Teacher[] teachers = new Teacher[10];
        int teacherCount = 0;
        Project[] projects = new Project[10];
        int projectsCount = 0;

        Student s1 = new Student("Nume", LocalDate.of(2000,10,10), "120");
        Student s2 = new Student("Nume2", LocalDate.of(2004, 12, 13), "121" );
        Student s3 = new Student("Nume3", LocalDate.of(2003, 3, 12), "122");
        Student s4 = new Student("Nume4", LocalDate.of(2002, 6, 21), "123");
        Student s5 = new Student("Nume5", LocalDate.of(2002, 6, 22), "120");

        studentsCount=addStudent(students, studentsCount, s1);
        studentsCount=addStudent(students, studentsCount, s2);
        studentsCount=addStudent(students, studentsCount, s3);
        studentsCount=addStudent(students, studentsCount, s4);
        studentsCount=addStudent(students, studentsCount, s5);


        Project p1 =  new Project("web", Project.projectType.PRACTICAL);
        Project p2 = new Project("web", Project.projectType.THEORETICAL);
        Project p3 = new Project("Rust", Project.projectType.PRACTICAL);
        Project p4 = new Project("DB", Project.projectType.PRACTICAL);
        Project p5 = new Project("web", Project.projectType.PRACTICAL);

        s1.setPreferences(p1,p2);
        s2.setPreferences(p1,p3);
        s3.setPreferences(p3,p4);
        s4.setPreferences(p1,p4);

        projectsCount=addProject(projects, projectsCount, p1);
        projectsCount=addProject(projects, projectsCount, p2);
        projectsCount=addProject(projects, projectsCount, p3);
        projectsCount=addProject(projects, projectsCount, p4);
        projectsCount=addProject(projects, projectsCount, p5);


        Teacher t1=new Teacher("prof1");
        Teacher t2=new Teacher("prof2");
        Teacher t3=new Teacher("prof3");
        t1.setProjects(projects[0],projects[1]);
        t2.setProjects(projects[2]);
        t3.setProjects(projects[3]);
        teacherCount=addTeacher(teachers, teacherCount,t1);
        teacherCount=addTeacher(teachers, teacherCount,t2);
        teacherCount=addTeacher(teachers, teacherCount,t3);


        System.out.println("\nStudents: \n");
        for (Student student : students) {
            if(student!=null)
             System.out.println(student);
        }

        System.out.println("\nTeacher: \n");
        for(Teacher teacher : teachers) {
            if(teacher!=null)
                System.out.println(teacher);
        }


        System.out.println("\nProject: \n");
        for(Project project : projects) {
            if(project!=null)
                System.out.println(project);
        }

        System.out.println("\nProblem: \n");
        Problem p = new Problem(students,teachers);
        p.computePopularity();


        System.out.println("\nProject: \n");
        for(Project project : projects) {
            if(project!=null)
                System.out.println(project);
        }

        System.out.println("\n Assignment: \n");
        p.assignProjects();
        System.out.println("\nStudents: \n");
        for (Student student : students) {
            if(student!=null)
                System.out.println(student);
        }

    }

    public static int addStudent(Student[] students,int count, Student s){
        for(int i=0;i<students.length;i++){
            if(students[i]!=null && students[i].equals(s)){
                System.out.println("Duplicate student with registrationID: " +s.getRegistrationID());
                return count;
            }
        }
        if(count<students.length){
            students[count]= s;
            return count+1;
        }
        return count;
    }

    public static int addTeacher(Teacher[] teachers,int count, Teacher t){
        for (Teacher teacher : teachers) {
            if (teacher != null && teacher.equals(t)) {
                System.out.println("Duplicate teacher");
                return count;
            }
        }
        if(count<teachers.length){
            teachers[count]= t;
            return count+1;
        }
        return count;
    }

    public static int addProject(Project[] projects,int count, Project p){
        for(int i=0;i<projects.length;i++){
            if(projects[i]!=null && projects[i].equals(p)){
                System.out.println("Duplicate project");
                return count;
            }
        }
        if(count<projects.length){
            projects[count]= p;
            return count+1;
        }
        return count;
    }

    public void bonus() {

    }
}