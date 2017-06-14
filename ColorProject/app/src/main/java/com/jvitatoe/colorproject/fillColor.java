package com.jvitatoe.colorproject;

/**
 * Created by JVitatoe on 9/2/15.
 */
public class fillColor {
    private int r;
    private int g;
    private int b;

    public fillColor(){

    }
    public fillColor(int red, int green, int blue){
        r = red;
        g = green;
        b = blue;

    }

    public int getR(){
        return r;
    }

    public int getB() {
        return b;
    }

    public int getG() {
        return g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int rGB(int r, int g, int b){
        r = (r*65536);
        g = (g*256);
        b = b;
        return r+g+b;

    }
}

