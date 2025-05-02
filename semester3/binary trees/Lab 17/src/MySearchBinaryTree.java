import java.util.Stack;

public class MySearchBinaryTree {
    
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
    
    public MySearchBinaryTree(String tree_string){
        this.tree_string = tree_string;
        convertTreeStringToBinaryTree();
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

    private Node addNodeRecursively(Node currentNode, int value){
        if (currentNode == null) {
            return new Node(value);
        }

        if (value > currentNode.value) {
            currentNode.right = addNodeRecursively(currentNode.right, value);
        }else if (value < currentNode.value) {
            currentNode.left = addNodeRecursively(currentNode.left, value);
        }else {
            return currentNode;
        }
        return currentNode;
    }

    public void add(int value){
        this.root = addNodeRecursively(root, value);
    }

    private boolean containNodeRecursive(Node current , int value ){
        if (current==null) {
            return false;
        }
        if (current.value==value){
            return true;
        }
        return value<current.value ? containNodeRecursive(current.left, value) : containNodeRecursive(current.right, value);
    }

    public boolean containNode(int value ){
        return containNodeRecursive(root, value);
    }

    public boolean deleteNode(int value ){
        Node currentNode = root;
        Node parentNode = root;
        boolean isLeftChild = true;
        while (currentNode.value != value) { // начинаем поиск узла
            parentNode = currentNode;
            if (value < currentNode.value) { // Определяем, нужно ли движение влево?
                isLeftChild = true;
                currentNode = currentNode.left;
            }
            else { // или движение вправо?
                isLeftChild = false;
                currentNode = currentNode.right;
            }
            //System.out.println(currentNode.value+" "+ parentNode.value);
            if (currentNode == null)
                return false; // yзел не найден
        }

        if (currentNode.left == null && currentNode.right == null) { // узел просто удаляется, если не имеет потомков
            if (currentNode == root) // если узел - корень, то дерево очищается
                root = null;
            else if (isLeftChild)
                parentNode.left=null; // если нет - узел отсоединяется, от родителя
            else
                parentNode.right=null;
        }
        else if (currentNode.right == null) { // узел заменяется левым поддеревом, если правого потомка нет
            if (currentNode == root)
                root = currentNode.left;
            else if (isLeftChild)
                parentNode.left=(currentNode.left);
            else
                parentNode.right=(currentNode.left);
        }
        else if (currentNode.left == null) { // узел заменяется правым поддеревом, если левого потомка нет
            if (currentNode == root)
                root = currentNode.right;
            else if (isLeftChild)
                parentNode.left=(currentNode.right);
            else
                parentNode.right=(currentNode.right);
        }
        else { // если есть два потомка, узел заменяется преемником
            Node heir = receiveHeir(currentNode);// поиск преемника для удаляемого узла
            if (currentNode == root)
                root = heir;
            else if (isLeftChild) {
                parentNode.left = heir;
                heir.left = currentNode.left;
            }
            else{
                parentNode.right = heir;
                heir.right=currentNode.right;
            }
        }
        //System.out.println(currentNode.value+" "+ parentNode.value);
        return true; // элемент успешно удалён
    }

    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.right; // Переход к правому потомку
        while (currentNode != null) // Пока остаются левые потомки
        {
            parentNode = heirNode;// потомка задаём как текущий узел
            heirNode = currentNode;
            currentNode = currentNode.left; // переход к левому потомку
            try {
                System.out.println(currentNode.value+" "+ parentNode.value+" "+heirNode.value);
            }catch (NullPointerException e){
                System.out.println("null "+ parentNode.value+" "+heirNode.value);
            }

        }
        // Если преемник не является
        if (heirNode != node.right) // правым потомком,
        { // создать связи между узлами
            parentNode.left=(heirNode.right);
            heirNode.right=(node.right);
        }
        System.out.println(heirNode.value);
        return heirNode;// возвращаем приемника
    }

    public void printTree() { // метод для вывода дерева в консоль
        Stack<Node> globalStack = new Stack<>(); // общий стек для значений дерева
        globalStack.push(root);
        int gaps = 32; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Stack<Node> localStack = new Stack<>(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                Node temp = (Node) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.value); // выводим его значение в консоли
                    localStack.push(temp.left); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.right);
                    if (temp.left != null ||
                            temp.right != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }

}

