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

public class MainActivity extends LogCicloVidaActividad {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_POINTS = "PUNTOS";
    public static final String KEY_CONTADOR = "CONTADOR";
    public static final String KEY_CURRENT = "CURRENT";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            this.contador = savedInstanceState.getInt(KEY_CONTADOR);
            this.indexCurrentQuestion = savedInstanceState.getInt(KEY_CURRENT);
        }

        SharedPreferences preferences =  getPreferences(Context.MODE_PRIVATE);
        this.contador = preferences.getInt(KEY_CONTADOR, this.contador);
        this.indexCurrentQuestion = preferences.getInt(KEY_CURRENT, this.indexCurrentQuestion);

        Intent intent = getIntent();
        this.categoryId = intent.getIntExtra(CategoryActivity.EXTRA_CATEGORY_ID, 0);
        this.categoryName = intent.getStringExtra(CategoryActivity.EXTRA_CATEGORY_NAME);

        setContentView( R.layout.activity_main);
        this.questionBank = this.getQuestionBank();
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

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    private Question [] getQuestionBank() {
        Category cRandom = new Category("Random", 0);
        Question[] questionsRandom = new Question[] {
                new Question( getString(R.string.pregunta_1) , false, cRandom),
                new Question( getString(R.string.pregunta_2) , true, cRandom),
                new Question( getString(R.string.pregunta_3), false, cRandom),
        };

        Category cAnimals = new Category("Animales", 1);
        Question[] questionsAnimals = new Question[] {
                new Question( "Pregunta de Animales 1" , true, cAnimals),
                new Question( "Pregunta de Animales 2" , false, cAnimals),
                new Question( "Pregunta de Animales 3" , true, cAnimals),
        };

        Category cPlants = new Category("Plantas", 2);
        Question[] questionsPlants = new Question[] {
                new Question( "Pregunta de Plantas 1" , true, cPlants),
                new Question( "Pregunta de Plantas 2" , false, cPlants),
                new Question( "Pregunta de Plantas 3" , true, cPlants),
        };

        Category cHistory = new Category("Historia", 3);
        Question[] questionsHistory = new Question[] {
                new Question( "Pregunta de Historia 1" , true, cHistory),
                new Question( "Pregunta de Historia 2" , false, cHistory),
                new Question( "Pregunta de Historia 3" , true, cHistory),
        };

        Category cProgramming = new Category("Programación", 4);
        Question[] questionsProgramming = new Question[] {
                new Question( "Pregunta de Progamación 1" , true, cHistory),
                new Question( "Pregunta de Progamación 2" , false, cHistory),
                new Question( "Pregunta de Progamación 3" , true, cHistory),
        };

        Category[] categorias = new Category[] {
                cRandom, cAnimals, cPlants, cHistory, cProgramming
        };

        Question[] result;

        Log.d(LOG_TAG, "ID de la categoria: " + this.categoryId );

        switch (this.categoryId) {
            case 1:
                result = questionsAnimals;
                break;
            case 2:
                result = questionsPlants;
                break;
            case 3:
                result = questionsHistory;
                break;
            case 4:
                result = questionsProgramming;
                break;
            default:
            case 0:
                result = questionsRandom;
                break;
        }
        return result;
    }

    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_CONTADOR, this.contador);
        savedInstanceState.putInt(KEY_CURRENT, this.indexCurrentQuestion);
        Log.i(LOG_TAG, "onSaveInstanceState(), guardando datos...");
    }
}