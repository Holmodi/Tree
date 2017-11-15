package Tree;
import java.util.*;
public class BinaryTree<T> {
    public BinaryNode<T> root;
    private int i;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(T[] prelist) {
        this.root = create(prelist);
    } //构造函数 用数组初始化

    private BinaryNode<T> create(T[] prelist) {       //创建二叉树
        BinaryNode<T> p = null;
        if (i < prelist.length) {
            T elem = prelist[i];
            i++;
            if (elem != null) {
                p = new BinaryNode<T>(elem);
                p.left = create(prelist);
                p.right = create(prelist);
            }
        }
        return p;
    }

    public BinaryNode<T> insert(T x) { //根插入节点

        return this.root = new BinaryNode<T>(x, root, null);

    }

    public BinaryNode<T> insert(BinaryNode<T> parent, T x, boolean lor) {   //中间插入节点
        if (x == null) {
            return null;
        }
        ;
        if (lor) {
            return parent.left = new BinaryNode<T>(x, parent.left, null);    //判断左孩子右孩子

        }
        return parent.right = new BinaryNode<T>(x, null, parent.right);

    }

    public void rmove(BinaryNode<T> parent, boolean lor) {   //删除节点
        if (lor)
            parent.left = null;
        else
            parent.right = null;


    }

    /* 实现三种递归遍历*/
    public void preorder(BinaryNode<T> p) {  //先序

        if (p != null) {
            System.out.println(p.data);
            preorder(p.left);
            preorder(p.right);
        }

    }

    public void inorder(BinaryNode<T> p) {   //中序

        if (p != null) {
            inorder(p.left);
            System.out.println(p.data);
            inorder(p.right);
        }
    }

    public void postorder(BinaryNode<T> p) {   //后序

        if (p != null) {
            postorder(p.left);
            postorder(p.right);
            System.out.println(p.data);
        }
    }

    /*
       实现三种非递归遍历
     */
    public void clear() {
        this.root = null;
    }

    public void preorderTraverse() {  //先根非递归
        System.out.println("先根非递归：");
        Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();
        BinaryNode<T> p = this.root;
        while (p != null || !stack.empty()) {           //栈非空就继续执行
            if (p != null) {
                System.out.print(p.data + "");
                stack.push(p);     //输出后入栈
                p = p.left;
            } else {
                System.out.print("^");
                p = stack.pop();
                p = p.right;
            }
            System.out.println();
        }
    }

    public void inorderTraverse() {  //中根非递归
        System.out.println("中根非递归");
        Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();
        BinaryNode<T> p = this.root;
        while (p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }    //搜索至最左端子树
            p = stack.pop();  //出栈
            System.out.println(p.data + "");
            p = p.right;   //查找是否有右子树，有则进入
        }
    }

    public void postorderTraverse() {   //后根非递归
        System.out.println("后根非递归");
        Stack<BinaryNode<T>> s = new Stack<BinaryNode<T>>();
        BinaryNode<T> curNode = this.root;
        BinaryNode<T> lastVisitNode = null;
        while (curNode != null) {   //到达最左边的子树
            s.push(curNode);
            curNode = curNode.left;
        }
        while (!s.empty()) {
            curNode = s.pop();  //出栈
            //一个根节点被访问的前提是：无右子树或右子树已被访问过
            if (curNode.right != null && curNode.right != lastVisitNode) {
                //根节点再次入栈
                s.push(curNode);
                //进入右子树，右子树一定不为空
                curNode = curNode.right;
                while (curNode != null) {
                    //再走到右子树的最左边
                    s.push(curNode);
                    curNode = curNode.left;
                }
            } else {
                //访问
                System.out.println(curNode.data);
                //修改最近被访问的节点
                lastVisitNode = curNode;
            }
        } //while

    }
 /*
      完成三种非递归遍历
     */

    public static void main(String args[]) {
        String[] prelist = {"A", "B", "D", null, "G", null, null, null, "C", "E", null, null, "F", "H"};
        BinaryTree<String> test = new BinaryTree<String>(prelist);
        test.preorderTraverse();    //实现非递归
        test.inorderTraverse();
        test.postorderTraverse();
        System.out.println("先序递归："); //实现递归
        test.preorder(test.root);
        System.out.println("中序递归：");
        test.inorder(test.root);
        System.out.println("后序递归：");
        test.postorder(test.root);
    }
}