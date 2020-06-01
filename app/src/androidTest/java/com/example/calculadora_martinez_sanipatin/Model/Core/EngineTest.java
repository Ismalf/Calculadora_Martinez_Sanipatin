package com.example.calculadora_martinez_sanipatin.Model.Core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineTest {
    private Engine _engine;
    @Before
    public void setUp(){
        _engine = new Engine();
        System.out.println("Ready for testing");
    }
    @Test
    public void addition() {
        String _result = _engine.calculate("2+2");
        assertEquals("Addition failed","4",_result);
    }
    @Test
    public void subtraction() {
        String _result = _engine.calculate("6-2");
        assertEquals("Addition failed","4",_result);
    }
    @Test
    public void multiplication() {
        String _result = _engine.calculate("-2*2");
        assertEquals("Addition failed","-4",_result);
    }
    @Test
    public void division() {
        String _result = _engine.calculate("2/4");
        assertEquals("Addition failed","0.5",_result);
    }
    @Test
    public void complex1() {
        String _result = _engine.calculate("2+2*7+(5+1*(3/8))");
        assertEquals("Addition failed","21.375",_result);
    }
}