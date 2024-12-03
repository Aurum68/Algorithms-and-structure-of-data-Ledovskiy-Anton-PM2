public class MyBinaryTree {
    
    private class Node {
        int value;
        Node right; 
        Node left;

        Node(int value){
            this.value = value;
            this.right = null;
            this.left = null;
        }
    }

    private String tree_string;
    private Node root;
    
    public MyBinaryTree(String tree_string){
        this.tree_string = tree_string;
    }

    private int recurse_depth = 0;
    private Node addRecursively(Node currentNode, int value){
        if (currentNode == null) {
            return new Node(value);
        }
        recurse_depth += 1;
        if (side) {
            if (recurse_depth == 1) {
                currentNode.left = addRecursively(currentNode.left, value);
            }
            else if (last_not_int_simbol == ',' & recurse_depth == parenthesis_counter || 
               pre_last_not_int_simbol == ',' & last_not_int_simbol == '(' & parenthesis_counter - recurse_depth >= 1 ||
               pre_last_not_int_simbol == ',' & last_not_int_simbol == ',' &  parenthesis_counter - recurse_depth >= 1) 
            {
                currentNode.right = addRecursively(currentNode.right, value);
            }
            else if (last_not_int_simbol == '(' || recurse_depth != parenthesis_counter) {
                currentNode.left = addRecursively(currentNode.left, value);
            }
            return currentNode;
        }else {
            if (recurse_depth == 1) {
                currentNode.right = addRecursively(currentNode.right, value);
            }else if (last_not_int_simbol == ',' || recurse_depth != parenthesis_counter) {
                currentNode.right = addRecursively(currentNode.right, value);
            }else if(pre_last_not_int_simbol == ',' & last_not_int_simbol == '(' & recurse_depth == parenthesis_counter){
                currentNode.left = addRecursively(currentNode.left, value);
            }
            return currentNode;
        }
        
        
    }

    private int parenthesis_counter = 0;
    private char last_not_int_simbol = '0';
    private char pre_last_not_int_simbol = '0';
    private boolean side = true;

    private void convertTreeStringToBinaryTree(){
        String nums = "0123456789";
        String number = "";
        for (int index = 0; index < this.tree_string.length(); index++) {
            char num = tree_string.charAt(index);
            if (nums.indexOf(num) != -1) {
                number += String.valueOf(num);
                continue;
            } else if (nums.indexOf(num) == -1) {
                if (number.equals("")) {
                    last_not_int_simbol = num;
                    if (last_not_int_simbol == '(') {
                        parenthesis_counter++;
                    }else if(last_not_int_simbol == ')') {
                        parenthesis_counter--;
                        if (parenthesis_counter == 1) {
                            side = false;
                        }
                    }
                    if (last_not_int_simbol == ',' & num == '('){
                        pre_last_not_int_simbol = last_not_int_simbol;
                    }else if (last_not_int_simbol == ')'){
                        pre_last_not_int_simbol = '0';
                    }
                    continue;
                }
                int loc_value = Integer.parseInt(number);
                number = "";

                this.root = addRecursively(root, loc_value);
                recurse_depth = 0;
                if (last_not_int_simbol == ',' & num == '('){
                    pre_last_not_int_simbol = last_not_int_simbol;
                }else if (last_not_int_simbol == ')'){
                    pre_last_not_int_simbol = '0';
                }

                last_not_int_simbol = num;
                if (last_not_int_simbol == '(') {
                    parenthesis_counter++;
                }else if(last_not_int_simbol == ')') {
                    parenthesis_counter--;
                    if (parenthesis_counter == 1) {
                        side = false;
                    }
                }
            }
        }
    }

    private String traversingStringByDepth = "";
    private Node traversingBinaryTreeInDepth(Node node){
        this.traversingStringByDepth += Integer.toString(node.value) + " ";
        if (node.left != null) {
            traversingBinaryTreeInDepth(node.left);
        }
        if (node.right != null) {
            traversingBinaryTreeInDepth(node.right);
        }
        return node;
    }

    private String traversingStringCentrality = " ";
    private Node traversingBinaryTreeCentrality(Node node){
        if (node.left != null) {
            traversingBinaryTreeCentrality(node.left);
        }
        this.traversingStringCentrality += Integer.toString(node.value) + " ";
        if (node.right != null) {
            traversingBinaryTreeCentrality(node.right);
        }
        return node;
    }

    private String traversingStringByEnd = " ";
    private Node traversingBinaryTreeByEnd(Node node){
        if (node.left != null) {
            traversingBinaryTreeByEnd(node.left);
        }
        if (node.right != null) {
            traversingBinaryTreeByEnd(node.right);
        }
        this.traversingStringByEnd += Integer.toString(node.value) + " ";
        return node;
    }

    public String[] getTraversingString(){ 
        convertTreeStringToBinaryTree();
        traversingBinaryTreeInDepth(root);
        traversingBinaryTreeCentrality(root);
        traversingBinaryTreeByEnd(root);
        String[] result = {this.traversingStringByDepth, this.traversingStringCentrality, this.traversingStringByEnd};
        return result;
    }
}
