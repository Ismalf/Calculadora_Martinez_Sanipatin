package com.example.calculadora_martinez_sanipatin.Presenter;

import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;
import com.example.calculadora_martinez_sanipatin.Model.CalculatorModel;

/**
 * Esta clase implementa los metodos de la Interfaz Calculator.Presenter,
 * y se conecta con la Vista y el Modelo
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class CalculatorPresenter implements Calculator.Presenter {

    /**
     * Declaracion de las Interfaces tanto del Modelo como de la Vista
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
     * Invoca al metodo implementado en la Interfaz de la Vista.
     * @param result es el valor enviado desde el Presentador a la Vista
     */
    @Override
    public void showResult(String result) { _view.showResult(result); }

    /**
     * Invoca el metodo implementado en la Interfaz de la Vista
     * @param buffer
     */
    @Override
    public void showBuffer(String buffer) { _view.showBuffer(buffer); }

    /**
     * Crea una nueva instancia para la interfaz _model de tipo CalculatorModel
     */
    @Override
    public void emptyData() { _model = new CalculatorModel(this); }

    /**
     * Invoca el metodo implementado en la Interfaz del Modelo para calcular un resultado
     */
    @Override
    public void calculate() { _model.calculate(); }

    /**
     * Invoca el metodo implementado en la Intefaz del Modelo para agregar operandos
     * @param operand es el parametro tipo String de una operacion
     */
    @Override
    public void addOperand(String operand) { _model.addOperand(operand); }

    /**
     * Invoca el metodo implementado en la Intefaz del Modelo para remover operandos
     */
    @Override
    public void removeOperand() { _model.removeOperand(); }

    /**
     * Invoca el metodo implementado en la Interfaz del Modelo para agregar cierto valor a memoria
     */
    @Override
    public void memoryAdd() { _model.memoryAdd(); }

    /**
     * Invoca el metodo implementado en la Interfaz del Modelo para substraer cierto valor de la memoria
     */
    @Override
    public void memorySub() { _model.memorySub(); }

    /**
     * Invoca el metodo implementado en la Interfaz del Modelo para agregar cierto valor ingresado,
     * al valor guardado en memoria.
     */
    @Override
    public void memoryRecall() { _model.memoryRecall(); }

    /**
     * Invoca el metodo implementado en la Interfaz del Modelo para limpiar los valores almacenados en memoria
     */
    @Override
    public void memoryClear() { _model.memoryClear(); }

    @Override
    public void toHex() {
        _model.toHex();
    }

    @Override
    public void toOct() {
        _model.toOct();
    }

    @Override
    public void toBinary() {
        _model.toBinary();
    }

}
