package com.example.calculadora_martinez_sanipatin.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;
import com.example.calculadora_martinez_sanipatin.Presenter.CalculatorPresenter;
import com.example.calculadora_martinez_sanipatin.R;

public class CalculatorView extends AppCompatActivity implements Calculator.View {

    private TextView _result;
    private TextView _input;
    private Calculator.Presenter _presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _input = findViewById(R.id.input);
        _result = findViewById(R.id.results);
        _presenter = new CalculatorPresenter(this);
    }

    public void addToInput(View k){
        switch(k.getId()){
            case (R.id.number1):  _presenter.addOperand("1"); break;
            case (R.id.number2):  _presenter.addOperand("2"); break;
            case (R.id.number3):  _presenter.addOperand("3"); break;
            case (R.id.number4):  _presenter.addOperand("4"); break;
            case (R.id.number5):  _presenter.addOperand("5"); break;
            case (R.id.number6):  _presenter.addOperand("6"); break;
            case (R.id.number7):  _presenter.addOperand("7"); break;
            case (R.id.number8):  _presenter.addOperand("8"); break;
            case (R.id.number9):  _presenter.addOperand("9"); break;
            case (R.id.number0):  _presenter.addOperand("0"); break;
            case (R.id.parenthesisopen):  _presenter.addOperand("("); break;
            case (R.id.parenthesisclose):  _presenter.addOperand(")"); break;
            case (R.id.divide):  _presenter.addOperand("/"); break;
            case (R.id.decimal):  _presenter.addOperand("."); break;
            case (R.id.addition):  _presenter.addOperand("+"); break;
            case (R.id.substract):  _presenter.addOperand("-"); break;
            case (R.id.multi):  _presenter.addOperand("*"); break;
        }
    }

    public void clearInput(View v){ _presenter.emptyData(); }

    public void removeLast(View v){ _presenter.removeOperand(); }

    public void calculate(View v){ _presenter.calculate(); }

    @Override
    public void showResult(String result) { _input.setText(result); }

    @Override
    public void showBuffer(String buffer) { _result.setText(buffer); }
}
