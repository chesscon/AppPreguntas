package fciencias.pdm20222.apppreguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class LogCicloVidaActividad extends AppCompatActivity {

    public final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate(), Inicializando la Actividad");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart(),  La Actividad sera visible");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume(),  La Actividad ya es visible y esta activa (tiene el foco)");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause(),  La Actividad esta pausada y es parcialmente visible");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop(),  La Actividad ya no visible");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart(),  La Actividad vuelve a ser visible, despues de que no lo era");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy(),  La Actividad sera destruida");
    }

}