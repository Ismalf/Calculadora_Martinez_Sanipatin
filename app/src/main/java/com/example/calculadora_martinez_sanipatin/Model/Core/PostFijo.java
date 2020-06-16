package com.example.calculadora_martinez_sanipatin.Model.Core;

import java.util.Vector;

/**
 * Clase encargada de transformar una cadena INFIJA en una cadena POSTFIJA
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class PostFijo {
    /**
     * Atributos del la clase PostFijo
     */
    private Vector<String> rpn;
    private String[] cadena;
    private static final int precision = 50;

    /**
     * Retorna una instancia de una cadena postfijo
     *
     * @param cadenaInfija Array de strings obtenidos de la cadena ingresada por el usuario
     *                     para efectuar cálculos, debe ser una cadena INFFIJA correcta
     */
    public PostFijo(String[] cadenaInfija) {
        cadena = cadenaInfija;
        rpn = new Vector();
        creaPostFijo();
    }

    /**
     * Transforma la cadena provista en formato infijo, a una cadena de tipo postfijo.
     * La cadena debe ser correcta y es provista durante la instanciación de la clase.
     */
    private void creaPostFijo() { // LA CADENA INFIJA DEBE ESTAR CORRECTA
        Stack pila = new Stack(cadena.length);
        int i, sim;
        i = 0;
        while (i < cadena.length) {
            String s = cadena[i];
            sim = getPriori(s);
            if (sim == 0) { //Es número

                rpn.add(s);
            } else { //Es operador
                while (pila.isEmpty() == false && Precedencia(pila.StackTop(), s) && sim > 0) // Falta √ y ^ de derecha a izquierda
                    rpn.add(pila.pop());
                if (sim == -2) { //SI ENCUENTRA UN SIGNO DE AGRUPACIÓN QUE CIERRA
                    while (getPriori(pila.StackTop()) != -1) //HASTA QUE ENCUENTRE EL SIGNO DE AGRUPACIÓN QUE ABRE
                        rpn.add(pila.pop());
                    pila.pop(); // QUITA LOS PARENTESIS DE LA PILA
                } else
                    pila.push(s);
            }
            i++;
        }
        while (pila.isEmpty() == false)
            rpn.add(pila.pop());
    }

    /**
     * Retorna verdadero o falso si el primer operador tiene una mayor jerarquía (matemática) que el segundo
     *
     * @param strPila primer operador
     * @param str     segundo operador
     * @return boolean
     */
    public boolean Precedencia(String strPila, String str) {
        int p1 = getPriori(strPila);
        int p2 = getPriori(str);
        return p1 >= p2;
    }

    /**
     * Retorna un identificador del operador que se ingresa, es un número entero que indica
     * la jerarquía matemática del operador que va desde 1 a 4. siendo 1 la jerarquía más baja y 4 la más alta
     *
     * @param str operador del que se requiere
     * @return valores enteros de 1 a 4 para la jerarquía de operadores,
     * -1 para indicar que se ha abierto una agrupación de operandos y operadores y,
     * -2 para indicar que se ha cerrado una agrupación de operandos y operadores
     */
    private static int getPriori(String str) {
        switch (str) {
            case "-":
                return 1;
            case "+":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            case "%":
                return 2;
            case "^":
                return 3;
            case "√":
                return 3;
            case "!":
                return 4;
            case "→":
                return 3;
            case "s":
                return 3;
            case "c":
                return 3;
            case "(":
                return -1;
            case "[":
                return -1;
            case "{":
                return -1;
            case ")":
                return -2;
            case "]":
                return -2;
            case "}":
                return -2;
            default:
                return 0;
        }
    }

    /**
     * Retorna el valor calculado usando la cadena post fija que se crea al momento
     * de instanciar el objeto
     *
     * @return float: número decimal resultante de efectuar las operaciones marcadas en
     */
    public float getValor() throws Exception {
        Stack pila = new Stack(cadena.length);
        String s, valor;
        float op1, op2;
        int i, simbol;
        //simbol = 0;
        for (i = 0; i < rpn.size(); i++) {
            s = rpn.get(i);
            simbol = getPriori(s);
            if (getPriori(s) == 0) { //es número
                pila.push(s);
            } else {
                //try{
                if (simbol == 4) {  //Es factorial{
                    op1 = Float.valueOf(pila.pop());
                    //if(getDecimal(op1) == 0){
                    valor = String.valueOf(factorial((int) op1));
                    pila.push(valor);
                    //else
                    //error++;
                } else {
                    op2 = Float.valueOf(pila.pop());
                    op1 = Float.valueOf(pila.pop());
                    valor = operar(s, op1, op2);
                    pila.push(valor);
                }

            }
        }
        return Float.valueOf(pila.pop());
    }

    /**
     * Se efectuan las operaciones matemáticas denotadas por el operador sobre los operandos.
     * Las operaciones se realizan en orden izquirda a derecha, así el order de los operandos
     * marca una diferencia a excepción de que el operador marque una suma.
     *
     * @param str operador
     * @param op1 primer operando
     * @param op2 segundo operando
     * @return una cadena con el resultado de las operaciones matemáticas efectuadas sobre los datos
     * de ingreso
     */
    private static String operar(String str, float op1, float op2) throws Exception {
        float valor = 0;
        switch (str) {
            case "+":
                valor = op1 + op2;
                break;
            case "-":
                valor = op1 - op2;
                break;
            case "*":
                valor = op1 * op2;
                break;
            case "/":
                valor = op1 / op2;
                break;
            case "%":
                valor = mod(op1, op2);
                break;
            case "^":
                valor = potencia(op1, op2);
                break;
            case "√":
                valor = raiz(op1, op2);
                break;
            case "→":
                valor = log(op1, op2);
                break;
            case "s":
                valor = sin(op2);
                break;
            case "c":
                valor = cos(op2);
                break;
        }
        return String.valueOf(valor);
    }

    /**
     * Metodo para obtener el logaritmo de un numero en base a un numero
     *
     * @param b es la base del logaritmo
     * @param n es el valor a calcular
     * @return retorna el resultado del logaritmo
     */
    private static float log(float b, float n) throws Exception {
        double val = 0;
        System.out.println(b);
        if (b < 0 || n < 0) throw new Exception("Error");
        int i, accurate = 10, reps = 0;
        while (n != 1 && accurate >= 0) {
            for (i = 0; n >= b; i++) n /= b;
            n = p(n, 10);
            val = 10 * (val + i);
            accurate--;
            reps++;
        }
        return (float) val / p(10, reps);
    }

    /**
     * Metodo para sacar el modulo entre dos numeros
     *
     * @param op1 valor del dividendo
     * @param op2 valor del divisor
     * @return retorna el resultado del modulo obtenido entre don numeros
     */
    private static float mod(float op1, float op2) {
        float result = 0;
        if (op1 < 0 && op2 < 0) {
            if (op1 == op2) {
                result = 0;
            } else {
                result = -1 * ((-1 * op1) % (-1 * op2));
            }
        } else if (op1 < 0) {
            result = op2 - ((op1 * -1) % op2);
        } else if (op2 < 0) {
            result = op2 + (op1 % (op2 * -1));
        } else {
            result = op1 % op2;
        }
        return result;
    }

    /**
     * Metodo para
     *
     * @param x
     * @param i
     * @return
     */
    private static float p(float x, int i) {
        float r = 1.0f;
        for (int j = i; j > 0; j--) r *= x;
        return r;
    }

    /**
     * Metodo para calcular la potencia de un numero
     *
     * @param n valor a calcular
     * @param e exponente a elevar
     * @return
     */
    private static float potencia(float n, float e) {
        Sintactico sin = null;
        float result = n;
        if ((int) e == 0) {
            result = 1;
        } else if ((int) e < 0) {
            if (n == 0) {
                result = sin.getError();
            } else {
                for (int i = 1; i < ((int) e) * (-1); i++) {
                    result = result * n;
                }
                result = 1 / result;
            }

        } else if ((int) e > 0) {
            for (int i = 1; i < (int) e; i++) {
                result = result * n;
            }
        }
        return result;
    }

    /**
     * Metodo para calcular la raiz de un numero
     *
     * @param e exponente de la raíz
     * @param m valor a calcular de la raíz
     * @return resultado de la raíz ingresada
     */
    private static float raiz(float e, float m) {
        float i = 0;
        float x1, x2 = 0;
        Sintactico sin = null;
        if (m < 0) {
            x2 = sin.getError();
        } else if (m > 0) {
            while ((i * i) <= m)
                i += 0.1;
            x1 = i;
            for (int j = 0; j < 10; j++) {
                x2 = m;
                x2 /= x1;
                x2 += x1;
                x2 /= 2;
                x1 = x2;
            }
        } else if (m == 0) {
            x2 = 0;
        }
        return x2;
    }

    /**
     * Se calcula el factorial de un número dado
     *
     * @param num número del cual se quiere calcular el factorial, debe ser un entero mayor a 0
     * @return el valor calculado del factorial del número ingresado, 1 en caso de que el valor ingresado
     * sea menor o igual a 0
     */
    private static float factorial(int num) {
        float x = 1;
        int i;
        for (i = 1; i < num; i++)
            x = x * (i + 1);
        return x;
    }

    private static float sin(float val) {
        //val = (float) Math.toRadians(val);
        val = val * (float) (Math.PI / 180);
        System.out.println(val);
        int i;
        float s = 0;
        int sign = 1;
        for (i = 1; i < precision; i += 2) {
            s += sign * potencia(val, i) / factorial(i);
            sign *= -1;
        }
        System.out.println(s);
        s = (float) Math.toRadians(s);
        return s;
    }

    private static float cos(float val) {
        //val = (float)Math.toRadians(val);
        val = val * (float) (Math.PI / 180);
        System.out.println(val);
        int i;
        float s = 0;
        int sign = 1;
        for (i = 0; i < precision; i += 2) {
            s += sign * potencia(val, i) / factorial(i);
            sign *= -1;
        }
        System.out.println(s);
        s = (float) Math.toRadians(s);
        return s;
    }
}

