import java.time.LocalDate;

public class Student extends Person {
    private String registrationNumber;
    private Project[] preferences;
    private Project assignedProject;



    public Student(String name, LocalDate date, String registrationNumber, Project ... projects) {
        super(name, date);
        this.registrationNumber = registrationNumber;
        this.preferences = projects;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setPreferences(Project ... projects) {
        this.preferences = projects;
    }
    public Project[] getPreferences() {
        return preferences;
    }
    public void assignProject(Project project) {
        assignedProject = project;
    }

    @Override
    public String toString() {
        return "Student{" + getName() + ", " + getBirthDate() + ", " +
                "registrationNumber='" + registrationNumber + '\'' +
                ", acceptable projects=" + preferences +
                '}';
    }
}