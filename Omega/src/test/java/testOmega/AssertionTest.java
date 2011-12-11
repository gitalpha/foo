package testOmega;

import org.junit.*;
import static org.junit.Assert.*;

import ca.uqam.mgl7361.a2011.omega.framework.core.Assertion;

/**
 * Batterie de tests sur l'assertion
 * 
 * @version 14 novembre 2011
 */
public class AssertionTest {

    public AssertionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void un_egale_un_entier() {

        Integer i = 1, j = 1;

        boolean testWasSuccessful;
        try {
            Assertion.AssertExpectedEqualsActual(i, j);
            testWasSuccessful = true;
        } catch (AssertionError ex) {
            testWasSuccessful = false;
        }
        assertTrue(testWasSuccessful);

    }

    @Test
    public void un_egale_un_entier2() {

        int i = 1, j = 1;

        boolean testWasSuccessful;
        try {
            Assertion.AssertExpectedEqualsActual(i, j);
            testWasSuccessful = true;
        } catch (AssertionError ex) {
            testWasSuccessful = false;
        }
        assertTrue(testWasSuccessful);
    }

    @Test
    public void un_egale_un_chaine() {

        String i = "1";
        String j = "1";

        boolean testWasSuccessful;
        try {
            Assertion.AssertExpectedEqualsActual(i, j);
            testWasSuccessful = true;
        } catch (AssertionError ex) {
            testWasSuccessful = false;
        }
        assertTrue(testWasSuccessful);

    }

    @Test
    public void types_differents() {

        Integer i = 1;
        String j = "1";

        boolean testWasSuccessful;
        try {
            Assertion.AssertExpectedEqualsActual(i, j);
            testWasSuccessful = true;
        } catch (AssertionError ex) {
            testWasSuccessful = false;
        }
        // On sait que çà doit retourner FAUX !!
        // Car 2 objets différents
        assertTrue( ! testWasSuccessful);
    }
    
    @Test
    public void types_null() {

        Integer i = 1;
        String j = "1";

        boolean testWasSuccessful;
        try {
            Assertion.AssertExpectedEqualsActual(null, j);
            testWasSuccessful = true;
        } catch (AssertionError ex) {
            testWasSuccessful = false;
        }
        // On sait que çà doit retourner FAUX !!
        // Car 2 objets différents
        assertTrue( ! testWasSuccessful);
    }
    
    @Test
    public void deux_entiers_differents() {

        int i = 1;
        int j = 2;

        boolean testWasSuccessful;
        try {
            Assertion.AssertExpectedEqualsActual(i, j);
            testWasSuccessful = true;
        } catch (AssertionError ex) {
            testWasSuccessful = false;
        }
        // On sait que çà doit retourner FAUX !!
        // Car 2 objets différents
        assertTrue( ! testWasSuccessful);
    }
}
