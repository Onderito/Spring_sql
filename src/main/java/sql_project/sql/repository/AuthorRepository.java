package sql_project.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql_project.sql.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
