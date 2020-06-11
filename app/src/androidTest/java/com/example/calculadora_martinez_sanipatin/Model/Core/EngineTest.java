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
    public void setUp() {
        _engine = new Engine();
        System.out.println("Ready for testing");
    }

    /**
     * UnitTest para la operación suma.
     */
    @Test
    public void addition() throws Exception {
        String _result = null;
        _result = _engine.calculate("2+2");
        assertEquals("Addition failed", "4", _result);
    }

    /**
     * UnitTest para la operación resta.
     */
    @Test
    public void subtraction() throws Exception {
        String _result = null;
        _result = _engine.calculate("6-2");
        assertEquals("Addition failed", "4", _result);
    }

    /**
     * UnitTest para la operación multiplicacion.
     */
    @Test
    public void multiplication() throws Exception {
        String _result = null;
        _result = _engine.calculate("-2*2");
        assertEquals("Addition failed", "-4", _result);
    }

    /**
     * UnitTest para la operación division.
     */
    @Test
    public void division() throws Exception {
        String _result = _engine.calculate("2/4");
        assertEquals("Addition failed", "0.5", _result);
    }

    /**
     * UnitTest para una operacion compleja.
     */
    @Test
    public void complex1() throws Exception {
        String _result = _engine.calculate("2+2*7+(5+1*(3/8))");
        assertEquals("Addition failed", "21.375", _result);
    }

    /**
     * UnitTest para una operacion compleja con varios parentesis.
     */
    @Test
    public void complex2() throws Exception {
        String _result = _engine.calculate("(0/3)+6-((3/8)*(7-(6/2)))");
        assertEquals("Addition failed", "4.5", _result);
    }

    /**
     * UnitTest para una division por cero.
     */
    @Test
    public void divisionPorCero() throws Exception {
        String _result = _engine.calculate("(2/0)");
        assertEquals("Addition failed", "Infinity", _result);
    }

    /**
     * UnitTest para verificar el ingreso de un valor nulo.
     */
    @Test
    public void ingresoNull() throws Exception {
        String _result = _engine.calculate("");
        assertEquals("Addition failed", "Error en sintaxis", _result);
    }

    /**
     * UnitTest para una operacion con decimales.
     */
    @Test
    public void decimales() throws Exception {
        String _result = _engine.calculate(".03*6-7.8*3-(4/5)");
        assertEquals("Addition failed", "-24.02", _result);
    }

    /**
     * UnitTest para un ingreso con error en la sistaxis.
     */
    @Test
    public void ingresoIncorrecto() throws Exception {
        String _result = _engine.calculate("5*((7-8)+5//3");
        assertEquals("Addition failed", "Error en sintaxis", _result);
    }

    @Test
    public void mod1() throws Exception {
        String _result = _engine.calculate("5%6");
        assertEquals("Addition failed", "5", _result);
    }

    @Test
    public void mod2() throws Exception {
        String _result = _engine.calculate("6%2");
        assertEquals("Addition failed", "0", _result);
    }

    @Test
    public void mod3() throws Exception {
        String _result = _engine.calculate("-5%3");
        assertEquals("Addition failed", "1", _result);
    }

    @Test
    public void mod4() throws Exception {
        String _result = _engine.calculate("-5%8");
        assertEquals("Addition failed", "3", _result);
    }

    @Test
    public void mod5() throws Exception {
        String _result = _engine.calculate("5%-3");
        assertEquals("Addition failed", "-1", _result);
    }

    @Test
    public void mod6() throws Exception {
        String _result = _engine.calculate("5%-8");
        assertEquals("Addition failed", "-3", _result);
    }

    @Test
    public void mod7() throws Exception {
        String _result = _engine.calculate("-9%-9");
        assertEquals("Addition failed", "-0", _result);
    }

    @Test
    public void mod8() throws Exception {
        String _result = _engine.calculate("-2%-6");
        assertEquals("Addition failed", "-2", _result);
    }

    @Test
    public void transformInput() throws Exception {
        String _result = _engine.makeExplicit("1+-3.1");
        assertEquals("Transform failed", "1+(0-3.1)", _result);
    }

    @Test
    public void transformInput2() throws Exception {
        String _result = _engine.makeExplicit("-3.1");
        assertEquals("Transform failed", "(0-3.1)", _result);
    }

    @Test
    public void transformInput3() throws Exception {
        String _result = _engine.makeExplicit("1-(3.1+1)");
        assertEquals("Transform failed", "1-(3.1+1)", _result);
    }

    @Test
    public void transformInput4() throws Exception {
        String _result = _engine.makeExplicit("5%-8");
        assertEquals("Transform failed", "5%(0-8)", _result);
    }

    @Test
    public void transformInputlog1() throws Exception {
        String _result = _engine.makeExplicit("1+ln(3.1)");
        assertEquals("Transform failed", "1+(e→(3.1))", _result);
    }

    @Test
    public void transformInputlog2() throws Exception {
        String _result = _engine.makeExplicit("1+log3");
        assertEquals("Transform failed", "1+(10→3)", _result);
    }
    @Test
    public void transformInputlog7() throws Exception {
        String _result = _engine.makeExplicit("1+log33+2");
        assertEquals("Transform failed", "1+(10→33)+2", _result);
    }
    @Test
    public void transformInputlog3() throws Exception {
        String _result = _engine.makeExplicit("1+log(3)");
        assertEquals("Transform failed", "1+(10→3)", _result);
    }
    @Test
    public void transformInputlog4() throws Exception {
        String _result = _engine.makeExplicit("1+log(3,5)");
        assertEquals("Transform failed", "1+(5→3)", _result);
    }
    @Test
    public void transformInputlog5() throws Exception {
        String _result = _engine.makeExplicit("ln(3+5)");
        System.out.println(_result);
        assertEquals("Transform failed", "(e→(3+5))", _result);
    }
    @Test
    public void transformInputlog6() throws Exception {
        String _result = _engine.makeExplicit("log((1+5),3)");
        System.out.println(_result);
        assertEquals("Transform failed", "(3→(1+5))", _result);
    }

    @Test
    public void log1() throws Exception {
        String _result = _engine.calculate("log(3)");
        System.out.println(_result);
        assertEquals("Transform failed", "0.47712126", _result);
    }

    @Test
    public void log3() throws Exception {
        String _result = _engine.calculate("ln(3)");
        System.out.println(_result);
        assertEquals("Transform failed", "2.71828182846", _result);
    }
}