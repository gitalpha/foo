

package Alpha.Statistique;

import java.util.Iterator;
import java.util.Collection;
import Alpha.Historique.Historique;
import java.util.Date;
import Alpha.Util.Temps;
import Alpha.Historique.RegistreHistorique;

import Alpha.Util.AccesFichier;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  TDD pour le package Alpha.Statistique;
 */
public class StatistiqueTest {
    

    public StatistiqueTest() {
    }
    
    RegistreHistorique rHistoriqueSimple;
    RegistreHistorique rHistoriqueComplexe;
    
    
    
    /**
     * Preparer un historique simple pour les tests
     */
    public void construireHistoriqueSimple()
    {

        // On crée un registre historique simple, qui va servir à quelques tests
        AccesFichier.viderFichier("Histo_StatsTestSimple.dat");
        rHistoriqueSimple = RegistreHistorique.getInstanceOf();
        rHistoriqueSimple.changerHistorique("Histo_StatsTestSimple.dat");
        
        // =====================================================
        // 1er jeu
        String testId = "JeuxTest001";
        String testEtat = Historique.ETAT_VERT;
        int testNombreTests = 10;
                                         // 1 minute ! = 60 secondes        
        Date uneDateDebut = Temps.stringToDate("27-11-2011", "12:12:00");
        Date uneDateFin = Temps.stringToDate("27-11-2011", "12:13:00");
        long testVitesse = Temps.calculerVitesse(uneDateDebut, uneDateFin);

        Historique unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, uneDateDebut );
        
