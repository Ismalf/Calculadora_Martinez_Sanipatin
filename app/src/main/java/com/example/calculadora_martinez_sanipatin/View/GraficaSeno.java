package com.example.calculadora_martinez_sanipatin.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;
import com.example.calculadora_martinez_sanipatin.Model.Core.Sintactico;
import com.example.calculadora_martinez_sanipatin.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class GraficaSeno extends AppCompatActivity {

    private Calculator.Presenter _presenter;
    private LineGraphSeries<DataPoint> series;
    private GraphView funcion;
    private static final int precision = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grafica_seno);
        LineGraphSeries<DataPoint> series;
        GraphView funcion;
        float x,y;
        x=-360;
        funcion = findViewById(R.id.graph_view_seno);

        funcion.getViewport().setScrollable(true);
        funcion.getViewport().setScrollableY(true);

        funcion.getViewport().setScalable(true);
        funcion.getViewport().setScalableY(true);

        funcion.getViewport().setXAxisBoundsManual(true);
        funcion.getViewport().setMinX(-360);
        funcion.getViewport().setMaxX(360);

        series = new LineGraphSeries<DataPoint>();
        for(int i = 0; i <= 8000; i++){
            y = seno(x);
            System.out.println("X:"+x+",Y:"+y);
            series.appendData(new DataPoint(x,y),true,8000);
            x+=0.1;
        }
        funcion.addSeries(series);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grafica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.calculadora:
                Toast.makeText(getApplicationContext(),"Calculadora",Toast.LENGTH_SHORT).show();
                Intent objGraficaCalcu = new Intent(GraficaSeno.this, CalculatorView.class);
                startActivity(objGraficaCalcu);
                return true;
            case R.id.grafica_coseno:
                Toast.makeText(getApplicationContext(),"Grafica del Coseno",Toast.LENGTH_SHORT).show();
                Intent objGraficaCoseno = new Intent(GraficaSeno.this, GraficaCoseno.class);
                startActivity(objGraficaCoseno);
                return true;
            default:
                super.onOptionsItemSelected(item);
                return false;
        }
    }

    public float seno(float val){
        //val = (float) Math.toRadians(val);
        val = val * (float) (Math.PI / 180);
        System.out.println(val);
        int i;
        float s = 0;
        int sign = 1;
        for (i = 1; i < precision; i += 2) {
            s += sign * potencia(val, i) / factorial(i);
            sign *= -1;
        }
        System.out.println(s);
        return s;
    }

    private static float potencia(float n, float e) {
        Sintactico sin = null;
        float result = n;
        if ((int) e == 0) {
            result = 1;
        } else if ((int) e < 0) {
            if (n == 0) {
                result = sin.getError();
            } else {
                for (int i = 1; i < ((int) e) * (-1); i++) {
                    result = result * n;
                }
                result = 1 / result;
            }

        } else if ((int) e > 0) {
            for (int i = 1; i < (int) e; i++) {
                result = result * n;
            }
        }
        return result;
    }

    private static float factorial(int num){
        if (num < 0);
        float x = 1;
        int i;
        for (i = 1; i < num; i++)
            x = x * (i + 1);
        return x;
    }
}
