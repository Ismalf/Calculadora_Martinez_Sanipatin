package com.example.calculadora_martinez_sanipatin.Model.Core;

import java.util.Vector;

public class Engine {

    private String[] stringInfija;
    public int error = 0;
    private Float resp = 0.0f;

    public Engine(){
    }

    public String calculate(String input){
        getStrInfija(input);
        if(error > 0){
            return "Error en sintaxis";
        }else{
            PostFijo post = new PostFijo(stringInfija); // CONVERTIR CADENA INFIJA EN POSTFIJA
            resp =  post.getValor();
            return removeDecimals(resp.toString());
        }
    }
    private String removeDecimals(String _v){
        String[] tmp = _v.split("\\.");
        String _d = tmp[1];
        int _numOfZeroes = 0;
        for(int i = 0; i < _d.length(); i++){
            if(_d.charAt(i) == '0') _numOfZeroes++;
        }
        return _numOfZeroes == _d.length() ? tmp[0] : _v;
    }
    //CODIGOS ASCII OPERADORES
    //40 => (
    //41 => )
    //42 => *
    //43 => +
    //45 => -
    //46 => .
    //47 => /

    public void getStrInfija(String str){
        Vector<String> cadenaInfija = new Vector();

        Sintactico asin = new Sintactico(str.length());
        Lexico alex = new Lexico();

        error = 0;
        //if(str.length() == 0)
        //error++;

        String num = "";
        int i,j,tipo,atipo,nOperadores,nNumeros;
        char c;
        atipo = -5;
        i = 0;
        j = 1;
        nOperadores = 0;
        nNumeros = 0;
        while( i < str.length() && error == 0){
            c = str.charAt(i);
            if( c != ' '){
                j = 1;
                tipo = alex.getTipo(c); //Tipo es un entero que con el que indica un caracter
                if( (atipo == 0 && tipo == 2) || (atipo == 3 && tipo == 0) || (atipo == 3 && tipo == 2)){
                    c = '*';  //Agrega operador '*' en expresiones
                    j = 0;    ///   x * (..      )*[        (...)*999
                    tipo = 4;
                }
                if( (atipo == -5 && c == '-') || (atipo == 2 && c == '-')){ //Agrega el 0 en expresiones (-9) ó -5 -> (0-9), 0-5
                    c = '0';
                    j = 0;
                    tipo = 0;
                }
                alex.add(c);
                asin.setType(tipo);
                asin.add(c);

                error = error + alex.getError() + asin.getError();

                if(error == 0){
                    if( tipo < 2){ //Es número
                        num = num + String.valueOf(c);
                    }else{ //Es operador o signo de agrupación
                        if(num.equals("") == false){
                            try{
                                float x = Float.valueOf(num).floatValue();
                                cadenaInfija.add(num);
                                nNumeros++;
                            }catch(NumberFormatException e){
                                error++;
                            }
                        }
                        num = "";
                        if(tipo == 4) //Es operador
                            nOperadores++;
                        cadenaInfija.add(String.valueOf(c));
                    }
                }
                atipo = tipo;
            }
            i = i + j;
        }
        if(asin.isEmpty() == false)
            error++; //Si no están bien emperajados los paréntesis
        if(num.equals("") == false){ // Si la cadena "num" tiene un número
            try{
                float x = Float.valueOf(num).floatValue();
                nNumeros++;
                cadenaInfija.add(num);
            }catch(NumberFormatException e){
                error++;
            }
        }
        if(nOperadores + 1 != nNumeros)
            error++; //Si número de operadores no le corresponde al número de operandos

        if(error == 0){
            int tamanio = cadenaInfija.size();
            stringInfija = new String[tamanio];
            for(i = 0; i < tamanio; i++){ //Pasa el vector a una arreglo de String
                stringInfija[i] = cadenaInfija.get(i);
            }
        }
    }
}