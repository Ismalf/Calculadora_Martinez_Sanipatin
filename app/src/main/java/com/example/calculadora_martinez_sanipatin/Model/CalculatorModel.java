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
     *
     * @param presenter parametro de tipo CalculatorPresenter.
     */
    public CalculatorModel(Calculator.Presenter presenter) {
        _presenter = presenter;
        _presenter.showResult("");
        _presenter.showBuffer("");
        _engine = new Engine();
    }

    /**
     * Este metodo añade un digito u operando a la cadena ingresada por el usuario
     *
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
        _input = _input.length() != 0 ? _input.substring(0, _input.length() - 1) : _input;
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
        _buffer += _input + "\n";
        _checkbuffer();
        try {
            _input = _engine.calculate(_input);
        } catch (Exception e) {
            e.printStackTrace();
            _input = "Error de sintaxis";
        }
        _presenter.showBuffer(_buffer);
        _presenter.showResult(_input);
    }

    /**
     * Este metodo adiciona cierto valor igresado al valor guardado en memoria si lo hubiere,
     * caso contratio almacena en memoria.
     */
    @Override
    public void memoryAdd() {
        if (_input == "") return;
        _memory = _memory == "" ? "0" : _memory;
        try {
            _memory = _engine.calculate(_memory + "+" + _input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo substrae cierto valor ingreso al valor guardado en memoria si lo hubiere,
     * caso contrario lo almacena en memoria.
     */
    @Override
    public void memorySub() {
        if (!_memory.equals("") && _input != "") {
            try {
                _memory = _engine.calculate(_memory + "-" + _input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public void toHex() {
        if (!isNumber(_input)) return;
        // For storing remainder
        int rem;
        int num = Integer.parseInt(_input);
        // For storing result
        String str2 = "";

        // Digits in hexadecimal number system
        char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        while (num > 0) {
            rem = num % 16;
            str2 = hex[rem] + str2;
            num = num / 16;
        }
        _input = str2;
        _presenter.showResult(_input);
    }

    @Override
    public void toOct() {
        if (!isNumber(_input)) return;
        // For storing remainder
        int rem;
        int num = Integer.parseInt(_input);
        // For storing result
        String str = "";

        // Digits in Octal number system
        char dig[] = {'0', '1', '2', '3', '4', '5', '6', '7'};

        while (num > 0) {
            rem = num % 8;
            str = dig[rem] + str;
            num = num / 8;
        }
        _input = str;
        _presenter.showResult(_input);
    }

    @Override
    public void toBinary() {
        if (!isNumber(_input)) return;
        int binary[] = new int[40];
        int index = 0;
        int num = Integer.parseInt(_input);
        while (num > 0) {
            binary[index++] = num % 2;
            num = num / 2;
        }
        _input = "";
        for (int i = index - 1; i >= 0; i--) {
            _input += binary[i] + "";
        }
        _presenter.showResult(_input);
    }

    /**
     * Este metodo almacena las operaciones ingresadas por el usuario,
     * creando un registro a manera de historial de cada operacion o
     * conjunto de operaciones.
     */
    private void _checkbuffer() {
        String[] tmp = _buffer.split("\n");
        if (tmp.length > 5) {
            _buffer = "";
            for (int j = 1; j < tmp.length; j++) {
                _buffer += tmp[j] + "\n";
            }
        }
    }

    private boolean isNumber(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
