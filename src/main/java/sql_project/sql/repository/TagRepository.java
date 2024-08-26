package sql_project.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql_project.sql.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
