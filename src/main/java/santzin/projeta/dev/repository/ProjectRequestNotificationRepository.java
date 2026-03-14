package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.ProjectRequestNotificationModel;

import java.util.List;

@Repository
public interface ProjectRequestNotificationRepository extends JpaRepository<ProjectRequestNotificationModel, Long> {
    List<ProjectRequestNotificationModel> findByUserId(Long userId);

    List<ProjectRequestNotificationModel> findByProjectRequest_Project_Id(Long projectId);
}
