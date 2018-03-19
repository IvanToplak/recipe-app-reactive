package hr.from.ivantoplak.recipeappreactive.repositories.reactive;

import hr.from.ivantoplak.recipeappreactive.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {

    Mono<Category> findByDescription(String description);
}
