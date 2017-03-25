package mappinggid;

import mappinggid.*;

/**
 * Created by René Düber 25.03.2017
 */
public class MappingGIDTbl implements MappingGIDColumns {

    /**
     * Name der Datenbanktabelle.
     */
    public static final String TABLE_NAME = "mappinggid";

    /**
     * SQL Anweisung zur Schemadefinition.
     */
    public static final String SQL_CREATE = "";

    /**
     * Standard-Sortierreihenfolge fuer die Tabelle.
     *
     * Sortiert wird nach Zeitstempel absteigend.
     */
    public static final String DEFAULT_SORT_ORDER = ORIGTABLE + " DESC";

    /**
     * SQL Anweisung zur Loeschung der Tabelle.
     */
    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * SQL Anweisung fuer Erzeugung eines
     * neuen Eintrages.
     */
    public static final String STMT_INSERT = "INSERT INTO "+ TABLE_NAME +
            " (origtable, tablename, fieldname)" +
            " VALUES (?,?,?)";

    /**
     * SQL Anweisung fuer den Update eines Eintrages.
     */
    public static final String STMT_UPDATE = "UPDATE "+ TABLE_NAME + " SET " +
            " origtable=?, tablename=?, fieldname=? WHERE ID=?";
    
    /**
     * SQL-Anweisung zur Loeschung aller Pfeile
     */
    public static final String STMT_MAPPINGGID_DELETE = "DELETE " + TABLE_NAME;

    /** Liste aller bekannten Attribute. */
    public static final String[] ALL_COLUMNS = new String[] {
            ID,
            ORIGTABLE,
            TABLENAME,
            FIELDNAME
    };

    /**
     * WHERE-Bedingung fuer ID-Anfrage.
     */
    public static final String WHERE_ID_EQUALS = ID + "=?";

    /**
     * STMT WHERE-Bedingung fuer ID-Anfrage.
     */
    public static final String STMT_WHERE_ID_EQUALS =
            "select " +
                ID + ", " +
                ORIGTABLE + ", " +
                TABLENAME + ", " +
                FIELDNAME + " " +
            "from " + TABLE_NAME + " " +
            "where " + ID + "=?";
    
    /**
     * WHERE-Bedingung fuer Orig-Table-Anfrage.
     */
    public static final String WHERE_ORIGTABLE_EQUALS = ORIGTABLE + "=?";

    /**
    * Statement to get all records (OrigTable)
    */
    public static final String STMT_WHERE_ORIGTABLE_EQUALS  =
            "select " + 
                ID   + ", " + 
                ORIGTABLE + ", " + 
                TABLENAME + ", " +
                FIELDNAME + " " +
            "from " + TABLE_NAME;
    
    /**
     * Klasse enthaelt nur Konstanten.
     * Daher keine Objekterzeugung vorgesehen.
     */
    private MappingGIDTbl() {}

}
