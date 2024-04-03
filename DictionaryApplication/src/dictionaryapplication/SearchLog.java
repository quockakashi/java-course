/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dictionaryapplication;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class SearchLog {
    private Date timestamp;
    private String word;

    public Date getTimestamp() {
        return timestamp;
    }

    public String getWord() {
        return word;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public SearchLog(Date timestamp, String word) {
        this.timestamp = timestamp;
        this.word = word;
    }
    
    
    
}
