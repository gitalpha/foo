
package Alpha.Historique;

import Alpha.Util.Temps;
import ca.uqam.mgl7361.a2011.omega.framework.results.Result;
import java.util.Date;

/**
 * Contenir les informations qui forment un historique.
 * @version 8 decembre 2011
 */
public class Historique {

        
    /**
     * (Chaîne) Constante de l'état des tests si echec
     */
    final public static String ETAT_ROUGE = "Rouge";
    
    /**
     * (Chaîne) Constante de l'état des tests si reussi a 100%
     */
    final public static String ETAT_VERT = "Vert"; 
    
    
    /**
     * (Chaîne) identifiant
     */
    private String id;
   
    /**
     * (Chaîne) etat 
     */
    private String etat;  

    /**
     * (Entier) nombreTests
     */
    private int nombreTests;

    /**
     * (Entier long) vitesse
     */
    private long vitesse;    

    /**
     * (Date) date et heure 
     */
    private Date dateHeure;    


    /**
     * Constructeur : instancie tous les membres de l'objet
     * @param a_id : (Chaîne) identifiant de l'historique
     * @param a_etat : (Chaine) Etat des tests
     * @param a_nombreTests : (Entier) nombre de tests
     * @param a_vitesse : (Entier long) vitesse des tests
     * @param a_dateHeure : (Date) date et heure de l'exécution
     */
    public Historique( String a_id, String a_etat, int a_nombreTests, long a_vitesse, Date a_dateHeure ) {
        id = a_id;
        etat = a_etat;
        nombreTests = a_nombreTests;
        vitesse = a_vitesse;
        dateHeure = a_dateHeure;
    }
    
    /**
     * Constructeur : instancie l'objet avec seulement l'id
     * @param a_id : (Chaîne) identifiant de l'historique
     */
    public Historique( String a_id ) {
        this( a_id, "", 0, 0, new Date() );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }    
    
    public int getNombreTests() {
        return nombreTests;
    }

    public void setNombreTests(int nombreTests) {
        this.nombreTests = nombreTests;
    }    
    
    public long getVitesse() {
        return vitesse;
    }

    public void setVitesse(long vitesse) {
        this.vitesse = vitesse;
    }
    
    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }
    
    

    /**
     * Vérifier l'égalité entre deux Historiques via l'id
     * @param objet (Object) un objet Historique.
     * @return egal (Booléen) si égaux retourne vrai, sinon faux.
     */
    public boolean equals (Object objet){
        Historique unHistorique;
        boolean egal = false;
        if(objet instanceof Historique){
            unHistorique = (Historique) objet;
            if( this.getId().equals( unHistorique.getId() ) )
                egal = true;
        }
        return egal;
    }

    public String toString(){
        return this.getId() + ";" + this.getEtat() + ";" + String.valueOf(this.getNombreTests()) + ";" + String.valueOf(this.getVitesse()) + ";" + Temps.dateToString( this.getDateHeure() ) + ";";
    }
    
    
    /**
     * Compiler une instance Historique
     * @param a_identifiant
     * @param a_resultats
     * @return (Historique) l'historique compilé
     */
    public static Historique compilerUnHistorique( String a_identifiant, Result a_resultats ){
       
       Historique unHistorique = null;
       String etatTest = "";
       int nombreTotalTests = 0;
       

       if ( a_resultats == null ){
           System.err.println("Il n'y a aucun résultat pour ces tests.");
       }
       else if( a_identifiant == null ){
           System.err.println("L'identifiant de l'historique n'est pas valide.");
       }
       else{

            etatTest = evaluerEtatHistorique(a_resultats.getNumberOfFailedTests());

            nombreTotalTests = calculerNombreTotalTests(a_resultats.getNumberOfFailedTests(), a_resultats.getNumberOfSuccessfullTests());

            unHistorique = new Historique( a_identifiant, etatTest, nombreTotalTests, Temps.calculerVitesse(a_resultats.getDateDebut(), a_resultats.getDateFin()), a_resultats.getDateDebut());
       }
       
       return unHistorique;
    }
 

    /**
     * 
     * @param a_nombreTestsEchoues
     * @return (String) etat du test (Rouge ou Vert)
     */
    private static String evaluerEtatHistorique( int a_nombreTestsEchoues ){
        
        String etatTest = null;
        
        if( a_nombreTestsEchoues < 0 ){
            System.err.println("Ce nombre de tests est invalide.");
        }else{
            if( a_nombreTestsEchoues == 0 )
            {
                etatTest = ETAT_VERT;
            }else{
                etatTest = ETAT_ROUGE;
            }
        }

        return etatTest;
        
    }
    
    /**
     * 
     * @param nombreTestsEchoues
     * @param nombreTestsReussis
     * @return (int) nombre Total de Tests
     */
    private static int calculerNombreTotalTests( int nombreTestsEchoues, int nombreTestsReussis ){
        
        int nombreTotalTests = 0;
        
        nombreTotalTests = nombreTestsEchoues + nombreTestsReussis;
                
        return nombreTotalTests;
        
    }

}
