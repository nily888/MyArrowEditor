/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleanuptables;

import java.sql.Connection;
import java.sql.SQLException;

import db.MyArrowDB;
import parcour.ParcourSpeicher;
import bogen.BogenSpeicher;
import pfeil.PfeilSpeicher;
import schuetzen.SchuetzenSpeicher;
import ziel.ZielSpeicher;

/**
 *
 * @author Rene Dueber
 */
public class CleanupTables {
    
     /**
     * Markierung für Logging. 
     */
    private static final String TAG = "CleanupTables";

    /**
     * Verweis auf die MyArrow-Datenbank.
     */
    private final Connection mDb;
    
    public CleanupTables() {
        System.out.println("=====================================================================");
        System.out.println("System: Constructor(): " + TAG + " wird initialisiert...");
        System.out.println("=====================================================================");
        
        /**
         * Open Link to the MyArrow DB
         */
        mDb = new MyArrowDB().getInstance();
        
    }
    
    /**
     * Per table which field should be common to identify it's a doulbe entry
     * first only the name, the rest the user can decide.
     * 
     * @param table
     *      Table to look for duplicate
     * 
     * @return
     *      List of GID, which are double (GID, GID, Name)
     */
    public String[][] getWorklist(String table) {
        String[][] getWorklist = null;
        switch(table) {
            case "parcour":
                getWorklist = new ParcourSpeicher().checkForDuplicates().clone(); 
                break;
            case "bogen":
                getWorklist = new BogenSpeicher().checkForDuplicates().clone(); 
                break;
            case "pfeil":
                getWorklist = new PfeilSpeicher().checkForDuplicates().clone(); 
                break;
            case "schuetzen":
                getWorklist = new SchuetzenSpeicher().checkForDuplicates().clone(); 
                break;
            case "ziel":
                getWorklist = new ZielSpeicher().checkForDuplicates().clone(); 
                break;
            default:
                System.err.println("System: GetWockList(): tablename could not be mapped!");
                break;
        }
        return getWorklist;
    }
    
    /**
     * Schliesst die zugrundeliegende Datenbank.
     * <br>
     * Vor dem naechsten Zugriff muss oeffnen() aufgerufen
     * werden.
     */
    public void schliessen() {
        try {
            mDb.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}
