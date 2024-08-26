package sql_project.sql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // afin d'avoir des articles dans une catégorie
    @OneToMany(mappedBy = "category")
    private List<Article> articles;


    // Getters et setters


}
