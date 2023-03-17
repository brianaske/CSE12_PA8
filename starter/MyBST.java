import java.util.ArrayList;


public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    public int size() {
        return size;
    }

    public V insert(K key, V value) {
        // TODO
        if (key == null){
            throw new NullPointerException();
        }
        if (this.root == null){
            this.root = new MyBSTNode<K,V>(key, value, null);
        }

        return insertHelper(root, key, value);
    }

    protected V insertHelper(MyBSTNode curr, K key, V value){

        int compareResult = ((Comparable<K>) curr.getKey()).compareTo(key);

        if (compareResult == 0){
            V removedValue = (V) curr.getValue();
            curr.setValue(value);
            return removedValue;
        }
        if (compareResult > 0){
            if (curr.getLeft() == null){
                curr.setLeft(new MyBSTNode<K,V>(key, value, curr));
                this.size++;
                return null;
            }
            else {
                return insertHelper(curr.left, key, value);
            }
        }
        else{
            if (curr.getRight() == null){
                curr.setRight(new MyBSTNode<K,V>(key, value, curr));
                this.size++;
                return null;
            }
            else {
                return insertHelper(curr.right, key, value);
            }
        }
    }

    public V search(K key) {
        // TODO
        return searchHelper(root, key);
    }

    protected V searchHelper(MyBSTNode curr, K key){
        int compareResult = ((Comparable<K>) curr.getKey()).compareTo(key);
        if (compareResult == 0){
            return (V) curr.getValue();
        }
        else if (compareResult < 0){
            if (curr.right == null){
                return null;
            }
            return searchHelper(curr.right, key);
        }
        else{
            if (curr.left == null){
                return null;
            }
            return searchHelper(curr.left, key);
        }
    }

    public V remove(K key) {

        MyBSTNode<K,V> curr = removeHelper(root, key);
        //not found
        if (curr == null){
            System.out.println("no such node");
            return null;
        }
        //found
        V removedValue = curr.getValue();
        //root node
        if (curr == root){
            
            if ( curr.getLeft() == null && curr.getRight() == null){
                this.root = null;
                size--;
                return removedValue;
            }
            else if (curr.getLeft() != null && curr.getRight() == null){
                root = curr.getLeft();
                size--;
                return removedValue;
            }
            else if (curr.getLeft() ==null && curr.getRight() != null){
                root = curr.getRight();
                size--;
                return removedValue;
            }
        }

        //a leaf node
        if (curr.getLeft() == null && curr.getRight() == null){
            int compareWithParent = curr.getKey().compareTo(curr.parent.getKey());
            if (compareWithParent > 0){
                curr.getParent().setRight(null);
                size--;
                return removedValue;
            }
            else{
                curr.getParent().setLeft(null);
                size--;
                return removedValue;
            }
        }

        //a node with one children
        if (curr.getLeft() == null ^ curr.getRight() == null){
            int compareWithParent = curr.getKey().compareTo(curr.parent.getKey());
            if (compareWithParent > 0){
                if (curr.getLeft() != null){
                    curr.getParent().setRight(curr.getLeft());
                    size--;
                    return removedValue;
                }
                else{
                    curr.getParent().setRight(curr.getRight());
                    size--;
                    return removedValue;
                }
            }
            else{
                if (curr.getLeft() != null){
                    curr.getParent().setLeft(curr.getLeft());
                    size--;
                    return removedValue;
                }
                else{
                    curr.getParent().setLeft(curr.getRight());
                    size--;
                    return removedValue;
                }
            }
        }

        //
        if (curr.getLeft() != null && curr.getRight() != null){
            MyBSTNode<K,V> successor = curr.successor();
            K swapKey = curr.getKey();
            V swapValue = curr.getValue();
            curr.setKey(successor.getKey());
            curr.setValue(successor.getValue());
            successor.setKey(swapKey);
            successor.setValue(swapValue);
            while (curr.getRight() != null){
                swapKey = curr.getKey();
                swapValue = curr.getValue();
                curr.setKey(curr.right.getKey());
                curr.setValue(curr.right.getValue());
                curr.getRight().setKey(swapKey);
                curr.getRight().setValue(swapValue);
                curr = curr.right;
            }
            int compareWithParent = curr.getKey().compareTo(curr.parent.getKey());
            if (compareWithParent > 0){
                curr.getParent().setRight(null);
                size--;
            }
            else{
                curr.getParent().setLeft(null);
                size--;
            }
            return removedValue;
        }
        return null;
    }

    protected MyBSTNode<K,V> removeHelper(MyBSTNode curr, K key){
        int compareResult = ((Comparable<K>) curr.getKey()).compareTo(key);
        if (compareResult == 0){
            return curr;
        }
        else if (compareResult < 0){
            if (curr.right == null){
                return null;
            }
            return removeHelper(curr.right, key);
        }
        else{
            if (curr.left == null){
                return null;
            }
            return removeHelper(curr.left, key);
        }
    }

    public ArrayList<MyBSTNode<K, V>> inorder() {
        // TODO
        return null;
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        public MyBSTNode<K, V> successor() {
            // TODO
            if (this.right != null){
                MyBSTNode curr = this.right;
                while (curr.left != null){
                    curr = curr.left;
                }
                return curr; 
            }
            else if (this.right == null){
                MyBSTNode curr = this;
                MyBSTNode parent = this.parent;
                while (parent != null && parent.right == this){
                    curr = parent;
                    parent = curr.parent;
                }
                return parent;
            }
            else{
                return null;
            }
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
