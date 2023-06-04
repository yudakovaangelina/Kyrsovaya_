package com.mirea.kt.android.kyrsovaya_yudakova;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private List<Dictionary> favoriteTerms;
    private Context context;

    public FavoriteAdapter(Context context,List<Dictionary> favoriteTerms) {
        this.favoriteTerms = favoriteTerms;
        this.context= context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {


        private ArrayList<Dictionary> terms;
        private final TextView termView;
        private final TextView defView;
        private final ImageView deleteView;
        private final ImageView favoriteView;


        ViewHolder(View view) {
            super(view);
            termView = view.findViewById(R.id.tvWord);
            defView = view.findViewById(R.id.tvDef);
            deleteView = view.findViewById(R.id.delete_image_view);
            favoriteView = view.findViewById(R.id.image_favorite);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @NonNull




    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        Dictionary favoriteTerm = favoriteTerms.get(position);
        holder.termView.setText(favoriteTerm.getTerm());
        holder.defView.setText(favoriteTerm.getDefinition());
    }

    @Override
    public int getItemCount() {
        return favoriteTerms.size();
    }
}
