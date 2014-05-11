
package edu.unitec.normalization;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Edilson
 */
public class FunctionalDependency {
    private List<Field> rightFields;
    private List<Field> leftFields;
    
    public FunctionalDependency(List<Field> left, List<Field> right) throws InvalidDataException {
        if (right == null || left == null) {
            throw new InvalidDataException("Some list is null");
        }
        
        if (right.isEmpty() || left.isEmpty()) {
            throw new InvalidDataException("Some list is empty");
        }
        
        TreeSet<Character> rightFieldsSet = new TreeSet();
        TreeSet<Character> leftFieldsSet = new TreeSet();
        
        for (int i = 0; i < right.size(); i++) {
            List<Character> current = right.get(i).getFieldNames();
            
            for (int j = 0; j < current.size(); j++) {
                if (!rightFieldsSet.contains(current.get(j))) {
                    rightFieldsSet.add(current.get(j));
                } else {
                    throw new InvalidDataException("Some fields are repeated");
                }
            }
        }
        
        for (int i = 0; i < left.size(); i++) {
            List<Character> current = left.get(i).getFieldNames();

            for (int j = 0; j < current.size(); j++) {
                if (!leftFieldsSet.contains(current.get(j))) {
                    leftFieldsSet.add(current.get(j));
                } else {
                    throw new InvalidDataException("Some fields are repeated");
                }
            }
        }
        
        this.rightFields = right;
        this.leftFields = left;
    } 

    @Override
    public String toString() {
        String retVal = "";
        
        for (int i = 0; i < this.leftFields.size(); i++) {
            retVal += this.leftFields.get(i);
        }
        
        retVal += " -> ";
        
        for (int i = 0; i < this.rightFields.size(); i++) {
            retVal += this.rightFields.get(i);
        }
        
        return retVal;
    }
    
    public List<FunctionalDependency> applyReflexiveAndDecompositionProperties() throws InvalidDataException {
        List<FunctionalDependency> retList = new ArrayList();
        
        for (int i = 0; i < this.leftFields.size(); i++) {
            List<Character> current = this.leftFields.get(i).getFieldNames();
          
            for (int j = 0; j < current.size(); j++) {
                List<Field> neoFieldList = new ArrayList();
                List<Character> neoCharacter = new ArrayList();
                
                neoCharacter.add(current.get(j));                
                neoFieldList.add(new Field(neoCharacter, false));
                retList.add(new FunctionalDependency(leftFields, neoFieldList));
            }
        }
        
        for (int i = 0; i < this.rightFields.size(); i++) {
            List<Character> current = this.rightFields.get(i).getFieldNames();

            for (int j = 0; j < current.size(); j++) {
                List<Field> neoFieldList = new ArrayList();
                List<Character> neoCharacter = new ArrayList();

                neoCharacter.add(current.get(j));
                neoFieldList.add(new Field(neoCharacter, false));
                retList.add(new FunctionalDependency(leftFields, neoFieldList));
            }
        }
        
        return retList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FunctionalDependency other = (FunctionalDependency) obj;
        
        for (int i = 0; i < other.leftFields.size(); i++) {
            if (!this.leftFields.contains(other.leftFields.get(i))) {
                return false;
            }
        }
        
        for (int i = 0; i < other.rightFields.size(); i++) {
            if (!this.rightFields.contains(other.rightFields.get(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    
    
}
