package com.example.calculadora_martinez_sanipatin.Core;

import java.util.Vector;

public class PostFijo {

    private Vector<String> rpn;
    private String[] cadena;

    public PostFijo(String[] cadenaInfija){
        cadena = cadenaInfija;
        rpn = new Vector();
        creaPostFijo();
    }
    private void creaPostFijo(){ // LA CADENA INFIJA DEBE ESTAR CORRECTA
        Stack pila = new Stack(cadena.length);
        int i,sim;
        i = 0;
        while(i < cadena.length){
            String s= cadena[i];
            sim = getPriori(s);
            if(sim == 0){ //Es número
                rpn.add(s);
            }else{ //Es operador
                while(pila.isEmpty() == false && Precedencia(pila.StackTop() ,s) && sim > 0) // Falta √ y ^ de derecha a izquierda
                    rpn.add(pila.pop());
                if(sim == -2){ //SI ENCUENTRA UN SIGNO DE AGRUPACIÓN QUE CIERRA
                    while(getPriori(pila.StackTop()) != -1) //HASTA QUE ENCUENTRE EL SIGNO DE AGRUPACIÓN QUE ABRE
                        rpn.add(pila.pop());
                    pila.pop(); // QUITA LOS PARENTESIS DE LA PILA
                }else
                    pila.push(s);
            }
            i++;
        }
        while(pila.isEmpty() == false)
            rpn.add(pila.pop());
    }
    public String get(int index){
        return rpn.get(index);
    }
    public int Size(){
        return rpn.size();
    }
    public boolean Precedencia(String strPila,String str){
        int p1 = getPriori(strPila);
        int p2 = getPriori(str);
        return p1 >= p2;
    }
    private static int getPriori(String str){
        int i=0;
        if(str.equals("-"))
            i = 1;
        if(str.equals("+"))
            i = 1;
        if(str.equals("*"))
            i = 2;
        if(str.equals("/"))
            i = 2;
        if(str.equals("^"))
            i = 3;
        if(str.equals("√"))
            i = 3;
        if(str.equals("!"))
            i = 4;
        if(str.equals("("))
            i = -1;
        if(str.equals("["))
            i = -1;
        if(str.equals("{"))
            i = -1;
        if(str.equals(")"))
            i = -2;
        if(str.equals("]"))
            i = -2;
        if(str.equals("}"))
            i = -2;
        return i;
    }
    public float getValor(){
        Stack pila = new Stack(cadena.length);
        String s,valor;
        float op1,op2;
        int i,simbol;
        //simbol = 0;
        for(i = 0; i < rpn.size(); i++){
            s = rpn.get(i);
            simbol = getPriori(s);
            if(getPriori(s) == 0){ //es número
                pila.push(s);
            }else{
                //try{
                if(simbol == 4){  //Es factorial{
                    op1 = Float.valueOf(pila.pop());
                    //if(getDecimal(op1) == 0){
                    valor = String.valueOf(factorial((int)op1));
                    pila.push(valor);
                    //else
                    //error++;
                }else{
                    op2 = Float.valueOf(pila.pop());
                    op1 = Float.valueOf(pila.pop());
                    valor = operar(s,op1,op2);
                    pila.push(valor);
                }

            }
        }
        return Float.valueOf(pila.pop());
    }
    private static String operar(String str, float op1, float op2){
        float valor = 0;
        if(str.equals("+"))
            valor = op1 + op2;
        if(str.equals("-"))
            valor = op1 - op2;
        if(str.equals("*"))
            valor = op1*op2;
        if(str.equals("/")){
            //if(op2 != 0)
            valor = op1 / op2;
            //else
            //xerror++;
        }
        if(str.equals("^"))
            valor = (float) Math.pow(op1,op2);
        if(str.equals("√"))
            valor = (float) Math.pow(op2, 1/op1);
        return String.valueOf(valor);
    }

    private static float factorial(int num){
        float x = 1;
        int i;
        for (i = 1; i < num; i++)
            x = x * (i+1);
        return x;
    }
}

