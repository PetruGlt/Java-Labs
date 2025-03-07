
public class Project {

    private Main.projectType type;
    private String projectId;

        public Project(Main.projectType type, String projectId) {
            this.type = type;
            this.projectId = projectId;
        }

        public void setType(Main.projectType type) {
            this.type = type;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectId() {
            return projectId;
        }

        public Main.projectType getProjectType() {
            return type;
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Project{" +
                    "type=" + type +
                    ", projectId='" + projectId + '\'' +
                    '}';
        }
    }


