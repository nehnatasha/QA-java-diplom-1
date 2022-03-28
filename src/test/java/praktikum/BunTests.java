package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTests {

    @Test
    public void checkBunsPriceTest(){
        Bun bun = new Bun("Лучшая булочка с маком", 35.0f);
        float expectedPrice = 35.0f;
        float actualPrice = bun.getPrice();
        assertEquals("Несоответствие в ценах булочки", expectedPrice,actualPrice, 0.0f);
    }

    @Test
    public void checkBansNameTest(){
        Bun bun = new Bun("Майская булочка с повидлом", 24.45f);
        String expectedName = "Майская булочка с повидлом";
        String actualName = bun.getName();
        assertEquals("Несоответсвие в названиях булочки",expectedName, actualName);
    }
}
