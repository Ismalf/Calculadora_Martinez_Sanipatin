package com.example.calculadora_martinez_sanipatin;

import com.example.calculadora_martinez_sanipatin.Model.Core.Engine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Engine _engine;
    @Before
    public void setUp() {
        _engine = new Engine();
        System.out.println("Ready for testing");
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void transformInputlog2() throws Exception {
        String _result = _engine.makeExplicit("1+log3");
        assertEquals("Transform failed", "1+(10→3)", _result);
    }
    @Test
    public void sin1() throws Exception{
        String _result = _engine.calculate("sin(59.8)");
        System.out.println(_result);
        assertEquals("Sin failed", "0.707106781", _result);
    }
    @Test
    public void ln() throws Exception{
        String _result = _engine.calculate("ln(180)");
        System.out.println(_result);
        assertEquals("Sin failed", "0.707106781", _result);
    }
    @Test
    public void log() throws Exception{
        String _result = _engine.calculate("log(-180)");
        System.out.println(_result);
        assertEquals("Sin failed", "2.2552726", _result);
    }
    @Test
    public void cos1() throws Exception{
        String _result = _engine.calculate("cos(45)");
        System.out.println(_result);
        assertEquals("Cos failed", "0.707106781", _result);
    }
}