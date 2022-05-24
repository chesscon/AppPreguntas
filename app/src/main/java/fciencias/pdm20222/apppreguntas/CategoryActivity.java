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

import fciencias.pdm20222.apppreguntas.repository.CategoryArrayRepository;

public class CategoryActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";

    private ListView listView;
    private CategoryArrayRepository categoriesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        listView = (ListView) findViewById(R.id.category_list);
        categoriesRepository = new CategoryArrayRepository(getBaseContext());

        ArrayList<Category> categories = categoriesRepository.getCategoryArray();
        CategoryAdapter adapter = new CategoryAdapter(this, R.layout.category_list_item, categories);
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

}