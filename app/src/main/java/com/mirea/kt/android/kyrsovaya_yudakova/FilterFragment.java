package com.mirea.kt.android.kyrsovaya_yudakova;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FilterFragment extends Fragment {

    private RecyclerView listView;
    private ImageView filterView;
    private Button filterButton;
    private SQLiteDatabase database;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private TermAdapter termAdapter;
    private ArrayList<Dictionary> terms = new ArrayList<>();

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_filter, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recFilter);
        termAdapter = new TermAdapter(terms, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Dictionary> filerTerms = new ArrayList<>();
        TextView filterView = (TextView) v.findViewById(R.id.etWordFilter);

        filterButton = (Button) v.findViewById(R.id.btnFilter);
        filterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("simple_app_tag", "Нажатие на кнопку Поиск");
                String searchString = filterView.getText().toString().trim();
                sqLiteOpenHelper = new MyAppSQLiteHelper(getActivity(), "my_database.db", null, 1);
                database = sqLiteOpenHelper.getReadableDatabase();
                Cursor cursor = database.rawQuery("SELECT * FROM TABLE_DICTIONARY WHERE term LIKE '%" + searchString + "%';", null);
                filerTerms.clear();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String term = cursor.getString(1);
                    String definition = cursor.getString(2);
                    boolean isFavorite = cursor.getInt(3) == 1;
                    filerTerms.add(new Dictionary(term, definition, isFavorite));
                    TermAdapter adapter = new TermAdapter(filerTerms, getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                }
                cursor.close();
                termAdapter.notifyDataSetChanged();
            }
        });


        return v;
    }

}



