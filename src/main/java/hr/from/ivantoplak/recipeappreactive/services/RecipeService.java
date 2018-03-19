package hr.from.ivantoplak.recipeappreactive.services;

import hr.from.ivantoplak.recipeappreactive.commands.RecipeCommand;
import hr.from.ivantoplak.recipeappreactive.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {

    Flux<Recipe> getRecipes();

    Mono<Recipe> findById(String id);

    Mono<RecipeCommand> findCommandById(String id);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);

    void deleteById(String idToDelete);
}
