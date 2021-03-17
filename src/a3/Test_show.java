package a3;

public class Test_show {
    BSTImpl bst = new BSTImpl();

    public void run() {
        bst.insert("a");
        bst.insert("b");
        bst.insert("c");
        bst.insert("d");
        bst.insert("e");
        bst.insert("f");
        bst.insert("g");
        bst.insert("h");
        bst.insert("i");
        bst.insert("j");
        bst.insert("k");
        bst.insert("l");
        System.out.println(bst.get("l"));

    }

}
