import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DetAutomaton {
    String beginSt;
    Set<String> endSt;
    Map<String, Map<Character,String>> tranFunc;

    DetAutomaton(String[] ss, String[] ts) {
        endSt = new TreeSet<String>();
        tranFunc = new TreeMap<String, Map<Character,String>>();

        for (String v : ss) {
            String[] pieces = v.split(",");
            if (pieces.length>1) {
                if (pieces[1].equals("S")) beginSt = pieces[0];
                else if (pieces[1].equals("E")) endSt.add(pieces[0]);
            }
        }

        for (String e : ts) {
            String[] pieces = e.split(",");
            String from = pieces[0], to = pieces[1];
            if (!tranFunc.containsKey(from)) tranFunc.put(from, new TreeMap<Character,String>());
            for (int i=2; i<pieces.length; i++) {
                tranFunc.get(from).put(pieces[i].charAt(0), to);
            }
        }

        System.out.println("begin:"+ beginSt);
        System.out.println("end:"+ endSt);
        System.out.println("transitions:"+ tranFunc);
    }

    public boolean match(String s) {
        String curr = beginSt;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (!tranFunc.get(curr).containsKey(c)) {
                System.out.println("Oops... Transition function failure. No transition from "+curr+" to "+c);
                return false;
            }
            curr = tranFunc.get(curr).get(c);
        }
        return endSt.contains(curr);
    }

    public void test(String[] inputs) {
        for (String s : inputs)
            System.out.println(s + ":" + match(s));
    }
}
