/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dictionaryapplication;

/**
 *
 * @author LENOVO
 */
public class Meaning {
    private String meaning;
    private boolean favoriate;
    
    @Override
    public boolean equals(Object obj) {
        Meaning otherWord = (Meaning) obj;
        return otherWord.meaning.toLowerCase().equals(meaning.toLowerCase());
              
    }
    
    @Override
    public int hashCode() {
        return meaning.hashCode() * 31;
    }
    
    @Override
    public String toString() {
        return meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public boolean isFavoriate() {
        return favoriate;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setFavoriate(boolean favoriate) {
        this.favoriate = favoriate;
    }

    public Meaning(String meaning, boolean favorite) {
        this.meaning = meaning;
        this.favoriate = favorite;
    }
    
    
    
}
