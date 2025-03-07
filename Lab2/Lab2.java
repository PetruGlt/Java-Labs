import java.util.*;

public class Lab2{
    public static void main(String[] args) {
        Lab2 lab = new Lab2();
        lab.compulsory();
    }

    enum projectType{
        THEORETICAL,
        PRACTICAL
    }

    public class Project{
        private projectType type;
        private String projectId;

        public Project(projectType type, String projectId) {
            this.type = type;
            this.projectId = projectId;
        }

        public void setType(projectType type) {
            this.type = type;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectId() {
            return projectId;
        }

        public projectType getProjectType() {
            return type;
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Project{" +
                    "type=" + type +
                    ", projectId='" + projectId + '\'' +
                    '}';
        }
    }

    public class Student{
        private String studentId;
        private List<Project> projects;

        public Student(String studentId, List<Project> projects) {
            this.studentId = studentId;
            this.projects = projects;
        }
        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }
        public String getStudentId() {
            return studentId;
        }
        public void setProjects(List<Project> projects) {
            this.projects = projects;
        }
        public List<Project> getProjects() {
            return projects;
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Student{" +
                    "studentId='" + studentId + '\'' +
                    ", projects=" + projects +
                    '}';
        }
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