
package Alpha.Util;

import Alpha.Util.AccesFichier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @version 27 novembre 2011
 */
public class AccesFichierTest {
    
    public AccesFichierTest() {
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
    public void ecrireUnFichier() {
    
        assertTrue("Impossible d'écrire le fichier !", 
                AccesFichier.ecrireFichier("TESTecrireUnFichier.txt", "Test d'écriture", false, false) );
    
    }
    
    @Test
    public void mettreAJourUnFichier() {
    
        assertTrue("Impossible d'écrire le fichier !", 
                AccesFichier.ecrireFichier("TESTecrireUnFichier.txt", "Test d'écriture", false, false) );
        assertTrue("Impossible d'écrire à nouveau dans le fichier !", 
                AccesFichier.ecrireFichier("TESTecrireUnFichier.txt", "Test d'écriture 2", true, true) );
        
    }
}
