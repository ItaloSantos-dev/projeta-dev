package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.ProjectRequestModel;

@Repository
public interface ProjectRequestRespository extends JpaRepository<ProjectRequestModel, Long> {
}
