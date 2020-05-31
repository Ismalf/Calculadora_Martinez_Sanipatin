package com.example.calculadora_martinez_sanipatin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadora_martinez_sanipatin.Core.Core;

public class MainActivity extends AppCompatActivity {

    private String _input = "";
    private String _result = "";
    private TextView result;
    private TextView input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        result = findViewById(R.id.results);
    }

    public void addToInput(View k){
        switch(k.getId()){
            case (R.id.number1):  this._input+="1"; input.setText(_input); break;
            case (R.id.number2):  this._input+="2"; input.setText(_input); break;
            case (R.id.number3):  this._input+="3"; input.setText(_input); break;
            case (R.id.number4):  this._input+="4"; input.setText(_input); break;
            case (R.id.number5):  this._input+="5"; input.setText(_input); break;
            case (R.id.number6):  this._input+="6"; input.setText(_input); break;
            case (R.id.number7):  this._input+="7"; input.setText(_input); break;
            case (R.id.number8):  this._input+="8"; input.setText(_input); break;
            case (R.id.number9):  this._input+="9"; input.setText(_input); break;
            case (R.id.number0):  this._input+="0"; input.setText(_input); break;
            case (R.id.parenthesisopen):  this._input+="("; input.setText(_input); break;
            case (R.id.parenthesisclose):  this._input+=")"; input.setText(_input);break;
            case (R.id.divide):  this._input+="/"; input.setText(_input);break;
            case (R.id.decimal):  this._input+="."; input.setText(_input);break;
            case (R.id.addition):  this._input+="+"; input.setText(_input);break;
            case (R.id.substract):  this._input+="-"; input.setText(_input);break;
            case (R.id.multi):  this._input+="*"; input.setText(_input);break;

        }
    }

    public void clearInput(View v){
        _input = "";
        input.setText(_input);
        _result="";
        result.setText(_result);
    }

    public void removeLast(View v){

        _input = _input.length() != 0 ? _input.substring(0,_input.length()-1) : _input;
        input.setText(_input);
    }

    public void calculate(View v){
        _result+=_input+"\n";
        _checkbuffer();
        result.setText(_result);
        _input = new Core().calculate(_input);
        input.setText(_input);
    }

    private void _checkbuffer(){
        String[] tmp =_result.split("\n");
        if( tmp.length > 5 ){
            _result = "";
            for(int j = 1; j < tmp.length; j++){
                _result += tmp[j] + "\n";
            }
        }
    }
}
