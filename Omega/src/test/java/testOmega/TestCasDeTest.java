package testOmega;

import org.junit.*;
import static org.junit.Assert.*;


import ca.uqam.mgl7361.a2011.omega.framework.core.*;
import ca.uqam.mgl7361.a2011.omega.framework.results.*;
import ca.uqam.mgl7361.a2011.omega.framework.results.writers.*;
import ca.uqam.mgl7361.a2011.omega.framework.results.formats.*;

/**
 * Batterie de tests de la librairie OMEGA
 * 
 * @version 15 novembre 2011
 */
public class TestCasDeTest {

    public TestCasDeTest() {
    }



    
    // Ceci pour vérifier si une suite de test Omega plante si aucun test ne lui a été donné
    @Test
    public void casDeTest_aucunTest() {

        TestSuite testSuite = new TestSuite("AucunTest");

        TestRunner testRunner = TestRunner.getInstance();
        Result testSuiteResult = testRunner.execute(testSuite);
        
        assertTrue(testSuiteResult.getNumberOfSuccessfullTests() == 0);
        assertTrue(testSuiteResult.getNumberOfFailedTests() == 0);
    
        Writer resultWriter = new ConsoleWriter();
        resultWriter.write(testSuiteResult, new TextFormat());
        
        // On écrit aussi dans un fichier --> AucunTest.txt
        Writer resultWriter2 = new TextFileWriter();
        resultWriter2.write(testSuiteResult, new TextFormat());

    }


    // Ceci pour vérifier si une suite de test Omega plante si un test lui est ajouté, mais que ce test ne contient
    // que deux méthodes (setUp, tearDown), donc pas de méthode de test
    @Test
    public void casDeTestVide() {

        TestSuite testSuite = new TestSuite("CasDeTest_vide");
        
        try {
            testSuite.add(TestCaseFactory.getInstance().makeTestCase("testOmega.CasDeTest_vide"));

            TestRunner testRunner = TestRunner.getInstance();
            Result testSuiteResult = testRunner.execute(testSuite);
            
            assertTrue(testSuiteResult.getNumberOfSuccessfullTests() == 0);
            assertTrue(testSuiteResult.getNumberOfFailedTests() == 0);

            Writer resultWriter = new ConsoleWriter();
            resultWriter.write(testSuiteResult, new TextFormat());

        } catch (ClassNotFoundException e) {
            System.out.print(" Classe inexistante ");
        }
        
    }


    // Ceci pour vérifier si une suite de test Omega plante si un test lui est ajouté, mais que ce test ne contient
    // que deux méthodes (setUp, tearDown), donc pas de méthode de test
    @Test
    public void casDeTestClasseVide() {

        TestSuite testSuite = new TestSuite("CasDeTest_Classe_vide");

        try {
            testSuite.add(TestCaseFactory.getInstance().makeTestCase("testOmega.CasDeTest_Classe_vide"));

            TestRunner testRunner = TestRunner.getInstance();
            Result testSuiteResult = testRunner.execute(testSuite);
            
            assertTrue(testSuiteResult.getNumberOfSuccessfullTests() == 0);
            assertTrue(testSuiteResult.getNumberOfFailedTests() == 0);

            Writer resultWriter = new ConsoleWriter();
            resultWriter.write(testSuiteResult, new TextFormat());

        } catch (ClassNotFoundException e) {
            System.out.print(" Classe inexistante ");
        }

    }
    
    
    // Ceci pour vérifier si une suite de test Omega exécute comme il faut un seul test
    @Test
    public void casDeTestSimple() {

        TestSuite testSuite = new TestSuite("CasDeTest_simple");

        TestCaseFactory factory = TestCaseFactory.getInstance();

        try {
            testSuite.add(factory.makeTestCase("testOmega.CasDeTest_simple"));

            TestRunner testRunner = TestRunner.getInstance();
            Result testSuiteResult = testRunner.execute(testSuite);

            Writer resultWriter = new ConsoleWriter();
            resultWriter.write(testSuiteResult, new TextFormat());

        } catch (ClassNotFoundException e) {
            System.out.print(" Classe inexistante ");
        }

    }

    // Ceci pour vérifier si une suite de test Omega exécute comme il faut 4 tests dans une classe
    @Test
    public void casDeTestComplexe() {

        TestSuite testSuite = new TestSuite("CasDeTest_complexe");

        TestCaseFactory factory = TestCaseFactory.getInstance();

        try {
            testSuite.add(factory.makeTestCase("testOmega.CasDeTest_complexe"));

            TestRunner testRunner = TestRunner.getInstance();
            Result testSuiteResult = testRunner.execute(testSuite);

            Writer resultWriter = new ConsoleWriter();
            resultWriter.write(testSuiteResult, new TextFormat());
            
            // On écrit aussi dans un fichier --> CasDeTest_complexe.xml
            Writer resultWriter2 = new TextFileWriter();
            resultWriter2.write(testSuiteResult, new XMLFormat());

        } catch (ClassNotFoundException e) {
            System.out.print(" Classe inexistante ");
        }

    }
    

    // Ceci pour vérifier si une suite de test Omega peut supporter des tests et des suites de tests
    @Test
    public void casDeTest_SuiteDeTest() {

        TestSuite testSuite = new TestSuite("casDeTest_SuiteDeTest");

        try {
            testSuite.add(TestCaseFactory.getInstance().makeTestCase("testOmega.CasDeTest_simple"));
            
            TestSuite testSuite2 = new TestSuite("casDeTest_SuiteDeTest2");
            testSuite2.add(TestCaseFactory.getInstance().makeTestCase("testOmega.CasDeTest_complexe"));
            
            // On ajoute la suite 2 dans la premiere suite
            testSuite.add(testSuite2);

            TestRunner testRunner = TestRunner.getInstance();
            Result testSuiteResult = testRunner.execute(testSuite);

            Writer resultWriter = new ConsoleWriter();
            resultWriter.write(testSuiteResult, new TextFormat());

        } catch (ClassNotFoundException e) {
            System.out.print(" Classe inexistante ");
        }

    }
    


    // Ceci pour vérifier si une suite de test Omega survit au test de FOWLER
    //   voir http://martinfowler.com/bliki/JunitNewInstance.html
    @Test
    public void casDeTestFowler() {

        TestSuite testSuite = new TestSuite("CasDeTest_Exemple_Fowler");

        try {
            testSuite.add(TestCaseFactory.getInstance().makeTestCase("testOmega.CasDeTest_Exemple_Fowler"));

            TestRunner testRunner = TestRunner.getInstance();
            Result testSuiteResult = testRunner.execute(testSuite);
            
            //assertTrue(testSuiteResult.getNumberOfSuccessfullTests() == 2);
            //assertTrue(testSuiteResult.getNumberOfFailedTests() == 0);

            Writer resultWriter = new ConsoleWriter();
            resultWriter.write(testSuiteResult, new TextFormat());

        } catch (ClassNotFoundException e) {
            System.out.print(" Classe inexistante ");
        }

    }
    
    
}