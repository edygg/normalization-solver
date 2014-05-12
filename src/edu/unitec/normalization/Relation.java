
package edu.unitec.normalization;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edilson
 */
public class Relation {
    public static final String RELATION_REGEX = "[r|R][(]([A-Z][,])*[A-Z][)]";
    private List<Field> fields;
    private List<FunctionalDependency> functionalDependencies;
    
    public Relation() {
        this.fields = new ArrayList();
        this.functionalDependencies = new ArrayList();
    }
    
    public boolean addField(Field field) {        
        if (field == null) {
            return false;
        }
        
        if (this.fields.contains(field)) {
            return false;
        }
        
        return this.fields.add(field);
    }
    
    public boolean addFunctionalDependency(FunctionalDependency fd) {
        if (fd == null) {
            return false;
        }
        
        if (this.functionalDependencies.contains(fd)) {
            return false;
        }
        
        return this.functionalDependencies.add(fd);
    }
    
    public boolean hasField(Field field) {
        return this.fields.contains(field);
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
    
    public List<FunctionalDependency> getFunctionalDependencies() {
        return this.functionalDependencies;
    }    
}
