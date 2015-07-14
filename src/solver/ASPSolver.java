/**
 * License: creative commons 4.0, by-sa
 * This program is distributed WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * @author maximilianstrauch
 */

package solver;

import java.util.ArrayList;
import java.util.List;

public class ASPSolver {

    public static void main(String[] args) {
      // Siehe KI1 Folien, Folie 272
        Program pg = new Program()
                // Tweety
                .add(
                        new Rule()
                            .setHead(new Literal("vogel(tweety)"))
                            .setPos(new Literal("pinguin(tweety)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("fliegt(tweety)"))
                            .setPos(new Literal("vogel(tweety)"))
                            .setNeg(new Literal("fliegt(tweety)", true))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("fliegt(tweety)", true))
                            .setPos(new Literal("pinguin(tweety)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("fliegt(tweety)"))
                            .setPos(new Literal("fledermaus(tweety)"))
                )
                .add(
                        new Rule()
                            .setPos(new Literal("vogel(tweety)"), new Literal("fledermaus(tweety)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("pinguin(tweety)"))
                )
                
                // Batman
                .add(
                        new Rule()
                            .setHead(new Literal("vogel(batman)"))
                            .setPos(new Literal("pinguin(batman)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("fliegt(batman)"))
                            .setPos(new Literal("vogel(batman)"))
                            .setNeg(new Literal("fliegt(batman)", true))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("fliegt(batman)", true))
                            .setPos(new Literal("pinguin(batman)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("fliegt(batman)"))
                            .setPos(new Literal("fledermaus(batman)"))
                )
                .add(
                        new Rule()
                            .setPos(new Literal("vogel(batman)"), new Literal("fledermaus(batman)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("fledermaus(batman)"))
                );
        solve(pg);
        
        // Siehe KI1 Folien, Folie 291
        pg = new Program()
                .add(
                        new Rule()
                            .setHead(new Literal("p(a)"))
                            .setNeg(new Literal("q(a)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("p(b)"))
                            .setNeg(new Literal("q(b)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("q(a)"))
                            .setPos(new Literal("r(a)"))
                            .setNeg(new Literal("p(a)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("q(b)"))
                            .setPos(new Literal("r(b)"))
                            .setNeg(new Literal("p(b)"))
                )
                .add(
                        new Rule()
                            .setHead(new Literal("r(a)"))
                );
        solve(pg);
    }
    
    public static void solve(Program pg) {
        System.out.println("--- ASP logical program ---");
        System.out.println(pg);
        System.out.println();
        
        List<Literal> F = pg.allFacts();
        System.out.println("F = " + F); // Alle Fakten in pg
        System.out.println("H = " + pg.allHeadLiterals()); // Alle Kopfliterale
        System.out.println("");
        
        System.out.println("Searching for answer sets ...");
        List<Solution> sols = new ArrayList<>();
        for (List<Literal> Kdash : pg.headLiteralIterator()) {
            List<Literal> FuKdash = merge(Kdash, F);
            
            Program reduct = pg.getReduct(FuKdash);
            List<Literal> mm = reduct.getMinimalModel();
            
            if (Program.eq(mm, FuKdash)) {
                Solution sol = new Solution(mm, reduct);
                if (!sols.contains(sol)) {
                    sols.add(sol);
                }
            }
        }
        
        System.out.println();
        int i = 1;
        for (Solution solution : sols) {
            System.out.println((i++) + ") " + solution);
        }
    }
    
    public static final <T> List<T> merge(List<T> a, List<T> b) {
        List<T> res = new ArrayList<>();
        res.addAll(a);
        for (T be : b) {
            if (!res.contains(be)) {
                res.add(be);
            }
        }
        return res;
    }
    
}
