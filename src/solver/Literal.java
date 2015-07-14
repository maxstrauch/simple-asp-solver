/**
 * License: creative commons 4.0, by-sa
 * This program is distributed WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * @author maximilianstrauch
 */

package solver;

import java.util.Objects;

public class Literal {
    
    private String name;
    private boolean negative;
    
    public Literal(String name) {
        this(name, false);
    }
    
    public Literal(String name, boolean negative) {
        this.name = name;
        this.negative = negative;
    }

    @Override
    public Literal clone() {
        return new Literal(name, negative);
    }
    
    @Override
    public String toString() {
        return (negative ? "¬" : "") + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Literal) {
            return ((Literal) obj).name.equals(name) &&
                    ((Literal) obj).negative == negative;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + (this.negative ? 1 : 0);
        return hash;
    }
    
}
