package com.example.calculadora_martinez_sanipatin.Core;

public class Sintactico {
    private int tipo,error;
    private Stack pila;

    public Sintactico(int tamanio){
        tipo = -1;
        error = 0;
        pila = new Stack(tamanio);
    }

    public void add(char c){ //Falta optimizar
        String s = String.valueOf(c);
        char pi;
        if(c == '(' || c == '[' || c == '{'){ //VERIFICACION DE LOS PARENTESIS
            pila.push(s);
        }else{
            if(c == ')' || c == ']' || c == '}'){
                if(pila.isEmpty())
                    error++;
                else{
                    pi = pila.pop().charAt(0);
                    if( ((pi == '(' && c == ')') || (pi == '[' && c == ']') || ( pi == '{' && c == '}')) == false )
                        error++;
                }
            }
        }
    }
    public boolean isEmpty(){
        return pila.isEmpty();
    }
    public void setType(int xtipo){
        if(tipo == 1){
            if(xtipo != 0)
                error++; // SI LUEGO DE UN "." NO CONTINUA UN NUMERO
        }
        if(tipo == 4)   {
            if(xtipo == 4)
                error++; // SI RETORNA DOS OPERADORES SEGUIDOS
        }
        tipo = xtipo;
    }
    public int getError(){
        return error;
    }
}
