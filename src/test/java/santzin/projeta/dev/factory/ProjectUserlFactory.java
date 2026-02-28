package santzin.projeta.dev.factory;

import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.UserModel;

public class ProjectUserlFactory {
    public static class ProjectUserFactoryBuilder {
        public static ProjectUserModel projectUserModel( ) {
            ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();
            UserModel user = UserFactory.UserFactoryBuilder.userModel();
            ProjectPositionModel position = ProjectPositionFactory.ProjectPositionFactoryBuilder.projectPositionModel(project);

            ProjectUserModel projectUserModel = new ProjectUserModel();
            projectUserModel.setId(1L);
            projectUserModel.setProject(project);
            projectUserModel.setUser(user);
            projectUserModel.setPosition(position);

            return projectUserModel;
        }
    }
}
