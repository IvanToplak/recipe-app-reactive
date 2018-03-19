package hr.from.ivantoplak.recipeappreactive.converters;

import hr.from.ivantoplak.recipeappreactive.commands.NotesCommand;
import hr.from.ivantoplak.recipeappreactive.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private static final String ID_VALUE = "1";
    private static final String RECIPE_NOTES = "Notes";
    private NotesCommandToNotes converter;

    @Before
    public void setUp() {
        converter = new NotesCommandToNotes();

    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {

        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

}