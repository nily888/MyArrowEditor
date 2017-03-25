package mappinggid;

import mappinggid.*;

/**
 * Created by René Düber 25.03.2017
 */
public interface MappingGIDColumns {

    /**
     * Primaerschluessel.
     * 
     * INTEGER
     */
    String ID = "_id";

    /**
     * Table where the GID was changed
     * 
     * STRING
     */
    String ORIGTABLE = "orgtable";

    /**
     * Table where the forgein GID should be changed
     *
     * TEXT
     */
    String TABLENAME = "tablename";

    /**
     * Forgein field name
     *
     * TEXT
     */
    String FIELDNAME = "fieldname";

}
