package com.example.calculadora_martinez_sanipatin.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;
import com.example.calculadora_martinez_sanipatin.Presenter.CalculatorPresenter;
import com.example.calculadora_martinez_sanipatin.R;

/**
 * Clase CalculatorView
 *
 * Clase encargada de manejar la vista de la calculadora,
 * tiene una conexión con el presentador, estableciendo así, el patrón MVP
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class CalculatorView extends AppCompatActivity implements Calculator.View {
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

    /**
     * Método onCreate()
     * Este método se implementa junto con la creación del proyecto.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _input = findViewById(R.id.input);
        _resultsBuffer = findViewById(R.id.results);
        _presenter = new CalculatorPresenter(this);
    }

    /**
     * Método memoryOperation()
     * Este método permite gestionar el funcionamiento de los valores en memoria.
     * @param v
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
     * Se indica al presentador que se debe eliminar el último caracter de la cadena del input del
     * motor, ofreciendo al usuario la posibilidad de corregir un valor mal ingresado.
     *
     * @param v
     */
    public void removeLast(View v) {
        _presenter.removeOperand();
    }

    /**
     * Se indica al presentador que se debe efectuar el cálculo de lo ingresado por el usuario.
     *
     * @param v
     */
    public void calculate(View v) {
        _presenter.calculate();
    }

    /**
     * Método invocado por el presentador cuando el modelo indica un cambio en el resulado de la
     * operación ingresada.
     *
     * @param result valor enviado desde el presentador, es el resultado de una operación
     */
    @Override
    public void showResult(String result) {
        _input.setText(result);
    }

    /**
     * Método invocado por el presentador cuando el modelo actualiza lo que se mostrará en el historial
     * de operaciones realizadas.
     *
     * @param buffer historial de operaciones efectuadas recientemente, contiene un máximo de 5 líneas.
     *               String de formato "operacion \n operacion \n"
     */
    @Override
    public void showBuffer(String buffer) {
        _resultsBuffer.setText(buffer);
    }
}
