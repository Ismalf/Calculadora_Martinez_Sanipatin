package com.example.calculadora_martinez_sanipatin.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.calculadora_martinez_sanipatin.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GraficaCoseno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_coseno);
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
}
