package hr.from.ivantoplak.recipeappreactive.services;

import hr.from.ivantoplak.recipeappreactive.domain.Recipe;
import hr.from.ivantoplak.recipeappreactive.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {

        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {

        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    Byte[] byteObjects;
                    try {
                        byteObjects = new Byte[file.getBytes().length];
                        int i = 0;
                        for (byte b : file.getBytes()) {
                            byteObjects[i++] = b;
                        }
                        recipe.setImage(byteObjects);
                        return recipe;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        recipeReactiveRepository.save(recipeMono.block()).block();

        return Mono.empty();
    }
}
