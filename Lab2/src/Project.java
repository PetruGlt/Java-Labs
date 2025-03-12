import java.util.Objects;

public class Project {
    private String title;
    private projectType type;
    private boolean assigned = false;
    private Student student;
    private int popularity;

    public enum projectType{
        PRACTICAL,
        THEORETICAL;
    }

    public Project(String title, projectType type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public projectType getType() {
        return type;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void Assign() {
        assigned = true;
    }

    public void increasePopularity() {
        popularity++;
    }

    public int getPopularity() {
        return popularity;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", popularity=" + popularity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Project project)) return false;
        return Objects.equals(title, project.title) && type == project.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type);
    }
}
