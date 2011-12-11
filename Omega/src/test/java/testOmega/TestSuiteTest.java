
package testOmega;


import ca.uqam.mgl7361.a2011.omega.framework.core.*;
import ca.uqam.mgl7361.a2011.omega.framework.results.*;
import ca.uqam.mgl7361.a2011.omega.framework.results.writers.*;
import ca.uqam.mgl7361.a2011.omega.framework.results.formats.*;

import static org.junit.Assert.*;

/**
 *
 */
public class TestSuiteTest {


    // Ici, on s'amuse à lancer ce test via JUnit ou par Omega
    // donc, 2 annotations
    @ca.uqam.mgl7361.a2011.omega.framework.annotation.Test
    @org.junit.Test
    public void testSuiteDeSuite() {

        TestSuite testSuite = new TestSuite("suiteDeSuite");

        assertTrue(testSuite.getName().equalsIgnoreCase("suiteDeSuite"));
        
        TestCaseFactory factory = TestCaseFactory.getInstance();
         
        try {
            testSuite.add(factory.makeTestCase("testOmega.CasDeTest_simple"));
            testSuite.add(factory.makeTestCase("testOmega.CasDeTest_complexe"));

            // Une suite dans une suite
            TestSuite testSuiteInterne = new TestSuite("Suite_interne");
            testSuiteInterne.add(factory.makeTestCase("testOmega.CasDeTest_Exemple_Fowler"));
            testSuite.add(testSuiteInterne);

            TestRunner testRunner = TestRunner.getInstance();
            Result testSuiteResult = testRunner.execute(testSuite);
          
            Writer resultWriter = new ConsoleWriter();
            resultWriter.write(testSuiteResult, new XMLFormat());
            

        } catch (ClassNotFoundException e) {
            System.out.print(" Classe inexistante ");
        }
        
    }
}
