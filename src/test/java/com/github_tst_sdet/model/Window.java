package com.github_tst_sdet.model;

public class Window {

    private int width;

    private int height;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public String toString() {
        return "Window{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
