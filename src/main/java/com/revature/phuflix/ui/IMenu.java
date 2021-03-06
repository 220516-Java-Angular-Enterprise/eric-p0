package com.revature.phuflix.ui;

import java.util.List;

public abstract class IMenu {


    abstract void start();


    protected void displayLine(){
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    protected void displayTextBanner(String str){
        int l = str.length();
        String anw = "";

        anw = buildLine((57 - l)/2) + "  " + str +  "  ";
        int l2 = 61- anw.length();
        anw = anw + buildLine(l2);

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(anw);
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    protected void displayTextMiddle(String str){
        int l = str.length();
        int spaces = (61 - l)/2;
        String anw = "";

        for (int i = 0; i< spaces; i++){
            anw += " ";
        }
        System.out.println(anw + str);
    }

    private static String buildLine(int n){
        String anw = "";
        for (int i = 0; i<n; i++){
            if(i%2 == 0){
                anw += "+";
            }
            else{
                anw += "-";
            }
        }
        return anw;
    }

    protected void newPage(){
        for(int i = 0; i <20; i++){
            System.out.println(" ");
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    protected void displayBlankLine(int n){
        for(int i=0; i< n; i++){
            System.out.println(" ");
        }
    }

    protected void displayBox(List<String> l, int num){
        // line = len(l) + 2
        int mid = l.size()/2;
        int spaces;
        System.out.println("+-----------------------------------------------------------+");
        for(int i=0; i < l.size() ;i++){
            spaces = 61;
            String start = "|";
            if (l.get(i).charAt(0) == '⭑'){
                spaces -= 1;
            }
            if(i == mid){
                start = start +" [" + num + "]";
            }
            //
            spaces = (spaces - start.length()*2  -l.get(i).length())/2;
            for (int j = 0; j< spaces; j++){
                start += " ";
            }
            start = start + l.get(i);
            int remain = 60 - start.length();
            if (l.get(i).charAt(0) == '⭑'){
                remain = 59 - start.length();
            }
            for (int j = 0; j< remain; j++){
                start += " ";
            }

            start +="|";
            System.out.println(start);

        }
        System.out.println("+-----------------------------------------------------------+");
    }

    protected void displayTextLine(String str){
        int l = str.length();
        String anw = "";

        anw = buildLine((57 - l)/2) + "  " + str +  "  ";
        int l2 = 61- anw.length();
        anw = anw + buildLine(l2);


        System.out.println(anw);

    }

    // ----------------

    protected String displayLine1(){
        return "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n";
    }

    protected String displayTextBanner1(String str){
        int l = str.length();
        String anw = "";

        String line="+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n";
        String line2;

        anw = buildLine((57 - l)/2) + "  " + str +  "  ";
        int l2 = 61- anw.length();
        anw = anw + buildLine(l2);

        System.out.println();
         return line + anw + "\n" + line;

    }

    protected String displayTextMiddle1(String str){
        int l = str.length();
        int spaces = (61 - l)/2;
        String anw = "";

        for (int i = 0; i< spaces; i++){
            anw += " ";
        }
        return anw + str + "\n";
    }

    private static String buildLine1(int n){
        String anw = "";
        for (int i = 0; i<n; i++){
            if(i%2 == 0){
                anw += "+";
            }
            else{
                anw += "-";
            }
        }
        return anw;
    }

    protected String newPage1(){
        String anw = "";
        for(int i = 0; i <20; i++){
            anw = anw + "\n";
        }
        anw = anw + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n";
        return anw;
    }

    protected String displayBlankLine1(int n){
        String anw = "";
        for(int i=0; i< n; i++){
            anw = anw + "\n";
        }
        return anw;
    }

    protected String displayBox1(List<String> l, int num){
        // line = len(l) + 2
        int mid = l.size()/2;
        int spaces;
        String line = "+-----------------------------------------------------------+\n";
        String anw = line;

        for(int i=0; i < l.size() ;i++){
            spaces = 61;
            String start = "|";
            if (l.get(i).charAt(0) == '⭑'){
                spaces -= 1;
            }
            if(i == mid){
                start = start +" [" + num + "]";
            }
            //
            spaces = (spaces - start.length()*2  -l.get(i).length())/2;
            for (int j = 0; j< spaces; j++){
                start += " ";
            }
            start = start + l.get(i);
            int remain = 60 - start.length();
            if (l.get(i).charAt(0) == '⭑'){
                remain = 59 - start.length();
            }
            for (int j = 0; j< remain; j++){
                start += " ";
            }
            anw = anw + start + "|\n";

            //System.out.println(start);

        }
        return anw + line;
    }

    protected String displayTextLine1(String str){
        int l = str.length();
        String anw = "";

        anw = buildLine((57 - l)/2) + "  " + str +  "  ";
        int l2 = 61- anw.length();
        anw = anw + buildLine(l2);


        return anw + "\n";

    }

    public String scoreToStars(double d){
        int i = (int)Math.round(d);

        if(i ==0){
            return "No Reviews";
        }else if(i == 1){
            return "⭑⭒⭒⭒⭒";
        }else if(i == 2){
            return "⭑⭑⭒⭒⭒";
        }else if(i == 3){
            return "⭑⭑⭑⭒⭒";
        }else if(i == 4){
            return "⭑⭑⭑⭑⭒";
        }else{
            return "⭑⭑⭑⭑⭑";
        }

    }





}
