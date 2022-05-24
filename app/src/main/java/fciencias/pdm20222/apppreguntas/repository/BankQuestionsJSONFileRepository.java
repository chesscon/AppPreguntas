package fciencias.pdm20222.apppreguntas.repository;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fciencias.pdm20222.apppreguntas.Category;
import fciencias.pdm20222.apppreguntas.MainActivity;
import fciencias.pdm20222.apppreguntas.Question;
import fciencias.pdm20222.apppreguntas.R;

public class BankQuestionsJSONFileRepository implements BankQuestionsRepository {

    private static final String LOG_TAG = BankQuestionsJSONFileRepository.class.getSimpleName();

    private Context context;
    private Map<Integer, ArrayList<Question>> categoriesMap;


    public BankQuestionsJSONFileRepository(Context context) {
        this.context = context;
        categoriesMap = new HashMap<Integer, ArrayList<Question>>();
        initBankQuestions();
    }

    private void initBankQuestions() {
        InputStream jsonFileInputStream = context.getResources().openRawResource(R.raw.questions);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int read = -1;
        StringBuffer buffer = new StringBuffer();

        try {
            while((read = jsonFileInputStream.read()) != -1 ) {
                buffer.append((char) read);
            }
            jsonFileInputStream.close();

            String jsonString = buffer.toString();
            Log.d(LOG_TAG, "JSON LEIDO:");
            Log.d(LOG_TAG, jsonString);

            JSONObject obj = new JSONObject(jsonString);

            JSONArray categories = obj.getJSONArray("categories");
            for(int i=0; i < categories.length(); i++) {
                JSONObject categoryDetail = categories.getJSONObject(i);
                String nameCategory= categoryDetail.getString("name");
                int idCategory = categoryDetail.getInt("id");
                Category category = new Category(nameCategory, idCategory);
                ArrayList<Question> questionList = new ArrayList<Question>();

                JSONArray questions = categoryDetail.getJSONArray("questions");
                for(int j=0; j < questions.length(); j++) {
                    JSONObject questionDetail = questions.getJSONObject(j);
                    String textQuestion = questionDetail.getString("text");
                    boolean answerTrue = questionDetail.getBoolean("answerTrue");
                    Question question = new Question(textQuestion, answerTrue, category);
                    questionList.add(question);
                }
                categoriesMap.put(category.getId(), questionList);
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "ocurrio un error al intentar abrir el archivo json de preguntas");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(LOG_TAG, "ocurrio un error al intentar procesar el archivo json de preguntas");
            e.printStackTrace();
        }
    }

    @Override
    public Question[] getQuestionBank(int categoryId) {
        if (!this.categoriesMap.containsKey(categoryId)) {
            return null;
        }

        ArrayList<Question> questions = categoriesMap.get(categoryId);
        
        Question[] result = new Question[questions.size()];
        for (int i=0; i < questions.size(); i++) {
            result[i] = questions.get(i);
        }
        
        return  result;
    }

}
