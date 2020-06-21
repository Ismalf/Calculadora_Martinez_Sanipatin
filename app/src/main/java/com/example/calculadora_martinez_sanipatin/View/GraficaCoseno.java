package com.example.calculadora_martinez_sanipatin.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadora_martinez_sanipatin.Model.Core.Sintactico;
import com.example.calculadora_martinez_sanipatin.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GraficaCoseno extends AppCompatActivity {
private static int precision = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_coseno);
        LineGraphSeries<DataPoint> series;
        GraphView funcion;
        float x,y;
        x=-360;
        funcion = findViewById(R.id.graph_view_cos);

        funcion.getViewport().setScrollable(true);
        funcion.getViewport().setScrollableY(true);

        funcion.getViewport().setScalable(true);
        funcion.getViewport().setScalableY(true);

        funcion.getViewport().setXAxisBoundsManual(true);
        funcion.getViewport().setMinX(-360);
        funcion.getViewport().setMaxX(360);

        series = new LineGraphSeries<DataPoint>();
        for(int i = 0; i <= 8000; i++){
            try {
                y = cos(x);
                System.out.println("X:"+x+",Y:"+y);
                series.appendData(new DataPoint(x,y),true,8000);
                x+=0.1;
            } catch (Exception e) {
                e.printStackTrace();
            }

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
                Intent objGraficaCalcu = new Intent(GraficaCoseno.this, CalculatorView.class);
                startActivity(objGraficaCalcu);
                return true;
            case R.id.grafica_seno:
                Toast.makeText(getApplicationContext(),"Grafica del Seno",Toast.LENGTH_SHORT).show();
                Intent objGraficaSeno = new Intent(GraficaCoseno.this, GraficaSeno.class);
                startActivity(objGraficaSeno);
                return true;
            default:
                super.onOptionsItemSelected(item);
                return false;
        }
    }
    private static float cos(float val) throws Exception {
        //val = (float)Math.toRadians(val);
        val = val * (float) (Math.PI / 180);
        System.out.println(val);
        int i;
        float s = 0;
        int sign = 1;
        for (i = 0; i < precision; i += 2) {
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
    private static float factorial(int num) throws Exception {
        if (num < 0)throw new Exception("Error");
        float x = 1;
        int i;
        for (i = 1; i < num; i++)
            x = x * (i + 1);
        return x;
    }
}
