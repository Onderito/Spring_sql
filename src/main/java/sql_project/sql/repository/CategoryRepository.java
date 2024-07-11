package sql_project.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql_project.sql.model.Category;



public interface CategoryRepository extends JpaRepository<Category, Long> {
}
