class Node {
    int key, height;
    Node left, right;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

public class SimpleAVL {

    static int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    static int balance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node insert(Node root, int key) {

        if (root == null)
            return new Node(key);

        if (key < root.key)
            root.left = insert(root.left, key);
        else if (key > root.key)
            root.right = insert(root.right, key);

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int bf = balance(root);

        // LL
        if (bf > 1 && key < root.left.key)
            return rightRotate(root);

        // RR
        if (bf < -1 && key > root.right.key)
            return leftRotate(root);

        // LR
        if (bf > 1 && key > root.left.key) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RL
        if (bf < -1 && key < root.right.key) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    static boolean search(Node root, int key) {
        while (root != null) {
            if (root.key == key)
                return true;
            else if (key < root.key)
                root = root.left;
            else
                root = root.right;
        }
        return false;
    }

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        Node root = null;

        int scores[] = {15, 10, 20, 5, 12, 25, 30};

        for (int x : scores)
            root = insert(root, x);

        System.out.println("AVL Tree (Inorder):");
        inorder(root);

        System.out.println("\n\nSearch 12 = " + search(root, 12));
    }
}