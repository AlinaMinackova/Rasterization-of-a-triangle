package com.cgvsu.rasterizationfxapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.cgvsu.rasterization.*;
import javafx.scene.paint.Color;

public class RasterizationController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

//        new Triangle(new int[]{60, 200, 10}, new int[]{10, 100, 60}, Color.BISQUE,
//                Color.PALEGOLDENROD, Color.AQUA).draw(canvas.getGraphicsContext2D());
//
//        new Triangle(new int[]{200, 200, 300}, new int[]{50, 100, 50}, Color.NAVAJOWHITE,
//                Color.RED, Color.MAGENTA).draw(canvas.getGraphicsContext2D());
//
//
//        new Triangle(new int[]{60, 200, 300}, new int[]{300, 200, 300}, Color.rgb(255, 255, 255),
//                Color.rgb(0, 0, 255), Color.rgb(255, 0, 0)).draw(canvas.getGraphicsContext2D());
//
//        new Triangle(new int[]{600, 550, 500}, new int[]{250, 200, 250}, Color.VIOLET,
//                Color.BLACK, Color.WHITE).draw(canvas.getGraphicsContext2D());


//        canvas.setOnMouseClicked(event -> {
//            switch (event.getButton()) {
//                case PRIMARY -> handlePrimaryClick(event);
//            }
//        });
        canvas.setOnMouseDragged(event -> {
            switch (event.getButton()) {
                case PRIMARY -> handlePrimaryMove(event);
            }
        });
    }

    private void handlePrimaryMove(MouseEvent event){
        new Triangle(new int[]{400, (int) event.getX(), 900}, new int[]{400, (int) event.getY(), 400},  Color.GREEN,
                Color.RED, Color.BLUE).draw(canvas.getGraphicsContext2D());
    }

//    private void handlePrimaryMove(MouseEvent event){
//        new Triangle(new int[]{600, (int) event.getX(), 600}, new int[]{300, (int) event.getY(), 600}, Color.GREEN,
//                Color.RED, Color.BLUE).draw(canvas.getGraphicsContext2D());
//    }



}