package ca.uqam.mgl7361.a2011.omega.framework.results;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Result {
    
    private String name;
    private ResultType resultType;
    private int numberOfSuccessfullTests = 0;
    private int numberOfFailedTests = 0;
    private List<Result> subResults = new ArrayList<Result>();
        
    // Ajout équipe ALPHA
    // Date et heure de début et de fin du test pour les statistiques
    private Date dateDebut = null;
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    
    private Date dateFin = null;
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

        
    public Result(String name, ResultType resultType,
                    int numberOfSuccessfullTests, int numberOfFailedTests) {
            super();
            this.name = name;
            this.resultType = resultType;
            this.numberOfSuccessfullTests = numberOfSuccessfullTests;
            this.numberOfFailedTests = numberOfFailedTests;
    }
    public String getName() {
            return name;
    }
    public ResultType getResultType() {
            return resultType;
    }
    public void add(Result subResult) {
            this.subResults.add(subResult);

        // Ajout équipe ALPHA
        // Date et heure de début et de fin du test pour les statistiques
        //      Ici, des suites ou des cas de tests
        // Donc, on cherche dans les sous-résultats
        // Il faut prendre note que ceci est malheureusement nécessaire à cause que les tests
        // de la librairie Omega ne sont pas exécutés dans l'ordre
        dateDebut = getDateDebut(this);
        dateFin = getDateFin(this);

    }

    public int getNumberOfSuccessfullTests() {
            return numberOfSuccessfullTests + this.getNumberOfSuccessfullTests(subResults);
    }
    public int getNumberOfFailedTests() {
            return numberOfFailedTests + this.getNumberOfFailedTests(subResults); 
    }
    public List<Result> getSubResults() {
            return subResults;
    }	
    private int getNumberOfSuccessfullTests(List<Result> subResults) {
            int numberOfSuccessfulTests = 0;
            for(Result result : subResults) {
                    // Correction de l'équipe ALPHA
                    // Remplacer =+  par  +=
                    numberOfSuccessfulTests += result.getNumberOfSuccessfullTests();
            }
            return numberOfSuccessfulTests;
    }
    private int getNumberOfFailedTests(List<Result> subResults) {
            int numberOfFailedTests = 0;
            for(Result result : subResults) {
                    // Correction de l'équipe ALPHA
                    // Remplacer =+  par  +=
                    numberOfFailedTests += result.getNumberOfFailedTests();
            }
            return numberOfFailedTests;
    }
        
        
    // Ajout équipe Alpha
    // on cherche la plus petite date de début des sous-résultats
    private Date getDateDebut(Result subResult) {
        Date retour = subResult.getDateDebut();
        for (Result result : subResult.getSubResults()) {
            Date dateSousResultat = getDateDebut(result);
            if (retour == null) {
                retour = dateSousResultat;
            } else {
                if (dateSousResultat != null && retour.after(dateSousResultat)) {
                    retour = dateSousResultat;
                }
            }
        }
        return retour;
    }

    // Ajout équipe Alpha
    // On prends la plus grande date de fin des sous-résultats
    private Date getDateFin(Result subResult) {
        Date retour = subResult.getDateFin();
        for (Result result : subResult.getSubResults()) {
            Date dateSousResultat = getDateFin(result);
            if (retour == null) {
                retour = dateSousResultat;
            } else {
                if (dateSousResultat != null && retour.before(dateSousResultat)) {
                    retour = dateSousResultat;
                }
            }
        }
        return retour;
    }

}
