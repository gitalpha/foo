
package Alpha.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * But : Classe de fonctions statiques pour manipuler le temps, des dates, la vitesse, etc.
 * @version 5 décembre 2011
 */
public class Temps {


    /**
     *
     * @param (String) strDate
     * @return (Date)la date
     */
    public static Date stringToDate( String strDate ){

        Date uneDate = null;
        DateFormat formatter ;
        try{
            formatter = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" );
            uneDate = formatter.parse( strDate );

        } catch(ParseException e){
            System.out.println("Exception :"+e);
        }

        return uneDate;

    }

    /**
     *
     * @param (String) strDate
     * @param (String) strTime
     * @return (Date) la date et l'heure
     */
    public static Date stringToDate( String strDate, String strTime ){

        Date uneDate = null;
        DateFormat formatter ;
        try{
            formatter = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" );
            uneDate = formatter.parse( strDate + " " + strTime );
        } catch(ParseException e){
            System.out.println("Exception :"+e);
        }

        return uneDate;

    }


    /**
     *
     * @param (Date) uneDate
     * @return (String) la date formattee en chaine
     */
    public static String dateToString( Date uneDate ){
        DateFormat formatter;
        String strDate = null;

        formatter = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" );

        strDate = (String)formatter.format(uneDate);

        return strDate;
    }
   
    
    /**
     * But : Calculer la vitesse de l'execution des tests.
     * @param (Date) a_debut : date du debut de l'execution
     * @param (Date) a_fin : date de la fin de l'execution
     * @return (long) la vitesse en milliseconde
     */
    public static long calculerVitesse( Date a_debut, Date a_fin ){
    
        long vitesse = a_fin.getTime() - a_debut.getTime();

        return vitesse;
    
    }
    
        


}
