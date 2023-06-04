package com.mirea.kt.android.kyrsovaya_yudakova;

public class Dictionary {
    private String term;
    private String definition;
    private boolean isFavorite;


    public Dictionary(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public Dictionary(String term, String definition, boolean isFavorite) {
        this.term = term;
        this.definition = definition;
        this.isFavorite = isFavorite;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setFavourite(boolean isFavourite) {
        this.isFavorite = isFavourite;
    }

    public boolean isFavourite() {
        return isFavorite;
    }

    public Dictionary() {
    }


}
