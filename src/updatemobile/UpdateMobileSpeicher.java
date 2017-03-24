package updatemobile;

import updatemobile.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MyArrowDB;
import java.sql.ResultSet;

/**
 * Created by René Düber on 24.03.2017
 */
public class UpdateMobileSpeicher {

    /** Markierung für Logging. */
    private static final String TAG = "UpdateMobileSpeicher";

    /**
     * Verweis auf die MyArrow-Datenbank.
     */
    private final Connection mDb;

    /**
     * Erzeugt einen neuen ZielSpeicher.<br>
     * Dabei wird sichergestellt, dass die zugrundeliegende
     * Datenbank unmittelbar nutzbar ist.
     */
    public UpdateMobileSpeicher() {
        mDb = new MyArrowDB().getInstance();
    }

    /**
     * Legt ein neues Bogen in der Datenbank an.
     *
     */
    public void insertUpdateMobile(
            String tablename,
            String fieldname,
            String old_gid,
            String new_gid) {
        
        PreparedStatement insertData = null;
        
        try {
            mDb.setAutoCommit(false);
            /**
             * Datensatz einfügen
             */
            System.out.println("System: insertUpdateMobile(): Datensatz einfügen");
            insertData = mDb.prepareStatement(UpdateMobileTbl.STMT_INSERT);
            insertData.setString(1, tablename);
            insertData.setString(2, fieldname);
            insertData.setString(3, old_gid);
            insertData.setString(4, new_gid);
            insertData.setInt(5, 0);
            insertData.executeUpdate();
            mDb.commit();
        } catch (SQLException ex) {
            /**
             * Wenn Fehlercode = 1062; Duplicate, einfach ein Update
             */
            if (ex.getErrorCode()==10620000){
                try {
                    System.out.println("System: insertUpdateMobile(): Datensatz aktualisieren");
                    insertData = mDb.prepareStatement(UpdateMobileTbl.STMT_UPDATE);
                    insertData.setString(1, tablename);
                    insertData.setString(2, fieldname);
                    insertData.setString(3, old_gid);
                    insertData.setString(4, new_gid);
                    insertData.setInt(5, 0);
                    insertData.executeUpdate();
                    mDb.commit();
                } catch(SQLException excep) {
                    System.out.println("System: insertUpdateMobile(): Error Code    = " + excep.getErrorCode());
                    System.out.println("System: insertUpdateMobile(): Error Message = " + excep.getMessage());
                    System.err.println(excep);
                }
            } else {
                System.out.println("System: insertUpdateMobile(): Error Code    = " + ex.getErrorCode());
                System.out.println("System: insertUpdateMobile(): Error Message = " + ex.getMessage());
                System.err.println(ex);
                if (mDb != null) {
                    try {
                        System.err.println("System: insertUpdateMobile(): Transaction is being rolled back");
                        mDb.rollback();
                    } catch(SQLException excep) {
                        System.out.println("System: insertUpdateMobile(): Error Code    = " + excep.getErrorCode());
                        System.out.println("System: insertUpdateMobile(): Error Message = " + excep.getMessage());
                        System.err.println(excep);
                    }
                }
            }
        } finally {
            if (insertData != null) {
                try {
                    System.out.println("System: insertUpdateMobile(): Transaction will be closed");
                    insertData.close();
                } catch(SQLException excep) {
                    System.err.println(excep);
                }                
            }
            try {
                System.out.println("System: insertUpdateMobile(): AutoCommit() switched on again");
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
     * @param updatemobile zu speichernder Datensatz zum Aktualisieren.
     */
    public void insertUpdateMobile(UpdateMobile updatemobile) {
        insertUpdateMobile(
                updatemobile.getTableName(),
                updatemobile.getFieldName(),
                updatemobile.getoldGID(),
                updatemobile.getnewGID());
    }

    public UpdateMobile loadUpdateMobileDetails(String gid) {
        PreparedStatement queryData;
        ResultSet rs = null;
        queryData = null;
        try {
            queryData = mDb.prepareStatement(UpdateMobileTbl.STMT_WHERE_ID_EQUALS);
            queryData.setString(1, gid);
            rs = queryData.executeQuery();
            rs.last();
            if (rs.getRow() == 1) {
                UpdateMobile loadUpdateMobileDetails = new UpdateMobile();
                loadUpdateMobileDetails.setID(rs.getInt(UpdateMobileTbl.ID));
                loadUpdateMobileDetails.setTableName(rs.getString(UpdateMobileTbl.TABLENAME));
                loadUpdateMobileDetails.setFieldName(rs.getString(UpdateMobileTbl.FIELDNAME));
                loadUpdateMobileDetails.setoldGID(rs.getString(UpdateMobileTbl.OLD_GID));
                loadUpdateMobileDetails.setnewGID(rs.getString(UpdateMobileTbl.NEW_GID));
                loadUpdateMobileDetails.setTransfered(rs.getInt(UpdateMobileTbl.TRANSFERED));
                return loadUpdateMobileDetails;
            } else {
                System.err.println("System: loadUpdateMobileDetails(): Treffer nicht eindeutig = " + rs.getRow());
            }
            
        } catch (SQLException ex) {
            System.err.println("System: loadUpdateMobileDetails(): " + ex);
            if (mDb != null) {
                try {
                    System.out.println("System: loadUpdateMobileDetails(): Transaction is being rolled back");
                    mDb.rollback();
                } catch(SQLException excep) {
                    System.err.println(excep);
                }
            }
            return null;
            
        } finally {
            try {
                System.out.println("System: loadUpdateMobileDetails(): Alles wird geschlossen");
                if (rs != null) rs.close();
                if (queryData != null) queryData.close();
            } catch(SQLException excep) {
                System.err.println("System: loadUpdateMobileDetails(): " + excep);
            }
        }
        return null;
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