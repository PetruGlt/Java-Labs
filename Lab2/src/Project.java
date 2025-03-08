
public class Project {

    enum projectType{
        THEORETICAL,
        PRACTICAL
    }
    private projectType type;
    private String projectId;
    private Teacher author;

        public Project(projectType type, String projectId) {
            this.type = type;
            this.projectId = projectId;
        }

        public void setType(projectType type) {
            this.type = type;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectId() {
            return projectId;
        }

        public projectType getProjectType() {
            return type;
        }

        public void setAuthor(Teacher author) {
            this.author = author;
        }

        public String getAuthor() {
            return author.getName();
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Project{" +
                    "type=" + type +
                    ", projectId='" + projectId +
                    "', author=" + getAuthor() +
                    '}';
        }
    }


