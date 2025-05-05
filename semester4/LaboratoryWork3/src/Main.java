import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "abbaddeabbuiabba";
        String pattern = "abb";
        Automaton automaton = new Automaton(pattern);
        List<Integer> results = automaton.search(text);

        System.out.println(results);
    }
}