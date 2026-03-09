package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.HabilityModel;

@Repository
public interface HabilityRepository extends JpaRepository<HabilityModel, Long> {
    boolean existsByTitle(String title);
}
