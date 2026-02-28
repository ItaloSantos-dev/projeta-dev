package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectUserModel;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
    List<ProjectModel> findByCreatorId(Long userId);
}
