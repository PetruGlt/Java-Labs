import java.time.LocalDate;
/**
* Clasa 'Person' reprezinta o persoana cu un nume si data de nastere.
 * Este o clasa abstracta deoarece nu putem instantia un obiect de tip Person
 * Aceasta este o clasa de baza, ce este extinsa la clasele: Student, Teacher
 *
 * @author Galateanu Petru
 *
* */
public class Person {
    // Numele
    protected String name;
    // Data de nastere
    protected LocalDate dateOfBirth;
}
