import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Student extends Person {
    private String registrationID;
    private Project[] desiredProjects;
    private Project ownProject;

    public Student(String name, LocalDate dob, String registrationID) {
        this.name = name;
        this.dateOfBirth = dob;
        this.registrationID = registrationID;
    }
    public String getRegistrationID() {
        return registrationID;
    }

    public void setPreferences(Project ... projects){
        desiredProjects = projects;
    }

    public Project[] getPreferences(){
        return desiredProjects;
    }

    public void setProject(Project project){
        ownProject = project;
    }

    public Project getProject(){
        return ownProject;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(registrationID, student.registrationID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(registrationID);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", registrationID='" + registrationID + '\'' +
                ", desiredProjects=" + Arrays.toString(desiredProjects) +
                ", ownProject=" + ownProject +
                '}';
    }
}
