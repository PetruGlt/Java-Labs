import java.util.*;


public class Main{
    public static void main(String[] args) {
        Main lab = new Main();
        lab.compulsory();
    }

    enum projectType{
        THEORETICAL,
        PRACTICAL
    }

    void compulsory() {

        Project p1 = new Project(projectType.THEORETICAL, "p1");

        Student s1 = new Student("s1", Arrays.asList(p1));
        System.out.println(s1);
        System.out.println(p1);

    }

    void homework() {}

    void bonus() {}
}