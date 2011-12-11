
package Alpha.Util;

import java.text.DecimalFormat;

/**
 * Classe de fonctions statiques pour formatter des valeurs
 * @version 8 décembre 2011
 */
public class Formatage {
    
    /**
     * Formatter un nombre decimal
     * @param (double) a_nombre
     * @return (chaine) Nombre formatte
     */
    public static String formatterNombreDecimal( Double a_nombre )
    {
        if(a_nombre == null || Double.isNaN(a_nombre)) return "---";
        
        return (new DecimalFormat("0.00").format(a_nombre).toString());
    }
}
