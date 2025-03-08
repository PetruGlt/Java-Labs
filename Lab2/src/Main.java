import java.time.LocalDate;
import java.util.*;


public class Main{
    public static void main(String[] args) {
        Main lab = new Main();
        lab.compulsory();
    }



    void compulsory() {

        Teacher t1 = new Teacher("Prof",LocalDate.of(1990,1,1));
        Project p1 = new Project(Project.projectType.THEORETICAL, "p1");
        Project p2 = new Project(Project.projectType.PRACTICAL, "p2");
        Teacher t2 = new Teacher("Prof2",LocalDate.of(1990,10,1), p2);
        p1.setAuthor(t2);
        p2.setAuthor(t1);

        Student s1 = new Student("Petru", LocalDate.of(2003,10,1), "310910401esl27", p2);
        System.out.println(s1);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(t1);
        System.out.println(t2);
    }

    void homework() {}

    void bonus() {}
}