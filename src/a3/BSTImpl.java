package a3;

public class BSTImpl implements BST {

    private Node root;
    private int size;

    public BSTImpl() {
        root = null;
        size = 0;
    }

    public BSTImpl(String s) {
        root = new NodeImpl(s);
        size = 0;
    }

    // The implementation of "height" is given to you below
    // it is here to illustrate for you how to set up
    // the method implementation as recursion.
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive methods

    @Override
    public int height() { // public interface method signature
        // this method is the public interface method
        // it is not recursive, but it calls a recursive
        // private method and passes it access to the tree cells
        return height_recursive(this.root);
    }
    private int height_recursive(Node c) {
        // inner method with different signature
        // this helper method uses recursion to traverse
        // and process the recursive structure of the tree of cells
        if (c==null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.max(lht,rht) + 1;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public String insert(String value) {
        if(root==null) {
            root = new NodeImpl(value);
            size++;
            return value;
        }
        size++;
        return insert_r(value, this.root);
    }
    private String insert_r(String k, Node c) {
        Node add = new NodeImpl(k);
        if (c==null){
            return k;
        }

        int cflag = k.compareTo(c.getValue());

        if (cflag<0) {
            if (c.getLeft() != null) {
                insert_r(k, c.getLeft());
            }else{
                c.setLeft(add);
            }
        } else {
            if (c.getRight()!=null) {
                insert_r(k, c.getRight());
            }else{
                c.setRight(add);
            }
        }

        return k;


    }

    // remove implementation given to you, do NOT change
    @Override
    public void remove(String value) {
        remove_r(value,this.root);
        size--;
    }
    private Node remove_r(String k, Node c) {
        if (c==null) return null; // item not found, nothing to do

        // now we know we have a real node to examine
        int cflag = k.compareTo(c.getValue());

        if (cflag<0) { // k is smaller than node
            c.setLeft(remove_r(k,c.getLeft()));
            return c;
        }
        else
        if (cflag>0) { // k is larger than node
            c.setRight(remove_r(k,c.getRight()));
            return c;
        }
        else //cflag==0
        { // found it... now figure out how to rearrange

            // cases
            if (c.getLeft()==null && c.getRight()==null) { c = null; } // leaf
            else if (c.getLeft()==null && c.getRight()!=null) { c = c.getRight(); } // RC only
            else if (c.getLeft()!=null && c.getRight()==null) { c = c.getLeft(); } // LC only
            else { // 2 children
                Node max = maxCell(c.getLeft());
                c.setValue(max.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;
        }

    }

    private Node maxCell(Node c) { // this is used in remove too
        if (c.getRight()==null) return c;
        return maxCell(c.getRight());
    } ;

    @Override
    public boolean isFull() {
        return isFull_r(this.root);
    }

    private boolean isFull_r(Node c){
        if(c == null){
            return true;
        }
        if(c.getLeft()==null && c.getRight()==null){
            return true;
        }
        if ((c.getLeft()!=null)&&(c.getRight()!=null)){
            return (isFull_r(c.getLeft())&&isFull_r(c.getRight()));
        }
        return false;
    }



    @Override
    public String findMin() {
        return findMin_r(this.root);
    }

    private String findMin_r(Node c){
        if (c.getLeft() != null){
            return findMin_r(c.getLeft());
        }

        return c.getValue();

    }


    @Override
    public String findMax() {
        return findMax_r(this.root);
    }

    private String findMax_r(Node c){
        if (c.getRight() != null){
            return findMax_r(c.getRight());
        }
        return c.getValue();
    }

    @Override
    public boolean contains(String s) {
        return contains_r(this.root, s);
    }

    private boolean contains_r(Node c, String s) {
        int cflag = s.compareTo(c.getValue());

        if (cflag<0 && c.getLeft()!=null) {
            return contains_r(c.getLeft(), s);
        }

        if (cflag>0 && c.getRight()!=null) {
            return contains_r(c.getRight(), s);
        }

        return cflag == 0;

    }

    @Override
    public Node get(String s) {
        return get_r(root, s);
    }

    private Node get_r(Node c, String s) {
        int cflag = s.compareTo(c.getValue());

        if (cflag<0 && c.getLeft()!=null) {
            return get_r(c.getLeft(), s);
        }

        if (cflag>0 && c.getRight()!=null) {
            return get_r(c.getRight(), s);
        }

        if(cflag==0) {
            return c;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void show() {
        int off=4;
        int lev=0;
        for (int k=0; k<10; k++) {
            System.out.print("+");
            for (int kk=0; kk<off-1; kk++) { System.out.print("-");}
        }
        System.out.println("+");
        show_r(this.root,lev,off);
        for (int k=0; k<10; k++){
            System.out.print("+");
            for (int kk=0; kk<off-1; kk++) {System.out.print("-"); }
        }
        System.out.println("+");
    }

    private void show_r(Node n, int lev, int off) {
        if (n==null) return;
        show_r(n.getRight(), lev+off, off);
        for (int b=0; b<lev; b++) { System.out.print(" "); }
        System.out.println(n.getValue());
        show_r(n.getLeft(), lev+off, off);
    }
}
