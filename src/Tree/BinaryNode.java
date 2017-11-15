package Tree;

public class BinaryNode<T> {
    public T data;
    public  BinaryNode<T> left;   //左孩子
    public  BinaryNode<T> right;  //右孩子
    public  BinaryNode(T d,BinaryNode<T> l,BinaryNode<T> r){
        this.data=d;
        this.left=l;
        this.right=r;
    }

    public BinaryNode(T data){
        this(data,null,null);
    }

    public String toString(){
        return this.data.toString();           //返回节点数据的描述字符串
    }


}