        rHistoriqueSimple.creerHistorique( unHistorique );

        
        // =====================================================
        // 2e jeu
        testId = "JeuxTest002";
        testEtat = Historique.ETAT_ROUGE;
        testNombreTests = 20;
                                         // 2 minutes ! = 120 secondes        
        uneDateDebut = Temps.stringToDate("28-11-2011", "12:15:00");
        uneDateFin = Temps.stringToDate("28-11-2011", "12:17:00");
        testVitesse = Temps.calculerVitesse(uneDateDebut, uneDateFin);

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, uneDateDebut );
        
        rHistoriqueSimple.creerHistorique( unHistorique );
        
        
        // =====================================================
        // 3e jeu
        testId = "JeuxTest003";
        testEtat = Historique.ETAT_VERT;
        testNombreTests = 30;
                                         // 3 minutes ! = 180 secondes
        uneDateDebut = Temps.stringToDate("29-11-2011", "12:18:00");
        uneDateFin = Temps.stringToDate("29-11-2011", "12:21:00");
        testVitesse = Temps.calculerVitesse(uneDateDebut, uneDateFin);

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, uneDateDebut );
        
        rHistoriqueSimple.creerHistorique( unHistorique );        
    }
   
    
    
    /**
     * Preparer un historique complexe pour les tests
     */
    public void construireHistoriqueComplexe()
    {

        // On crée un registre historique complexe, qui va servir à quelques tests
        //
        //  Essentiellement pour tester le : temps de turn around : Rouge --> Vert
        //
        AccesFichier.viderFichier("Histo_StatsTestComplexe.dat");
        rHistoriqueComplexe = RegistreHistorique.getInstanceOf();
        rHistoriqueComplexe.changerHistorique("Histo_StatsTestComplexe.dat");
               
        Date uneDateDebut = Temps.stringToDate("27-11-2011", "12:12:00");
        Date uneDateFin = Temps.stringToDate("27-11-2011", "12:13:00");
        long testVitesse = Temps.calculerVitesse(uneDateDebut, uneDateFin);
        int testNombreTests = 10;
        
        
        
        // =====================================================
        // 1er jeu
        String testId = "JeuxTest001";
        String testEtat = Historique.ETAT_VERT;
        Date dateDuTest = Temps.stringToDate("27-11-2011", "12:00:00");

        Historique unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, dateDuTest );
        rHistoriqueComplexe.creerHistorique( unHistorique );

        
        // =====================================================
        // 2e jeu
        testId = "JeuxTest002";
        testEtat = Historique.ETAT_ROUGE;  // ------------------------------> ROUGE !! 10 minutes de turn around
        dateDuTest = Temps.stringToDate("27-11-2011", "12:10:00"); // + 10 minutes

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, dateDuTest );
        rHistoriqueComplexe.creerHistorique( unHistorique );
        
        
        // =====================================================
        // 4e jeu
        testId = "JeuxTest004";
        testEtat = Historique.ETAT_VERT;
        dateDuTest = Temps.stringToDate("27-11-2011", "12:20:00"); // + 10 minutes

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, dateDuTest );
        rHistoriqueComplexe.creerHistorique( unHistorique );        
        
        
        // =====================================================
        // 5e jeu
        testId = "JeuxTest005";
        testEtat = Historique.ETAT_ROUGE;  // ------------------------------> ROUGE !! 30 minutes de turn around
        dateDuTest = Temps.stringToDate("27-11-2011", "12:30:00"); // + 10 minutes

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, dateDuTest );
        rHistoriqueComplexe.creerHistorique( unHistorique );        
        
        
        // =====================================================
        // 6e jeu
        testId = "JeuxTest006";
        testEtat = Historique.ETAT_ROUGE;
        dateDuTest = Temps.stringToDate("27-11-2011", "12:40:00"); // + 10 minutes

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, dateDuTest );
        rHistoriqueComplexe.creerHistorique( unHistorique );        
        
        
        // =====================================================
        // 7e jeu
        testId = "JeuxTest007";
        testEtat = Historique.ETAT_ROUGE;
        dateDuTest = Temps.stringToDate("27-11-2011", "12:50:00"); // + 10 minutes

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, dateDuTest );
        rHistoriqueComplexe.creerHistorique( unHistorique );        
        
        
        // =====================================================
        // 8e jeu
        testId = "JeuxTest008";
        testEtat = Historique.ETAT_VERT;
        dateDuTest = Temps.stringToDate("27-11-2011", "13:00:00"); // + 10 minutes

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, dateDuTest );
        rHistoriqueComplexe.creerHistorique( unHistorique );        
        
        
        // =====================================================
        // 9e jeu
        testId = "JeuxTest009";
        testEtat = Historique.ETAT_ROUGE;  // ------------------------------> ROUGE !!  0 minutes On ne compte pas celui-ci
        dateDuTest = Temps.stringToDate("27-11-2011", "13:10:00"); // + 10 minutes

        unHistorique = new Historique(testId, testEtat, testNombreTests, testVitesse, dateDuTest );
        rHistoriqueComplexe.creerHistorique( unHistorique );        
    }
   
  

    /**
     * Test de lecture du fichier d'historique simple
     */
    @Test
    public void testLectureFichierHistoriqueSimple() {
        
        construireHistoriqueSimple();

        Collection lesHistoriques = rHistoriqueSimple.getlisteHistoriques();
        Iterator it = lesHistoriques.iterator();
        Historique unHistoriqueLu;
        int nbHisto = 0;
        int nbVert = 0;
        int nbTestsTotaux = 0;
        long tempsExecutionTotal = 0;

        while (it.hasNext()) {

            unHistoriqueLu = (Historique) it.next();
            nbHisto++;
            
            if (unHistoriqueLu.getEtat().equalsIgnoreCase(Historique.ETAT_VERT)) nbVert++;
            
            nbTestsTotaux += unHistoriqueLu.getNombreTests();
            tempsExecutionTotal += unHistoriqueLu.getVitesse();

        }

        assertTrue (nbHisto == 3);
        assertTrue (nbVert == 2);
        assertTrue (nbTestsTotaux == 60);  // 10+20+30
        assertTrue (tempsExecutionTotal == 360000);  // 1 + 2 + 3 minutes

    }
    
    

    /**
     * Test des statistiques des temps d'execution
     */
    @Test
    public void testCalculerTempsExecution() {
        
        construireHistoriqueSimple();

        Statistique stats = CalculsStatistiques.calculerTempsExecution(rHistoriqueSimple);
        
        assertTrue(stats.getMinimum() == 60000);
        assertTrue(stats.getMaximum() == 180000);
        assertTrue(stats.getMoyenne() == 120000);
        assertTrue(stats.getEcartType() == 60000);
        assertTrue(stats.getMediane() == 120000);
        assertTrue(stats.getPourcentile25() == 60000);
        assertTrue(stats.getPourcentile75() == 180000);
        
        
        
    }
    
    

    /**
     * Test des statistiques du nombre d'execution par jour
     */
    @Test
    public void testCalculerNbExecutionParJour() {
        
        construireHistoriqueSimple();

        Statistique stats = CalculsStatistiques.calculerNbExecutionParJour(rHistoriqueSimple);
        
        assertTrue(stats.getMinimum() == 1);
        assertTrue(stats.getMaximum() == 1);
        assertTrue(stats.getMoyenne() == 1);
        assertTrue(stats.getEcartType() == 0);
        assertTrue(stats.getMediane() == 1);
        assertTrue(stats.getPourcentile25() == 1);
        assertTrue(stats.getPourcentile75() == 1);
        
    }
    
    

    /**
     * Test des statistiques du temps de turnAround : 
     *    Passage du Rouge  -->  Vert 
     */
    @Test
    public void testCalculerTempsTurnAround() {
    
        construireHistoriqueComplexe();

        Statistique stats = CalculsStatistiques.calculerTempsTurnAround(rHistoriqueComplexe);
        
        assertTrue(stats.getMinimum() ==  600000);
        assertTrue(stats.getMaximum() == 1800000);
        assertTrue(stats.getMoyenne() == 1200000);
//        assertTrue(stats.getEcartType() == ??);
        assertTrue(stats.getMediane() == 1200000);
        assertTrue(stats.getPourcentile25() ==  600000);
        assertTrue(stats.getPourcentile75() == 1800000);
        
    }
    
    

    @Test
    public void testVerifierHTMLStatistiques() {
    
        
        construireHistoriqueSimple();
        Statistique statsTempsExecution = CalculsStatistiques.calculerTempsExecution(rHistoriqueSimple);
        
        construireHistoriqueSimple();
        Statistique statsNbExecutionParJour = CalculsStatistiques.calculerNbExecutionParJour(rHistoriqueSimple);
        
        construireHistoriqueComplexe();
        Statistique statsTempsTurnAround = CalculsStatistiques.calculerTempsTurnAround(rHistoriqueComplexe);
        
        AccesFichier.ecrireFichier(
                "testVerifHTML.html",
                HTMLStatistiques.creerHTML(statsTempsExecution, statsNbExecutionParJour, statsTempsTurnAround),
                false,
                true);
        
    }
    
    
    
    /**
     * Test de la fonction creerHtmlStatistique
     */
    @Test
    public void testSortieHTML() {

        construireHistoriqueComplexe();
        CalculsStatistiques.creerHtmlStatistique(rHistoriqueComplexe, "testUtilitaire.html");

    }
    
}
