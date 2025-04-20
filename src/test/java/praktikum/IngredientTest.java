package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTest {

    private Ingredient ingredient;

    @Before
    public void setUp() {
        ingredient = new Ingredient(IngredientType.SAUCE, "Соус Spicy-X", 50.0f);
    }

    @Test
    public void testGetName() {
        assertEquals("Соус Spicy-X", ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(50.0f, ingredient.getPrice(), 0.01f);
    }

    @Test
    public void testGetType() {
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}
