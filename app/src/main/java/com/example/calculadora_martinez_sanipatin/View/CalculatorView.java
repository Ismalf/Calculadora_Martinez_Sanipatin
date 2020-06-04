package com.example.calculadora_martinez_sanipatin.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;
import com.example.calculadora_martinez_sanipatin.Presenter.CalculatorPresenter;
import com.example.calculadora_martinez_sanipatin.R;

/**
 * Clase encargada de manejar la vista de la calculadora,
 * tiene una conexión con el presentador, estableciendo así, el patrón MVP
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class CalculatorView extends AppCompatActivity implements Calculator.View {
    //region attributes
    /**
     * Variable que servirá para almacenar una referencia al campo de texto en el que se mostrará
     * un historial de las operaciones realizadas
     */
    private TextView _resultsBuffer;
    /**
     * Variable que servirá para almacenar una referencia al campo de texto en el que se mostrará
     * los datos ingresados para efectuar una operación matemática
     */
    private TextView _input;

    private Calculator.Presenter _presenter;

    private View.OnClickListener addInput = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            addToInput(v);
        }
    };

    private View.OnClickListener memOp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            memoryOperation(v);
        }
    };

    private View.OnClickListener backspace = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            removeLast(v);
        }
    };

    private View.OnClickListener clear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clearInput(v);
        }
    };

    private View.OnClickListener calc = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculate(v);
        }
    };
    //endregion

    /**
     * Este metodo se implementa junto con la creación del proyecto.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _input = findViewById(R.id.input);
        _resultsBuffer = findViewById(R.id.results);
        _presenter = new CalculatorPresenter(this);
        _init();
    }

    //region methods
    /**
     * Metodo de inicialización para vincular los botones con el evento click para accionar las
     * funciones respectivas del presentador
     */
    private void _init(){
        findViewById(R.id.number0).setOnClickListener(addInput);
        findViewById(R.id.number1).setOnClickListener(addInput);
        findViewById(R.id.number2).setOnClickListener(addInput);
        findViewById(R.id.number3).setOnClickListener(addInput);
        findViewById(R.id.number4).setOnClickListener(addInput);
        findViewById(R.id.number5).setOnClickListener(addInput);
        findViewById(R.id.number6).setOnClickListener(addInput);
        findViewById(R.id.number7).setOnClickListener(addInput);
        findViewById(R.id.number8).setOnClickListener(addInput);
        findViewById(R.id.number9).setOnClickListener(addInput);
        findViewById(R.id.parenthesisopen).setOnClickListener(addInput);
        findViewById(R.id.parenthesisclose).setOnClickListener(addInput);
        findViewById(R.id.decimal).setOnClickListener(addInput);
        findViewById(R.id.divide).setOnClickListener(addInput);
        findViewById(R.id.multi).setOnClickListener(addInput);
        findViewById(R.id.addition).setOnClickListener(addInput);
        findViewById(R.id.substract).setOnClickListener(addInput);
        findViewById(R.id.memadd).setOnClickListener(memOp);
        findViewById(R.id.memclear).setOnClickListener(memOp);
        findViewById(R.id.memrecall).setOnClickListener(memOp);
        findViewById(R.id.memsub).setOnClickListener(memOp);
        findViewById(R.id.backspace).setOnClickListener(backspace);
        findViewById(R.id.clear).setOnClickListener(clear);
        findViewById(R.id.calculate).setOnClickListener(calc);
    }

    /**
     * Este metodo permite gestionar el funcionamiento de los valores en memoria.
     * @param v instancia de la vista
     */
    public void memoryOperation(View v) {
        switch (v.getId()) {
            case R.id.memclear:
                _presenter.memoryClear();
                break;
            case R.id.memadd:
                _presenter.memoryAdd();
                break;
            case R.id.memsub:
                _presenter.memorySub();
                break;
            case R.id.memrecall:
                _presenter.memoryRecall();
                break;
        }
    }

    /**
     * Envía al presentador el valor del boton accionado en la vista
     *
     * @param k instancia de la vista
     */
    public void addToInput(View k) {
        switch (k.getId()) {
            case (R.id.number1):
                _presenter.addOperand("1");
                break;
            case (R.id.number2):
                _presenter.addOperand("2");
                break;
            case (R.id.number3):
                _presenter.addOperand("3");
                break;
            case (R.id.number4):
                _presenter.addOperand("4");
                break;
            case (R.id.number5):
                _presenter.addOperand("5");
                break;
            case (R.id.number6):
                _presenter.addOperand("6");
                break;
            case (R.id.number7):
                _presenter.addOperand("7");
                break;
            case (R.id.number8):
                _presenter.addOperand("8");
                break;
            case (R.id.number9):
                _presenter.addOperand("9");
                break;
            case (R.id.number0):
                _presenter.addOperand("0");
                break;
            case (R.id.parenthesisopen):
                _presenter.addOperand("(");
                break;
            case (R.id.parenthesisclose):
                _presenter.addOperand(")");
                break;
            case (R.id.divide):
                _presenter.addOperand("/");
                break;
            case (R.id.decimal):
                _presenter.addOperand(".");
                break;
            case (R.id.addition):
                _presenter.addOperand("+");
                break;
            case (R.id.substract):
                _presenter.addOperand("-");
                break;
            case (R.id.multi):
                _presenter.addOperand("*");
                break;
        }
    }

    /**
     * Se indica al presentador que se deben re inicializar los datos del historial de
     * operaciones (buffer) y el input para el motor de la calculadora, brindando al usuario la
     * posibilidad de limpiar la interfaz de la calculadora.
     *
     * @param v
     */
    public void clearInput(View v) {
        _presenter.emptyData();
    }

    /**
     * Se indica al presentador que se debe eliminar el ultimo caracter de la cadena del input del
     * motor, ofreciendo al usuario la posibilidad de corregir un valor mal ingresado.
     *
     * @param v
     */
    public void removeLast(View v) {
        _presenter.removeOperand();
    }

    /**
     * Se indica al presentador que se debe efectuar el calculo de lo ingresado por el usuario.
     *
     * @param v
     */
    public void calculate(View v) {
        _presenter.calculate();
    }

    /**
     * Metodo invocado por el presentador cuando el modelo indica un cambio en el resulado de la
     * operacion ingresada.
     *
     * @param result valor enviado desde el presentador, es el resultado de una operación
     */
    @Override
    public void showResult(String result) {
        _input.setText(result);
    }

    /**
     * Metodo invocado por el presentador cuando el modelo actualiza lo que se mostrara en el historial
     * de operaciones realizadas.
     *
     * @param buffer historial de operaciones efectuadas recientemente, contiene un maximo de 5 lineas.
     *               String de formato "operacion \n operacion \n"
     */
    @Override
    public void showBuffer(String buffer) {
        _resultsBuffer.setText(buffer);
    }
    //endregion
}
