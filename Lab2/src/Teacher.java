import java.util.*;

/**
* Clasa 'Teacher' reprezinta un profesor care propune proiecte.
 * Aceasta clasa extinde clasa 'Person'
 * */
public class Teacher extends Person{

    /**
     * Array-ul de proiecte propuse de profesor
     */
    private Project[] projects;

    /**
     * Constructor pentru crearea unui profesor cu un nume specificat
     *
     * @param name Numele profesorului
     * */
    public Teacher(String name) {
        super(name);
    }

    /**
     * Getter pentru numele profesorului
     *
     * @return Numele profesorului
     * */
    public String getName() {
        return name;
    }

    /**
     * Seteaza proiectele propuse de profesor si le asociaza profesorului curent.
     *
     * @param projects Proiectele propuse (unul sau mai multe).
     */
    public void setProjects(Project ... projects){

        this.projects = projects;
        for(Project project : projects){
            if(project!=null)
                project.setTeacher(project); // Asociaza profesorului curent proiectul
        }
    }

    /**
     * Returneaza array-ul de proiecte propuse de profesor.
     *
     * @return Array-ul de proiecte.
     * */
    public Project[] getProjects(){
        return projects;
    }

    /**
     * Returneaza reprezentarea textuala a obiectului 'Teacher'
     *
     * @return String care descrie profesorul si proiectele propuse de acesta.
     * */
    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", projects=" + Arrays.toString(projects) +
                '}';
    }

    /**
     * Verifica daca doi profesori au aceleasi proiecte.
     *
     * @param o Obiectul cu care se compara
     * @return 'true' daca profesorii sunt egali, 'false' in caz contrar.
     * */
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
