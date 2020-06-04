package com.example.calculadora_martinez_sanipatin.Model.Core;

/**
 * Clase encargada de implementar metodos que verifiquen que la cadena
 * ingresada tenga operandos y operadores de manera correcta.
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class Lexico {
    /**
     * Atributos de tipo entero que almacenan el tipo de caracter y el numero de errores identificados
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
     * Llama al metodo getType() el cual retorna un numero que corresponde al tipo de caracter,
     * y de esa manera determinar si es un error o no.
     * @param x parametro de tipo char correspondiente a cada carecter de la cadena ingresada.
     */
    public void add(char x){
        tipo = getType(x);
        if(tipo == -1)
            error++;
    }

    /**
     * Retorna un entero generado por el metodo getType(), el cual
     * corresponde al tipo de caracter.
     * @param c parametro de tipo char correspondiente a cada carecter de la cadena ingresada.
     * @return retorna un entero que hace referencia al tipo de caracter de la cadena.
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
     * Este metodo estatico verifica que tipo de caracter es el que se ha ingresado.
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
            //case '√': i = 4; break;
            case '!': i = 5; break;
        }
        return i;
    }
}