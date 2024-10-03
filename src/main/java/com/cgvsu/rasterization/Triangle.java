package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Triangle {

    private final int[] coorX;
    private final int[] coorY;
    private Color color0;
    private Color color1;
    private Color color2;

    public Triangle(int[] coorX, int[] coorY, Color color0, Color color1, Color color2) {
        this.coorX = coorX;
        this.coorY = coorY;
        this.color0 = color0;
        this.color1 = color1;
        this.color2 = color2;
    }

    public int[] barycentricCoordinates(int x, int y){


        double alfa = (double) ((coorY[1] - coorY[2]) * (x - coorX[2]) + (coorX[2] - coorX[1]) * (y - coorY[2])) /
                (double) ((coorY[1] - coorY[2]) * (coorX[0] - coorX[2]) + (coorX[2] - coorX[1]) * (coorY[0] - coorY[2]));

        double betta = (double) ((coorY[2] - coorY[0]) * (x - coorX[2]) + (coorX[0] - coorX[2]) * (y - coorY[2])) /
                (double) ((coorY[1] - coorY[2]) * (coorX[0] - coorX[2]) + (coorX[2] - coorX[1]) * (coorY[0] - coorY[2]));

        double gamma = 1 - alfa - betta;

        int r = Math.min(255, (int) Math.abs(color0.getRed()*255 * alfa + color1.getRed()*255 * betta + color2.getRed()*255 * gamma));
        int g = Math.min(255, (int) Math.abs(color0.getGreen()*255 * alfa + color1.getGreen()*255 * betta + color2.getGreen()*255 * gamma));
        int b = Math.min(255, (int) Math.abs(color0.getBlue()*255 * alfa + color1.getBlue()*255 * betta + color2.getBlue()*255 * gamma));

        return new int[] {r, g, b};
    }


    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, 1500, 1000);
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        //сортируем вершины
        if(coorY[0] > coorY[1]){
            int termY = coorY[0];
            coorY[0] = coorY[1];
            coorY[1] = termY;
            int termX = coorX[0];
            coorX[0] = coorX[1];
            coorX[1] = termX;
            Color color = color0;
            color0 = color1;
            color1 = color;
        }
        if(coorY[0] > coorY[2]){
            int term = coorY[0];
            coorY[0] = coorY[2];
            coorY[2] = term;
            int termX = coorX[0];
            coorX[0] = coorX[2];
            coorX[2] = termX;
            Color color = color0;
            color0 = color2;
            color2 = color;
        }
        if(coorY[1] > coorY[2]){
            int term = coorY[1];
            coorY[1] = coorY[2];
            coorY[2] = term;
            int termX = coorX[1];
            coorX[1] = coorX[2];
            coorX[2] = termX;
            Color color = color1;
            color1 = color2;
            color2 = color;
        }

        if(coorY[0] == coorY[1] && coorY[1] == coorY[2]){
            for(int x = coorX[0]; x <= coorX[2]; x++){
                int[] rgb = barycentricCoordinates(x, coorY[0]);
                pixelWriter.setColor(x, coorY[0], Color.rgb(rgb[0], rgb[1], rgb[2]));
            }
        }
        if(coorY[0] != coorY[1]){
            for (int y = coorY[0]; y <= coorY[1]; y++) {
                //находим x
                int xl = ((coorX[1] - coorX[0]) * (y - coorY[0]) / (coorY[1] - coorY[0])) + coorX[0];
                int xr = ((coorX[2] - coorX[0]) * (y - coorY[0]) / (coorY[2] - coorY[0])) + coorX[0];
                if (xl > xr) { // проверяем границы
                    int tempX = xl;
                    xl = xr;
                    xr = tempX;
                }
                for (int x = xl; x <= xr; x++) {
                    int[] rgb = barycentricCoordinates(x, y);
                    pixelWriter.setColor(x, y, Color.rgb(rgb[0], rgb[1], rgb[2]));
                }
            }
        }
        if (coorY[1] != coorY[2]){
            for (int y = coorY[1]; y <= coorY[2]; y++) {
                //находим x
                int xl = ((coorX[1] - coorX[2]) * (y - coorY[2]) / (coorY[1] - coorY[2])) + coorX[2];
                int xr = ((coorX[0] - coorX[2]) * (y - coorY[2]) / (coorY[0] - coorY[2])) + coorX[2];
                if (xl > xr) {
                    int tempX = xl;
                    xl = xr;
                    xr = tempX;
                }
                for (int x = xl; x <= xr; x++) {
                    int[] rgb = barycentricCoordinates(x, y);
                    pixelWriter.setColor(x, y, Color.rgb(rgb[0], rgb[1], rgb[2]));
                }
            }
        }
