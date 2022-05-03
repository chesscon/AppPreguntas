package fciencias.pdm20222.apppreguntas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Category> elements;

    public CategoryAdapter(Context context, int layout, ArrayList<Category> elements){
        this.context = context;
        this.layout = layout;
        this.elements = elements;
    }


    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int i) {
        return elements.get(i);
    }

    @Override
    public long getItemId(int i) {
        Category category = elements.get(i);
        return category.getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View v = layoutInflater.inflate(this.layout, null);

        // Valor actual según la posición
        Category category = elements.get(i);

        // Referenciamos el elemento a modificar y lo rellenamos
        TextView textView = (TextView) v.findViewById(R.id.textViewCategoryItem);
        textView.setText(category.getId() + " - " + category.getName());

        ImageView imageView = (ImageView) v.findViewById(R.id.imageViewCategory);
        switch (category.getId()) {
            case 3:
                imageView.setImageResource(R.drawable.chess);
                break;
            case 4:
                imageView.setImageResource(R.drawable.matematicas);
                break;
            default:
                imageView.setImageResource(R.drawable.category);
        }

        //Devolvemos la vista inflada
        return v;
    }
}