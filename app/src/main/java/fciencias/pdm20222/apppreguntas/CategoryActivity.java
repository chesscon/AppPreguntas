package fciencias.pdm20222.apppreguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryActivity extends AppCompatActivity {

    private ListView listView;

    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        listView = (ListView) findViewById(R.id.category_list);

        CategoryAdapter adapter = new CategoryAdapter(this, R.layout.category_list_item, getCategoryArray());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View view,
                                     int position, long id ) {
                Category elemento = (Category) adapter.getItem(position);
                Toast.makeText(CategoryActivity.this, elemento.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                intent.putExtra( CategoryActivity.EXTRA_CATEGORY_ID, elemento.getId() );
                intent.putExtra( CategoryActivity.EXTRA_CATEGORY_NAME, elemento.getName() );
                startActivity(intent);
            }
        });

    }

    private Category [] getCategoryBank() {
        return new Category[] {
                new Category("Aleatoria", 0),
                new Category("Animales", 1),
                new Category("Plantas", 2),
                new Category( "Historia", 3),
                new Category( "Programación", 4),
                //new Category( "Matemáticas", 5),
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

    private ArrayList<Category> getCategoryArray() {
        Category [] categories = getCategoryBank();
        return new ArrayList<Category>(Arrays.asList(categories));
    }

}