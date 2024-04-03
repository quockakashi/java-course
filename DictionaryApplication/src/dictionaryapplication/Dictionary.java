/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dictionaryapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */



public class Dictionary {
    private static Dictionary instance;
    private Map<String, Meaning> engVieMap;
    private Map<String, Meaning> vieEngMap;
    private List<SearchLog> searchLogs;
    
    public static enum Mode {
        ENG_VIE_MODE, VIE_ENG_MODE;
    }
    
    private Dictionary() {
        engVieMap = new HashMap<>();
        vieEngMap = new HashMap<>();
        searchLogs = new ArrayList<>();
    }

    public List<SearchLog> getSearchLogs() {
        return searchLogs;
    }
    
    public static Dictionary getInstance() {
        if(instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }
    
    private boolean isEngExists(String word) {
        return engVieMap.keySet().contains(word.toLowerCase());
    }
    
    private boolean isVieExists(String word) {
        return vieEngMap.keySet().contains(word.toLowerCase());
    }

    public Map<String, Meaning> getEngVieMap() {
        return engVieMap;
    }

    public Map<String, Meaning> getVieEngMap() {
        return vieEngMap;
    }
    
    public Meaning searchWord(String word, Mode mode) {
        if(mode == Mode.ENG_VIE_MODE) {
            return engVieMap.get(word.toLowerCase());
        } else {
            return vieEngMap.get(word.toLowerCase());
        }
    }
    
    public boolean addWord(String word, Meaning meaning, Mode mode) {
        if(mode == Mode.ENG_VIE_MODE) {
            if(isEngExists(word))
                return false;
            engVieMap.put(word.toLowerCase(), meaning);
        } else {
            if(isVieExists(word))
                return false;
            vieEngMap.put(word.toLowerCase(), meaning);
        }
        
        return true;
    }
    
    public void removeWord(String word, Mode mode) {
        if(mode == Mode.ENG_VIE_MODE) {
            engVieMap.remove(word.toLowerCase());
        } else {
            vieEngMap.remove(word.toLowerCase());
        }      
    }
    
    public void toggleFavoriteWord(String word, Mode mode) {
        Meaning meaning;
        if(mode == Mode.ENG_VIE_MODE) {
            meaning = engVieMap.get(word);
        } else {
            meaning = vieEngMap.get(word);
        }
        
        if(meaning.isFavoriate()) {
            meaning.setFavoriate(false);
        } else {
            meaning.setFavoriate(true);
        }
    }
    
    public void addLog(SearchLog log) {
        searchLogs.add(log);
    }
    
    
}
