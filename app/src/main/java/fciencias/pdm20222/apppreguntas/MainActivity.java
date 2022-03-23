package fciencias.pdm20222.apppreguntas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private Button btnTrue;
    private Button btnFalse;
    private Button btnNext;
    private TextView textView;
    private TextView txtContador;

    private Question[] questionBank;

    private int indexCurrentQuestion = 0;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        txtContador = (TextView) findViewById(R.id.txt_contador);
        updateQuestion();

        LinearLayout layout = (LinearLayout) findViewById(R.id.main_layout);

        this.btnNext = new Button(this);
        btnNext.setText(R.string.txt_sig);
        layout.addView(btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexCurrentQuestion++;
                Log.d(LOG_TAG, "BTN NEXT pressed, indexCurrentQuestion: " + indexCurrentQuestion);
                if (indexCurrentQuestion >= questionBank.length-1) {
                    btnNext.setEnabled(false);
                }
                updateQuestion();
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

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    private Question [] getQuestionBank() {
        return new Question[] {
                new Question( getString(R.string.pregunta_1) , false),
                new Question( getString(R.string.pregunta_2) , true),
                new Question( getString(R.string.pregunta_3), false),
        };
    }
}