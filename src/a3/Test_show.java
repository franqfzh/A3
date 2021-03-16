package a3;

public class Test_show {
    BSTImpl bst = new BSTImpl();

    public void run() {
        bst.insert("h");
        bst.insert("w");
        bst.insert("c");
        bst.insert("t");
        bst.insert("b");
        bst.insert("g");
        bst.insert("z");
        bst.insert("a");

        bst.show();

    }

}
