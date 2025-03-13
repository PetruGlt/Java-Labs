/**
 * Clasa 'Problem' gestioneaza asignarea proiectelor studentilor si calculeaza cat de dorite sunt proiectele.
 * Aceasta clasa utilizeaza array-uri de studenti si profesori pentru a gestiona proiectele si preferintele studentilor
 */

public class Problem {

    /**
     * Array-ul de studenti care participa la asignarea proiectelor
     * */
    private final Student[] students;
    /**
     * Array-ul de profesori care au proiectele propuse
     * */
    private final Teacher[] teachers;

    /**
     * Constructor pentru crearea unei instante a clasei 'Problem'
     *
     * @param students Array-ul de studenti
     * @param teachers Array-ul de profesori
     * */
    public Problem(Student[] students, Teacher[] teachers) {
        this.students = students;
        this.teachers = teachers;
    }

    /**
     * Metoda principala care rezolva problema de asignare a proiectelor.
     * Aceasta initializeaza o solutie, calculeaza popularitatea proiectelor
     * si efectueaza asignarea propriu-zisa a proiectelor catre studenti.
     */
    public void solveTheProblem() {
        Solution sol = new Solution(this);
        sol.computePopularity(this);
        sol.assignProjects(this);
    }

    /**
     * Returneaza lista de studenti implicati in procesul de asignare.
     *
     * @return Un array de obiecte de tip Student.
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * Returneaza lista de studenti implicati in procesul de asignare.
     *
     * @return Un array de obiecte de tip Student.
     */
    public Teacher[] getTeachers() {
        return teachers;
    }


}
