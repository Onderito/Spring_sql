package sql_project.sql.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sql_project.sql.dto.ArticleDTO;
import sql_project.sql.dto.CategoryDTO;
import sql_project.sql.model.Article;
import sql_project.sql.model.Category;
import sql_project.sql.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CategoryDTO> categoryDTOs = categories.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(categoryDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Category category = optionalCategory.get();
        return ResponseEntity.ok(convertToDTO(category));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.status(201).body(convertToDTO(savedCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Category category = optionalCategory.get();
        category.setName(categoryDTO.getName());
        Category updatedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(convertToDTO(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Category category = optionalCategory.get();
        categoryRepository.delete(category);
        return ResponseEntity.noContent().build();
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        if (category.getArticles() != null) {

        categoryDTO.setArticles(category.getArticles().stream().map(article -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(article.getId());
            articleDTO.setTitle(article.getTitle());
            articleDTO.setContent(article.getContent());
            articleDTO.setCreatedAt(article.getCreatedAt());
            articleDTO.setUpdatedAt(article.getUpdatedAt());
            articleDTO.setCategoryId(article.getCategory().getId());
            return articleDTO;
        }).collect(Collectors.toList()));
        }
        return categoryDTO;
    }

    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        if (categoryDTO.getArticles() != null) {

        category.setArticles(categoryDTO.getArticles().stream().map(articleDTO -> {
            Article article = new Article();
            article.setId(articleDTO.getId());
            article.setTitle(articleDTO.getTitle());
            article.setContent(articleDTO.getContent());
            article.setCreatedAt(articleDTO.getCreatedAt());
            article.setUpdatedAt(articleDTO.getUpdatedAt());
            if (articleDTO.getCategoryId() != null) {
                Category categoryRef = categoryRepository.findById(articleDTO.getCategoryId()).orElse(null);
                article.setCategory(categoryRef);
            }
            return article;
        }).collect(Collectors.toList()));
        }
        return category;
    }
}