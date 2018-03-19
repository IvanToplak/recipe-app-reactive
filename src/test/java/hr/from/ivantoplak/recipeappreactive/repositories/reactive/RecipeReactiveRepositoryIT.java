package hr.from.ivantoplak.recipeappreactive.repositories.reactive;

import hr.from.ivantoplak.recipeappreactive.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {

    private static final Long COUNT = 1L;
    private static final String DESCRIPTION = "Yummy";

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() {

        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testRecipeSave(){

        Recipe recipe = new Recipe();
        recipe.setDescription(DESCRIPTION);

        recipeReactiveRepository.save(recipe).block();

        Long count = recipeReactiveRepository.count().block();
        assertEquals(COUNT, count);
    }
}