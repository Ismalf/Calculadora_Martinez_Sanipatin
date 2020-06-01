package com.example.calculadora_martinez_sanipatin.Model;

import com.example.calculadora_martinez_sanipatin.Model.Core.Engine;
import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;

public class CalculatorModel implements Calculator.Model {

    private Calculator.Presenter _presenter;
    private String _input = "";
    private String _buffer = "";
    private Engine _engine;

    public CalculatorModel(Calculator.Presenter presenter){
        _presenter = presenter;
        _presenter.showResult("");
        _presenter.showBuffer("");
        _engine = new Engine();
    }

    @Override
    public void addOperand(String operand) {
        _input += operand;
        _presenter.showResult(_input);
    }

    @Override
    public void removeOperand() {
        _input = _input.length() != 0 ? _input.substring(0,_input.length()-1) : _input;
        _presenter.showResult(_input);
    }

    @Override
    public void calculate() {
        _buffer+=_input+"\n";
        _checkbuffer();
        _input = _engine.calculate(_input);
        _presenter.showBuffer(_buffer);
        _presenter.showResult(_input);
    }

    // Esto mover a una clase
    private void _checkbuffer(){
        String[] tmp =_buffer.split("\n");
        if( tmp.length > 5 ){
            _buffer = "";
            for(int j = 1; j < tmp.length; j++){
                _buffer += tmp[j] + "\n";
            }
        }
    }
}
