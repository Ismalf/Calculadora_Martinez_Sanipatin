package com.example.calculadora_martinez_sanipatin.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadora_martinez_sanipatin.Interfaces.Calculator;
import com.example.calculadora_martinez_sanipatin.Model.Core.Sintactico;
import com.example.calculadora_martinez_sanipatin.Presenter.CalculatorPresenter;
import com.example.calculadora_martinez_sanipatin.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GraficaCoseno extends AppCompatActivity implements Calculator.View{
    private Calculator.Presenter _presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_coseno);
        _presenter = new CalculatorPresenter(this);
        GraphView function;
        function = findViewById(R.id.graph_view_cos);
        function.getViewport().setScrollable(true);
        function.getViewport().setScrollableY(true);
        function.getViewport().setScalable(true);
        function.getViewport().setScalableY(true);
        function.getViewport().setXAxisBoundsManual(true);
        function.getViewport().setMinX(-360);
        function.getViewport().setMaxX(360);
        function.addSeries(_presenter.getGraphicOf("cos"));
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


    @Override
    public void showResult(String result) {

    }

    @Override
    public void showBuffer(String buffer) {

    }
}
