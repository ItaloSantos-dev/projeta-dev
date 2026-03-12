package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.ProjectRequestNotificationModel;

@Repository
public interface ProjectRequestNotificationRepository extends JpaRepository<ProjectRequestNotificationModel, Long> {
}
