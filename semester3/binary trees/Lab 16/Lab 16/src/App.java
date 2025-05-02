import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String tree_string = scanner.nextLine();
        MyBinaryTree tree = new MyBinaryTree(tree_string);
        System.out.println(tree.getTraversingString());
    }
}
