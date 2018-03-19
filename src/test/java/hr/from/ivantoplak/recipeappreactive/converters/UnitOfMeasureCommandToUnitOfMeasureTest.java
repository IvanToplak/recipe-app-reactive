package hr.from.ivantoplak.recipeappreactive.converters;

import hr.from.ivantoplak.recipeappreactive.commands.UnitOfMeasureCommand;
import hr.from.ivantoplak.recipeappreactive.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final String DESCRIPTION = "description";
    private static final String LONG_VALUE = "1";

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();

    }

    @Test
    public void testNullParamter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {

        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }

}