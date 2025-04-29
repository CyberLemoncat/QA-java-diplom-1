package praktikum;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {
    Burger burger;
    @Mock
    private Bun bunMock;
    @Mock
    private Ingredient ingredientMock;
    @Mock
    private Ingredient extraIngredientMock;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(bunMock);  // Убедитесь, что булочка устанавливается на объект
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(ingredientMock);

        assertEquals("Неверное количество ингридиенитов", 1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.addIngredient(ingredientMock);

        assertTrue("Ингредиент должен быть в списке после добавления", burger.ingredients.contains(ingredientMock));
    }

    @Test
    public void testSizeWhenRemoveIngredient() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);

        assertEquals("Неверное количество ингрилиентов", 0, burger.ingredients.size());
    }
    @Test
    public void testRemoveIngredientRemovesCorrectIngredient() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);

        assertFalse("Список не должен содержать удаленный иггридиент", burger.ingredients.contains(ingredientMock));
    }

    @Test
    public void testSizeWhenAddExtraIngredient() {
        burger.addIngredient((ingredientMock));
        burger.addIngredient(extraIngredientMock);
        assertEquals("Размер списка не соответсвтует",2, burger.ingredients.size());
    }
    @Test
    public void testIndexWhenAddExtraIngredient() {
        burger.addIngredient((ingredientMock));
        burger.addIngredient(extraIngredientMock);
        assertEquals("Индекс второго ингредиента не соответсвтует",1, burger.ingredients.indexOf(extraIngredientMock));
    }
    @Test
    public void testIngredientIndexWhenRemoveExtraIngredient() {
        burger.addIngredient((ingredientMock));
        burger.addIngredient(extraIngredientMock);
        burger.moveIngredient(1, 0);
        assertEquals("Индекс второго ингредиента после перемещения не соответсвтует",0, burger.ingredients.indexOf(extraIngredientMock));
    }
    @Test
    public void testExtraIngredientIndexWhenRemoveExtraIngredient() {
        burger.addIngredient((ingredientMock));
        burger.addIngredient(extraIngredientMock);
        burger.moveIngredient(1, 0);
        assertEquals("Индекс ингредиента после перемещения не соответсвтует",1, burger.ingredients.indexOf(ingredientMock));
    }
    @Test
    public void testGetPrice() {
        Mockito.when(bunMock.getPrice()).thenReturn(988f);
        Mockito.when(ingredientMock.getPrice()).thenReturn(90f);
        Mockito.when(extraIngredientMock.getPrice()).thenReturn(1337f);
        burger.setBuns(bunMock);
        burger.addIngredient((ingredientMock));
        burger.addIngredient(extraIngredientMock);
        float actualPrice = burger.getPrice();
        float expectedPrice = (bunMock.getPrice() * 2) + ingredientMock.getPrice() + extraIngredientMock.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01);
        System.out.println(actualPrice);
    }

    @Test
    public void receiptStartsWithBunName() {

        Mockito.when(bunMock.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientMock.getName()).thenReturn("Говяжий метеорит (отбивная)");

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();
        Assert.assertTrue(
                "Рецепт должен начинаться с булочки", receipt.startsWith(String.format("(==== %s ====)", bunMock.getName()))
        );
    }

    @Test
    public void receiptContainsIngredient() {
        Mockito.when(bunMock.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientMock.getName()).thenReturn("Говяжий метеорит (отбивная)");

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        Assert.assertTrue(
                "Неверный ингредиент в рецепте", receipt.contains(String.format("= %s %s =",
                        ingredientMock.getType().toString().toLowerCase(),
                        ingredientMock.getName()))
        );
    }

    @Test
    public void receiptContainsTotalPrice() {
        Mockito.when(bunMock.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(bunMock.getPrice()).thenReturn(988.0f);
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientMock.getName()).thenReturn("Говяжий метеорит (отбивная)");
        Mockito.when(ingredientMock.getPrice()).thenReturn(3000.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        float expectedPrice = bunMock.getPrice() * 2 + ingredientMock.getPrice();
        Assert.assertTrue(
                "Неверная цена бургера", receipt.contains(String.format("Price: %.6f", expectedPrice))
        );
    }

    private int ingredientsCount() {
        return burger.ingredients.size();
    }
}

