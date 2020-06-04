package com.example.calculadora_martinez_sanipatin.Model.Core;

/**
 * Clase encargada de implementar metodos que verifiquen que la cadena
 * ingresada por el usuario sea correcta.
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class Sintactico {
    /**
     * Atributos de tipo int para almacenar el tipo de caracter y el numero de errores en la cadena ingresada,
     * y un Atributo pila de tipo Stack el cual nos permite ingresar a sus métodos.
     */
    private int tipo,error;
    private Stack pila;

    /**
     * Constructor de la clase Sintactico
     * @param tamanio parametro correspondiente al tamaño de la pila.
     */
    public Sintactico(int tamanio){
        tipo = -1;
        error = 0;
        pila = new Stack(tamanio);
    }

    /**
     * Este metodo verifica que la cadena ingresada este correctamente estructurada.
     * @param c parametro de tipo char correspondiente a cada carecter de la pila.
     */
    public void add(char c){
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

    /**
     * Se determina si la pila está vacía, y se lo hace mediante el tope de la pila,
     * el cual es -1 si la cadena ingresada está vacía.
     * @return retorna un tipo de dato booleano true o false
     */
    public boolean isEmpty(){
        return pila.isEmpty();
    }

    /**
     * Este metodo verifica que tipo de numero se ha ingresado
     * @param xtipo parametro correspondiente a un numero de la pila.
     */
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

    /**
     * Getter del atributo error
     * @return retorna el número de errores
     */
    public int getError(){
        return error;
    }
}
