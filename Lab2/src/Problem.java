public class Problem {
    private Student[] students;
    private Teacher[] teachers;

    public Problem(Student[] students, Teacher[] teachers) {
        this.students = students;
        this.teachers = teachers;
    }

    public void computePopularity(){
        for(Teacher t : teachers){
            if(t!=null)
            for(Project p: t.getProjects()){
                for(Student s: students){
                    if(s!=null)
                    for(Project pref : s.getPreferences()){
                        if(pref == p){
                            p.increasePopularity();
                        }
                    }
                }
            }
        }
    }

    public void assignProjects(){
        for (Teacher teacher : teachers) {
            if(teacher!=null)
            for(Project p : teacher.getProjects()){
                for(Student s : students){
                    if(s!=null)
                    for(Project pref : s.getPreferences()){
                        if(pref == getMinPopularity(teacher.getProjects())){
                            s.setProject(pref);
                            deleteProject(pref);
                        }
                    }
                }
            }
        }
    }

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

    public Project getMinPopularity(Project[] projects){
        int min=10;
        Project result = null;
        for (Teacher teacher : teachers) {
            if (teacher != null)
            for (Project p : teacher.getProjects()) {
                if(p != null)
                if(min > p.getPopularity()){
                    min = p.getPopularity();
                    result = p;
                }
            }
        }
        return result;
    }
}
