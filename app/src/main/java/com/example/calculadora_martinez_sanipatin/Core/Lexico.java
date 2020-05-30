package com.example.calculadora_martinez_sanipatin.Core;

public class Lexico {
    private int tipo,error;

    public Lexico(){
        error = 0;
        tipo = -1;
    }
    public void add(char x){
        tipo = getType(x);
        if(tipo == -1)
            error++;
    }
    public int getTipo(char c){
        return getType(c);
    }
    public int getError(){
        return error;
    }
    private static int getType(char x){
        int i = -1;
        switch(x){
            case '0': i = 0; break;
            case '1': i = 0; break;
            case '2': i = 0; break;
            case '3': i = 0; break;
            case '4': i = 0; break;
            case '5': i = 0; break;
            case '6': i = 0; break;
            case '7': i = 0; break;
            case '8': i = 0; break;
            case '9': i = 0; break;
            case '.': i = 1; break;
            case '(': i = 2; break;
            case ')': i = 3; break; //cierra
            case '[': i = 2; break;
            case ']': i = 3; break; //cierra
            case '{': i = 2; break;
            case '}': i = 3; break; //cierra
            case '+': i = 4; break;
            case '-': i = 4; break;
            case '*': i = 4; break;
            case '/': i = 4; break;
            case '^': i = 4; break;
            case '√': i = 4; break;
            case '!': i = 5; break;
        }
        return i;
    }
}