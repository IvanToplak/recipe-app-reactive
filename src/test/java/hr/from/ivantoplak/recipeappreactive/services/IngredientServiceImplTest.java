package hr.from.ivantoplak.recipeappreactive.services;

import hr.from.ivantoplak.recipeappreactive.commands.IngredientCommand;
import hr.from.ivantoplak.recipeappreactive.converters.IngredientCommandToIngredient;
import hr.from.ivantoplak.recipeappreactive.converters.IngredientToIngredientCommand;
import hr.from.ivantoplak.recipeappreactive.converters.UnitOfMeasureCommandToUnitOfMeasure;
import hr.from.ivantoplak.recipeappreactive.converters.UnitOfMeasureToUnitOfMeasureCommand;
import hr.from.ivantoplak.recipeappreactive.domain.Ingredient;
import hr.from.ivantoplak.recipeappreactive.domain.Recipe;
import hr.from.ivantoplak.recipeappreactive.repositories.reactive.RecipeReactiveRepository;
import hr.from.ivantoplak.recipeappreactive.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    private RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    private IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeReactiveRepository, unitOfMeasureReactiveRepository);
    }

    @Test
    public void findByRecipeIdAndId() {
    }

    @Test
    public void findByRecipeIdAndRecipeIdHappyPath() {

        //given
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("1");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "3").block();

        //when
        assertEquals("3", ingredientCommand.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
    }


    @Test
    public void testSaveRecipeCommand() {

        //given
        IngredientCommand command = new IngredientCommand();
        command.setId("3");
        command.setRecipeId("2");

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId("3");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(new Recipe()));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(savedRecipe));

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command).block();

        //then
        assertEquals("3", savedCommand.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    public void testDeleteById() {

        //given
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId("3");
        recipe.addIngredient(ingredient);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        //when
        ingredientService.deleteById("1", "3");

        //then
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }
}