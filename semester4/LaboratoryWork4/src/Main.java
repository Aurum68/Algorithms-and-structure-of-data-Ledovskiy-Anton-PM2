import Algorithms.BoyerMoore;
import Algorithms.KnutMorrisPratt;
import Algorithms.Rabin;

public class Main {
    public static void main(String[] args) {
        String text = "abbaabjjgartabbaabbbaabbabb";
        String pattern = "abba";
        System.out.println(KnutMorrisPratt.search(text, pattern));
        System.out.println(BoyerMoore.search(text, pattern));
        System.out.println(Rabin.search(text, pattern));
    }
}