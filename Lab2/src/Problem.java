public class Problem {
    private Student[] students;
    private Teacher[] teachers;
    private int studentCount = 0;
    private int teacherCount = 0;


    public void addStudent(Student student) {
        this.students[studentCount++] = student;
    }
    public void addTeacher(Teacher teacher) {
        this.teachers[teacherCount++] = teacher;
    }

    public Student[] getStudents() {
        return students;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }


}
