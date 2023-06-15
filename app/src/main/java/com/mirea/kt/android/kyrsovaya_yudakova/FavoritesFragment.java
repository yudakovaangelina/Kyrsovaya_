package com.mirea.kt.android.kyrsovaya_yudakova;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private TermAdapter termAdapter;
    private ArrayList<Dictionary> terms = new ArrayList<>();
    private MyAppSQLiteHelper sqLiteHelper;

    private SQLiteDatabase database;


    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerViewFav);
        termAdapter = new TermAdapter(terms, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Dictionary> favoriteTerms = new ArrayList<>();

        sqLiteHelper = new MyAppSQLiteHelper(getActivity(), "my_database.db", null, 1);
        database = sqLiteHelper.getReadableDatabase();

        Cursor cursor = database.query("TABLE_DICTIONARY", null,  "isFavorite=?", new String[]{"1"}, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String term = cursor.getString(cursor.getColumnIndexOrThrow("term"));
                String definition = cursor.getString(cursor.getColumnIndexOrThrow("definition"));
                boolean isFavorite = (cursor.getInt(cursor.getColumnIndexOrThrow("isFavorite")) == 1);
                favoriteTerms.add(new Dictionary(term, definition, isFavorite) {
                });
            }
            cursor.close();
            Log.i("simple_app_tag", "Выбор только избранных терминов");
        }
        database.close();
        TermAdapter adapter = new TermAdapter(favoriteTerms, this.getActivity());
        Log.i("simple_app_tag", "Получение записей из базы данных");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        Log.i("simple_app_tag", "Отображение в виде списка");


        FavoritesFragment favoritesFragment = (FavoritesFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.favorites);
        if (favoritesFragment != null) {
            favoritesFragment.updateTermList(terms);
        }


        return v;
    }


    public void updateTermList(ArrayList<Dictionary> terms) {
        this.terms.clear();
        this.terms.addAll(terms);
        termAdapter.notifyDataSetChanged();
    }


    }
