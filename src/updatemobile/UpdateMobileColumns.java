package updatemobile;

import updatemobile.*;

/**
 * Created by René Düber on 24.03.2017
 */
public interface UpdateMobileColumns {

    /**
     * Primaerschluessel.
     * */
    String ID = "_id";

    /**
     * Pflichtfeld: TableName
     *
     * TEXT
     */
    String TABLENAME = "tablename";

    /**
     * Pflichtfeld: FieldName
     *
     * TEXT
     */
    String FIELDNAME = "fieldname";

    /**
     * Pflichtfeld: alter Globaler Primaerschluessel
     * 
     * TEXT
     */
    String OLD_GID = "old_gid";

    /**
     * Pflichtfeld: neuer Globaler Primaerschluessel
     * 
     * TEXT
     */
    String NEW_GID = "new_gid";

    /**
     * Pflichtfeld: Transfered
     *
     * INT
     */
    String TRANSFERED = "transfered";

}