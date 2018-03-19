package hr.from.ivantoplak.recipeappreactive.converters;

import hr.from.ivantoplak.recipeappreactive.commands.CategoryCommand;
import hr.from.ivantoplak.recipeappreactive.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final String ID_VALUE = "1";
    private static final String DESCRIPTION = "description";
    private CategoryCommandToCategory conveter;

    @Before
    public void setUp() {
        conveter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(conveter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = conveter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}