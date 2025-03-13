
/**
 * Clasa 'Solution' gestioneaza colectia de proiecte disponibile si implementeaza
 * algoritmul de asignare a acestora catre studenti in functie de preferinte.
 */
public class Solution {
    /**
     * Array-ul de proiecte disponibile pentru asignare.
     */
    private Project[] projects;


    /**
     * Constructorul clasei 'Solution'.
     * Acesta initializeaza lista de proiecte pe baza profesorilor din problema data.
     *
     * @param problem Instanta clasei 'Problem' care contine profesorii si proiectele acestora.
     */
    public Solution(Problem problem) {
        int size = 0;

        // Calculam numarul total de proiecte
        for(Teacher teacher : problem.getTeachers()) {
            if(teacher!=null){
                size+=teacher.getProjects().length;
            }
        }

        // Initializam array-ul de proiecte
        projects = new Project[size];

        // Adaugam proiectele profesorilor in array-ul 'projects'
        int index = 0;
        for(Teacher teacher : problem.getTeachers()) {
            if(teacher!=null){
                for(Project project : teacher.getProjects()) {
                    projects[index++] = project;
                }
            }
        }
    }

    /**
     * Calculeaza popularitatea fiecarui proiect pe baza preferintelor studentilor.
     * Popularitatea unui proiect creste atunci cand un student il prefera.
     *
     * @param problem Instanta clasei 'Problem' care contine studentii si preferintele acestora.
     */
    public void computePopularity(Problem problem){
        for(Project p: projects){ // asta e projects din solution solutions
            for(Student s: problem.getStudents()) {
                if (s != null) {
                    for (Project pref : s.getPreferences()) {
                        if (pref == p) {
                            p.increasePopularity();
                        }
                    }
                }
            }
        }
    }

    /**
     * Asigneaza proiectele studentilor in functie de preferinte.
     * Fiecare student primeste proiectul cu cea mai mica popularitate dintre cele disponibile.
     *
     * @param problem Instanta clasei 'Problem' care contine studentii si profesorii implicati.
     */
    public void assignProjects(Problem problem){
        for(Project p : projects){
            for(Student s : problem.getStudents()){
                if(s!=null){
                    for(Project pref : s.getPreferences()){
                        if(pref == getMinPopularity()){
                            s.setProject(pref);
                            deleteProject(pref);
                        }
                    }
                }
            }
        }
    }


    /**
     * Elimina un proiect din lista proiectelor disponibile.
     *
     * @param project Proiectul care trebuie eliminat.
     */
    public void deleteProject(Project project){
        Project[] minusProject = new Project[10];
        int size = 0;
        for(Project p : projects) {
            if (p!=null && !(p.equals(project))) {
                minusProject[size] = p;
                size++;
            }
        }
        projects = minusProject;
    }

    /**
     * Returneaza proiectul cu cea mai mica popularitate dintre cele disponibile.
     *
     * @return Proiectul cu cea mai mica popularitate sau null daca nu exista proiecte disponibile.
     */
    public Project getMinPopularity(){
        int min=10;
        Project result = null;

        for (Project p : projects) {
            if(p != null){
                if(min > p.getPopularity()){
                    min = p.getPopularity();
                    result = p;
                }
            }
        }
        return result;
    }

}
