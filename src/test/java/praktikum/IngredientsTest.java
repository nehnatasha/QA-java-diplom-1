package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientsTest {


    private final String expectedIngredient;
    private final IngredientType expectedTypeOfIngredient;
    private final float expectedPriceOfIngredient;

    Ingredient ingredient;

    @Before
    public void createIngredient(){
        ingredient = new Ingredient(expectedTypeOfIngredient, expectedIngredient, expectedPriceOfIngredient);
    }

    public IngredientsTest(String expectedIngredient, IngredientType expectedTypeOfIngredient, float expectedPriceOfIngredient){

        this.expectedIngredient = expectedIngredient;
        this.expectedTypeOfIngredient = expectedTypeOfIngredient;
        this.expectedPriceOfIngredient = expectedPriceOfIngredient;
    }

    @Parameterized.Parameters
    public static Object[][] checkIngredient() {

        return new Object[][] {
                {"chili sauce", IngredientType.SAUCE, 300},
                {"dinosaur", IngredientType.FILLING, 200},
                {"cutlet", IngredientType.FILLING, 100},
                {"hot sauce", IngredientType.SAUCE, 100}
        };
    }

    @Test
    public void getPriceOfIngredientTest(){

        float actualPrice = ingredient.getPrice();

        assertEquals(expectedPriceOfIngredient, actualPrice, 0.0f);
    }

    @Test
    public void getNameOfIngredientTest(){

        String actualIngredientName = ingredient.getName();

        assertEquals(expectedIngredient, actualIngredientName);
    }

    @Test
    public void getTypeOfIngredientTest(){

        IngredientType actualType = ingredient.getType();

        assertEquals(expectedTypeOfIngredient, actualType);
    }
}
