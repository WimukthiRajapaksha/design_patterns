/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteratorPattern;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 *
 * @author wimukthirajapaksha
 */

class Node<T> {
    public T value;
    public Node<T> left, right, parent;

    public Node(T value) {
        this.value=value;
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
        left.parent=right.parent=this;
    }
}

class InOrderIterator<T> implements Iterator<T> {
    private Node<T> current, root;
    private boolean yieldedStart; // to find left most element

    public InOrderIterator(Node<T> root) {
        this.root = this.current = root;
        while(current.left!=null) {
            current=current.left;
        }
    }
    
    private boolean hasRightmostParent(Node<T> node) {
        if(node.parent==null) return false;
        else {
            return node==node.parent.left || hasRightmostParent(node.parent);
        }
    }

    @Override
    public boolean hasNext() {
        return current.left!=null || current.right!=null || hasRightmostParent(current);
    }

    @Override
    public T next() {
        if(!yieldedStart) {
            yieldedStart=true;
            return current.value;
        }
        if(current.right!=null) {
            current=current.right;
            while(current.left!=null) {
                current=current.left;
            }
            return current.value;
        } else {
            Node<T> p=current.parent;
            while(p!=null && current==p.right) {
                current=p;
                p=p.parent;
            }
            current=p;
            return current.value;
        }
    }
}


class BinaryTree<T> implements Iterable<T> {
    private Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for(T item: this) {
            action.accept(item);
        }
    }
}

public class TreeTraversal {
    public static void main(String[] args) {
        Node<Integer> root=new Node<>(
                1,
                new Node<>(2),
                new Node<>(3)
        );
        InOrderIterator<Integer> it=new InOrderIterator<>(root);
        while(it.hasNext()) {
            System.out.print(it.next()+",");
        }
        System.out.println();
        
        
        BinaryTree<Integer> tree=new BinaryTree<>(root);
        for(int t: tree) {
            System.out.println(t);
        }
    }
}
