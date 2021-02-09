import java.util.HashMap;

public class LL1Table {

    private HashMap<String, HashMap<String, String>> table;

    public LL1Table(){
        this.table = new HashMap<>();
        populate();
    }

    private void populate(){

        HashMap<String, String> SMap = new HashMap<>();
        SMap.put("epsilon", "A $");
        SMap.put("lparen", "A $");
        SMap.put("terminal", "A $");
        SMap.put("$", "$");
        this.table.put("S", SMap);

        HashMap<String, String> A = new HashMap<>();
        A.put("epsilon", "B C");
        A.put("lparen", "B C");
        A.put("terminal", "B C");
        this.table.put("A", A);

        HashMap<String, String> B = new HashMap<>();
        B.put("epsilon", "epsilon");
        B.put("lparen", "D");
        B.put("terminal", "D");
        this.table.put("B", B);

        HashMap<String, String> C = new HashMap<>();
        C.put("$", "");
        C.put("lparen", "D C");
        C.put("rparen", "");
        C.put("terminal", "D C");
        C.put("union", "union B C");
        C.put("","");
        this.table.put("C", C);

        HashMap<String, String> D = new HashMap<>();
        D.put("lparen", "E F");
        D.put("terminal", "E F");
        this.table.put("D", D);

        HashMap<String, String> E = new HashMap<>();
        E.put("lparen", "lparen A rparen");
        E.put("terminal", "terminal");
        this.table.put("E", E);

        HashMap<String, String> F = new HashMap<>();
        F.put("$", "");
        F.put("lparen", "");
        F.put("rparen", "");
        F.put("terminal", "");
        F.put("union", "");
        F.put("star", "star");
        F.put("question", "question");
        F.put("plus", "plus");
        F.put("","");
        this.table.put("F", F);
    }

    public HashMap<String, HashMap<String, String>> getTable() {
        return table;
    }
}