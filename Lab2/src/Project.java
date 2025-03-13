import java.util.Objects;

/**
 * Clasa 'Project' reprezinta un proiect care poate fi asignat unui student.
 * Fiecare proiect are un titlu, un tip (practic sau teoretic), o popularitate si
 * poate fi asociat unui singur profesor.
 */
public class Project {
    /**
     * Titlul proiectului.
     */
    private final String title;

    /**
     * Tipul proiectului (practic/teoretic)
     */
    private final projectType type;

    /**
     * Indica daca proiectul este asociat unui profesor
     */
    private boolean hasTeacher;

    /**
     * Popularitatea proiectului, determinata de numarul de studenti care il prefera.
     */
    private int popularity;

    /**
     * Enumerare care defineste tipurile posibile de proiecte.
     */
    public enum projectType{
        PRACTICAL,
        THEORETICAL
    }

    /**
     * Constructorul clasei 'Project'.
     *
     * @param title Titlul proiectului.
     * @param type Tipul proiectului (practic sau teoretic).
     */
    public Project(String title, projectType type) {
        this.title = title;
        this.type = type;
    }

    /**
     * Returneaza titlul proiectului.
     *
     * @return Titlul proiectului.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returneaza tipul proiectului.
     *
     * @return Tipul proiectului (practic sau teoretic).
     */
    public projectType getType() {
        return type;
    }

    /**
     * Creste popularitatea proiectului, indicand ca a fost ales de un student.
     */
    public void increasePopularity() {
        popularity++;
    }

    /**
     * Returneaza popularitatea proiectului.
     *
     * @return Numarul de studenti care au selectat acest proiect ca preferinta.
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Asociaza un profesor acestui proiect.
     *
     * @param project Proiectul care va fi asociat unui profesor.
     */
    public void setTeacher(Project project) {
        project.hasTeacher = true;
    }

    public boolean hasTeacher() {
        return hasTeacher;
    }

    /**
     * Returneaza o reprezentare textuala a obiectului 'Project'.
     *
     * @return Un string care contine titlul, tipul si popularitatea proiectului.
     */
    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", popularity=" + popularity +
                '}';
    }

    /**
     * Compara doua obiecte de tip 'Project' pe baza titlului si tipului.
     *
     * @param o Obiectul de comparat.
     * @return true daca titlul si tipul sunt identice, altfel false.
     */
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
