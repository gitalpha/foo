
package Alpha.Util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * But : Gérer la persistance dans des fichiers
 * @version 20 novembre 2011
 */
public class AccesFichier {

    /**
     * (DataOutputStream) flux de données en sortie
     */
    public static DataOutputStream sortie;

    /**
     * But : Écrire dans un fichier.
     * @param nomFichier (Chaîne) nom du fichier de persistance.
     * @param ligneDonnees (Chaîne) ligne de données à écrire.
     * @param estModeAjout (Booléen) témoin du mode d'ajout d'info dans le fichier.
     * @param estNouvelleLigne (Booléen) témoin d'ajout d'une nouvelle ligne.
     * @return pasErreur (Booléen) si l'écriture sans erreur retourne vrai, sinon faux.
     */
    public static boolean ecrireFichier(
            String nomFichier, String ligneDonnees,
            boolean estModeAjout, boolean estNouvelleLigne) {

        boolean pasErreur = true;
  
// Trace        
// System.out.println(nomFichier +", "+ ligneDonnees +", "+  estModeAjout +", "+  estNouvelleLigne);

        if (estNouvelleLigne) {
            ligneDonnees = "\n" + ligneDonnees;
        }

        try {
            File fichier = new File( nomFichier );

            if (estModeAjout) {
            sortie = new DataOutputStream(
                    new FileOutputStream( nomFichier, true ));
            } else {
            sortie = new DataOutputStream(
                    new FileOutputStream( fichier ));
            }


            sortie.writeBytes( ligneDonnees );
            sortie.close();

        } catch ( FileNotFoundException ex ) {
            pasErreur = false;
        }
        catch ( IOException ex ) {
            pasErreur = false;
        }

        return pasErreur;

    }


    /**
     * Lire le contenu du fichier de persistance.
     * @param a_nomFichier (Chaîne) nom du fichier.
     * @return donnees (ArrayList<String[]>) Liste de tableaux de chaînes des informations découpées
     *      (Elle sera vide s'il n'y a aucune donnée et s'il y a une erreur de récupération).
     */
    public static ArrayList<String[]> lireFichier( String a_nomFichier ) {
        String ligneDonnees = null;
        ArrayList<String[]> donnees = new ArrayList();
        String[] listeParametres = null;

        try {
            File fichier = new File( a_nomFichier );
            BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                  new FileInputStream( fichier )));

            ligneDonnees = br.readLine();
            while( ligneDonnees != null && !( ligneDonnees.equals("") ) ){
                listeParametres = extraireDonnees( ligneDonnees );
                donnees.add( listeParametres );
                ligneDonnees = br.readLine();
            }

            br.close();

        } catch ( FileNotFoundException ex ) {
            donnees.clear();
        }
        catch ( IOException ex ) {
            donnees.clear();
        }

        return donnees;

    }

    /**
     * Extraire les données de la chaîne selon le caractère de séparation.
     * @param a_ligneEnregistrement (Chaîne) La chaîne à découper.
     * @return listeParametres (Tableau de chaînes) les paramètres séparés.
     */
    public static String[] extraireDonnees( String a_ligneEnregistrement ){
        String[] listeParametres = null;

        if(a_ligneEnregistrement != null){
            listeParametres = a_ligneEnregistrement.split(";");
        }

        return listeParametres;

    }

    /**
     * Vider le fichier de persistance.
     * @param a_nomFichier (Chaîne) nom du fichier
     * @return vide (Booléen) si vidé retourne vrai, sinon faux.
     */
    public static boolean viderFichier( String a_nomFichier ){
        boolean vide = false;

        vide = ecrireFichier( a_nomFichier, "", false, false );

        return vide;
    }


}

