
package edu.unitec.normalization;

import java.util.Collections;
import java.util.List;
/**
 *
 * @author Edilson
 */
public class Field {
    public static final String PRIMARY_KEY_REGEX = "([A-Z])*";
    private List<Character> names;
    private boolean primaryKey;
    
    public Field(List<Character> names, boolean isPrimaryKey) throws InvalidDataException {
        if (names == null) {
            throw new InvalidDataException("Field name list is null");
        }
        
        if (names.isEmpty()) {
            throw new InvalidDataException("Field names are empty");
        }
        
        for (int i = 0; i < names.size(); i++) {
            if (!Character.isLetter(names.get(i))) {
                throw new InvalidDataException("Field names only can be letters.");
            }           
        }
        
        this.names = names;
        Collections.sort(this.names);
        this.primaryKey = isPrimaryKey;
    }
    
    public List<Character> getFieldNames() {
        return this.names;
    }
    
    public boolean isPrimaryKey() {
        return this.primaryKey;
    }
    
    public void setPrimaryKey(boolean val) {
        this.primaryKey = val;
    }

    @Override
    public String toString() {
        String retVal = "";
        
        for (int i = 0; i < this.names.size(); i++) {
            retVal += this.names.get(i);
        }
        
        return retVal;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Field other = (Field) obj;
        
        for (int i = 0; i < other.names.size(); i++) {
            if (!this.names.contains(other.names.get(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    
}
