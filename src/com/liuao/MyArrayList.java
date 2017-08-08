package com.liuao;

import java.util.ArrayList;

/**
 *@author:liuao
 *@description:自定义的ArrayList
 *@date:21:45 2017/8/7
 */
public class MyArrayList<E> implements  MyIterable<E> {

    private static final int DEFAULT_CAPACITY=10;

    private int theSize;

    private E[] theItems;

    public MyArrayList() {
        doClear();

    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize=0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return  theSize;
    }

    public boolean isEmpty() {
        return theSize==0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public E get(int index) {
        if(index<0||index>size()) throw  new ArrayIndexOutOfBoundsException();
        return theItems[index];
    }
    //返回旧的值
    public E set(int index, E newVal){
        /**
         *@author:liuao
         *@description:
         *@date:21:44 2017/8/7
         *@param:
         * * @param index
         * @param newVal
         */
        if(index<0||index>size()) throw  new ArrayIndexOutOfBoundsException();
        E oldVal=theItems[index];
        theItems[index]=newVal;
        return oldVal;
    }
    public void ensureCapacity(int newCapacity) {
        if(newCapacity<theSize)
            return;

        E[] oldeItems=theItems;
        theItems=(E [])new Object[newCapacity];

        for(int i=0;i<size();i++){
            theItems[i]=oldeItems[i];
        }
    }

    public boolean add( E x){
        add(size(),x);
        return  true;
    }
    public void add(int index,E x){
        if(theItems.length==size()) {
            ensureCapacity(size()*2+1);

        }//在指定位上添加，会导致后面的元素全部后移
        for(int i=theSize;i>index;i--) {
            theItems[i]=theItems[i-1];
            theItems[index]=x;

        }
        theSize++;
    }
    public E remove(int index){
        E item=theItems[index];
        for(int i=index;i<size()-1;i++){
            theItems[i]=theItems[i+1];

        }
        theSize--;
        return item;
    }
    public java.util.Iterator<E> iterator(){
        return new MyListIterator();
    }

    private class MyListIterator implements  java.util.Iterator<E>{

        private int current=0;
        public boolean hasNext(){
            return  current<size();
        }

        public E next() {
            if(!hasNext()){
                throw  new java.util.NoSuchElementException();
            }
            return theItems[current++];
        }

        public void remove(){
            MyArrayList.this.remove(--current);
        }


    }


}
