import java.util.List;

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