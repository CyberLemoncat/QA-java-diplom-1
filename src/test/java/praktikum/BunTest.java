package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BunTest {

    private Bun bun;

    @Before
    public void setUp() {
        bun = new Bun("Флюоресцентная булка R2-D3", 988f);
    }

    @Test
    public void testGetName() {
        String expectedName = "Флюоресцентная булка R2-D3";
        assertEquals("Название булочки не совпадает", expectedName, bun.getName());
    }

    @Test
    public void testGetPrice() {
        float expectedPrice = 988f;
        assertEquals("Цена булочки не совпадает", expectedPrice, bun.getPrice(), 0.01f);
    }
}