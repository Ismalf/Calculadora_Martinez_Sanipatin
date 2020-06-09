package com.example.calculadora_martinez_sanipatin.Model.Core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Clase que constiene los Test Unitarios de ciertos metodos implementados,
 * en la Calculadora.
 */
public class EngineTest {
    /**
     * Instancia de un Objeto de tipo Engine para poder acceder a sus metodos.
     */
    private Engine _engine;

    /**
     * Metodo para saber si la prueba esta lista para ejecutarse.
     */
    @Before
    public void setUp(){
        _engine = new Engine();
        System.out.println("Ready for testing");
    }

    /**
     * UnitTest para la operación suma.
     */
    @Test
    public void addition() {
        String _result = _engine.calculate("2+2");
        assertEquals("Addition failed","4",_result);
    }

    /**
     * UnitTest para la operación resta.
     */
    @Test
    public void subtraction() {
        String _result = _engine.calculate("6-2");
        assertEquals("Addition failed","4",_result);
    }

    /**
     * UnitTest para la operación multiplicacion.
     */
    @Test
    public void multiplication() {
        String _result = _engine.calculate("-2*2");
        assertEquals("Addition failed","-4",_result);
    }

    /**
     * UnitTest para la operación division.
     */
    @Test
    public void division() {
        String _result = _engine.calculate("2/4");
        assertEquals("Addition failed","0.5",_result);
    }

    /**
     * UnitTest para una operacion compleja.
     */
    @Test
    public void complex1() {
        String _result = _engine.calculate("2+2*7+(5+1*(3/8))");
        assertEquals("Addition failed","21.375",_result);
    }

    /**
     * UnitTest para una operacion compleja con varios parentesis.
     */
    @Test
    public void complex2(){
        String _result = _engine.calculate("(0/3)+6-((3/8)*(7-(6/2)))");
        assertEquals("Addition failed","4.5",_result);
    }

    /**
     * UnitTest para una division por cero.
     */
    @Test
    public void divisionPorCero(){
        String _result = _engine.calculate("(2/0)");
        assertEquals("Addition failed","Infinity",_result);
    }

    /**
     * UnitTest para verificar el ingreso de un valor nulo.
     */
    @Test
    public void ingresoNull(){
        String _result = _engine.calculate("");
        assertEquals("Addition failed","Error en sintaxis",_result);
    }

    /**
     * UnitTest para una operacion con decimales.
     */
    @Test
    public void decimales(){
        String _result = _engine.calculate(".03*6-7.8*3-(4/5)");
        assertEquals("Addition failed","-24.02",_result);
    }

    /**
     * UnitTest para un ingreso con error en la sistaxis.
     */
    @Test
    public void ingresoIncorrecto(){
        String _result = _engine.calculate("5*((7-8)+5//3");
        assertEquals("Addition failed","Error en sintaxis",_result);
    }

    @Test
    public void mod1() {
        String _result = _engine.calculate("5%6");
        assertEquals("Addition failed","5",_result);
    }

    @Test
    public void mod2() {
        String _result = _engine.calculate("6%2");
        assertEquals("Addition failed","0",_result);
    }
    @Test
    public void mod3() {
        String _result = _engine.calculate("(-5)%3");
        assertEquals("Addition failed","1",_result);
    }
    @Test
    public void mod4() {
        String _result = _engine.calculate("(-5)%8");
        assertEquals("Addition failed","3",_result);
    }

    @Test
    public void mod5() {
        String _result = _engine.calculate("5%(-3)");
        assertEquals("Addition failed","-1",_result);
    }
    @Test
    public void mod6() {
        String _result = _engine.calculate("5%(-8)");
        assertEquals("Addition failed","-3",_result);
    }
    @Test
    public void mod7() {
        String _result = _engine.calculate("(-9)%(-9)");
        assertEquals("Addition failed","0",_result);
    }
    @Test
    public void mod8() {
        String _result = _engine.calculate("-2%(-6)");
        assertEquals("Addition failed","4",_result);
    }
}