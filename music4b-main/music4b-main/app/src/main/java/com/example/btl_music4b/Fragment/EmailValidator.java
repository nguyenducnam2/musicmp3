package com.example.btl_music4b.Fragment;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    Pattern pattern;
    Matcher matcher;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]*@{1}gmail.com$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

}
