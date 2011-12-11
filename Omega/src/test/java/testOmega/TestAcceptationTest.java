

package testOmega;

import Alpha.Util.Temps;
import Alpha.Historique.RegistreHistorique;
import Alpha.Historique.Historique;
import Alpha.Statistique.CalculsStatistiques;
import ca.uqam.mgl7361.a2011.omega.framework.results.Result;
import ca.uqam.mgl7361.a2011.omega.framework.core.*;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * Classe de test complète, qui utilise la librairie OMEGA, 
 * et les ajouts d'Historique et de Statistiques de l'équipe ALPHA
 * 
 * Plusieurs tests et cas de test seront lancés grâce à la librairie OMEGA, 
 * et les résultats de chaque lancement seront enregistrés dans l'historique "HistoriqueEtStatTest.dat", et de plus, 
 * à chaque lancement, un fichier HTML contenant les statistiques de cet historique sera généré : HistoriqueEtStatTest.html 
 * 
 */
public class TestAcceptationTest {
    
    public TestAcceptationTest()
    { }
    
    
    @Test
    public void testOMEGA() 
    {
        
        TestSuite testSuite = new TestSuite("HistoriqueEtStat");

        assertTrue(testSuite.getName().equalsIgnoreCase("HistoriqueEtStat"));
        
        TestCaseFactory factory = TestCaseFactory.getInstance();
         
        try {

            testSuite.add(factory.makeTestCase("testOmega.CasDeTest_simple"));
            testSuite.add(factory.makeTestCase("testOmega.CasDeTest_complexe"));
            
            // Une suite dans une suite
            TestSuite testSuiteInterne = new TestSuite("Suite_interne");
            testSuiteInterne.add(factory.makeTestCase("testOmega.CasDeTest_Exemple_Fowler"));
            
            // Ici, on s'amuse à réutiliser un test de JUnit, mais cette fois avec OMEGA
            testSuiteInterne.add(factory.makeTestCase("testOmega.TestSuiteTest"));
            testSuite.add(testSuiteInterne);

            TestRunner testRunner = TestRunner.getInstance();
            Result testSuiteResult = testRunner.execute(testSuite);
            
            // On envoie dans l'historique
            RegistreHistorique rHistorique;
            rHistorique = RegistreHistorique.getInstanceOf();
            rHistorique.changerHistorique("HistoriqueEtStatTest.dat");

            Historique unHistorique = Historique.compilerUnHistorique( rHistorique.creerNouvelIdHistorique(), testSuiteResult);
        
            rHistorique.creerHistorique( unHistorique );
            
            // On génère les stats
            CalculsStatistiques.creerHtmlStatistique(rHistorique, "HistoriqueEtStatTest.html");
            

        } catch (ClassNotFoundException e) {
            System.out.print(" Classe inexistante ");
        }
        
        
    }
}
