
package Alpha.Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @version 8 decembre 2011
 */
public class FormatageTest {
    
    public FormatageTest() {
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

    @Test
    public void testFormatterNombreDecimal_null(){
        assertTrue( "Erreur, un non-nombre ne donne pas '---' !", Formatage.formatterNombreDecimal(null).equals("---") );
    }
    
//    
//    @Test
//    public void testFormatterNombreDecimal_valide(){
//        assertTrue( "Erreur, le nombre 3.25 ne donne pas '3.25' !", Formatage.formatterNombreDecimal(3.25).equals("3.25") );
//    }
    
}
