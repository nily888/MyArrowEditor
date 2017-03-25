package mappinggid;

import mappinggid.*;
import static java.util.Objects.isNull;

/**
 * Created by René Düber 25.03.2017
 */
public class MappingGID implements MappingGIDColumns {
    private long id;
    private String origtable;
    private String tablename;
    private String fieldname;

    public MappingGID() { }
  
    public MappingGID(
            final long id,
            final String origtable,
            final String tablename,
            final String fieldname ) {
        this.id = id;
        this.origtable=  origtable;
        this.tablename = tablename;
        this.fieldname = fieldname;
    }

    public long getID(){
        return id;
    }
        public void setID(final long id){
        this.id = id;
    }
    
    public String getorigTable(){
        return origtable;
    }
    public void setorigTable(final String origtable){
        this.origtable = origtable;
    }
    
    public String getTableName(){
        return tablename;
    }
    public void setTableName(final String tablename){
        this.tablename = tablename;
    }
    
    public String getFieldName(){
        return fieldname;
    }
    public void setFieldName(final String fieldname){
        this.fieldname = fieldname;
    }
 
    public String toString() {
        String toString = "table=mappinggig";
            toString += "&" + ID + "=" + String.valueOf(getID());
            if (!getorigTable().equals("")) toString += "&" + ORIGTABLE + "=" + getorigTable();
            if (!getTableName().equals("")) toString += "&" + TABLENAME + "=" + getTableName();
            if (!getFieldName().equals("")) toString += "&" + FIELDNAME + "=" + getFieldName();
        System.out.println("toString(): toString - " + toString);
        return toString;
    }
    
}
