package mappinggid;

import mappinggid.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MyArrowDB;
import java.sql.ResultSet;

/**
 * Created by nily on 15.12.15.
 */
public class MappingGIDSpeicher {

    /** Markierung für Logging. */
    private static final String TAG = "MappingGIDSpeicher";

    /**
     * Verweis auf die MyArrow-Datenbank.
     */
    private final Connection mDb;

    /**
     * Erzeugt einen neuen ZielSpeicher.<br>
     * Dabei wird sichergestellt, dass die zugrundeliegende
     * Datenbank unmittelbar nutzbar ist.
     */
    public MappingGIDSpeicher() {
        mDb = new MyArrowDB().getInstance();
    }

    /**
     * Legt ein neues Bogen in der Datenbank an.
     *
     */
    public void insertMappingGID(
            String origtable,
            String tablename,
            String fieldname ) {
        
        PreparedStatement insertData = null;
        
        try {
            mDb.setAutoCommit(false);
            /**
             * Datensatz einfügen
             */
            System.out.println("System: insertMappingGID(): Datensatz einfügen");
            insertData = mDb.prepareStatement(MappingGIDTbl.STMT_INSERT);
            insertData.setString(1, origtable);
            insertData.setString(2, tablename);
            insertData.setString(3, fieldname);
            insertData.executeUpdate();
            mDb.commit();
        } catch (SQLException ex) {
            /**
             * Wenn Fehlercode = 1062; Duplicate, einfach ein Update
             */
            if (ex.getErrorCode()==1062000){
                try {
                    System.out.println("System: insertMappingGID(): Datensatz aktualisieren");
                    insertData = mDb.prepareStatement(MappingGIDTbl.STMT_UPDATE);
                    insertData.setString(1, origtable);
                    insertData.setString(2, tablename);
                    insertData.setString(3, fieldname);
                    insertData.executeUpdate();
                    mDb.commit();
                } catch(SQLException excep) {
                    System.out.println("System: insertMappingGID(): Error Code    = " + excep.getErrorCode());
                    System.out.println("System: insertMappingGID(): Error Message = " + excep.getMessage());
                    System.err.println(excep);
                }
            } else {
                System.out.println("System: insertMappingGID(): Error Code    = " + ex.getErrorCode());
                System.out.println("System: insertMappingGID(): Error Message = " + ex.getMessage());
                System.err.println(ex);
                if (mDb != null) {
                    try {
                        System.err.println("System: insertMappingGID(): Transaction is being rolled back");
                        mDb.rollback();
                    } catch(SQLException excep) {
                        System.out.println("System: insertMappingGID(): Error Code    = " + excep.getErrorCode());
                        System.out.println("System: insertMappingGID(): Error Message = " + excep.getMessage());
                        System.err.println(excep);
                    }
                }
            }
        } finally {
            if (insertData != null) {
                try {
                    System.out.println("System: insertMappingGID(): Transaction will be closed");
                    insertData.close();
                } catch(SQLException excep) {
                    System.err.println(excep);
                }                
            }
            try {
                System.out.print("System: insertMappingGID(): AutoCommit() switched on again");
                mDb.setAutoCommit(true);
            } catch(SQLException excep) {
                System.err.println(excep);
            }                
        }
    }

    /**
     * Speichert ein neues Ziel. Ist dieser bereits in der
     * Datenbank bekannt, wird der vorhandene Datensatz
     * geändert.<br>
     * Ansonsten wird ein neuer Datensatz erzeugt.
     *
     * @param ziel zu speichernder Schuetze.
     */
    public void insertMappingGID(MappingGID mappinggid) {
        insertMappingGID(
                mappinggid.getorigTable(),
                mappinggid.getTableName(),
                mappinggid.getFieldName());
    }

    public ArrayList<String[]> loadMappingGIDDetails(String origtable) {
        PreparedStatement queryData;
        ArrayList<String[]> loadMappingGIDDetails = new ArrayList<String[]>();
        ResultSet rs = null;
        queryData = null;
        try {
            queryData = mDb.prepareStatement(MappingGIDTbl.STMT_WHERE_ORIGTABLE_EQUALS);
            queryData.setString(1, origtable);
            rs = queryData.executeQuery();
            rs.first();
            while (!rs.isAfterLast()) {
                    System.out.println("System: loadMappingGIDDetails(): add array - " + 
                            rs.getString(MappingGIDTbl.TABLENAME) + "-" + 
                            rs.getString(MappingGIDTbl.FIELDNAME));
                    String[] tempArray = new String[] {rs.getString(MappingGIDTbl.TABLENAME), 
                                                       rs.getString(MappingGIDTbl.FIELDNAME)};
                    System.out.println("System: loadMappingGIDDetails(): add array - " + 
                            tempArray[0] + "-" + tempArray[1]);
                    loadMappingGIDDetails.add(tempArray.clone());

                    rs.next();
            }
            
            return loadMappingGIDDetails;
            
        } catch (SQLException ex) {
            System.err.println("System: loadMappingGIDDetails(): " + ex);
            if (mDb != null) {
                try {
                    System.out.println("System: loadMappingGIDDetails(): Transaction is being rolled back");
                    mDb.rollback();
                } catch(SQLException excep) {
                    System.err.println(excep);
                }
            }
            return null;
            
        } finally {
            try {
                System.out.println("System: loadMappingGIDDetails(): Alles wird geschlossen");
                if (rs != null) rs.close();
                if (queryData != null) queryData.close();
            } catch(SQLException excep) {
                System.err.println("System: loadMappingGIDDetails(): " + excep);
            }
        }
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
