/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testOmega;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import ca.uqam.mgl7361.a2011.omega.framework.results.*;

/**
 * @author Sébastien
 * 
 * @version 14 novembre 2011
 */
public class ResultTest {
    
    public ResultTest() {
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
    public void un_result() {

        Result unResult = new Result("unResult", new ResultType("unResultType"),0,0);

        assertTrue("Il n'a pas le même nom ?",unResult.getName().equalsIgnoreCase("unResult"));
        assertTrue("Il n'a pas le même nom ?",unResult.getResultType().getName().equalsIgnoreCase("unResultType"));
        assertTrue("Il n'a pas 0 échec",unResult.getNumberOfFailedTests()== 0);
        assertTrue("Il n'a pas 0 succès",unResult.getNumberOfSuccessfullTests()== 0);
        assertTrue("Il n'est pas vide",unResult.getSubResults().isEmpty());
        
        Result unSousResult = new Result("unSousResult", new ResultType("unSousResultType"),1,0);
        unResult.add(unSousResult);
        
        assertTrue("Il n'a pas le même nom ?",unResult.getSubResults().get(0).getName().equalsIgnoreCase("unSousResult"));
        assertTrue("Il n'a pas le même nom ?",unResult.getSubResults().get(0).getResultType().getName().equalsIgnoreCase("unSousResultType"));
        assertTrue("Il n'a pas 0 échec",unResult.getNumberOfFailedTests()== 0);
        assertTrue("Il n'a pas 1 succès",unResult.getNumberOfSuccessfullTests()== 1);
        assertFalse("Il est vide ",unResult.getSubResults().isEmpty());
        
        
        Result deuxSousResult = new Result("deuxSousResult", new ResultType("deuxSousResultType"),1,0);
        Result troisSousResult = new Result("troisSousResult", new ResultType("troisSousResultType"),0,5);
        unResult.add(deuxSousResult);
        unResult.add(troisSousResult);
        
       
        assertTrue("Il n'a pas le même nom ?",unResult.getSubResults().get(2).getName().equalsIgnoreCase("troisSousResult"));
        assertTrue("Il n'a pas le même nom ?",unResult.getSubResults().get(2).getResultType().getName().equalsIgnoreCase("troisSousResultType"));
        assertTrue("Il n'a pas 5 échec",unResult.getNumberOfFailedTests()== 5);
        int ii = unResult.getNumberOfSuccessfullTests();
        assertTrue("Il n'a pas 2 succès",unResult.getNumberOfSuccessfullTests()== 2);
        assertFalse("Il est vide ",unResult.getSubResults().isEmpty());
        

    }
}
