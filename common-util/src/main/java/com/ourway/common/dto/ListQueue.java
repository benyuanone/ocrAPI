package com.ourway.common.dto;

/**
 * Created by D.chen.g on 2018/1/18.
 * 队列的实现
 */
public class ListQueue<E> {
    class Node<E> {
        Node<E> next = null;
        E data;
        public Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;

    public boolean isEmpty() {
        return head == null;
    }

    public void offer(E e) {
        Node<E> node = new Node<E>(e);
        if (isEmpty()) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public E poll() {
        if (isEmpty()) return null;
        E data = head.data;
        head = head.next;
        return data;
    }

    public int size() {
        Node<E> temp = head;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    public E getHeadData(){
        return head.data;
    }
    public E getNextData(){
        return head.next.data;
    }

    public static void main(String[] args) {
//        ListQueue<QuoteModel> queue = new ListQueue<>();
//        QuoteModel a=new QuoteModel();
//        a.setName("a");
//        a.setNow(new Date());
//        a.setTest(1);
//        QuoteModel b=new QuoteModel();
//        b.setName("b");
//        b.setNow(new Date());
//        b.setTest(2);
//        queue.offer(a);
//        queue.offer(b);
//
//        System.out.println(queue.getHeadData().getName());
//        System.out.println(queue.getNextData().getTest());
    }
}
