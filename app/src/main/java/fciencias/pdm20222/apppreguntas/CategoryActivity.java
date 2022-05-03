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

public class CategoryActivity extends AppCompatActivity {

    private ListView listView;

    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        listView = (ListView) findViewById(R.id.category_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getCategoryNames());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View view,
                                     int position, long id ) {
                String elemento = (String) adapter.getItem(position);
                Toast.makeText(CategoryActivity.this, elemento, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                intent.putExtra( CategoryActivity.EXTRA_CATEGORY_ID, position );
                intent.putExtra( CategoryActivity.EXTRA_CATEGORY_NAME, elemento );
                startActivity(intent);
            }
        });

    }

    private Category [] getCategoryBank() {
        return new Category[] {
                new Category("Aleatoria"),
                new Category("Animales"),
                new Category("Plantas"),
                new Category( "Historia"),
                new Category( "Programación"),
                //new Category( "Matemáticas"),
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