package com.example.calculadora_martinez_sanipatin.Interfaces;

public interface Calculator {

    interface View{
        void showResult(String result);
        void showBuffer(String buffer);
    }

    interface Model{
        void addOperand(String operand);
        void removeOperand();
        void calculate();

    }

    interface Presenter {
        void showResult(String result);
        void showBuffer(String buffer);
        void emptyData();
        void calculate();
        void addOperand(String operand);
        void removeOperand();
    }
}
