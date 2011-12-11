
package Alpha.Statistique;

/**
 *   Classe de données
 * 
 *   Contient les valeurs statistiques des historiques de tests
 */
public class Statistique {

    // Le type de statistiques qui sera enregistré ici
    String nom;
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
  
    double minimum;
    public double getMinimum() { return minimum; }
    public void setMinimum(double minimum) { this.minimum = minimum; }

    double maximum;
    public double getMaximum() { return maximum; }
    public void setMaximum(double maximum) { this.maximum = maximum; }
   
    double moyenne;
    public double getMoyenne() { return moyenne; }
    public void setMoyenne(double moyenne) { this.moyenne = moyenne; }
    
    double ecartType;
    public double getEcartType() { return ecartType; }
    public void setEcartType(double ecartType) { this.ecartType = ecartType; }
    
    double mediane;
    public double getMediane() { return mediane; }
    public void setMediane(double mediane) { this.mediane = mediane; }
    
    double pourcentile25;
    public double getPourcentile25() { return pourcentile25; }
    public void setPourcentile25(double pourcentile25) { this.pourcentile25 = pourcentile25; }
    
    double pourcentile75;
    public double getPourcentile75() { return pourcentile75; }
    public void setPourcentile75(double pourcentile75) { this.pourcentile75 = pourcentile75; }
    
}
