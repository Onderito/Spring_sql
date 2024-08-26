package sql_project.sql.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


    @Setter
    @Getter
    public class CategoryDTO {
        private Long id;
        private String name;
        private List<ArticleDTO> articles;

    }

