import java.time.LocalDate;
import java.util.*;

/**
 * Clasa 'Student' reprezinta un student care poate aplica pentru proiecte si poate primi asignarea unui proiect.
 * Mosteneste clasa 'Person'.
 */
public class Student extends Person {

    /**
     * ID-ul unic de inregistrare al studentului.
     */
    private final String registrationID;

    /**
     * Lista proiectelor dorite de student, in ordinea preferintelor sale.
     */
    private Project[] desiredProjects;

    /**
     * Proiectul propriu al studentului dupa ce acesta este asignat.
     */
    private Project ownProject;

    /**
     * Constructorul clasei 'Student'.
     *
     * @param name Numele studentului.
     * @param dob Data de nastere a studentului.
     * @param registrationID ID-ul unic de inregistrare al studentului.
     */
    public Student(String name, LocalDate dob, String registrationID) {
        super(name, dob);
        this.registrationID = registrationID;
    }

    /**
     * Getter pentru inregistrare al studentului.
     *
     * @return ID-ul unic al studentului.
     */
    public String getRegistrationID() {
        return registrationID;
    }

    /**
     * Setter in ceea ce priveste proiectele dorite de student.
     *
     * @param projects Lista de proiecte preferate de student.
     */
    public void setPreferences(Project ... projects){
        desiredProjects = projects;
    }

    /**
     * Getter pentru lista proiectelor dorite de student.
     *
     * @return Un array de obiecte de tip Project.
     */
    public Project[] getPreferences(){
        return desiredProjects;
    }

    /**
     * Asigneaza un proiect studentului.
     *
     * @param project Proiectul care ii este asignat studentului.
     */
    public void setProject(Project project){
        ownProject = project;
    }

    /**
     * Returneaza proiectul asignat studentului.
     *
     * @return Proiectul propriu al studentului sau null daca nu are inca un proiect asignat.
     */
    public Project getProject(){
        return ownProject;
    }

    /**
     * Compara doi studenti pe baza ID-ului de inregistrare.
     *
     * @param o Obiectul de comparat.
     * @return true daca obiectul este un student cu acelasi ID de inregistrare, altfel false.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return Objects.equals(registrationID, student.registrationID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(registrationID);
    }

    /**
     * Returneaza o reprezentare sub forma de String a obiectului Student.
     *
     * @return O reprezentare textuala a studentului, incluzand numele, ID-ul de inregistrare,
     * proiectele dorite si proiectul propriu asignat.
     */
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
