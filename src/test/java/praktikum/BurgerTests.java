package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    Database database = new Database();

    Burger burger;
    int sizeOfIngredientsBurger;

    @Before
    public void createNewBurger() {

        burger = new Burger();

        //Заполнение бургера ингредиентами
        for(int i = 0; i <= 5; i++) {
            burger.addIngredient(database.availableIngredients().get(i));
        }

        sizeOfIngredientsBurger = burger.ingredients.size();
    }

    @Mock
    Bun mockBun;

    @Test
    public void addIngredientsInBurgerTest(){

        int actualSize = burger.ingredients.size();

        assertEquals("Не доложили ингредиентов в бургер",6, actualSize);
    }

    @Test
    public void removeIngredientFromBurgerTest(){

        burger.removeIngredient(4);

        assertEquals("Ингредиент не удалился из бургера",sizeOfIngredientsBurger - 1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientFromBurgerTest(){

        Ingredient ingredientBeforeMoving = burger.ingredients.get(5);
        burger.moveIngredient(5, 0);
        Ingredient ingredientAfterMoving = burger.ingredients.get(0);

        assertEquals("Ингредиент не переместился в бургере", ingredientAfterMoving, ingredientBeforeMoving);
    }

    @Test
    public void getPriceOfBurgerTest(){

        burger.setBuns(mockBun);
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        burger.addIngredient(database.availableIngredients().get(4));

        float addPrice = database.availableIngredients().get(4).getPrice();
        float finishPrice = mockBun.getPrice() * 2 + addPrice;
        float actualPrice = burger.getPrice();

        assertEquals("Некорректно рассчитана стоимость бургера", finishPrice, actualPrice, 0.0f);
    }

    @Test
    public void getReceiptOfBurgerTest(){

        burger.setBuns(mockBun);
        List<Ingredient> ingredientList = new ArrayList<>(burger.ingredients);
        Mockito.when(mockBun.getName()).thenReturn("Булочка для проверки");
        assertTrue("Булочка из рецепта не соответствует действительности", burger.getReceipt().contains("Булочка для проверки"));
        assertTrue("В рецепте нет ингридента из сделанного бургера",
                burger.getReceipt().contains(ingredientList.get(0).getName())
                        &&burger.getReceipt().contains(ingredientList.get(1).getName())
                        &&burger.getReceipt().contains(ingredientList.get(2).getName())
                        &&burger.getReceipt().contains(ingredientList.get(3).getName())
                        &&burger.getReceipt().contains(ingredientList.get(4).getName())
                        &&burger.getReceipt().contains(ingredientList.get(5).getName())
        );
        assertEquals("(==== " + mockBun.getName() + " ====)\r\n" +
                "= sauce " + ingredientList.get(0).getName() + " =\r\n" +
                "= sauce " + ingredientList.get(1).getName() + " =\r\n" +
                "= sauce " + ingredientList.get(2).getName() + " =\r\n" +
                "= filling " + ingredientList.get(3).getName() + " =\r\n" +
                "= filling " + ingredientList.get(4).getName() + " =\r\n" +
                "= filling " + ingredientList.get(5).getName() + " =\r\n" +
                "(==== " + mockBun.getName() + " ====)\r\n" +
                "\r\n" +
                "Price: 1200,000000\r\n", burger.getReceipt());
    }

    @Test
    public void createBurgerTest(){

        burger.setBuns(mockBun);

        assertNotNull("Бургер пустой", burger.bun);
    }
}
