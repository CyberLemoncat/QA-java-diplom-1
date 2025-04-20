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
        assertEquals("Флюоресцентная булка R2-D3", bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(988f, bun.getPrice(), 0.01f);
    }
}