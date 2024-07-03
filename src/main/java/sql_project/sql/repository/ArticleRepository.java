package sql_project.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql_project.sql.model.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);
}
