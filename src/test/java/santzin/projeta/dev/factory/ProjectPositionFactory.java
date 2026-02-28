package santzin.projeta.dev.factory;

import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;

public class ProjectPositionFactory {
    public static class ProjectPositionFactoryBuilder {
        public static ProjectPositionModel projectPositionModel(ProjectModel project) {
            ProjectPositionModel position = new ProjectPositionModel();
            position.setId(1L);
            position.setName("Back end developer");
            position.setProject(project);

            return position;
        }
    }
}
