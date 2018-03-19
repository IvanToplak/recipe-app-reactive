package hr.from.ivantoplak.recipeappreactive.repositories.reactive;

import hr.from.ivantoplak.recipeappreactive.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
