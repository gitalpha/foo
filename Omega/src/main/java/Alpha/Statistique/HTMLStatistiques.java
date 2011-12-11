
package Alpha.Statistique;

import Alpha.Util.Formatage;

/**
 *  @version 8 decembre 2011
 */
public class HTMLStatistiques {
    
    
    /**
     * Creer les balises HTML des statistiques
     * @param a_statistiqueTempsExecution
     * @param a_statistiqueNbParJour
     * @param a_statistiqueTempsTurnAround
     * @return (chaine) les balises HTML
     */
    public static String creerHTML(
                Statistique a_statistiqueTempsExecution,
                Statistique a_statistiqueNbParJour,
                Statistique a_statistiqueTempsTurnAround)
    {
        String balisesHTML = null;
        
        if(a_statistiqueTempsExecution == null || a_statistiqueNbParJour == null || a_statistiqueTempsTurnAround == null){
            System.err.println("L'une des statistiques n'est pas valide.");
        }else{
                    
            balisesHTML = 
                "<html>" +
                        creerHTMLDUneStatistique(a_statistiqueTempsExecution) +
                        creerHTMLDUneStatistique(a_statistiqueNbParJour) +
                        creerHTMLDUneStatistique(a_statistiqueTempsTurnAround) +               
                "</html>";
        }
        return balisesHTML;
    }    
    
    
    
    /**
     * Creer les balises HTML pour une statistique donnee
     * @param a_statistique
     * @return (chaine) les balises HTML
     */
    public static String creerHTMLDUneStatistique(Statistique a_statistique)
    {
        String balisesHTML = null;
        
        if(a_statistique == null){
            System.err.println("Cette statistique n'est pas valide.");
            
        }else{

            balisesHTML =
                "<hr/> " +
                   "<b>" +
                       a_statistique.getNom() +
                   "</b>" +
                "<table border='1' CELLPADDING='4'>" +
                    "<tr>" +
                        "<th align='center'>" +
                           "Minimum" +
                        "</th>" +
                        "<th align='center'>" +
                           "Maximum" +
                        "</th>" +
                        "<th align='center'>" +
                           "Moyenne" +
                        "</th>" +
                        "<th align='center'>" +
                           "Ecart Type" +
                        "</th>" +
                        "<th align='center'>" +
                            "Pourcentile 25" +
                        "</th>" +
                        "<th align='center'>" +
                           "Mediane" +
                        "</th>" +
                        "<th align='center'>" +
                           "Pourcentile 75" +
                        "</th>" +
                    "</tr>" +
                    "<tr>" +
                        "<td align='center'>" +
                           Formatage.formatterNombreDecimal(a_statistique.getMinimum()) +
                        "</td>" +
                        "<td align='center'>" +
                           Formatage.formatterNombreDecimal(a_statistique.getMaximum()) +
                        "</td>" +
                        "<td align='center'>" +
                           Formatage.formatterNombreDecimal(a_statistique.getMoyenne()) +
                        "</td>" +
                        "<td align='center'>" +
                           Formatage.formatterNombreDecimal(a_statistique.getEcartType()) +
                        "</td>" +
                        "<td align='center'>" +
                           Formatage.formatterNombreDecimal(a_statistique.getPourcentile25()) +
                        "</td>" +
                        "<td align='center'>" +
                           Formatage.formatterNombreDecimal(a_statistique.getMediane()) +
                        "</td>" +
                        "<td align='center'>" +
                           Formatage.formatterNombreDecimal(a_statistique.getPourcentile75()) +
                        "</td>" +
                    "</tr>" +
                "</table>" +
                "<hr/><br/> <br/> ";  
        }
        return balisesHTML;
    }
 
}
