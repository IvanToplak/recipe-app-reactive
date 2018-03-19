package hr.from.ivantoplak.recipeappreactive.repositories.reactive;

import hr.from.ivantoplak.recipeappreactive.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    private static final Long COUNT = 1L;
    private static final String DESCRIPTION = "Foo";

    @Autowired
    private CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() {

        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {

        Category category = new Category();
        category.setDescription(DESCRIPTION);

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(COUNT, count);
    }

    @Test
    public void testFindByDescription() {

        Category category = new Category();
        category.setDescription(DESCRIPTION);

        categoryReactiveRepository.save(category).then().block();

        Category fetchedCat = categoryReactiveRepository.findByDescription(DESCRIPTION).block();

        assertNotNull(fetchedCat.getId());
    }
}