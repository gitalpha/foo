
package Alpha.Util;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @version 5 decembre 2011
 */
public class TempsTest {
    
    public TempsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of stringToDate method, of class Temps.
     */
    @Test
    public void testStringToDate() {

        String strDate = "12-12-2012 01:01:01";
        Date uneDate = Temps.stringToDate(strDate);
        String result = Temps.dateToString(uneDate);
        assertEquals(strDate, result);

    }

    /**
     * Test of stringToDate method, of class Temps.
    */
    @Test
    public void testStringToDate2() {

        String strDate = "12-12-2012";
        String strTime = "12:12:12";
        Date result = Temps.stringToDate(strDate, strTime);
        
        assertEquals(Temps.dateToString(Temps.stringToDate(strDate, strTime)), Temps.dateToString(result));

    }

    /**
     * Test of dateToString method, of class Temps.
     */
    @Test
    public void testDateToString() {
        Date uneDate = Temps.stringToDate("12-12-2012 12:12:12");
        String expResult = "12-12-2012 12:12:12";
        String result = Temps.dateToString(uneDate);
        assertEquals(expResult, result);

    }


    /**
     * Test of calculerVitesse method, of class Temps.
     */
    @Test
    public void testCalculerVitesse() {

        Date a_debut = Temps.stringToDate("12-12-2012", "12:12:12");;
        Date a_fin = Temps.stringToDate("12-12-2012", "12:13:12");;
        long expResult = 60000;
        long result = Temps.calculerVitesse(a_debut, a_fin);
        assertEquals(expResult, result);

    }


}
