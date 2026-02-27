package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.ProjectPositionModel;

import java.util.List;

@Repository
public interface ProjectPositionRepository extends JpaRepository<ProjectPositionModel, Long> {
    List<ProjectPositionModel> findAllByProjectId(Long projectId);
}
