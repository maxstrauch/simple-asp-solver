/**
 * License: creative commons 4.0, by-sa
 * This program is distributed WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * @author maximilianstrauch
 */

package solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rule {
    
    private Literal head;
    private List<Literal> pos, neg;
    
    public Rule() {
        pos = new ArrayList<>();
        neg = new ArrayList<>();
    }
    
    public Rule setHead(Literal head) {
        this.head = head;
        return this;
    }

    public Rule setPos(Literal...poss) {
        pos.clear();
        if (poss != null) {
            pos.addAll(Arrays.asList(poss));
        }
        return this;
    }
    
    public Rule setNeg(Literal...negs) {
        neg.clear();
        if (negs != null) {
            neg.addAll(Arrays.asList(negs));
        }
        return this;
    }
    
    public boolean isFact() {
        return neg.isEmpty() && pos.isEmpty() && head != null;
    }
    
    public boolean isConstraint() {
        return (!neg.isEmpty() || !pos.isEmpty()) && head == null;
    }
    
    public boolean isRule() {
        return head != null && (!neg.isEmpty() || !pos.isEmpty());
    }

    public Literal getHead() {
        return head;
    }

    public List<Literal> getPos() {
        return pos;
    }
    
    public List<Literal> getNeg() {
        return neg;
    }
    
    public boolean posContains(List<Literal> ls) {
        if (ls.isEmpty()) {
            return true; // Empty set contained every time
        }
        
        if (pos == null) {
            return false;
        }
        
        for (Literal l : ls) {
            if (pos.contains(l)) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean negContains(List<Literal> ls) {
        if (ls.isEmpty()) {
            return true; // Empty set contained every time
        }
        
        if (neg == null) {
            return false;
        }
        
        for (Literal l : ls) {
            if (neg.contains(l)) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isEmpty() {
        return head == null && (pos == null || pos.isEmpty()) && (neg == null || 
                neg.isEmpty());
    }
    
    @Override
    public Rule clone() {
        Rule r = new Rule();
        r.head = head == null ? null : head.clone();
        r.pos = pos == null ? null : new ArrayList<>(pos);
        r.neg = neg == null ? null : new ArrayList<>(neg);
        return r;
    }
    
    @Override
    public String toString() {
        if (isFact()) {
            return head.toString() + ".";
        } else if (isConstraint()) {
            String str = "<- ";
            
            if (pos.size() > 0) {
                str += join(pos, ", ");
                if (neg.size() > 0) {
                    str += ", ";
                }
            }
            
            if (neg.size() > 0) {
                str += "not " + join(neg, ", not ");
            }
            
            str += ".";
            return str;
        } else if (isRule()) {
            String str = head + " <- ";
            
            if (pos.size() > 0) {
                str += join(pos, ", ");
                if (neg.size() > 0) {
                    str += ", ";
                }
            }
            
            if (neg.size() > 0) {
                str += "not " + join(neg, ", not ");
            }
            
            str += ".";
            return str;
        }
        
        return "E_UNKNWN_RULE_TYPE";
    }
    
    
    
    public static final <T> String join(Iterable<T> iter, String joiner) {
        StringBuilder str = new StringBuilder();
        for (T t : iter) {
            str.append(String.valueOf(t));
            str.append(joiner);
        }
        str.setLength(str.length() - joiner.length());
        return str.toString();
    }
    
}
