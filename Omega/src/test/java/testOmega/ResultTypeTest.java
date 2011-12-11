
package testOmega;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import ca.uqam.mgl7361.a2011.omega.framework.results.*;

/**
 *
 * @author Administrator
 */
public class ResultTypeTest {
    
    public ResultTypeTest() {
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
    public void un_resultType() {

        ResultType unResultType = new ResultType("unResultType");

        assertTrue("Il n'a pas le même nom ?",unResultType.getName().equalsIgnoreCase("unResultType"));

        unResultType.setName("unNouveauResultType");
        
        assertFalse("Il n'a pas changé de nom ?!",unResultType.getName().equalsIgnoreCase("unResultType"));
        assertTrue("Il n'a pas le même nouveau nom ?",unResultType.getName().equalsIgnoreCase("unNouveauResultType"));

    }
}
