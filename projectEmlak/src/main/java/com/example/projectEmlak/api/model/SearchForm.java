package com.example.projectEmlak.api.model;

public class SearchForm {

    private String keyword;

    public SearchForm(String keyword) {
        this.keyword = keyword;
    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
