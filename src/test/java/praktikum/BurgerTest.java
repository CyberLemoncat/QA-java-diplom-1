package praktikum;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public void setUp(){
    burger = new Burger();
}
@Test
    public void testAddIngredient(){

        burger.addIngredient(ingredientMock);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredientMock));
    }
@Test
    public void  testRemoveIngredient(){
    burger.addIngredient(ingredientMock);
    burger.removeIngredient(0);
    assertEquals(0, burger.ingredients.size());
    assertFalse(burger.ingredients.contains(ingredientMock));
}
@Test
    public void testRemoveExtraIngredient(){
    burger.addIngredient((ingredientMock));
    burger.addIngredient(extraIngredientMock);
    assertEquals(2, burger.ingredients.size());
    assertEquals(1, burger.ingredients.indexOf(extraIngredientMock));
    burger.moveIngredient(1, 0);
    assertEquals(0, burger.ingredients.indexOf(extraIngredientMock));
    assertEquals(1, burger.ingredients.indexOf(ingredientMock));
}

@Test
    public void testGetPrice(){
    Mockito.when(bunMock.getPrice()).thenReturn(988f);
    Mockito.when(ingredientMock.getPrice()).thenReturn(90f);
    Mockito.when(extraIngredientMock.getPrice()).thenReturn(1337f);
    burger.setBuns(bunMock);
    burger.addIngredient((ingredientMock));
    burger.addIngredient(extraIngredientMock);
    float actualPrice = burger.getPrice();
    float expectedPrice = (bunMock.getPrice()*2) +ingredientMock.getPrice() + extraIngredientMock.getPrice();
    assertEquals(expectedPrice, actualPrice, 0.01);
    System.out.println(actualPrice);
}
    @Test
    public void getReceiptTest() {
        Mockito.when(bunMock.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(bunMock.getPrice()).thenReturn(988.0f);
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientMock.getName()).thenReturn("Говяжий метеорит (отбивная)");
        Mockito.when(ingredientMock.getPrice()).thenReturn(3000.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        Assert.assertTrue(receipt.startsWith(String.format("(==== %s ====)", bunMock.getName())));

        Assert.assertTrue(receipt.contains(String.format("= %s %s =", ingredientMock.getType().toString().toLowerCase(), ingredientMock.getName())));

        float expectedPrice = bunMock.getPrice() * 2 + ingredientMock.getPrice();
        Assert.assertTrue(receipt.contains(String.format("Price: %.6f", expectedPrice)));
    }

    private int ingredientsCount() {
        return burger.ingredients.size();
    }
}

