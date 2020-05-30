package com.example.calculadora_martinez_sanipatin.Core;

public class Stack {
    private String[] pila;
    private int tope;

    public Stack(int capacidad){
        pila = new String[capacidad];
        tope =  -1;
    }
    public boolean isEmpty(){
        return tope == -1;
    }
    public void push(String str){
        if(tope + 1 < pila.length)
            pila[++tope] = str;
    }
    public String pop(){
        if(isEmpty())
            return "";
        return pila[tope--];
    }
    public String StackTop(){
        return pila[tope];
    }
}
