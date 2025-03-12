import java.util.Arrays;
import java.util.Objects;

public class Teacher extends Person{
    private Project[] projects;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProjects(Project ... projects){
        this.projects = projects;
    }

    public Project[] getProjects(){
        return projects;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", projects=" + Arrays.toString(projects) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Teacher teacher)) return false;
        return Objects.deepEquals(projects, teacher.projects);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(projects);
    }
}
