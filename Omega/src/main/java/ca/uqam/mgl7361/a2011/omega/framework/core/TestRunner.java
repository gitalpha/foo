package ca.uqam.mgl7361.a2011.omega.framework.core;

import ca.uqam.mgl7361.a2011.omega.framework.results.*;
import java.util.Date;

public class TestRunner {
	private TestRunner() { }
	
	private static class SingletonHolder { 
        public static final TestRunner instance = new TestRunner();
	}
	
	public static TestRunner getInstance() {
        return SingletonHolder.instance;
	}
	
	public Result execute(TestSuite testSuite){
		Result testSuiteResult = new Result(testSuite.getName(), new ResultType("Test Suite"), 0, 0);
		for(TestCase testCase : testSuite.getTestCases()) {
			testSuiteResult.add(this.execute(testCase));
		}
		for (TestSuite innerTestSuite : testSuite.getTestSuites()) {
			testSuiteResult.add(this.execute(innerTestSuite));
		}
		return testSuiteResult;
	}

	public Result execute(TestCase testCase) {
		Result testCaseResult = new Result(testCase.getName(), new ResultType("Test Case"), 0, 0);
		for (Test test : testCase.getTests()) {
			testCaseResult.add(this.execute(test));			
		}
		return testCaseResult;
	}
	
	public Result execute(Test test) {
		Result testResult;
                
                    // Ajout équipe ALPHA
                    // Date et heure de début et de fin du test pour les statistiques
                    Date dateDebut = new Date();
                    
		try {
                    
                    // Correction de l'équipe ALPHA
                    // Pour nous assurer de l'indépendance de chaque test, nous
                    // allons instancier chaque classe de test avant
                    // chaque appel de ses méthodes de test !
                    // Par le fait même, les méthodes non-statiques pourront maintenant être utilisés
                    Object notreObjetDeTest = test.getTestMethod().getDeclaringClass().newInstance(); 
                    
                                       
                    
			// Correction de l'équipe ALPHA
                        // S'assurer q'une méthode setUp existe, 
                        // et que si elle plante, tout le test échoue
                        if (test.getSetupMethod() != null)
                            test.getSetupMethod().invoke(notreObjetDeTest);
                        
			test.getTestMethod().invoke(notreObjetDeTest);
                        
			// Correction de l'équipe ALPHA
                        // S'assurer q'une méthode tearDown existe, 
                        // et que si elle plante, tout le test échoue
                        if (test.getTearDownMethod() != null)
                            test.getTearDownMethod().invoke(notreObjetDeTest);
                    
			testResult = new Result(test.getTestMethod().getName(), new ResultType("Test"), 1, 0);
                        
         } catch (Throwable ex) {
        	 testResult = new Result(test.getTestMethod().getName(), new ResultType("Test"), 0, 1);
         }
                
            // Ajout équipe ALPHA
            // Date et heure de début et de fin du test pour les statistiques
            Date dateFin = new Date();
            
            testResult.setDateDebut(dateDebut);
            testResult.setDateFin(dateFin);
                    
         return testResult;
	}
	
	
	
}
