package fciencias.pdm20222.apppreguntas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import fciencias.pdm20222.apppreguntas.repository.BankQuestionsArrayRepository;
import fciencias.pdm20222.apppreguntas.repository.BankQuestionsJSONFileRepository;
import fciencias.pdm20222.apppreguntas.repository.BankQuestionsRepository;

public class MainActivity extends LogCicloVidaActividad {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_POINTS = "PUNTOS";
    public static final String KEY_CONTADOR = "CONTADOR";
    public static final String KEY_CURRENT = "CURRENT";

    public static final String FILE_NAME_TEST = "puntuacion.txt";

    private Button btnTrue;
    private Button btnFalse;
    private Button btnNext;
    private Button btnPagFC;
    private Button btnMapFC;
    private TextView textView;
    private TextView txtContador;

    private Question[] questionBank;

    private int indexCurrentQuestion = 0;
    private int contador = 0;

    private int categoryId;
    private String categoryName;

    private BankQuestionsRepository questionsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Contador Inicial: " + this.contador);
        Log.d(TAG, "Indice Inicial: " + this.indexCurrentQuestion);

        questionsRepository = new BankQuestionsJSONFileRepository(getBaseContext());

        if (savedInstanceState != null) {
            this.contador = savedInstanceState.getInt(KEY_CONTADOR);
            this.indexCurrentQuestion = savedInstanceState.getInt(KEY_CURRENT);

            Log.d(TAG, "Contador savedInstanceState: " + this.contador);
            Log.d(TAG, "Indice savedInstanceState: " + this.indexCurrentQuestion);
        }

        SharedPreferences preferences =  getPreferences(Context.MODE_PRIVATE);
        this.contador = preferences.getInt(KEY_CONTADOR, this.contador);
        //this.indexCurrentQuestion = preferences.getInt(KEY_CURRENT, this.indexCurrentQuestion);

        Log.d(TAG, "Contador preferences: " + this.contador);
        Log.d(TAG, "Indice preferences: " + this.indexCurrentQuestion);

        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME_TEST);
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while((read = fileInputStream.read()) != -1 ) {
                buffer.append((char) read);
            }
            fileInputStream.close();
            String data = buffer.toString();
            Log.d(TAG,"Datos leidos del archivo: " + data);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "ocurrio un error al intentar abrir el archivo:" + FILE_NAME_TEST);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "ocurrio un error al intentar leer el archivo:" + FILE_NAME_TEST);
        }

        Intent intent = getIntent();
        this.categoryId = intent.getIntExtra(CategoryActivity.EXTRA_CATEGORY_ID, 0);
        this.categoryName = intent.getStringExtra(CategoryActivity.EXTRA_CATEGORY_NAME);

        setContentView( R.layout.activity_main);
        this.questionBank = questionsRepository.getQuestionBank(this.categoryId);
        this.initViewReferences();
    }

    public void clickEnFalse(View view) {
        //textView.setText(R.string.txt_falso);
        Log.d( LOG_TAG, "Respuesta Se presiono el boton de falso !!!");
        Log.i( LOG_TAG, "Respuesta incorrecta =( !!!");
    }

    private void initViewReferences() {
        btnTrue = (Button) findViewById(R.id.btn_true);
        btnFalse = (Button) findViewById(R.id.btn_false);
        textView = (TextView) findViewById(R.id.txt_pregunta);
        btnPagFC = (Button) findViewById(R.id.btn_pag_fc);
        btnMapFC = (Button) findViewById(R.id.btn_map_fc);
        txtContador = (TextView) findViewById(R.id.txt_contador);
        txtContador.setText("Puntacion: " + this.contador);
        updateQuestion();

        btnNext = (Button) findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexCurrentQuestion++;
                Log.d(LOG_TAG, "BTN NEXT pressed, indexCurrentQuestion: " + indexCurrentQuestion);
                if (indexCurrentQuestion >= questionBank.length) {
                    btnNext.setEnabled(false);
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra(EXTRA_POINTS, contador);
                    startActivity(intent);
                } else {
                    updateQuestion();
                }
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d( LOG_TAG, "Mensaje de Prueba");
                checkAnswer(true);
            }
        });
        // listener vs subscriber
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d( LOG_TAG, "Mensaje de Prueba");
                checkAnswer(false);
            }
        });

        btnPagFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("http://www.fciencias.unam.mx"));
                intent.setData(Uri.parse("geo:19.3242765,-99.1791318"));
                startActivity(intent);
            }
        });

        btnMapFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://goo.gl/maps/cTrjN7MhWxSqbF1K7"));
                startActivity(intent);
            }
        });
    }

    private void updateQuestion() {
        Question currentQuestion = questionBank[indexCurrentQuestion];
        textView.setText(currentQuestion.getText());
        btnTrue.setEnabled(true);
        btnFalse.setEnabled(true);
    }

    private void checkAnswer(boolean userPressedTrue) {
        Question currentQuestion = questionBank[indexCurrentQuestion];

        int messageResId = 0;

        if (currentQuestion.isAnswerTrue() == userPressedTrue) {
            messageResId = R.string.txt_correct_toast;
            // Si la respuesta es correcta, sumamos un punto:
            contador++;
        } else {
            messageResId = R.string.txt_incorrect_toast;
        }
        // Bloquear botones
        btnTrue.setEnabled(false);
        btnFalse.setEnabled(false);
        // Actualizar texto del contador:
        txtContador.setText("Puntación :" + contador);

        SharedPreferences preferences =  getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_CONTADOR, this.contador);
        editor.putInt(KEY_CURRENT, this.indexCurrentQuestion);
        editor.apply();

        String data = "Mensaje de Prueba";
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME_TEST, Context.MODE_PRIVATE);
            byte [] bytes = data.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "ocurrio un error al intentar abrir el archivo:" + FILE_NAME_TEST);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "ocurrio un error al intentar escribir en el archivo:" + FILE_NAME_TEST);
        }

        Log.d(TAG, "Contador guardado: " + this.contador);
        Log.d(TAG, "Indice guardado: " + this.indexCurrentQuestion);

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_CONTADOR, this.contador);
        savedInstanceState.putInt(KEY_CURRENT, this.indexCurrentQuestion);
        Log.i(LOG_TAG, "onSaveInstanceState(), guardando datos...");
    }
}