package db;

import java.sql.*;

public class MyArrowDB {
    // TODO: Indicies erstellen

    /** Markierung für Logging. */
    private static final String TAG = "MyArrowDB";

    private static final int DATENBANK_VERSION = 1;

    private Connection sINSTANCE;

    private static final Object sLOCK = "";

    /**
     * Die Datenbank kann nur nach Kenntnis "ihrer" Anwendung
     * verwaltet werden. Daher muss der Context der Anwendung
     * uebergeben werden.<br>
     * Der Constructor darf nur von getInstance aufgerufen werden,
     * um eine Mehrfach-Instanziierung zu verhindern.
     *
     * @param context
     *          Context der aufrufenden Anwendung.
     */
    public MyArrowDB() {
        System.out.println("=====================================================================");
        System.out.println("System: Constructor(): " + TAG + " wird initialisiert...");
        System.out.println("=====================================================================");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("System: " + TAG + ": Class.forName - Done");
            sINSTANCE = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/myarrow?autoReconnect=true&useSSL=false",
                    "root",
                    "satelindo" );
            System.out.println("System: " + TAG + ": getConnection() - Done");
        } catch( SQLException sqlex ) {
            System.out.println("SQLException: " + sqlex.getMessage());
            System.out.println("SQLState: " + sqlex.getSQLState());
            System.out.println("VendorError: " + sqlex.getErrorCode());
        } catch( Exception ex ) {
            System.out.println(ex);
        }
    }

    /**
     * Die Datenbank kann nur nach Kenntnis "ihrer" Anwendung
     * verwaltet werden. Daher muss der Context der Anwendung
     * uebergeben werden.
     *
     * @return Das <i>eine</i> Exemplar der MyArrow-Datenbank,
     *    das in der Anwendung verwendet werden darf.
     */
    public Connection getInstance() {
        System.out.println("System: MyArrowDB(): getInstance() - Start");
        if( sINSTANCE == null ) {
            synchronized(sLOCK) {
                if( sINSTANCE == null ) {
                    new MyArrowDB();
                }
            }
        }
        return sINSTANCE;
    }

    public boolean close() {
        try {
            if( sINSTANCE != null ) sINSTANCE.close();
            return true;
        } catch( Exception ex ) {
            System.out.println(ex);
            return false;
        }
    }

    public Boolean insertDataset(
                PreparedStatement insertData,
                PreparedStatement updateData) {

        Boolean insertDataset = false;
        Connection mDb=null;
        try {
            /**
             * Link zur Datenbank holen
             */
            mDb = insertData.getConnection();
            /**
             * AutoCommit abschalten
             */
            mDb.setAutoCommit(false);
            /**
             * Datensatz einfügen
             */
            System.out.println("System: insertDataset(): Datensatz einfügen");
            insertData.executeUpdate();
            mDb.commit();
            insertDataset = true;
        } catch (SQLException ex) {
            /**
             * Wenn Fehlercode = 1062; Duplicate, einfach ein Update ausprobieren
             */
            if (ex.getErrorCode()==1062){
                try {
                    System.out.println("System: insertDataset(): Datensatz aktualisieren");
                    updateData.executeUpdate();
                    mDb.commit();
                    insertDataset = true;
                } catch(SQLException excep) {
                    System.out.println("System: insertSchuetzen(): " + insertData.toString());
                    System.err.println("System: insertSchuetzen(): Error Code    = " + excep.getErrorCode());
                    System.err.println("System: insertSchuetzen(): Error Message = " + excep.getMessage());
                    System.err.println("System: insertSchuetzen(): " + excep);
                }
            } else {
                System.out.println("System: insertSchuetzen(): " + insertData.toString());
                System.err.println("System: insertSchuetzen(): Error Code    = " + ex.getErrorCode());
                System.err.println("System: insertSchuetzen(): Error Message = " + ex.getMessage());
                System.err.println("System: insertSchuetzen(): " + ex);
                if (mDb != null) {
                    try {
                        System.err.print("System: insertSchuetzen(): Transaction is being rolled back");
                        mDb.rollback();
                    } catch(SQLException excep) {
                        System.out.println("System: insertSchuetzen(): Error Code    = " + excep.getErrorCode());
                        System.out.println("System: insertSchuetzen(): Error Message = " + excep.getMessage());
                        System.err.println("System: insertSchuetzen(): " + excep);
                    }
                }
            }
        } finally {
            if (insertData != null) {
                try {
                    System.out.print("System: insertSchuetzen(): Transaction will be closed");
                    insertData.close();
                } catch(SQLException excep) {
                    System.err.print(excep);
                }                
            }
            try {
                System.out.print("System: insertSchuetzen(): AutoCommit() switched on again");
                mDb.setAutoCommit(true);
            } catch(SQLException excep) {
                System.err.print(excep);
            }                
        }
        return insertDataset;
    }
    
    public void executeSQL(String strSQL) {
        PreparedStatement executeData = null;
        Connection mDb=null;

        try {
            /**
             * Link zur Datenbank holen
             */
            mDb = new MyArrowDB().getInstance();
            executeData = mDb.prepareStatement(strSQL);
            /**
             * AutoCommit abschalten
             */
            mDb.setAutoCommit(false);
            /**
             * Datensatz einfügen
             */
            System.out.println("System: executeSQL(): Datensatz einfügen");
            executeData.execute();
            mDb.commit();
            
        } catch (SQLException ex) {
            System.out.println("System: executeSQL(): " + executeData.toString());
            System.err.println("System: executeSQL(): Error Code    = " + ex.getErrorCode());
            System.err.println("System: executeSQL(): Error Message = " + ex.getMessage());
            System.err.println("System: executeSQL(): " + ex);
            if (mDb != null) {
                try {
                    System.err.print("System: executeSQL(): Transaction is being rolled back");
                    mDb.rollback();
                } catch(SQLException excep) {
                    System.out.println("System: executeSQL(): Error Code    = " + excep.getErrorCode());
                    System.out.println("System: executeSQL(): Error Message = " + excep.getMessage());
                    System.err.println("System: executeSQL(): " + excep);
                }
            }
        } finally {
            if (executeData != null) {
                try {
                    System.out.println("System: executeSQL(): Transaction will be closed");
                    executeData.close();
                } catch(SQLException excep) {
                    System.err.println(excep);
                }                
            }
            try {
                System.out.println("System: executeSQL(): AutoCommit() switched on again");
                mDb.setAutoCommit(true);
            } catch(SQLException excep) {
                System.err.println(excep);
            }                
        }
    }
}