package hr.from.ivantoplak.recipeappreactive.repositories;

import hr.from.ivantoplak.recipeappreactive.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
