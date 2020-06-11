package com.example.calculadora_martinez_sanipatin.Model.Core;

import java.util.Vector;

/**
 * Clase encargada de implementar metodos para calcular el resultado
 * de las operaciones ingresadas por el usuario.
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class Engine {
    /**
     * Atributos de tipo String, int y float, utilizados en la clase
     * para almacenar algun valor.
     */
    static final double e = 2.71828; //Math.E;
    private String[] stringInfija;
    public int error = 0;
    private Float resp = 0.0f;

    /**
     * Constructor de la Clase Engine
     */
    public Engine() {
    }

    /**
     * Este metodo retorna el resultado de la cadena de operaciones ingresada por el usuario
     *
     * @param input parametro de tipo String ingresado por el usuario
     * @return retorna un String del resultado obtenido.
     */
    public String calculate(String input) throws Exception {
        getStrInfija(makeExplicit(input));
        if (error > 0) {
            return "Error en sintaxis";
        } else {
            PostFijo post = new PostFijo(stringInfija); // CONVERTIR CADENA INFIJA EN POSTFIJA
            resp = post.getValor();
            return removeDecimals(resp.toString());
        }
    }

    /**
     * Transforma una cadena ingresada por el usuario de tal forma que aquellos números que son
     * precedidos por un signo '-' se transforma en una expresión de la siguiente manera '-9' -> '(0-9)'
     * así, se evitan problemas cuando no existen dos operandos, y el signo '-' se usa exclusivamente para
     * definir el signo del número
     *
     * @param input lo que el usuario registra en la calculadora
     * @return cadena transformada para definir claramente los números negativos
     */
    public String makeExplicit(String input) throws Exception {
        String newString = "";
        boolean isNegative = false;
        boolean isLog = false;
        boolean check = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-') {
                // check if before has a number or operand
                if (i != 0) {
                    if (!isNumber(input.charAt(i - 1)) && i + 1 < input.length() && isNumber(input.charAt(i + 1))) {
                        newString += "(0";
                        isNegative = true;
                    }
                }
                if (i == 0) {
                    newString += "(0";
                    isNegative = true;
                }
            }

            if (c == 'l') { // check if it's logarithm base e or n
                if (i + 1 < input.length() && input.charAt(i + 1) == 'n') { // define a logarithm base e
                    i += 2; //increase counter to ignore letter n
                    if (input.charAt(i) != '(' && !isNumber(input.charAt(i)))
                        throw new Exception("Syntax Error"); // must be ln(3) or ln3
                    newString += "(("+e+")→";
                    int parenthesis = 0;
                    do {
                        if (input.charAt(i) == '(') parenthesis++;
                        if (input.charAt(i) == ')') parenthesis--;

                        newString += input.charAt(i) + "";
                        i++;
                    } while (i < input.length() && parenthesis != 0);
                    newString += ")";
                } else if (i + 3 < input.length() && input.charAt(i + 1) == 'o' && input.charAt(i + 2) == 'g') { // define a logarithm base n
                    i += 3; //increase counter to ignore letters og
                    if (input.charAt(i) != '(' && !isNumber(input.charAt(i)))
                        throw new Exception("Syntax Error"); // must be log(3) or log3
                    if (input.charAt(i) == '(') { // defines a function of type log(a,b), where b is the base
                        int j = i; // search for ','
                        String base = "";
                        int parenthesis = 0;
                        do {
                            j++;
                            if (input.charAt(j) == '(') parenthesis++;
                            if (input.charAt(j) == ')') parenthesis--;
                        } while (j < input.length() && parenthesis != 0);

                        String value = input.substring(++i, ++j);
                        System.out.println(value);
                        if (input.charAt(j) == ')')
                            base = "10";
                        else
                            while (isNumber(input.charAt(++j)) || input.charAt(j) == '.') {
                                base += input.charAt(j);
                            }
                        newString += "(" + base + "→" + value + ")";
                        i = j;
                        i++;
                    } else if (isNumber(input.charAt(i))) { //defines default logarithm with base 10
                        newString += "(10→";
                        while (i < input.length() && (isNumber(input.charAt(i)) || input.charAt(i) == '.')) {
                            newString += input.charAt(i);
                            i++;
                        }
                        newString += ")";
                    }
                }
            }
            if (i >= input.length())
                return newString; // check if it's possible to continue, else return the newly formed string

            newString += input.charAt(i) + "";

            if (isNegative && check) {
                if (i + 1 < input.length() && !isNumber(input.charAt(i + 1)) && input.charAt(i + 1) != '.') {
                    newString += ")";
                    isNegative = false;
                }
                if (i == input.length() - 1) {
                    newString += ")";
                    isNegative = false;
                }
            }
            check = isNegative;

        }
        return newString;
    }

    /**
     * Realiza una casting sencillo de un caracter a un número
     *
     * @param c Caracter de una cadena
     * @return retorna verdadero si el casting se ha logrado correctamente indicando que es un número
     */
    private boolean isNumber(char c) {
        try {
            Float.parseFloat(c + "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Este metodo retira los los ceros de un resultado con con decimales innecesarios,
     * por ejemplo de un resultado: 4.0 el método retorna: 4 entero.
     *
     * @param _v parametro correspondiente al resultado obtenido de las operaciones.
     * @return regresa un valor sin decimales cuando todos estos son cero, o el resultado original
     * en caso de que los decimales sean diferentes de 0 ó sea INFINITY / ERROR
     */
    private String removeDecimals(String _v) {
        try {
            String[] tmp = _v.split("\\.");
            String _d = tmp[1];
            int _numOfZeroes = 0;
            for (int i = 0; i < _d.length(); i++) {
                if (_d.charAt(i) == '0') _numOfZeroes++;
            }
            return _numOfZeroes == _d.length() ? tmp[0] : _v;
        } catch (Exception e) {
            return _v;
        }

    }
    //CODIGOS ASCII OPERADORES
    //40 => (
    //41 => )
    //42 => *
    //43 => +
    //45 => -
    //46 => .
    //47 => /

    /**
     * Este metodo verifica que la cadena ingresada por el usuario,
     * tengan relacion de operabilidad entre numeros, es decir 5 + 3 * 2
     *
     * @param str parametro correspondiente a la cadena ingresada por el usuario.
     */
    public void getStrInfija(String str) {
        Vector<String> cadenaInfija = new Vector();

        Sintactico sintactico = new Sintactico(str.length());
        Lexico lex = new Lexico();

        error = 0;
        //if(str.length() == 0)
        //error++;

        String num = "";
        int i, j, tipo, atipo, nOperadores, nNumeros;
        char c;
        atipo = -5;
        i = 0;
        j = 1;
        nOperadores = 0;
        nNumeros = 0;
        boolean isNegative = false;
        while (i < str.length() && error == 0) {
            c = str.charAt(i);
            if (c != ' ') {
                j = 1;
                tipo = lex.getTipo(c); //Tipo es un entero que con el que indica un caracter
                if ((atipo == 0 && tipo == 2) || (atipo == 3 && tipo == 0) || (atipo == 3 && tipo == 2)) {
                    c = '*';  //Agrega operador '*' en expresiones
                    j = 0;    ///   x * (..      )*[        (...)*999
                    tipo = 4;
                }
                if ((atipo == -5 && c == '-') || (atipo == 2 && c == '-')) { //Agrega el 0 en expresiones (-9) ó -5 -> (0-9), 0-5
                    // se debe transformar -9 -> (0-9)
                    c = '0';
                    j = 0;
                    tipo = 0;
                }
                lex.add(c);
                sintactico.setType(tipo);
                sintactico.add(c);

                error = error + lex.getError() + sintactico.getError();

                if (error == 0) {
                    if (tipo < 2) { //Es número
                        num = num + String.valueOf(c);
                    } else { //Es operador o signo de agrupación
                        if (num.equals("") == false) {
                            try {
                                float x = Float.valueOf(num).floatValue();
                                cadenaInfija.add(num);
                                nNumeros++;
                            } catch (NumberFormatException e) {
                                error++;
                            }
                        }
                        num = "";
                        if (tipo == 4) //Es operador
                            nOperadores++;
                        cadenaInfija.add(String.valueOf(c));
                    }
                }
                atipo = tipo;
            }
            i = i + j;
        }
        if (sintactico.isEmpty() == false)
            error++; //Si no están bien emperajados los paréntesis
        if (num.equals("") == false) { // Si la cadena "num" tiene un número
            try {
                float x = Float.valueOf(num).floatValue();
                nNumeros++;
                cadenaInfija.add(num);
            } catch (NumberFormatException e) {
                error++;
            }
        }
        if (nOperadores + 1 != nNumeros)
            error++; //Si número de operadores no le corresponde al número de operandos

        if (error == 0) {
            int tamanio = cadenaInfija.size();
            stringInfija = new String[tamanio];
            for (i = 0; i < tamanio; i++) { //Pasa el vector a una arreglo de String
                stringInfija[i] = cadenaInfija.get(i);
            }
        }
    }
}
