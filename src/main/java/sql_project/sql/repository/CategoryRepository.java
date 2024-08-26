package sql_project.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sql_project.sql.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
