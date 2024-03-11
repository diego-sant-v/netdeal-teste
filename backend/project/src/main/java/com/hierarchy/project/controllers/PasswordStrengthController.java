package com.hierarchy.project.controllers;

import com.hierarchy.project.enums.ScorePasswordEnum;
import com.hierarchy.project.models.StaffModel;
import com.hierarchy.project.util.SecurityConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasswordStrengthController {
    private int countTotalCaracter;
    private int countUppercaseLetters;
    private int countLowercaseLetters;
    private int countNumbers;
    private int countSymbols;
    private int countMiddleNumbersOrSymbols;
    private int countForAllRequirements;
    private int countUpperCaseLettersSequential;
    private int countLowercaseLettersSequential;
    private int countNumbersConsecutive;
    private int countLetterSequential;
    private int countNumbersSequential;
    private int countSimbolsSequential;
    private int countCharacterRepeated;
    private int countIsOnlyLetters;
    private int countIsOnlyNumbers;
    private List<Character> characterList = new ArrayList<>();
    private List<Character> characterRepeted = new ArrayList<>();;
    private StaffModel staffModel;

    private int countBonusScore = 0;
    public StaffModel passwordStrength(String password, StaffModel staffModel) {
        this.staffModel = staffModel;
        countTotalCaracter = staffModel.getPassword().length();
        countBonusScore = 0;
        countUppercaseLetters = 0;
        countLowercaseLetters = 0;
        countNumbers = 0;
        countSymbols = 0;
        countMiddleNumbersOrSymbols = 0;
        countForAllRequirements = 0;
        countUpperCaseLettersSequential = 0;
        countLowercaseLettersSequential = 0;
        countNumbersConsecutive = 0;
        countLetterSequential = 0;
        countNumbersSequential = 0;
        countSimbolsSequential = 0;
        countCharacterRepeated = 0;
        countIsOnlyLetters = 0;
        countIsOnlyNumbers = 0;
        countBonusScore = (staffModel.getPassword().length() * 4);
        countCaracteresAndTypes(password);
        adictions();
        deductions(password);
        return staffModel;
    }
    public Number countCaracteresAndTypes(String password) {
        for (int i = 0; i < password.length(); i++) {
            char caractere = password.charAt(i);
            switch (Character.getType(caractere)) {
                case Character.UPPERCASE_LETTER:
                    countUppercaseLetters++;
                    break;
                case Character.LOWERCASE_LETTER:
                    countLowercaseLetters++;
                    break;
                case Character.DECIMAL_DIGIT_NUMBER:
                    countNumbers++;
                    break;
                default:
                    if (!Character.isLetterOrDigit(caractere) && !Character.isWhitespace(caractere) && caractere != '_') {
                        countSymbols++;
                    }
            }
        }
        //caso tenha somente numeros, não soma para o bonus
        if(password.matches("\\d+")){
            countNumbers = 0;
        }else{
            countNumbers++;
        }
        //números ou símbolos intermediários
        for(int i = 1; i < password.length() - 1; i++){
            char caractere = password.charAt(i);
            if(!Character.isWhitespace(caractere)  && (Character.isDigit(caractere) || !Character.isAlphabetic(caractere)) ){
                countMiddleNumbersOrSymbols++;
            }
        }
        return 2;
    }

    public void adictions() {
        countBonusScore += countUppercaseLetters > 0 ? ((countTotalCaracter - countUppercaseLetters)*2) : 0;
        countBonusScore += countLowercaseLetters > 0 ? ((countTotalCaracter - countLowercaseLetters)*2) : 0;
        countBonusScore += countNumbers > 0 ? (countNumbers * 4) : 0;
        countBonusScore += countSymbols > 0 ? (countSymbols * 6) : 0;
        countBonusScore += countMiddleNumbersOrSymbols > 0 ? (countMiddleNumbersOrSymbols * 2) : 0;
        //contador caso cumpra todos os requisitos
        if(countTotalCaracter >= 8){
            countForAllRequirements++;
            countForAllRequirements += (countUppercaseLetters > 0) ? 1 : 0;
            countForAllRequirements += (countLowercaseLetters > 0) ? 1 : 0;
            countForAllRequirements += (countNumbers > 0) ? 1 : 0;
            countForAllRequirements += (countSymbols > 0) ? 1 : 0;
        }
        countBonusScore += countForAllRequirements > 0 ? (countForAllRequirements * 2) : 0;
    }

    public StaffModel deductions(String password){;
        Set<Character> charSet = new HashSet<>();
        if(password.matches("[a-zA-Z]+")){
            countIsOnlyLetters = password.length();//apenas letras
        }

        if(password.matches("\\d+")){
            countIsOnlyNumbers = password.length();//somente números
        }

        //adiciona cada caracter de password ao array
        for(int i = 0; i < password.length(); i++){
            char currentChar = password.charAt(i);
            characterList.clear();
            characterList.add(currentChar);
        }

        for(int i = 0; i < password.length(); i++){
            char currentChar = password.charAt(i);
            characterRepeted = characterList.stream().filter(value -> value.charValue() == currentChar).toList();//pega os characteres repetidos
            if(characterRepeted.size() > 1){
                countCharacterRepeated += characterRepeted.size();
                characterList = characterList.stream().filter(value -> value.charValue() != currentChar).toList();//retira o character já contado da lista
            }
        }

        for(int i = 0; i < password.length(); i ++){
            char caractere = password.charAt(i);
            int signalToOperation = 1;
            Character.getType(caractere);
            if(i == password.length() - 1){
                signalToOperation = -1;
            }

            if(Character.isUpperCase(caractere) &&  Character.isUpperCase(password.charAt(i + signalToOperation))) {
                countUpperCaseLettersSequential++;
            }

            if(Character.isLowerCase(caractere) &&  Character.isLowerCase(password.charAt(i + signalToOperation))) {
                countLowercaseLettersSequential++;
            }

            if (Character.isDigit(caractere) && Character.isDigit(password.charAt(i + signalToOperation)) && password.charAt(i + signalToOperation) == caractere + signalToOperation) {
                countNumbersConsecutive++;
                countNumbersSequential += (countNumbersConsecutive > 2) ? 1 : 0;
            }


            if (Character.isLetter(caractere) && Character.isLetter(password.charAt(i + signalToOperation)) && password.charAt(i + signalToOperation) == caractere + i + signalToOperation) {
                countLetterSequential++;
            }

            if (!Character.isLetterOrDigit(caractere) && !Character.isWhitespace(caractere) &&
                    !Character.isLetterOrDigit(password.charAt(i + signalToOperation)) && !Character.isWhitespace(password.charAt(i + signalToOperation)) &&
                    password.charAt(i + signalToOperation) == caractere + i + signalToOperation) {
                countSimbolsSequential++;
            }
        }
        //não achei o calculo exato para a taxa do tipo comp, não achei a doc dele sobre
        countBonusScore = countIsOnlyLetters > 0 ? countBonusScore - countIsOnlyLetters : countBonusScore;
        countBonusScore = countIsOnlyNumbers > 0 ? countBonusScore - countIsOnlyNumbers : countBonusScore;
        countBonusScore = countCharacterRepeated > 0 ? countBonusScore - countCharacterRepeated : countBonusScore;
        countBonusScore = countUpperCaseLettersSequential > 0 ? (countBonusScore-(countUpperCaseLettersSequential * 2)) : countBonusScore;
        countBonusScore = countLowercaseLettersSequential > 0 ? (countBonusScore-(countLowercaseLettersSequential * 2)) : countBonusScore;
        countBonusScore = countNumbersConsecutive > 0 ? (countBonusScore-(countNumbersConsecutive * 2)) : countBonusScore;
        countBonusScore = countLetterSequential > 0 ? (countBonusScore-(countLetterSequential * 3)) : countBonusScore;
        countBonusScore = countNumbersSequential > 0 ? (countBonusScore-(countNumbersSequential * 3)) : countBonusScore;
        countBonusScore = countSimbolsSequential > 0 ? (countBonusScore-(countSimbolsSequential * 3)) : countBonusScore;
        if(countBonusScore <= 10){
            staffModel.setPasswordRate(ScorePasswordEnum.RUIM.toString());
        }
        if(countBonusScore > 10 && countBonusScore <= 45){
            staffModel.setPasswordRate(ScorePasswordEnum.MEDIANA.toString());
        }

        if(countBonusScore > 45 && countBonusScore <= 70){
            staffModel.setPasswordRate(ScorePasswordEnum.BOM.toString());
        }

        if(countBonusScore > 70){
            staffModel.setPasswordRate(ScorePasswordEnum.FORTE.toString());
        }
        SecurityConfig encryptedPassword = new SecurityConfig();
        staffModel.setPassword(encryptedPassword.encryptData(staffModel.getPassword()));
        staffModel.setScorePassword(BigDecimal.valueOf(countBonusScore));
        switch (staffModel.getHierarchy()){
            case("CEO"):
                staffModel.setOrderHierarchy(-1);
                break;
            case("TEAMLEADER"):
                staffModel.setOrderHierarchy(1);
                break;
            case("STAFF"):
                staffModel.setOrderHierarchy(2);
                break;
        }
        return staffModel;
    }
}
