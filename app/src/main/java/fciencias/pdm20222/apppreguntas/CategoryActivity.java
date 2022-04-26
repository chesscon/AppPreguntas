package fciencias.pdm20222.apppreguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        listView = (ListView) findViewById(R.id.category_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getCategoryNames());
        listView.setAdapter(adapter);
    }

    private Category [] getCategoryBank() {
        return new Category[] {
                new Category("Animales"),
                new Category("Plantas"),
                new Category( "Historia"),
                new Category( "Programación"),
                new Category( "Matemáticas"),
                new Category("Aleatoria"),
        };
    }

    private ArrayList<String> getCategoryNames() {
        ArrayList<String> list = new ArrayList<String>();
        Category [] categories = getCategoryBank();
        for (Category category:categories) {
            list.add(category.getName());
        }
        return list;
    }

}