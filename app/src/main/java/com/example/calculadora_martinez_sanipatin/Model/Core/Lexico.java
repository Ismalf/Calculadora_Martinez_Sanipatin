package com.example.calculadora_martinez_sanipatin.Model.Core;

/**
 * Clase Lexico
 *
 * Clase encargada de implementar métodos que verifiquen que la cadena
 * ingresada tenga operandos y operadores de manera correcta.
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class Lexico {
    /**
     * Atributos de tipo entero que almacenan el tipo y el error identificado
     * en la cadena ingresada.
     */
    private int tipo,error;

    /**
     * Constructor de la clase Lexico
     */
    public Lexico(){
        error = 0;
        tipo = -1;
    }

    /**
     * Método add()
     * Este método invoca al método getType() que retorna un número que corresponde al tipo de caracter,
     * y de esa manera determinar si es un error o no.
     * @param x parametro de tipo char correspondiente a cada carecter de la cadena ingresada.
     */
    public void add(char x){
        tipo = getType(x);
        if(tipo == -1)
            error++;
    }

    /**
     * Getter del atributo tipo, retorna un entero generado por el metodo getTyper, que
     * corresponde al tipo de caracter al que sea.
     * @param c parametro de tipo char correspondiente a cada carecter de la cadena ingresada.
     * @return
     */
    public int getTipo(char c){
        return getType(c);
    }

    /**
     * Getter del atributo error
     * @return retorna el némero de erores
     */
    public int getError(){
        return error;
    }

    /**
     * Método estático getType()
     * Este método verifica que tipo de caracter es el que se ha ingresado.
     * @param x parametro de tipo char correspondiente a cada carecter de la cadena ingresada.
     * @return retorna el tipo de caracter es.
     */
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