package com.example.calculadora_martinez_sanipatin.Interfaces;

/**
 * Interface Calculator,
 * Se implementa tres Interfaces, una para el Modelo, otra para la Vista y otra para el Presentador.
 *
 * @author Ismael Martinez - Kevin Sanipatin
 * @version 02/06/2020 v1
 */
public interface Calculator {

    /**
     * Interface View
     * Esta interface contiene los métodos a implementarse en la Clase ClaculatorView,
     * y así poder comunicarse con el Modelo.
     */
    interface View{
        void showResult(String result);
        void showBuffer(String buffer);
    }

    /**
     * Interface Model
     * Esta interface contiene los métodos a implementarse en la Clase CalculatorModel,
     * y así poder comunicarse con el Presentador.
     */
    interface Model{
        void addOperand(String operand);
        void removeOperand();
        void calculate();
        void memoryAdd();
        void memorySub();
        void memoryRecall();
        void memoryClear();
    }

    /**
     * Interface Presenter
     * Esta interface contiene los métodos a implementarse en la Clase CalculatorPresenter,
     * y así poder comunicarse tanto con la Vista como con el Modelo.
     */
    interface Presenter {
        void showResult(String result);
        void showBuffer(String buffer);
        void emptyData();
        void calculate();
        void addOperand(String operand);
        void removeOperand();
        void memoryAdd();
        void memorySub();
        void memoryRecall();
        void memoryClear();
    }
}
