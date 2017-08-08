package com.liuao;

/**
 * author:liuao
 * description:自定义的linklist
 * Date: create on 21:48 2017/8/7
 * modify by:
 */
public class MyLinkList<E> implements  Iterable<E> {


    private static class Node<E> {
        private E data;
        private Node<E> pre;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            pre = p;
            next = n;
        }
    }

    private int theSize;
    private int modCount = 0;
    private Node<E> begin;
    private Node<E> end;

    public MyLinkList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        begin = new Node<E>(null, null, null);
        end = new Node<E>(null, begin, null);
        begin.next = end;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    public void add(int index, E e) {
        addBefore(getNode(index, 0, size()), e);
    }

    public E get(int index) {
        return getNode(index).data;
    }

    public E set(int index, E newVal) {
        Node<E> p = getNode(index);
        E oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public E remove(int index) {
        return remove(getNode(index));

    }

    private void addBefore(Node<E> p, E e) {

        Node<E> newNode=new Node<E>(e,p.pre,p.next);
        newNode.pre.next=newNode;
        p.pre=newNode;
        theSize++;
        modCount++;
    }

    private E remove(Node<E> p){
        p.next.pre=p.pre;
        p.pre.next=p.next;
        theSize--;
        modCount++;
        return p.data;

    }
    private Node<E> getNode(int index){
        return getNode(index,0,size()-1);
    }

    private Node<E> getNode(int index,int low,int upper){
        Node<E> p;
        if(index<low||index>upper){
            throw new IndexOutOfBoundsException();
        }

        if(index<size()/2){
            p=begin.next;
            for(int i=0;i<index;i++){
                p=p.next;
            }}else{
                p=end;
                for(int i=size();i>index;i--){
                    p=p.pre;
                }
            }
        return p;
    }
    public java.util.Iterator<E> iterator() {
        return new LinkListIterator();
    }
    private class LinkListIterator implements  java.util.Iterator<E>{
        private Node<E> current=begin.next;
        private int expectedModeCount=modCount;
        private boolean okToRemove=false;
        public boolean hasNext() {
            return  current!=end;
        }
        public E next() {
            if(modCount!=expectedModeCount)
                throw new java.util.ConcurrentModificationException();
            if(!hasNext()){
                throw  new java.util.NoSuchElementException();
            }
            E nextItem=current.data;
            current=current.next;
            okToRemove=true;
            return  nextItem;
        }
        public void remove(){
            if (modCount!=expectedModeCount){
                throw  new java.util.ConcurrentModificationException();
            }
            if(!okToRemove){
                throw  new IllegalStateException();
            }
            MyLinkList.this.remove(current.pre);
            expectedModeCount++;
            okToRemove=false;

        }
    }
}