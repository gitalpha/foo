
package Alpha.Historique;

import Alpha.Util.AccesFichier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Alpha.Util.Temps;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * @version 8 decembre 2011
 */

public class RegistreHistoriqueTest {
    
    RegistreHistorique rHistorique;
    
    public RegistreHistoriqueTest() {
        
        rHistorique = RegistreHistorique.getInstanceOf();
        
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
    public void testHistoriqueNExistePas() {
        rHistorique.changerHistorique("Historique_TestRegistreHistorique.dat");
        assertFalse("Le historique inexistant a été trouvé.", rHistorique.existeHistorique("TEST9999") );
    }

    @Test
    public void testHistoriqueExiste() {
        rHistorique.changerHistorique("Historique_TestRegistreHistorique.dat");
        Historique historique = new Historique( "TEST0001" );
        rHistorique.creerHistorique( historique );
        assertTrue( "Le historique 1 n'a pas été trouvé.", rHistorique.existeHistorique("TEST0001") );
    }

    @Test
    public void testObtenirHistoriqueComplet() {
        rHistorique.changerHistorique("Historique_TestRegistreHistorique.dat");
        String testId = "TEST7777";
        
        String testEtat = Historique.ETAT_VERT;
        
        int testNombreTests = 10;
        
        Date uneDateDebut = Temps.stringToDate("27-11-2011", "12:12:00");
        Date uneDateFin = Temps.stringToDate("27-11-2011", "12:13:00");
        long testVitesse = Temps.calculerVitesse(uneDateDebut, uneDateFin);
        
        Date testDate = Temps.stringToDate("27-11-2011", "12:12:00");
        
        
        Historique unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, testDate );
        
        rHistorique.creerHistorique( unHistorique );
        
        assertEquals( "Le historique 7777 n'a pas été obtenu.", unHistorique, rHistorique.obtenirHistorique( unHistorique.getId() ) );

    }

    @Test
    public void testcreerDesHistoriques() {
        rHistorique.changerHistorique("Historique_TestRegistreHistorique.dat");
        rHistorique.creerHistorique( new Historique( "TEST0004" ) );

        rHistorique.creerHistorique( new Historique( "TEST0005" ) );
   
        Historique historique = new Historique( "TEST0006" );
        rHistorique.creerHistorique( historique );

        assertTrue( "Le 4e historique n'a pas été trouvé.", rHistorique.existeHistorique("TEST0004") );
        assertTrue( "Le 5e historique n'a pas été trouvé.", rHistorique.existeHistorique("TEST0005") );
        assertTrue( "Le 6e historique n'a pas été trouvé.", rHistorique.existeHistorique("TEST0006") );
        
        assertEquals( "Le 6e historique n'a pas été enregistré.", historique, rHistorique.obtenirHistorique( historique.getId() ) );

    }


    
    @Test
    public void testChangerHistorique() {
        // Historique_CasSimple.dat est un fichier déjà existant
        rHistorique.changerHistorique("Historique_CasSimple.dat");

        assertTrue( "Le cas 5 n'existe pas.", rHistorique.existeHistorique("TEST5") );
        assertTrue( "Le cas 5 n'est pas vert !", rHistorique.obtenirHistorique("TEST5").getEtat().equals("Vert") );
       
    }
    
    
    @Test
    public void testObtenirHistorique() {
        // Historique_CasSimple.dat est un fichier déjà existant
        rHistorique.changerHistorique("Historique_CasSimple.dat");

        ArrayList<Historique> historique = rHistorique.getlisteHistoriques();
        Iterator it = historique.iterator();
        
        Historique unHistorique;
        
        int index = 1;

        while( it.hasNext() ) {
            unHistorique = (Historique)it.next();
            assertTrue("L'identifiant trouve n'est pas valide : TEST"+index, unHistorique.getId().equals("TEST"+index) );
            index++;
        }

    }
    
    
    @Test
    public void testCreerNouvelIdHistorique_MemeId() {
        // Le fichier n'existe pas ou est vide
        rHistorique.changerHistorique("Historique_vide1.dat");
        
        String id1 = rHistorique.creerNouvelIdHistorique();
        
        String id2 = rHistorique.creerNouvelIdHistorique();
        
        assertEquals("Ces identifiants ne sont pas le même !", id1, id2);     
        
    }
    
    
    @Test
    public void testCreerNouvelIdHistorique_IdDifferents() {
        // Le fichier n'existe pas ou est vide
        rHistorique.changerHistorique("Historique_vide2.dat");
        
        String id1 = rHistorique.creerNouvelIdHistorique();
        
        Historique historique = new Historique( id1 );
        rHistorique.creerHistorique( historique );
        
        String id2 = rHistorique.creerNouvelIdHistorique();
               
        assertFalse( "L'identifiant est le meme ?!", id1.equals(id2) );

        AccesFichier.viderFichier("Historique_vide2.dat");
    }
    
}
