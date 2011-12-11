
package Alpha.Historique;

import ca.uqam.mgl7361.a2011.omega.framework.results.Result;
import ca.uqam.mgl7361.a2011.omega.framework.results.ResultType;
import java.lang.String;
import java.util.Date;
import Alpha.Util.Temps;
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
public class HistoriqueTest {
    
    public HistoriqueTest() {
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
    public void creerHistoriqueSimple() {
        Historique unHistorique = new Historique( "TEST0001" );
        assertTrue( "L'historique n'existe pas !", (unHistorique.getId().equals("TEST0001")) );
    }
    
    @Test
    public void creerHistoriqueComplet() {
        
        String testId = "TEST7777";
        
        String testEtat = Historique.ETAT_VERT;
        
        int testNombreTests = 10;
        
        Date uneDateDebut = Temps.stringToDate("27-11-2011", "12:12:00");
        Date uneDateFin = Temps.stringToDate("27-11-2011", "12:13:00");
        long testVitesse = Temps.calculerVitesse(uneDateDebut, uneDateFin);
        
        Date testDate = Temps.stringToDate("27-11-2011", "12:12:00");
        
        
        Historique unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, testDate );
        
        assertTrue( "L'historique n'existe pas !", (unHistorique.getId().equals( testId )) );
        assertTrue( "L'historique n'a pas le même état !", (unHistorique.getEtat().equals( testEtat )) );
        assertTrue( "L'historique n'a pas le même nombre de tests !", (unHistorique.getNombreTests() == testNombreTests ) );
        assertTrue( "L'historique n'a pas la même vitesse !", (unHistorique.getVitesse() == testVitesse ) );
        assertTrue( "L'historique n'a pas la même date !", (unHistorique.getDateHeure().equals( testDate )) );

    }    
    
    @Test
    public void compilerUnHistorique_null() {
        
        Historique unHistorique = Historique.compilerUnHistorique("", null);
        
        assertTrue("Erreur cet historique n'est pas null.", unHistorique == null );
        
        
        Result unResultat = new Result("unResultat", new ResultType("unResultType"),5,0);
        Historique unAutreHistorique = Historique.compilerUnHistorique(null, unResultat);
        
        assertTrue("Erreur cet historique n'est pas null.", unAutreHistorique == null );

    }
        
    @Test
    public void compilerUnHistorique_simple() {
        
        Result unResultat = new Result("unResultat", new ResultType("unResultType"),5,0);
        
        unResultat.setDateDebut(Temps.stringToDate("11-11-2011", "12:39:00"));
        unResultat.setDateFin(Temps.stringToDate("11-11-2011", "12:39:10"));
        
        Historique unHistorique = Historique.compilerUnHistorique("TEST1", unResultat);
        
        assertTrue("Ce n'est pas le bon historique.", unHistorique.getId().equals("TEST1") );
        assertTrue("Erreur cet historique n'est pas vert.", unHistorique.getEtat().equals(Historique.ETAT_VERT) );
        assertTrue("Ce n'est pas le nombre de tests corrects.", unHistorique.getNombreTests() == 5 );
        assertTrue("Ce n'est pas la vitesse correcte.", unHistorique.getVitesse() == 10000 );
                        
    }
    
    @Test
    public void compilerUnHistorique_complexe() {
        
        Result unResultat = new Result("unResultat", new ResultType("unResultType"),5,0);
        unResultat.setDateDebut(Temps.stringToDate("11-11-2011", "12:39:00"));
        unResultat.setDateFin(Temps.stringToDate("11-11-2011", "12:39:10"));
        
        Result unSousResult1 = new Result("unSousResult1", new ResultType("unSousResultType"),1,0);
        unResultat.setDateDebut(Temps.stringToDate("11-11-2011", "12:39:00"));
        unResultat.setDateFin(Temps.stringToDate("11-11-2011", "12:39:10"));
        unResultat.add(unSousResult1);
        
        Result unSousResult2 = new Result("unSousResult2", new ResultType("unSousResultType"),3,1);
        unResultat.setDateDebut(Temps.stringToDate("11-11-2011", "12:39:00"));
        unResultat.setDateFin(Temps.stringToDate("11-11-2011", "12:39:10"));
        unResultat.add(unSousResult2);
        
        Historique unHistorique = Historique.compilerUnHistorique("TEST1", unResultat);
        
        assertTrue("Ce n'est pas le bon historique.", unHistorique.getId().equals("TEST1") );
        assertTrue("Erreur cet historique n'est pas vert.", unHistorique.getEtat().equals(Historique.ETAT_ROUGE) );
        assertTrue("Ce n'est pas le nombre de tests corrects.", unHistorique.getNombreTests() == 10 );
        assertTrue("Ce n'est pas la vitesse correcte.", unHistorique.getVitesse() == 10000 );
                        
    }

    
    
}
