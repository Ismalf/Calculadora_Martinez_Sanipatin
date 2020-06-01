package com.example.calculadora_martinez_sanipatin.Presenter;

import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;
import com.example.calculadora_martinez_sanipatin.Model.CalculatorModel;

public class CalculatorPresenter implements Calculator.Presenter {

    private Calculator.View _view;
    private Calculator.Model _model;

    public CalculatorPresenter(Calculator.View view){
        _view = view;
        _model = new CalculatorModel(this);
    }

    @Override
    public void showResult(String result) { _view.showResult(result); }

    @Override
    public void showBuffer(String buffer) { _view.showBuffer(buffer); }

    @Override
    public void emptyData() { _model = new CalculatorModel(this); }

    @Override
    public void calculate() { _model.calculate(); }

    @Override
    public void addOperand(String operand) { _model.addOperand(operand); }

    @Override
    public void removeOperand() { _model.removeOperand(); }
}
