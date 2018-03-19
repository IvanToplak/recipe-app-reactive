package hr.from.ivantoplak.recipeappreactive.repositories;

import hr.from.ivantoplak.recipeappreactive.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByDescription(String description);
}
