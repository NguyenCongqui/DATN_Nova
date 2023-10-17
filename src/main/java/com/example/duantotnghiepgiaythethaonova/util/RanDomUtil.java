package com.example.duantotnghiepgiaythethaonova.util;

import java.util.Random;

public class RanDomUtil {

    public static char[] ranDomFull() {
        char[] randomCode = new char[8];
        String upper = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghiklmnopqrstuvwxyz";
        String num = "0123456789";
        String specialChars = "<>,.?/}]{]+_-)(*&^%$#@!=";
        String combination = upper + lower + specialChars + num;
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            randomCode[i] = combination.charAt(r.nextInt(combination.length()));
        }
        return randomCode;
    }

    public static char[] rammDomNumber() {
        char[] ramdomCode = new char[6];
        String num = "0123456789";
        String combination = num;
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            ramdomCode[i] = combination.charAt(random.nextInt(combination.length()));

        }
        return ramdomCode;
    }
}
