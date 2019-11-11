/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenceTest;

import exception.CommandException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import logic.Product;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import persistence.DataSourceController;
import persistence.PersistenceFacade;

/**
 *
 * @author allan
 */
public class ProductMapperTest {

    private static DataSourceController DSC;

    @BeforeClass
    public static void setup() {
        DSC = new DataSourceController(true);
    }

    @Test
    public void getConnectionTest() throws CommandException {
        assertNotNull(DSC.getConnection());
    }

    @Test //getProductTest1, tests that we can get all the bikes(cykler) from the testDatabase.
    public void getProductTest1() throws CommandException {
        assertEquals(1, DSC.getProduct("Rød Cykel").getId());
        assertEquals(2, DSC.getProduct("Grøn Cykel").getId());
        assertEquals(3, DSC.getProduct("Blå Cykel").getId());
        assertEquals(4, DSC.getProduct("Pink Cykel").getId());
        assertEquals(5, DSC.getProduct("SORT Cykel").getId());
    }

    @Test //getProductTest2, tests that we can get all the phones(mobiler) from the testDatabase.
    public void getProductTest2() throws CommandException {
        assertEquals(6, DSC.getProduct("Samsung Galaxy S10").getId());
        assertEquals(7, DSC.getProduct("Apple iphone 11").getId());
        assertEquals(8, DSC.getProduct("Hauwei P30").getId());
        assertEquals(9, DSC.getProduct("Xiaomi redmi note 5").getId());
        assertEquals(10, DSC.getProduct("Sony Ericsson Xperia").getId());
    }

    @Test //getProductTest3, tests that we can get all the alcohol(alkohol) from the testDatabase.
    public void getProductTest3() throws CommandException {
        assertEquals(11, DSC.getProduct("Tuborg Classic 6 pack").getId());
        assertEquals(12, DSC.getProduct("Carlsberg 6 pack").getId());
        assertEquals(13, DSC.getProduct("Sierra Silver Tequila").getId());
        assertEquals(14, DSC.getProduct("Smirnoff Vodka 37,5%").getId());
        assertEquals(15, DSC.getProduct("Bornholmer Honningsyp").getId());
    }

    @Test //getProductTest4, tests that we can get all the computers(computer) from the testDatabase.
    public void getProductTest4() throws CommandException {
        assertEquals(16, DSC.getProduct("Hauwei R5").getId());
        assertEquals(17, DSC.getProduct("Apple Pro").getId());
        assertEquals(18, DSC.getProduct("Asus Zenbook").getId());
        assertEquals(19, DSC.getProduct("Acer Cromebook").getId());
        assertEquals(20, DSC.getProduct("Lenovo thinkpad L590").getId());
    }

    @Test //getProductTest5, tests that we can get all the beds(seng) from the testDatabase.
    public void getProductTest5() throws CommandException {
        assertEquals(21, DSC.getProduct("Auping Royal").getId());
        assertEquals(22, DSC.getProduct("Viking Birka").getId());
        assertEquals(23, DSC.getProduct("Jensen Prestige").getId());
        assertEquals(24, DSC.getProduct("Carpe Diem Harmano").getId());
        assertEquals(25, DSC.getProduct("Tempur Fusion").getId());
    }

    @Test //getProductTest1, tests that we can get all the bikes(cykler) from the testDatabase.
    public void getProductTest6() throws CommandException {
        Product p = DSC.getProduct("Rød Cykel");
        assertEquals(1, p.getId());
        assertEquals("Rød Cykel", p.getName());
        assertEquals("En Cykel der er rød", p.getDescription());
        assertEquals("Cykler", p.getCategoryname());
    }

    //Test that CommandException is thrown when searching for nonexisting product
    @Test(expected = CommandException.class)
    public void getProductTestFail() throws CommandException {
        Product p = DSC.getProduct("Flying Bike");
    }

    public void getProductsTest() {
        List<Product> productList = DSC.getProducts();
        assertEquals(25, productList.size());
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            assertEquals(i + 1, product.getId());
            assertNotNull(product.getName());
            assertNotNull(product.getDescription());
            assertNotNull(product.getCategoryname());
        }
    }

    @Test
    public void createProductTest() throws CommandException {
        Product p1 = new Product(123, "testProdukt", "Dette er et TestProdukt", "Cykler");
        DSC.createProduct(p1);
        Product p2 = DSC.getProduct("testProdukt");
        assertEquals(123, p2.getId());
        assertEquals("testProdukt", p2.getName());
        assertEquals("Dette er et TestProdukt", p2.getDescription());
        assertEquals("Cykler", p2.getCategoryname());

    }
}
