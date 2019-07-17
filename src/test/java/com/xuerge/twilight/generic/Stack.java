package com.xuerge.twilight.generic;

public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITAL_CAPACITY = 16;

    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITAL_CAPACITY];
    }

    public void push(E obj){
        elements[size++] = obj;
    }

    public E pop(){
        E result = elements[--size];
        elements[size] = null;
        return result;

    }

}