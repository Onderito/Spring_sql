package sql_project.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql_project.sql.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
