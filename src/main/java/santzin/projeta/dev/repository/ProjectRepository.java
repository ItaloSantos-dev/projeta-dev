package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectUserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
    List<ProjectModel> findByCreatorId(Long userId);

    Boolean existsByTitleAndCreatorId(String name, Long creatorId);

    Optional<ProjectModel> findBySlug(String slug);

    boolean existsByCreatorIdAndFixedPosition(Long creatorId, Integer fixiedPosition);

    List<ProjectModel> findByCreatorIdAndFixedPositionIsNotNull(Long creatorId);
}
