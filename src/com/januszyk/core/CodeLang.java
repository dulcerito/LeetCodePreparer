package com.januszyk.core;

public enum CodeLang {
    CPP("{\"lang\":\"C++\""),
    PYTHON("{\"lang\":\"Python\""),
    PYTHON3("{\"lang\":\"Python3\""),
    C("{\"lang\":\"C\""),
    CSHARP("{\"lang\":\"C#\""),
    JAVASCRIPT("{\"lang\":\"JavaScript\""),
    RUBY("{\"lang\":\"Ruby\""),
    SWIFT("{\"lang\":\"Swift\""),
    GO("{\"lang\":\"Go\""),
    SCALA("{\"lang\":\"Scala\""),
    KOTLIN("{\"lang\":\"Kotlin\""),
    RUST("{\"lang\":\"Rust\""),
    PHP("{\"lang\":\"PHP\""),
    JAVA("{\"lang\":\"Java\"");

    private String lang;

    CodeLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }
}
