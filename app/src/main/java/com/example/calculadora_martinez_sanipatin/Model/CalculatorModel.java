package com.example.calculadora_martinez_sanipatin.Model;

import com.example.calculadora_martinez_sanipatin.Model.Core.Engine;
import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;

/**
 * En esta Clase encontramos la logica de Negocio o Casos de Uso de la Calculadora,
 * esta se conecta con la Clase CalculatorPresenter, e implementa la interfaz Calculator.Model.
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public class CalculatorModel implements Calculator.Model {
    /**
     * Declaracion de la Interfaz del Presentador,
     * Campos de la clase de tipo String y,
     * un atributo de tipo Engine.
     */
    private Calculator.Presenter _presenter;
    private String _memory = "";
    private String _input = "";
    private String _buffer = "";
    private Engine _engine;

    /**
     * Este constructor es invocado en el Constructor del CalculatorPresenter,
     * para poder acceder a sus metodos.
     * @param presenter parametro de tipo CalculatorPresenter.
     */
    public CalculatorModel(Calculator.Presenter presenter){
        _presenter = presenter;
        _presenter.showResult("");
        _presenter.showBuffer("");
        _engine = new Engine();
    }

    /**
     * Este metodo añade un digito u operando a la cadena ingresada por el usuario
     * @param operand parametro de tipo String que viene del Presentador
     */
    @Override
    public void addOperand(String operand) {
        _input += operand;
        _presenter.showResult(_input);
    }

    /**
     * Este metodo borra el ultimo digito u operando ingresado por el usuario.
     */
    @Override
    public void removeOperand() {
        _input = _input.length() != 0 ? _input.substring(0,_input.length()-1) : _input;
        _presenter.showResult(_input);
    }

    /**
     * Este metodo primero verifica si no hay algun dato en memoria para adicionarlo al dato de entrada,
     * luego se invoca al metodo checkbuffer para agregar al buffer las operaciones ingresadas,
     * Seguidamente se llama a un metodo de la Clase Engine para calcular el resultado, y
     * Finalmente se procede a invocar al metodo showResult(_input) del Presentador enviandole como parametro el resultado.
     */
    @Override
    public void calculate() {
        _buffer+=_input+"\n";
        _checkbuffer();
        _input = _engine.calculate(_input);
        _presenter.showBuffer(_buffer);
        _presenter.showResult(_input);
    }

    /**
     * Este metodo adiciona cierto valor igresado al valor guardado en memoria si lo hubiere,
     * caso contratio almacena en memoria.
     */
    @Override
    public void memoryAdd() {
        _memory = _memory == "" ? "0" : _memory;
        _memory = _engine.calculate(_memory+"+"+_input);
    }

    /**
     * Este metodo substrae cierto valor ingreso al valor guardado en memoria si lo hubiere,
     * caso contrario lo almacena en memoria.
     */
    @Override
    public void memorySub() {
        _memory = _memory == "" ? "0" : _memory;
        _memory = _engine.calculate(_memory+"-"+_input);
    }

    /**
     * Este metodo adiciona al valor almacenado en memoria un valor ingresado por el usuario.
     */
    @Override
    public void memoryRecall() {
        _input += _memory;
        _presenter.showResult(_input);
    }

    /**
     * Este metodo elimina cualquier valor almacenado en memoria.
     */
    @Override
    public void memoryClear() {
        _memory = "";
    }

    /**
     * Este metodo almacena las operaciones ingresadas por el usuario,
     * creando un registro a manera de historial de cada operacion o
     * conjunto de operaciones.
     */
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
