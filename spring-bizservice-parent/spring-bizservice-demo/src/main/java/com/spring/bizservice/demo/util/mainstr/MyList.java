package com.spring.bizservice.demo.util.mainstr;

/**
 * @author liyongzhen
 * @create 2019-03-06 10:00
 **/
public class MyList<T> {

    private class Node<T>{
        public Node<T> pre;
        public Node<T> next;
        public T Date;

        public Node() {
            this.Date =null;
            this.pre = null;
            this.next = null;
        }

        public Node(T date,Node<T> pre, Node<T> next) {
            this.pre = pre;
            this.next = next;
            Date = date;
        }
    }

    private int size;
    private Node<T> header;
    private Node<T> tail;

    public MyList() {
        this.size =0;
        header =new Node<T>(null,null,null);
        tail =new Node<T>(null,header,null);
        header.next=tail;
        if(header == null){
            System.out.println("header is null");
        }
        if(tail == null){
            System.out.println("tail is null");
        }
    }

    public void add(T item){
        Node<T> newNode=new Node(item,null,null);
        tail.pre.next = newNode;
        newNode.pre = tail.pre;
        newNode.next =tail;
        tail.pre = newNode;
        size++;
    }
}
