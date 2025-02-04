package sql_project.sql.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sql_project.sql.dto.AuthorDTO;
import sql_project.sql.model.Author;
import sql_project.sql.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/author")
    public class AuthorController {
        private final AuthorRepository authorRepository;
        public AuthorController(AuthorRepository authorRepository) {
            this.authorRepository = authorRepository;
        }

        @GetMapping
        ResponseEntity<List<AuthorDTO>> getAllAuthors() {
            List<Author> authors = authorRepository.findAll();
            if (authors.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<AuthorDTO> authorDTOS = authors.stream().map(this::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(authorDTOS);
        }

        @GetMapping("/id")
        ResponseEntity<AuthorDTO> getAuthorById(@RequestParam Long id) {
            Author author = authorRepository.findById(id).orElse(null);
            if (author == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(convertToDTO(author));
        }

        @PostMapping
        ResponseEntity<AuthorDTO> addAuthor(@RequestBody Author author) {
            Author savedAuthor = authorRepository.save(author);
            return ResponseEntity.ok(convertToDTO(savedAuthor));
        }

        @PutMapping("/{id}")
        public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
            Author author = authorRepository.findById(id).orElse(null);
            if (author == null) {
                return ResponseEntity.notFound().build();
            }

            author.setFirstName(authorDetails.getFirstName());
            author.setLastName(authorDetails.getLastName());

            Author updatedAuthor = authorRepository.save(author);
            return ResponseEntity.ok(convertToDTO(updatedAuthor));
        }


        @DeleteMapping("/{id}")
        ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
            if (authorRepository.existsById(id)) {
                authorRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }



        private AuthorDTO convertToDTO(Author author) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(author.getId());
            authorDTO.setFirstName(author.getFirstName());
            authorDTO.setLastName(author.getLastName());
            return authorDTO;
        }
    }

