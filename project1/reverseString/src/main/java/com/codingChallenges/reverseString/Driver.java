package com.codingChallenges.reverseString;

public class Driver {

    public static void main(String[] args){
        String reverseThis = "Reverse this";

        char[] charArray = reverseThis.toCharArray();
        String resultString = "";

        for(int i =0; i < charArray.length;i++){
            resultString = charArray[i] + resultString;
        }
        System.out.println(resultString);

    }
}
