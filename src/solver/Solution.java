package solver;

import java.util.List;
import java.util.Objects;

public class Solution {

    private final List<Literal> minimalModel;
    private final Program program;

    public Solution(List<Literal> minimalModel, Program program) {
        this.minimalModel = minimalModel;
        this.program = program;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Solution) {
            return Program.eq(minimalModel, ((Solution) obj).minimalModel);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.minimalModel);
        hash = 89 * hash + Objects.hashCode(this.program);
        return hash;
    }

    @Override
    public String toString() {
        return "--- SOLUTION ---\n" +
//                "F u K' = " + minimalModel + "\n" +
                "P^(F u K'):\n" + program + "\n" +
                "Minimal model = " + minimalModel + "\n\n";
    }
    
}
