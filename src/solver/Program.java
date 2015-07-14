/**
 * License: creative commons 4.0, by-sa
 * This program is distributed WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * @author maximilianstrauch
 */

package solver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Program {
    
    private final List<Rule> rules;
    
    public Program() {
        this.rules = new ArrayList<>();
    }
    
    public Program add(Rule rule) {
        rules.add(rule);
        return this;
    }
    
    public List<Literal> allFacts() {
        List<Literal> res = new ArrayList<>();
        for (Rule rule : rules) {
            if (rule.isFact()) {
                res.add(rule.getHead());
            }
        }
        return res;
    }
    
    public List<Literal> allHeadLiterals() {
        List<Literal> res = new ArrayList<>();
        for (Rule rule : rules) {
            res.add(rule.getHead());
        }
        return res;
    }
    
    public Iterable<List<Literal>> headLiteralIterator() {
        return new Iterable<List<Literal>>() {

            @Override
            public Iterator<List<Literal>> iterator() {
                return new Iterator<List<Literal>>() {

                    private List<List<Literal>> powerSet = PowerSet.powerset(allHeadLiterals());
                    private int index = 0;
                    
                    @Override
                    public boolean hasNext() {
                        return index < powerSet.size();
                    }

                    @Override
                    public List<Literal> next() {
                        return powerSet.get(index++);
                    }
                };
            }
        };
    }
    
    public Program getReduct(List<Literal> state) {
        Program newProgram = new Program();
        
        for (Rule rule : rules) {
            
            if (rule.negContains(state)) {
                // Remove rule
                continue;
            }
            
            Rule newRule = rule.clone();
            newRule.setNeg(null);
            
            if (newRule.isEmpty()) {
                continue;
            }
            
            newProgram.add(newRule);
            
        }
        
        return newProgram;
    }
    
    public List<Literal> getMinimalModel() {
        List<Literal> x;
        List<Literal> xNew = new ArrayList<>();
        
        int cnt = 0;
        do {
            x = new ArrayList<>(xNew);
            xNew.clear();
            
            for (Rule rule : rules) {
                if (isSubset(rule.getPos(), x)) {
                    Literal l = rule.getHead();
                    if (l != null) {
                        xNew.add(l);
                    }
                }
            }
            
           // System.out.println((cnt++) + ": " + x + " vs. " + xNew);
            
        } while (!eq(x, xNew));
        
        return xNew;
    }
    
    
    

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Rule rule : rules) {
            str.append(rule);
            str.append('\n');
        }
        str.setLength(str.length() - 1);
        return str.toString();
    }
    
    /**
     * a ≤ b
     */
    public static final <T> boolean isSubset(List<T> a, List<T> b) {
        if ((a == null || a.isEmpty()) && (b == null || b.isEmpty())) {
            return true;
        }
        
        if ((a == null || a.isEmpty()) && (b != null && !b.isEmpty())) {
            return true;
        } 
        
        for (T ta : a) {
            if (!b.contains(ta)) {
                return false;
            }
        }
        
        return true;
    }
    
    public static final <T> boolean eq(List<T> a, List<T> b) {
        if (a.size() != b.size()) {
            return false;
        }
        
        for (T ta : a) {
            if (!b.contains(ta)) {
                return false;
            }
        }
        
        for (T tb : b) {
            if (!a.contains(tb)) {
                return false;
            }
        }
        
        return true;
    }
    
}
