import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String tree_string = scanner.nextLine();
        MySearchBinaryTree tree = new MySearchBinaryTree(tree_string);
        treeOperations(tree);
    }

    public static void treeOperations(MySearchBinaryTree loc_tree){
        while (true) {
            System.out.println("Input 'A' to add node to tree\nInput 'D' to delete node from tree\nInput 'F' to check if node in tree\nInput 'X' to exit" );
            Scanner scanner = new Scanner(System.in);
            char inpt = scanner.next().charAt(0);
            if (inpt == 'a' || inpt == 'A'){
                try {
                    int value = scanner.nextInt();
                    loc_tree.add(value);
                }catch (NumberFormatException e){
                    System.err.println(e.toString());
                }
            }
            else if (inpt == 'd' || inpt == 'D'){
                try {
                    int value = scanner.nextInt();
                    if (!loc_tree.deleteNode(value)){
                        System.err.println("There isn't that node");
                    }
                }catch (NumberFormatException e){
                    System.err.println(e.toString());
                }
            }
            else if (inpt == 'f' || inpt == 'F'){
                try {
                    int value = scanner.nextInt();
                    System.out.println(loc_tree.containNode(value));
                }catch (NumberFormatException e){
                    System.err.println(e.toString());
                }
            }
            else if (inpt == 'x' || inpt == 'X'){
                loc_tree.printTree();
                break;
            }
            else {
                System.err.println("Unknown command. Exit.");
                loc_tree.printTree();
                break;
            }
        }
    }
}
