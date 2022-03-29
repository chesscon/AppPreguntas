package fciencias.pdm20222.apppreguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        int contador = intent.getIntExtra(MainActivity.EXTRA_POINTS, 0);
        TextView txtView = (TextView) findViewById(R.id.textViewResult);
        txtView.setText("Puntuación total: " + contador);
    }
}