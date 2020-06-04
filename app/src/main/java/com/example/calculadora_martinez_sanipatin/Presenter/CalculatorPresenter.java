package com.example.calculadora_martinez_sanipatin.Presenter;

import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;
import com.example.calculadora_martinez_sanipatin.Model.CalculatorModel;

/**
 * Clase CalculatorPresenter
 *
 * Esta clase conecta la interfaz grafica con lo casos de uso del Modelo,
 * e implementa la Interzas Calculator.Presenter.
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class CalculatorPresenter implements Calculator.Presenter {

    /**
     * Declaración de las Interfaces tanto del Modelo como de la Vista
     *
     */
    private Calculator.View _view;
    private Calculator.Model _model;

    /**
     * Constructor de la Clase CalculatorPresenter
     * @param view instancia de la vista
     */
    public CalculatorPresenter(Calculator.View view){
        _view = view;
        _model = new CalculatorModel(this);
    }

    /**
     * Metodo showResult()
     * Invoca al metodo implementado en la Interfaz de la Vista.
     * @param result es el valor enviado desde el Presentador a la Vista
     */
    @Override
    public void showResult(String result) { _view.showResult(result); }

    /**
     * Metodo showBuffer()
     * Invoca el metodo implementado en la Interfaz de la Vista
     * @param buffer
     */
    @Override
    public void showBuffer(String buffer) { _view.showBuffer(buffer); }

    /**
     * Método emptyData()
     * Crea una nueva instancia para la interfaz _model de tipo CalculatorModel
     */
    @Override
    public void emptyData() { _model = new CalculatorModel(this); }

    /**
     * Método calculate()
     * Invoca el método implementado en la Interfaz del Modelo para calcular un resultado
     */
    @Override
    public void calculate() { _model.calculate(); }

    /**
     * Método addOperand()
     * Invoca el método implementado en la Intefaz del Modelo para agregar operandos
     * @param operand es el parametro tipo String de una operación
     */
    @Override
    public void addOperand(String operand) { _model.addOperand(operand); }

    /**
     * Método removeOperand()
     * Invoca el método implementado en la Intefaz del Modelo para remover operandos
     */
    @Override
    public void removeOperand() { _model.removeOperand(); }

    /**
     * Método memoryAdd()
     * Invoca el método implementado en la Interfaz del Modelo para agregar cierto valor a memoria
     */
    @Override
    public void memoryAdd() { _model.memoryAdd(); }

    /**
     * Método memorySub()
     * Invoca el método implementado en la Interfaz del Modelo para substraer cierto valor de la memoria
     */
    @Override
    public void memorySub() { _model.memorySub(); }

    /**
     * Método memoryRecall()
     * Invoca el método implementado en la Interfaz del Modelo para agregar cierto valor ingresado,
     * al valor guardado en memoria.
     */
    @Override
    public void memoryRecall() { _model.memoryRecall(); }

    /**
     * Método memoryClear()
     * Invoca el método implementado en la Interfaz del Modelo para limpiar los valores almacenados en memoria
     */
    @Override
    public void memoryClear() { _model.memoryClear(); }
}
