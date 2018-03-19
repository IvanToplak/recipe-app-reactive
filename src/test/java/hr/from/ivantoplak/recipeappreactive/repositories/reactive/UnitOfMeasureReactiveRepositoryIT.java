package hr.from.ivantoplak.recipeappreactive.repositories.reactive;

import hr.from.ivantoplak.recipeappreactive.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    private static final Long COUNT = 1L;
    private static final String EACH = "Each";

    @Autowired
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() {

        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveUom() {

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(EACH);

        unitOfMeasureReactiveRepository.save(uom).block();

        Long count = unitOfMeasureReactiveRepository.count().block();

        assertEquals(COUNT, count);

    }

    @Test
    public void testFindByDescription() {

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(EACH);

        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure fetchedUOM = unitOfMeasureReactiveRepository.findByDescription(EACH).block();

        assertEquals(EACH, fetchedUOM.getDescription());

    }
}