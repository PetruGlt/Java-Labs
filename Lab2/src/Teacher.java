import java.time.LocalDate;
import java.util.Arrays;

public class Teacher extends Person {
    Project[] proposedProjects;

    public Teacher(String name, LocalDate birthDate, Project ... proposedProjects) {
        super(name, birthDate);
        this.proposedProjects = proposedProjects;
    }

    public Teacher(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    public Project[] getProposedProjects() {
        return proposedProjects;
    }

    public void setProposedProjects(Project ... proposedProjects) {
        this.proposedProjects = proposedProjects;
        for(Project p : proposedProjects){
            p.setAuthor(this);
        }
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Teacher{" + getName() + ", " + getBirthDate() + ", "  +
                "proposedProjects=" + Arrays.toString(proposedProjects) +
                '}';
    }
}