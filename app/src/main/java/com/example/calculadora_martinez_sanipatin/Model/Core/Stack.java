package com.example.calculadora_martinez_sanipatin.Model.Core;

/**
 * Estructura de datos de pila para poder organizar los elementos ingresados por el usuario,
 * con la finalidad de armar una cadena postfija, usada en el método polaco inverso para la
 * calculadora.
 *
 */
public class Stack {
    /**
     * Array de Strings que son cada uno de los operadores
     * y operandos ingresados por el usuario
     */
    private String[] pila;
    /**
     * Capacidad máxima de la pila, se determina por la longitud de la cadena ingresada por el
     * usuario
     */
    private int tope;

    /**
     * Constructor de la pila, recibe como parámetro la longitud de la cadena ingresada por el
     * usuario, así se establece una longitud definida e inmutable de la pila para
     * efectuar las operaciones
     * al iniciar la pila se substrae uno a la capacidad máxima
     * debido a las posiciones de un arreglo (0,1,2....... capacidad-1), caso contrario existirían
     * errores al tratar de ingresar a la posición [capacidad]
     * @param capacidad valor entero obtenido de la longitud de la cadena ingresada por el usuario
     */
    public Stack(int capacidad){
        pila = new String[capacidad];
        tope =  -1;
    }

    /**
     * Se determina si la pila está vacía, esto se puede determinar mediante el tope de la pila,
     * el cual es -1 si la cadena ingresada está vacía.
     * @return valor booleano true o false
     */
    public boolean isEmpty(){
        return tope == -1;
    }

    /**
     * Operación de inserción de un elemento a la pila, se debe comprobar si el elemento a ingresar
     * no va a exceder la capacidad máxima de la pila, caso contrario se provocarían errores al tratar
     * de ingresar a un espacio de memoria no asignado
     * @param str operador u operando extraido de la cadena ingresada por el usuario
     */
    public void push(String str){
        if(tope + 1 < pila.length)
            pila[++tope] = str;
    }

    /**
     * Operación de extracción de elementos de la pila, retorna el último elemento de la pila y después lo
     * elimina.
     * @return último valor de la pila
     */
    public String pop(){
        if(isEmpty())
            return "";
        return pila[tope--];
    }

    /**
     * Devuelve el último valor ingresado a la pila
     * @return último valor ingresado a la pila
     */
    public String StackTop(){
        return pila[tope];
    }
}
