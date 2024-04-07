package com.project.bookstore.enums;

public enum SortBy {
    TITLE("title"),
    AUTHOR("author"),
    PRICE("price");

    private final String field;

    SortBy(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}

