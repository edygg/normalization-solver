
package edu.unitec.normalization;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Edilson
 */
public class Relation {
    private List<Field> fields;
    
    public Relation() {
        this.fields = new ArrayList();
    }
    
    public boolean addField(Field field) {        
        if (field == null || this.fields.contains(field)) {
            return false;
        }
        
        return this.fields.add(field);
    }

    @Override
    public String toString() {
        String retVal = "Relation{";
        
        for (int i = 0; i < this.fields.size(); i++) {
            if (i == this.fields.size() - 1) {
                retVal += this.fields.get(i);
            } else {
                retVal += this.fields.get(i) + ",";
            }
        }
        
        return retVal + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Relation other = (Relation) obj;
        for (int i = 0; i < other.fields.size(); i++) {
            if (!this.fields.contains(other.fields.get(i))) {
                return false;
            }
        }
        return true;
    }
    
        
}
