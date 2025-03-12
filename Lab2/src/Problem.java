/**
 * Clasa 'Problem' gestioneaza asignarea proiectelor studentilor si calculeaza cat de dorite sunt proiectele.
 * Aceasta clasa utilizeaza array-uri de studenti si profesori pentru a gestiona proiectele si preferintele studentilor
 *
 * */

public class Problem {

    /**
     * Array-ul de studenti care participa la asignarea proiectelor
     * */
    private Student[] students;
    /**
     * Array-ul de profesori care au proiectele propuse
     * */
    private Teacher[] teachers;

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
     * Calculeaza popularitatea fiecarui proiect bazandu-se pe preferintele studentilor.
     * Popularitatea unui proiect creste de fiecare data cand un student il prefera.
     * */
    public void computePopularity(){
        for(Teacher t : teachers){
            if(t!=null){
                for(Project p: t.getProjects()){
                    for(Student s: students) {
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
        }
    }

    /**
     * Asigneaza proiecte studentilor, prioritatea fiind data de cel mai nepopular proiect.
     * Dupa asignare, proiectul este eliminat din lista de proiecte disponibile.
     * */
    public void assignProjects(){
        for (Teacher teacher : teachers) {
            if(teacher!=null){
                for(Project p : teacher.getProjects()){
                    for(Student s : students){
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
        }
    }

    /**
     * Elimina un proiect din lista de proiecte disponibile pentru toti studentii
     *
     * @param project Proiectul ce se va elimina.
     * */
    public void deleteProject(Project project){
        for(Teacher teacher : teachers) {
            if (teacher != null) {
                Project[] minusProject = new Project[10];
                int size = 0;
                for (Project p : teacher.getProjects()) {
                    if (p!=null && !(p.equals(project))) {
                        minusProject[size] = p;
                        size++;
                    }
                }
                teacher.setProjects(minusProject);
            }
        }
    }

    /**
     * Returneaza proiectul cu cea mai mica popularitate.
     *
     * @return Proiectul cel mai putin popular sau null daca nu mai exista proiecte.
     * */
    public Project getMinPopularity(){
        int min=10;
        Project result = null;
        for (Teacher teacher : teachers) {
            if (teacher != null){
                for (Project p : teacher.getProjects()) {
                    if(p != null){
                        if(min > p.getPopularity()){
                            min = p.getPopularity();
                            result = p;
                        }
                    }
                }
            }
        }
        return result;
    }

}