//
//
//
//
//
//
//
//
//        else if (coorY[0] == coorY[1]){
//            for (int y = coorY[1]; y <= coorY[2]; y++) {
//                //находим x
//                int xl = ((coorX[1] - coorX[2]) * (y - coorY[2]) / (coorY[1] - coorY[2])) + coorX[2];
//                int xr = ((coorX[0] - coorX[2]) * (y - coorY[2]) / (coorY[0] - coorY[2])) + coorX[2];
//                if (xl > xr) {
//                    int tempX = xl;
//                    xl = xr;
//                    xr = tempX;
//                }
//                for (int x = xl; x <= xr; x++) {
//                    int[] rgb = barycentricCoordinates(x, y);
//                    pixelWriter.setColor(x, y, Color.rgb(rgb[0], rgb[1], rgb[2]));
//                }
//            }
//        }
//
//        else if (coorY[1] == coorY[2]){
//            for (int y = coorY[0]; y <= coorY[1]; y++) {
//                //находим x
//                int xl = ((coorX[1] - coorX[0]) * (y - coorY[0]) / (coorY[1] - coorY[0])) + coorX[0];
//                int xr = ((coorX[2] - coorX[0]) * (y - coorY[0]) / (coorY[2] - coorY[0])) + coorX[0];
//                if (xl > xr) { // проверяем границы
//                    int tempX = xl;
//                    xl = xr;
//                    xr = tempX;
//                }
//                for (int x = xl; x <= xr; x++) {
//                    int[] rgb = barycentricCoordinates(x, y);
//                    pixelWriter.setColor(x, y, Color.rgb(rgb[0], rgb[1], rgb[2]));
//                }
//            }
//        }
//        else {
//            for (int y = coorY[0]; y < coorY[1]; y++) {
//                //находим x
//                int xl = ((coorX[1] - coorX[0]) * (y - coorY[0]) / (coorY[1] - coorY[0])) + coorX[0];
//                int xr = ((coorX[2] - coorX[0]) * (y - coorY[0]) / (coorY[2] - coorY[0])) + coorX[0];
//                if (xl > xr) { // проверяем границы
//                    int tempX = xl;
//                    xl = xr;
//                    xr = tempX;
//                }
//                for (int x = xl; x <= xr; x++) {
//                    int[] rgb = barycentricCoordinates(x, y);
//                    pixelWriter.setColor(x, y, Color.rgb(rgb[0], rgb[1], rgb[2]));
//                }
//            }
//
//            for (int y = coorY[1]; y <= coorY[2]; y++) {
//                //находим x
//                int xl = ((coorX[1] - coorX[2]) * (y - coorY[2]) / (coorY[1] - coorY[2])) + coorX[2];
//                int xr = ((coorX[0] - coorX[2]) * (y - coorY[2]) / (coorY[0] - coorY[2])) + coorX[2];
//                if (xl > xr) {
//                    int tempX = xl;
//                    xl = xr;
//                    xr = tempX;
//                }
//                for (int x = xl; x <= xr; x++) {
//                    int[] rgb = barycentricCoordinates(x, y);
//                    pixelWriter.setColor(x, y, Color.rgb(rgb[0], rgb[1], rgb[2]));
//                }
//            }
//        }
    }

}