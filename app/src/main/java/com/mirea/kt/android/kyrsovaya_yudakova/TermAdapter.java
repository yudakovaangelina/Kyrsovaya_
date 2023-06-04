package com.mirea.kt.android.kyrsovaya_yudakova;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder>{
    private Context parent;
    private ArrayList<Dictionary> terms;
    private View.OnClickListener setOnClickListener;
    private Context context;
    private SQLiteDatabase database;
    private MyAppSQLiteHelper sqLiteHelper;

    public TermAdapter(ArrayList<Dictionary> terms, Context parent) {
        this.terms = terms;
        this.parent = parent;
        sqLiteHelper = new MyAppSQLiteHelper(parent.getApplicationContext(), "my_database.db", null, 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        private ArrayList<Dictionary> terms;
        private final TextView termView;
        private final TextView defView;
        private final ImageView deleteView;
        private final ImageView favoriteView;
        private final ImageView shareView;







        ViewHolder(View view){
            super(view);
            termView = view.findViewById(R.id.tvWord);
            defView = view.findViewById(R.id.tvDef);
            deleteView = view.findViewById(R.id.delete_image_view);
            favoriteView = view.findViewById(R.id.image_favorite);
            shareView = view.findViewById(R.id.imageShare);





        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_term, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TermAdapter.ViewHolder holder, int position) {
        Dictionary term = terms.get(position);
        holder.termView.setText(String.format(term.getTerm()));
        String selectedTerm = term.getTerm();

        if(term.isFavourite()){
           holder.favoriteView.setImageResource(R.drawable.baseline_star);
        }else{
            holder.favoriteView.setImageResource(R.drawable.baseline_star_border_24);
        }

        holder.termView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent actIntent = new Intent(parent, DefinitionActivity.class);
                actIntent.putExtra("definition", term.getDefinition());
                parent.startActivity(actIntent);
                Log.i("simple_app_tag", "Open next activity");
            }
        });
        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAppSQLiteHelper sqLiteHelper = new MyAppSQLiteHelper(v.getContext(), "my_database.db", null, 1);
                sqLiteHelper.deleteTerm(term.getTerm()); // Вызываем метод удаления из базы данных
                terms.remove(term); // Удаляем элемент из списка
                notifyItemRemoved(holder.getAdapterPosition()); // Оповещаем адаптер об удалении элемента
                Log.i("simple_app_tag", "Слово удалено!");
                Toast.makeText(v.getContext(), "Слово удалено!",Toast.LENGTH_LONG).show();
            }
        });

        holder.favoriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Добавление элемента в избранное

                boolean isFavorite = !term.isFavourite();
                term.setFavourite(isFavorite);
                notifyItemChanged(holder.getAdapterPosition());

                //Добавляем/удаляем термин из избранного в базе данных
                SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("isFavorite", isFavorite ? 1 : 0);
                db.update("TABLE_DICTIONARY", values, "term=?", new String[]{term.getTerm()});
                db.close();
                Log.i("simple_app_tag", "Слово добавлено/удалено в избранное!");
                Toast.makeText(view.getContext(), "Слово добавлено/удалено в избранное!",Toast.LENGTH_LONG).show();
            }
        });

        holder.shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", "Поделиться словом "+ term.getTerm());
                parent.startActivity(Intent.createChooser(intent, "Поделиться словом "+ term.getTerm()));

            }
        });




    }





    @Override
    public int getItemCount() {
        return terms.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder{

        TextView listenItemNumberView;
        TextView viewHolderIndex;
        public NumberViewHolder(View itemView){
            super(itemView);
            listenItemNumberView = itemView.findViewById(R.id.tvWord);


        }
    }

}
