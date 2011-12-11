
package Alpha.Historique;

import Alpha.Util.AccesFichier;
import Alpha.Util.Temps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Gérer une collection de Historique
 * @version 5 decembre 2011
 */
public class RegistreHistorique {

    /**
     * Instance du registre (Singleton)
     */
    public static RegistreHistorique instance;

    /**
     * (Chaîne) Constante du nom du fichier de persistance
     */
    final static String FICHIER_DONNEES_HISTORIQUE = "Historique.dat";
   

    /**
     * (ArrayList<Historique>) liste des historiques
     */
    private ArrayList<Historique> listeHistoriques;
    
    /**
     *  (Chaine) du fichier de l'historique
     */
    private String fichierHistorique = FICHIER_DONNEES_HISTORIQUE;

    
    
    /**
     * Avoir qu'une seule instance du registre.
     * @return l'instance du registre existante, sinon la première instanciation.
     */
    public static RegistreHistorique getInstanceOf(){
        if( RegistreHistorique.instance == null ){
            RegistreHistorique.instance = new RegistreHistorique();
        }
        return RegistreHistorique.instance;
    }

    /**
     * Constructeur : instancie la liste d'historique avec ce qui a été sauvegardé
     */
    private RegistreHistorique()
    {
        listeHistoriques = new ArrayList();

        ArrayList<Historique> tmpListe = new ArrayList();
        
        tmpListe = chargerHistorique();
               
        if( !( tmpListe.isEmpty() ) )
            listeHistoriques = tmpListe;
        
    }

    /**
     * Créer des identifiants uniques automatiquement à partir du dernier élément dans le registre.
     * @return (Chaîne) le nouvel identifiant.
     */
    public String creerNouvelIdHistorique(){
        String nouvelId = "TEST";
        int derniereValeur = 0;
        int tampon = 0;

        if( ! (listeHistoriques.isEmpty() ) ) {

            String dernierId = "";

            Iterator it = listeHistoriques.iterator();
            Historique derniereAffectation = null;

            while( it.hasNext() ) {
                dernierId = ( (Historique)it.next() ).getId();
                tampon = Integer.valueOf( dernierId.substring( nouvelId.length() ) );
                derniereValeur = Math.max( tampon, derniereValeur );
            }

        }

        derniereValeur++;
        nouvelId += derniereValeur;

        return nouvelId;

    }
    
    
    /**
     * Modifier le fichier de l'historique
     * @param a_fichierHistorique : (chaine) nom du fichier historique
     */
    public void changerHistorique( String a_fichierHistorique )
    {
        fichierHistorique = a_fichierHistorique;
        
        listeHistoriques.clear();
        
        ArrayList<Historique> tmpListe = new ArrayList();
        
        tmpListe = chargerHistorique();
               
        if( !( tmpListe.isEmpty() ) )
            listeHistoriques = tmpListe;
    }
    

    /**
     * But : Déterminer si un Historique existe
     * @param id : (chaine) identifiant du Historique
     * @return existe : (booléen) s’il existe vrai, sinon faux.
     */
    public boolean existeHistorique( String id ) {
        boolean existe = false;

        if( id != null && !listeHistoriques.isEmpty() ){
            Iterator it = listeHistoriques.iterator();
            
            Historique unHistorique = (Historique)it.next();
            if(unHistorique.getId().equals(id))
                existe = true;
            
            while( it.hasNext() && !existe) {

                unHistorique = (Historique)it.next();
                
                if(unHistorique.getId().equals(id))
                    existe = true;

            }
        }

        return existe;
    }

    
    /**
     * But : Ajouter ou modifier un Historique dans le registre et en persistance
     * @param a_historique : (Historique) l'instance de Historique à ajouter
     * @return (Booléen) si ajouté au registre retourne vrai, sinon faux.
     */
    public boolean creerHistorique( Historique a_historique ) {
        boolean estCree = false;
        
        if( a_historique != null ){
            listeHistoriques.add(a_historique);
            enregistrer();
            estCree = true; 
        }
        return estCree;
    }

    

    /**
     * But : Obtenir un Historique du registre
     * @param id : (chaine) l'identifiant du Historique
     * @return leHistorique : (Historique) l'instance du Historique.
     */
    public Historique obtenirHistorique( String id ) {
        Historique leHistorique = null;
        if( id != null ){

            int taille = listeHistoriques.size();
            int i = 0;

            for( i=0; i<taille; i++ ){
                if( listeHistoriques.get(i).getId().equals(id) ){
                    leHistorique = listeHistoriques.get(i);
                }  
            }

        }

        return leHistorique;
    }


    /**
     * But : Enregistrer en persistance le registre complet
     * @return pasErreur (booléen) Si enregistré = vrai, sinon false
     */
    private boolean enregistrer() {

        boolean pasErreur = true;

        if( ! listeHistoriques.isEmpty() ){

            Iterator it = listeHistoriques.iterator();
            
            Historique unHistorique = (Historique)it.next();

            String enregistrement = unHistorique.toString();

            pasErreur = AccesFichier.ecrireFichier(
                    fichierHistorique, enregistrement, false, false);

            while( it.hasNext() ) {
                unHistorique = (Historique)it.next();
                enregistrement = unHistorique.toString();
                pasErreur = AccesFichier.ecrireFichier(
                    fichierHistorique, enregistrement, true, true);
            }
        }

        return pasErreur;
    }



    /**
     * But : Charger les Historiques qui sont en persistance vers la mémoire vive
     * @return listeHistoriques (ArrayList<Historique>) liste des Historiques en persistance
     */
    private ArrayList<Historique> chargerHistorique(){
        ArrayList<Historique> listeHistoriques = new ArrayList();

        ArrayList<String[]> enregistrements = new ArrayList();

        String[] listeChaine = null;

        Historique unHistorique;

        String id = new String();

        enregistrements = AccesFichier.lireFichier( fichierHistorique );

        while(! enregistrements.isEmpty() ){
            listeChaine = enregistrements.get(0);
            unHistorique = 
                    new Historique( 
                            listeChaine[0], 
                            listeChaine[1], 
                            Integer.valueOf(listeChaine[2]), 
                            Long.valueOf(listeChaine[3]), 
                            Temps.stringToDate(listeChaine[4]) );

            listeHistoriques.add(unHistorique);

            enregistrements.remove(0);

        }

        return listeHistoriques;
    }
    

    /**
     * Obtenir l'historique
     * @return (ArrayList<Historique>) listeHistoriques
     */
    public  ArrayList<Historique> getlisteHistoriques() { 
        return listeHistoriques; 
    }

    
}
