
package Alpha.Statistique;

import Alpha.Historique.Historique;
import Alpha.Historique.RegistreHistorique;
import Alpha.Util.AccesFichier;
import Alpha.Util.Temps;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.HashMap;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

/**
 * 
 * @version 8 decembre 2011
 */
public class CalculsStatistiques {

    // Méthode utilitaire pour calculer les stats et créer le fichier
    //    -- Si le fichier existe déjà, on écrase
    public static void creerHtmlStatistique(
            RegistreHistorique rHistorique,
            String nomFichier) 
    {
    
        Statistique statsTempsExecution = CalculsStatistiques.calculerTempsExecution(rHistorique);
        
        Statistique statsNbExecutionParJour = CalculsStatistiques.calculerNbExecutionParJour(rHistorique);
        
        Statistique statsTempsTurnAround = CalculsStatistiques.calculerTempsTurnAround(rHistorique);
        
        AccesFichier.ecrireFichier(
                nomFichier,
                HTMLStatistiques.creerHTML(statsTempsExecution, statsNbExecutionParJour, statsTempsTurnAround),
                false,
                true);

        
    }
    
    
    
    public static Statistique calculerTempsExecution(RegistreHistorique rHistorique) {
        Statistique retour = new Statistique();

        DescriptiveStatistics stats = new DescriptiveStatistics();

        Collection lesHistoriques = rHistorique.getlisteHistoriques();
        Iterator it = lesHistoriques.iterator();
        Historique unHistorique;

        // Pour chaque jeux de tests dans le registre d'historique,
        // on conserve la vitesse (temps d'exécution)
        while (it.hasNext()) {

            unHistorique = (Historique) it.next();

            stats.addValue(unHistorique.getVitesse());

        }

        retour.setNom("Le temps d'exécution (en milliseconde)");
        retour.setMinimum(stats.getMin());
        retour.setMaximum(stats.getMax());
        retour.setMoyenne(stats.getMean());
        retour.setEcartType(stats.getStandardDeviation());
        retour.setMediane(stats.getPercentile(50));
        retour.setPourcentile25(stats.getPercentile(25));
        retour.setPourcentile75(stats.getPercentile(75));

        return retour;
    }

    
    
    public static Statistique calculerNbExecutionParJour(RegistreHistorique rHistorique) {
        Statistique retour = new Statistique();

        // Pour chaque jour, on additionne le nombre de jeux de tests qui ont été lancés
        HashMap<String, Integer> hashParJour = new HashMap();
        Calendar cal = Calendar.getInstance();


        Collection lesHistoriques = rHistorique.getlisteHistoriques();
        Iterator it = lesHistoriques.iterator();
        Historique unHistorique;

        while (it.hasNext()) {

            unHistorique = (Historique) it.next();

            Date dateExecution = unHistorique.getDateHeure();
            cal.setTime(dateExecution);

            // La clé est comme ceci : YYYYddd : Année + Jour de l'année (entre 1 et 365)
            String cle = "" + cal.get(Calendar.YEAR) + cal.get(Calendar.DAY_OF_YEAR);

            // +1 
            if (hashParJour.containsKey(cle)) {
                hashParJour.put(cle, (1 + hashParJour.get(cle)));
            } else {
                hashParJour.put(cle, 1);
            }

        }

        DescriptiveStatistics stats = new DescriptiveStatistics();

        Collection tousLesJours = hashParJour.values();
        Iterator it2 = tousLesJours.iterator();

        // Il ne reste qu'à compiler les statistiques
        while (it2.hasNext()) {

            Integer I = (Integer) it2.next();
            stats.addValue(I.longValue());
        }


        retour.setNom("Le nombre d'exécution par jour");
        retour.setMinimum(stats.getMin());
        retour.setMaximum(stats.getMax());
        retour.setMoyenne(stats.getMean());
        retour.setEcartType(stats.getStandardDeviation());
        retour.setMediane(stats.getPercentile(50));
        retour.setPourcentile25(stats.getPercentile(25));
        retour.setPourcentile75(stats.getPercentile(75));

        return retour;
    }

    
    
    public static Statistique calculerTempsTurnAround(RegistreHistorique rHistorique) {
        Statistique retour = new Statistique();

        DescriptiveStatistics stats = new DescriptiveStatistics();

        Collection lesHistoriques = rHistorique.getlisteHistoriques();
        Iterator it = lesHistoriques.iterator();
        Historique unHistorique;
        
        Date dateDebutRouge=null;
        Date dateDernierVert;

        // Pour chaque jeux de tests dans le registre d'historique,
        // on part un compteur si c'est rouge
        while (it.hasNext()) {

            unHistorique = (Historique) it.next();
            
            if (unHistorique.getEtat().equalsIgnoreCase(Historique.ETAT_ROUGE)
                &&
                (dateDebutRouge==null))
            {
                // On part un compteur
                dateDebutRouge = unHistorique.getDateHeure();
            }
            
            if (unHistorique.getEtat().equalsIgnoreCase(Historique.ETAT_VERT)
                &&
                (dateDebutRouge!=null))
            {
                // On arrete le compteur et on enregistre la statistique
                dateDernierVert = unHistorique.getDateHeure();
                stats.addValue(Temps.calculerVitesse(dateDebutRouge, dateDernierVert));
                dateDebutRouge=null;
            }

        }

        
        retour.setNom("Le temps de turn around (en milliseconde)");
        retour.setMinimum(stats.getMin());
        retour.setMaximum(stats.getMax());
        retour.setMoyenne(stats.getMean());
        retour.setEcartType(stats.getStandardDeviation());
        retour.setMediane(stats.getPercentile(50));
        retour.setPourcentile25(stats.getPercentile(25));
        retour.setPourcentile75(stats.getPercentile(75));

        return retour;
    }
    
 
}
