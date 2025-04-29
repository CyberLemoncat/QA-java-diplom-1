package praktikum;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class IngredientTypeTest {
    @Test
    public void testIngredientTypeContainsSauce() {
        assertTrue("Должен быть SAUSE", Arrays.asList(IngredientType.values()).contains(IngredientType.SAUCE));
    }

    @Test
    public void testIngredientTypeContainsFilling() {
        assertTrue("Должен быть FILLING", Arrays.asList(IngredientType.values()).contains(IngredientType.FILLING));
    }
}
