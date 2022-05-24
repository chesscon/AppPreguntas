package fciencias.pdm20222.apppreguntas.repository;

import android.content.Context;
import android.util.Log;

import fciencias.pdm20222.apppreguntas.Category;
import fciencias.pdm20222.apppreguntas.Question;
import fciencias.pdm20222.apppreguntas.R;

public class BankQuestionsArrayRepository implements BankQuestionsRepository {

    private static final String LOG_TAG = BankQuestionsArrayRepository.class.getSimpleName();

    private Context context;
    private Question[] questionsRandom;
    private Question[] questionsAnimals;
    private Question[] questionsPlants;
    private Question[] questionsHistory;
    private Question[] questionsProgramming;


    public BankQuestionsArrayRepository(Context context) {
        this.context = context;
        initBankQuestions();
    }

    private void initBankQuestions() {
        Category cRandom = new Category("Random", 0);
        this.questionsRandom = new Question[] {
                new Question( getString(R.string.pregunta_1) , false, cRandom),
                new Question( getString(R.string.pregunta_2) , true, cRandom),
                new Question( getString(R.string.pregunta_3), false, cRandom),
        };

        Category cAnimals = new Category("Animales", 1);
        this.questionsAnimals = new Question[] {
                new Question( "Pregunta de Animales 1" , true, cAnimals),
                new Question( "Pregunta de Animales 2" , false, cAnimals),
                new Question( "Pregunta de Animales 3" , true, cAnimals),
        };

        Category cPlants = new Category("Plantas", 2);
        this.questionsPlants = new Question[] {
                new Question( "Pregunta de Plantas 1" , true, cPlants),
                new Question( "Pregunta de Plantas 2" , false, cPlants),
                new Question( "Pregunta de Plantas 3" , true, cPlants),
        };

        Category cHistory = new Category("Historia", 3);
        this.questionsHistory = new Question[] {
                new Question( "Pregunta de Historia 1" , true, cHistory),
                new Question( "Pregunta de Historia 2" , false, cHistory),
                new Question( "Pregunta de Historia 3" , true, cHistory),
        };

        Category cProgramming = new Category("Programación", 4);
        this.questionsProgramming = new Question[] {
                new Question( "Pregunta de Progamación 1" , true, cProgramming),
                new Question( "Pregunta de Progamación 2" , false, cProgramming),
                new Question( "Pregunta de Progamación 3" , true, cProgramming),
        };
    }

    public Question[] getQuestionBank(int categoryId) {

        Question[] result;

        Log.d(LOG_TAG, "ID de la categoria: " + categoryId );

        switch (categoryId) {
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

    private String getString(int id) {
        return this.context.getString(id);
    }

}
