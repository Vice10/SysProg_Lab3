public class Dispatcher {
    public static void main(String[] argv){
        String[] ss1 = { "A,S", "B", "C,E" };
        String[] ts1 = { "A,B,i", "B,C,f"};
        DetAutomaton da = new DetAutomaton(ss1, ts1);
        String[] testsT1 = { "if"};
    }
}

