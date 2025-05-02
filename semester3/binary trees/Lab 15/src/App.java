import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String tree_string = scanner.nextLine();
        MyBinaryTree tree = new MyBinaryTree(tree_string);
        String[] res = tree.getTraversingString();
        for (int index = 0; index < res.length; index++) {
            if (index == 0) {
                System.out.print("Прямой обход: ");
            }else if (index == 1) {
                System.out.print("Центральный обход: ");
            }else {
                System.out.print("Концевой обход: ");
            }
        }
 
    }
}
