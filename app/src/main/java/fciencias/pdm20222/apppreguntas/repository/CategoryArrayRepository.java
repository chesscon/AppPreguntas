package fciencias.pdm20222.apppreguntas.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import fciencias.pdm20222.apppreguntas.Category;

public class CategoryArrayRepository {

    private Context context;
    private Category[] categories;

    public CategoryArrayRepository(Context context) {
        this.context = context;
        initBankCategories();
    }

    public Category[] getCategoryBank() {
        return categories;
    }

    public ArrayList<String> getCategoryNames() {
        ArrayList<String> list = new ArrayList<String>();
        Category [] categories = getCategoryBank();
        for (Category category:categories) {
            list.add(category.getName());
        }
        return list;
    }

    public ArrayList<Category> getCategoryArray() {
        Category [] categories = getCategoryBank();
        return new ArrayList<Category>(Arrays.asList(categories));
    }

    private void initBankCategories() {
        this.categories = new Category[] {
                new Category("Aleatoria", 0),
                new Category("Animales", 1),
                new Category("Plantas", 2),
                new Category( "Historia", 3),
                new Category( "Programación", 4),
                //new Category( "Matemáticas", 5),
        };
    }

}
