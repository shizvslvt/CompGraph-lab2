package com.example.compgraphlab2;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;

import java.util.Arrays;
public class HelloController {

    @FXML
    private void initialize() {
        initK();
        initY();
        initB();

        init0();
        init2();
        init0_1();
        init6();
        init2_1();
        init0_2();
        init0_3();
        init4();

    }

    double minX = 0; double minY = 0; double maxX = 1600; double maxY = 665;
    //****************************************
    //              Word K
    private int xSpinPointK; private int ySpinPointK; @FXML private Circle pointSpinK;
    @FXML private Slider widthSliderK, spinPointSliderK, xPointK, yPointK;
    @FXML private ColorPicker fillColorPickerK, lineColorPickerK, spinColorPickerK;
    @FXML private Pane canvasK; private double[][] pointsK;
    private void initK() {
        pointSpinK.setOnMouseDragged(this::DraggedPointK);
        xSpinPointK = (int) pointSpinK.getCenterX();
        ySpinPointK = (int) pointSpinK.getCenterY();
        xPointK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        yPointK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        spinPointSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        fillColorPickerK.setValue(Color.rgb(43, 110, 98));
        lineColorPickerK.setValue(Color.WHITE);
        spinColorPickerK.setValue(Color.RED);
        changeColor(canvasK, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK);
        fillColorPickerK.setOnAction(event -> changeColor(canvasK, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        lineColorPickerK.setOnAction(event -> changeColor(canvasK, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        spinColorPickerK.setOnAction(event -> changeColor(canvasK, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        widthSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        redrawK();
    }
    private void redrawK() {
        canvasK.getChildren().clear();
        int wK = (int) widthSliderK.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPointK.getValue(); double mainPointX = xPointK.getValue();
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK);
        int angle = (int) spinPointSliderK.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {initialY * yK, initialX * xK},
                {(initialY + 0) * yK, (initialX + 10) * xK},
                {(initialY + 2) * yK, (initialX + 10) * xK},
                {(initialY + 2) * yK, (initialX + 7) * xK},
                {(initialY + 5) * yK, (initialX + 10) * xK},
                {(initialY + 7) * yK, (initialX + 10) * xK},
                {(initialY + 3) * yK, (initialX + 5) * xK},
                {(initialY + 7) * yK, (initialX + 0) * xK},
                {(initialY + 5) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {initialY * yK, initialX * xK}
        };
        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) pointsK = rotate(pointsK, tempPoints, cosAngle, sinAngle, xSpinPointK, ySpinPointK);
        for (int i = 1; i < pointsK.length; i++) {
            double[] currentPoint = pointsK[i - 1];
            double[] nextPoint = pointsK[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPickerK.getValue(), canvasK);
        }
        fillShape(fillColorPickerK, canvasK, pointsK);
    }

    //****************************************
    //              Word Y
    private double[][] pointsY; @FXML private Pane canvasY;
    private void initY() {
        xPointK.valueProperty().addListener((observable, oldValue, newValue) -> redrawY());
        yPointK.valueProperty().addListener((observable, oldValue, newValue) -> redrawY());
        spinPointSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawY());
        changeColor(canvasY, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK);
        fillColorPickerK.setOnAction(event -> changeColor(canvasY, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        lineColorPickerK.setOnAction(event -> changeColor(canvasY, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        spinColorPickerK.setOnAction(event -> changeColor(canvasY, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        widthSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawY());
        redrawY();
    }
    private void redrawY() {
        canvasY.getChildren().clear();
        int wK = (int) widthSliderK.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPointK.getValue(); double mainPointX = xPointK.getValue();
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 10;
        int angle = (int) spinPointSliderK.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {initialY * yK, initialX * xK},
                {(initialY + 0) * yK, (initialX + 10) * xK},
                {(initialY + 2) * yK, (initialX + 10) * xK},
                {(initialY + 2) * yK, (initialX + 6) * xK},
                {(initialY + 4) * yK, (initialX + 6) * xK},
                {(initialY + 4) * yK, (initialX + 8) * xK},
                {(initialY + 6) * yK, (initialX + 10) * xK},
                {(initialY + 9) * yK, (initialX + 10) * xK},
                {(initialY + 11) * yK, (initialX + 8) * xK},
                {(initialY + 11) * yK, (initialX + 5) * xK},
                {(initialY + 9) * yK, (initialX + 5) * xK},
                {(initialY + 9) * yK, (initialX + 7) * xK},
                {(initialY + 8) * yK, (initialX + 8) * xK},
                {(initialY + 7) * yK, (initialX + 8) * xK},
                {(initialY + 6) * yK, (initialX + 7) * xK},
                {(initialY + 6) * yK, (initialX + 3) * xK},
                {(initialY + 7) * yK, (initialX + 2) * xK},
                {(initialY + 8) * yK, (initialX + 2) * xK},
                {(initialY + 9) * yK, (initialX + 3) * xK},
                {(initialY + 9) * yK, (initialX + 5) * xK},
                {(initialY + 11) * yK, (initialX + 5) * xK},
                {(initialY + 11) * yK, (initialX + 2) * xK},
                {(initialY + 9) * yK, (initialX) * xK},
                {(initialY + 6) * yK, (initialX) * xK},
                {(initialY + 4) * yK, (initialX + 2) * xK},
                {(initialY + 4) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},


                {initialY * yK, initialX * xK}
        };

        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) pointsY = rotate(pointsY, tempPoints, cosAngle, sinAngle, xSpinPointK, ySpinPointK);
        for (int i = 1; i < pointsY.length; i++) {
            double[] currentPoint = pointsY[i - 1];
            double[] nextPoint = pointsY[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPickerK.getValue(), canvasY);
        }
        fillShape(fillColorPickerK, canvasY, pointsY);
    }
    //****************************************
    //              Word B
    private double[][] pointsB; @FXML private Pane canvasB;
    private void initB() {
        xPointK.valueProperty().addListener((observable, oldValue, newValue) -> redrawB());
        yPointK.valueProperty().addListener((observable, oldValue, newValue) -> redrawB());
        spinPointSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawB());
        changeColor(canvasB, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK);
        fillColorPickerK.setOnAction(event -> changeColor(canvasB, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        lineColorPickerK.setOnAction(event -> changeColor(canvasB, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        spinColorPickerK.setOnAction(event -> changeColor(canvasB, lineColorPickerK, spinColorPickerK, fillColorPickerK, pointSpinK));
        widthSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawB());
        redrawB();
    }
    private void redrawB() {
        canvasB.getChildren().clear();
        int wK = (int) widthSliderK.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPointK.getValue(); double mainPointX = xPointK.getValue();
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 25;
        int angle = (int) spinPointSliderK.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY ) * yK, (initialX ) * xK},
                {(initialY ) * yK, (initialX + 10) * xK},
                {(initialY + 4+1) * yK, (initialX + 10) * xK},
                {(initialY + 5+1) * yK, (initialX + 9) * xK},
                {(initialY + 5+1) * yK, (initialX + 9) * xK},
                {(initialY + 5+1) * yK, (initialX + 6) * xK},
                {(initialY + 4+1) * yK, (initialX + 5) * xK},
                {(initialY + 0+1) * yK, (initialX + 5) * xK},
                {(initialY + 0+1) * yK, (initialX + 5) * xK},
                {(initialY + 2) * yK, (initialX + 6) * xK},
                {(initialY + 3+1) * yK, (initialX + 6) * xK},
                {(initialY + 4+1) * yK, (initialX + 7) * xK},
                {(initialY + 4+1) * yK, (initialX + 8) * xK},
                {(initialY + 3+1) * yK, (initialX + 9) * xK},
                {(initialY + 2) * yK, (initialX + 9) * xK},
                {(initialY + 2) * yK, (initialX + 9) * xK},
                {(initialY + 1) * yK, (initialX + 8) * xK},
                {(initialY + 1) * yK, (initialX + 7) * xK},
                {(initialY + 2) * yK, (initialX + 6) * xK},
                {(initialY + 0+1) * yK, (initialX + 5) * xK},
                {(initialY + 4+1) * yK, (initialX + 5) * xK},
                {(initialY + 5+1) * yK, (initialX + 4) * xK},
                {(initialY + 5+1) * yK, (initialX + 1) * xK},
                {(initialY + 4+1) * yK, (initialX + 0) * xK},
                {(initialY ) * yK, (initialX ) * xK},
                {(initialY + 2) * yK, (initialX + 1) * xK},
                {(initialY + 3+1) * yK, (initialX + 1) * xK},
                {(initialY + 4+1) * yK, (initialX + 2) * xK},
                {(initialY + 4+1) * yK, (initialX + 3) * xK},
                {(initialY + 3+1) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 4) * xK},
                {(initialY + 1) * yK, (initialX + 3) * xK},
                {(initialY + 1) * yK, (initialX + 2) * xK},
                {(initialY + 1) * yK, (initialX + 2) * xK},
                {(initialY + 2) * yK, (initialX + 1) * xK},
        };

        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) pointsB = rotate(pointsB, tempPoints, cosAngle, sinAngle, xSpinPointK, ySpinPointK);
        for (int i = 1; i < pointsB.length; i++) {
            double[] currentPoint = pointsB[i - 1];
            double[] nextPoint = pointsB[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPickerK.getValue(), canvasB);
        }
        fillShape(fillColorPickerK, canvasB, pointsB);
    }



    //****************************************
    //              Chislo 0
    private int xSpinPoint0; private int ySpinPoint0;  @FXML private Circle pointSpin0;
    @FXML private Slider widthSlider0, spinPointSlider0, xPoint0, yPoint0;
    @FXML private ColorPicker fillColorPicker0, lineColorPicker0, spinColorPicker0;
    @FXML private Pane canvas0;   private double[][] points0;
    private void init0() {
        pointSpin0.setOnMouseDragged(this::DraggedPoint0);
        xSpinPoint0 = (int) pointSpin0.getCenterX();
        ySpinPoint0 = (int) pointSpin0.getCenterY();
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        fillColorPicker0.setValue(Color.rgb(43, 110, 98));
        lineColorPicker0.setValue(Color.WHITE);
        spinColorPicker0.setValue(Color.BLUE);
        changeColor(canvas0, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0);
        fillColorPicker0.setOnAction(event -> changeColor(canvas0, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        lineColorPicker0.setOnAction(event -> changeColor(canvas0, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        spinColorPicker0.setOnAction(event -> changeColor(canvas0, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        redraw0();
    }
    private void redraw0() {
        canvas0.getChildren().clear();
        int wK = (int) widthSlider0.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPoint0.getValue(); double mainPointX = xPoint0.getValue() + 800;
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK);
        int angle = (int) spinPointSlider0.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 0) * yK, (initialX + 2) * xK},
                {(initialY + 0) * yK, (initialX + 8) * xK},
                {(initialY + 2) * yK, (initialX + 10)* xK},
                {(initialY + 5) * yK, (initialX + 10)* xK},
                {(initialY + 7) * yK, (initialX + 8) * xK},
                {(initialY + 7) * yK, (initialX + 2) * xK},
                {(initialY + 5) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 3) * yK, (initialX + 2) * xK},
                {(initialY + 4) * yK, (initialX + 2) * xK},
                {(initialY + 5) * yK, (initialX + 3) * xK},
                {(initialY + 5) * yK, (initialX + 7) * xK},
                {(initialY + 4) * yK, (initialX + 8) * xK},
                {(initialY + 3) * yK, (initialX + 8) * xK},
                {(initialY + 2) * yK, (initialX + 7) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
        };
        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) points0 = rotate(points0, tempPoints, cosAngle, sinAngle, xSpinPoint0, ySpinPoint0);
        for (int i = 1; i < points0.length; i++) {
            double[] currentPoint = points0[i - 1];
            double[] nextPoint = points0[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPicker0.getValue(), canvas0);
        }
        fillShape(fillColorPicker0, canvas0, points0);
    }

    //****************************************
    //              Chislo 2
    private double[][] points2; @FXML private Pane canvas2;
    private void init2() {
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw2());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw2());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw2());
        changeColor(canvas2, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0);
        fillColorPicker0.setOnAction(event -> changeColor(canvas2, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        lineColorPicker0.setOnAction(event -> changeColor(canvas2, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        spinColorPicker0.setOnAction(event -> changeColor(canvas2, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw2());
        redraw2();
    }
    private void redraw2() {
        canvas2.getChildren().clear();
        int wK = (int) widthSlider0.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPoint0.getValue(); double mainPointX = xPoint0.getValue() + 800;
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 10;
        int angle = (int) spinPointSlider0.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 6) * yK, (initialX + 0) * xK},
                {(initialY + 8) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 8) * xK},
                {(initialY + 8) * yK, (initialX + 8) * xK},
                {(initialY + 8) * yK, (initialX + 10) * xK},
                {(initialY + 0) * yK, (initialX + 10) * xK},
                {(initialY + 0) * yK, (initialX + 8) * xK},
                {(initialY + 6) * yK, (initialX + 4) * xK},
                {(initialY + 5) * yK, (initialX + 2) * xK},
                {(initialY + 3) * yK, (initialX + 2) * xK},
                {(initialY + 2) * yK, (initialX + 4) * xK},
                {(initialY + 0) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
        };


        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) points2 = rotate(points2, tempPoints, cosAngle, sinAngle, xSpinPoint0, ySpinPoint0);
        for (int i = 1; i < points2.length; i++) {
            double[] currentPoint = points2[i - 1];
            double[] nextPoint = points2[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPicker0.getValue(), canvas2);
        }
        fillShape(fillColorPicker0, canvas2, points2);
        Circle circle = new Circle();
        circle.setCenterX(points2[5][0]+wK/10);
        circle.setCenterY(points2[5][1]);
        circle.setRadius(wK/20);
        circle.setFill(Color.GRAY);
        canvas2.getChildren().add(circle);
    }

    //****************************************
    //              Chislo 0_1
    private double[][] points0_1; @FXML private Pane canvas0_1;
    private void init0_1() {
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_1());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_1());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_1());
        changeColor(canvas0_1, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0);
        fillColorPicker0.setOnAction(event -> changeColor(canvas0_1, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        lineColorPicker0.setOnAction(event -> changeColor(canvas0_1, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        spinColorPicker0.setOnAction(event -> changeColor(canvas0_1, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_1());
        redraw0_1();
    }
    private void redraw0_1() {
        canvas0_1.getChildren().clear();
        int wK = (int) widthSlider0.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPoint0.getValue(); double mainPointX = xPoint0.getValue() + 800;
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 20;
        int angle = (int) spinPointSlider0.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 0) * yK, (initialX + 2) * xK},
                {(initialY + 0) * yK, (initialX + 8) * xK},
                {(initialY + 2) * yK, (initialX + 10)* xK},
                {(initialY + 5) * yK, (initialX + 10)* xK},
                {(initialY + 7) * yK, (initialX + 8) * xK},
                {(initialY + 7) * yK, (initialX + 2) * xK},
                {(initialY + 5) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 3) * yK, (initialX + 2) * xK},
                {(initialY + 4) * yK, (initialX + 2) * xK},
                {(initialY + 5) * yK, (initialX + 3) * xK},
                {(initialY + 5) * yK, (initialX + 7) * xK},
                {(initialY + 4) * yK, (initialX + 8) * xK},
                {(initialY + 3) * yK, (initialX + 8) * xK},
                {(initialY + 2) * yK, (initialX + 7) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
        };
        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) points0_1 = rotate(points0_1, tempPoints, cosAngle, sinAngle, xSpinPoint0, ySpinPoint0);
        for (int i = 1; i < points0_1.length; i++) {
            double[] currentPoint = points0_1[i - 1];
            double[] nextPoint = points0_1[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPicker0.getValue(), canvas0_1);
        }
        fillShape(fillColorPicker0, canvas0_1, points0_1);
    }

    //****************************************
    //              Chislo 6
    private double[][] points6; @FXML private Pane canvas6;
    private void init6() {
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw6());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw6());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw6());
        changeColor(canvas6, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0);
        fillColorPicker0.setOnAction(event -> changeColor(canvas6, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        lineColorPicker0.setOnAction(event -> changeColor(canvas6, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        spinColorPicker0.setOnAction(event -> changeColor(canvas6, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw6());
        redraw6();
    }
    private void redraw6() {
        canvas6.getChildren().clear();
        int wK = (int) widthSlider0.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPoint0.getValue(); double mainPointX = xPoint0.getValue() + 800;
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 30;
        int angle = (int) spinPointSlider0.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY ) * yK, (initialX ) * xK},
                {(initialY ) * yK, (initialX + 10) * xK},
                {(initialY + 5) * yK, (initialX + 10) * xK},
                {(initialY + 6) * yK, (initialX + 10) * xK},
                {(initialY + 6) * yK, (initialX + 5) * xK},
                {(initialY + 1) * yK, (initialX + 5) * xK},
                {(initialY + 1) * yK, (initialX + 5) * xK},
                {(initialY + 2) * yK, (initialX + 6) * xK},
                {(initialY + 4) * yK, (initialX + 6) * xK},
                {(initialY + 5) * yK, (initialX + 7) * xK},
                {(initialY + 5) * yK, (initialX + 8) * xK},
                {(initialY + 4) * yK, (initialX + 9) * xK},
                {(initialY + 2) * yK, (initialX + 9) * xK},
                {(initialY + 2) * yK, (initialX + 9) * xK},
                {(initialY + 1) * yK, (initialX + 8) * xK},
                {(initialY + 1) * yK, (initialX + 7) * xK},
                {(initialY + 2) * yK, (initialX + 6) * xK},
                {(initialY + 1) * yK, (initialX + 5) * xK},
                {(initialY + 1) * yK, (initialX + 1) * xK},
                {(initialY + 6) * yK, (initialX + 1) * xK},
                {(initialY + 6) * yK, (initialX + 0) * xK},
                {(initialY + 0) * yK, (initialX + 0) * xK},
        };


        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) points6 = rotate(points6, tempPoints, cosAngle, sinAngle, xSpinPoint0, ySpinPoint0);
        for (int i = 1; i < points6.length; i++) {
            double[] currentPoint = points6[i - 1];
            double[] nextPoint = points6[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPicker0.getValue(), canvas6);
        }
        fillShape(fillColorPicker0, canvas6, points6);
        Circle circle = new Circle();
        circle.setCenterX(points6[3][0]+wK/10);
        circle.setCenterY(points6[3][1]);
        circle.setRadius(wK/20);
        circle.setFill(Color.GRAY);
        canvas6.getChildren().add(circle);
    }

    //****************************************
    //              Chislo 2_1
    private double[][] points2_1; @FXML private Pane canvas2_1;
    private void init2_1() {
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw2_1());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw2_1());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw2_1());
        changeColor(canvas2_1, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0);
        fillColorPicker0.setOnAction(event -> changeColor(canvas2_1, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        lineColorPicker0.setOnAction(event -> changeColor(canvas2_1, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        spinColorPicker0.setOnAction(event -> changeColor(canvas2_1, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw2_1());
        redraw2_1();
    }
    private void redraw2_1() {
        canvas2_1.getChildren().clear();
        int wK = (int) widthSlider0.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPoint0.getValue(); double mainPointX = xPoint0.getValue() + 800;
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 40;
        int angle = (int) spinPointSlider0.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 6) * yK, (initialX + 0) * xK},
                {(initialY + 8) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 8) * xK},
                {(initialY + 8) * yK, (initialX + 8) * xK},
                {(initialY + 8) * yK, (initialX + 10) * xK},
                {(initialY + 0) * yK, (initialX + 10) * xK},
                {(initialY + 0) * yK, (initialX + 8) * xK},
                {(initialY + 6) * yK, (initialX + 4) * xK},
                {(initialY + 5) * yK, (initialX + 2) * xK},
                {(initialY + 3) * yK, (initialX + 2) * xK},
                {(initialY + 2) * yK, (initialX + 4) * xK},
                {(initialY + 0) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
        };
        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) points2_1 = rotate(points2_1, tempPoints, cosAngle, sinAngle, xSpinPoint0, ySpinPoint0);
        for (int i = 1; i < points2_1.length; i++) {
            double[] currentPoint = points2_1[i - 1];
            double[] nextPoint = points2_1[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPicker0.getValue(), canvas2_1);
        }
        fillShape(fillColorPicker0, canvas2_1, points2_1);
    }

    //****************************************
    //              Chislo 0_2
    private double[][] points0_2; @FXML private Pane canvas0_2;
    private void init0_2() {
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_2());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_2());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_2());
        changeColor(canvas0_2, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0);
        fillColorPicker0.setOnAction(event -> changeColor(canvas0_2, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        lineColorPicker0.setOnAction(event -> changeColor(canvas0_2, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        spinColorPicker0.setOnAction(event -> changeColor(canvas0_2, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_2());
        redraw0_2();
    }
    private void redraw0_2() {
        canvas0_2.getChildren().clear();
        int wK = (int) widthSlider0.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPoint0.getValue(); double mainPointX = xPoint0.getValue() + 800;
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 50;
        int angle = (int) spinPointSlider0.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 0) * yK, (initialX + 2) * xK},
                {(initialY + 0) * yK, (initialX + 8) * xK},
                {(initialY + 2) * yK, (initialX + 10)* xK},
                {(initialY + 5) * yK, (initialX + 10)* xK},
                {(initialY + 7) * yK, (initialX + 8) * xK},
                {(initialY + 7) * yK, (initialX + 2) * xK},
                {(initialY + 5) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 3) * yK, (initialX + 2) * xK},
                {(initialY + 4) * yK, (initialX + 2) * xK},
                {(initialY + 5) * yK, (initialX + 3) * xK},
                {(initialY + 5) * yK, (initialX + 7) * xK},
                {(initialY + 4) * yK, (initialX + 8) * xK},
                {(initialY + 3) * yK, (initialX + 8) * xK},
                {(initialY + 2) * yK, (initialX + 7) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
        };
        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) points0_2 = rotate(points0_2, tempPoints, cosAngle, sinAngle, xSpinPoint0, ySpinPoint0);
        for (int i = 1; i < points0_2.length; i++) {
            double[] currentPoint = points0_2[i - 1];
            double[] nextPoint = points0_2[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPicker0.getValue(), canvas0_2);
        }
        fillShape(fillColorPicker0, canvas0_2, points0_2);
    }


    //****************************************
    //              Chislo 0_3
    private double[][] points0_3; @FXML private Pane canvas0_3;

    private void init0_3() {
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_3());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_3());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_3());
        changeColor(canvas0_3, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0);
        fillColorPicker0.setOnAction(event -> changeColor(canvas0_3, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        lineColorPicker0.setOnAction(event -> changeColor(canvas0_3, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        spinColorPicker0.setOnAction(event -> changeColor(canvas0_3, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0_3());
        redraw0_3();
    }

    private void redraw0_3() {
        canvas0_3.getChildren().clear();
        int wK = (int) widthSlider0.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPoint0.getValue(); double mainPointX = xPoint0.getValue() + 800;
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 60;
        int angle = (int) spinPointSlider0.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 0) * yK, (initialX + 2) * xK},
                {(initialY + 0) * yK, (initialX + 8) * xK},
                {(initialY + 2) * yK, (initialX + 10)* xK},
                {(initialY + 5) * yK, (initialX + 10)* xK},
                {(initialY + 7) * yK, (initialX + 8) * xK},
                {(initialY + 7) * yK, (initialX + 2) * xK},
                {(initialY + 5) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
                {(initialY + 3) * yK, (initialX + 2) * xK},
                {(initialY + 4) * yK, (initialX + 2) * xK},
                {(initialY + 5) * yK, (initialX + 3) * xK},
                {(initialY + 5) * yK, (initialX + 7) * xK},
                {(initialY + 4) * yK, (initialX + 8) * xK},
                {(initialY + 3) * yK, (initialX + 8) * xK},
                {(initialY + 2) * yK, (initialX + 7) * xK},
                {(initialY + 2) * yK, (initialX + 3) * xK},
        };
        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) points0_3 = rotate(points0_3, tempPoints, cosAngle, sinAngle, xSpinPoint0, ySpinPoint0);
        for (int i = 1; i < points0_3.length; i++) {
            double[] currentPoint = points0_3[i - 1];
            double[] nextPoint = points0_3[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPicker0.getValue(), canvas0_3);
        }
        fillShape(fillColorPicker0, canvas0_3, points0_3);
    }

    //****************************************
    //              Chislo 4
    private double[][] points4; @FXML private Pane canvas4;
    private void init4() {
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw4());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw4());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw4());
        changeColor(canvas4, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0);
        fillColorPicker0.setOnAction(event -> changeColor(canvas4, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        lineColorPicker0.setOnAction(event -> changeColor(canvas4, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        spinColorPicker0.setOnAction(event -> changeColor(canvas4, lineColorPicker0, spinColorPicker0, fillColorPicker0, pointSpin0));
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw4());
        redraw4();
    }
    private void redraw4() {
        canvas4.getChildren().clear();
        int wK = (int) widthSlider0.getValue(); int xK = (int) (wK / 15.0); int yK = (int) (wK / 15.0); int wL = 2;
        double mainPointY = yPoint0.getValue(); double mainPointX = xPoint0.getValue() + 800;
        double initialX = (mainPointY / xK); double initialY = (mainPointX / yK) + 70;
        int angle = (int) spinPointSlider0.getValue(); double angleRadians = Math.toRadians(angle); double cosAngle = Math.cos(angleRadians); double sinAngle = Math.sin(angleRadians);
        double[][] tempPoints = new double[][]{
                {(initialY + 0) * yK, (initialX + 0) * xK},
                {(initialY + 0) * yK, (initialX + 6) * xK},
                {(initialY + 4) * yK, (initialX + 6) * xK},
                {(initialY + 4) * yK, (initialX + 10) * xK},
                {(initialY + 6) * yK, (initialX + 10) * xK},
                {(initialY + 6) * yK, (initialX + 0) * xK},
                {(initialY + 4) * yK, (initialX + 0) * xK},
                {(initialY + 4) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 4) * xK},
                {(initialY + 2) * yK, (initialX + 0) * xK},
                {(initialY + 0) * yK, (initialX + 0) * xK},
        };
        boolean pointsWithinBounds = Arrays.stream(tempPoints).noneMatch(point -> point[0] < minX || point[0] > maxX || point[1] < minY || point[1] > maxY);
        if (pointsWithinBounds) points4 = rotate(points4, tempPoints, cosAngle, sinAngle, xSpinPoint0, ySpinPoint0);
        for (int i = 1; i < points4.length; i++) {
            double[] currentPoint = points4[i - 1];
            double[] nextPoint = points4[i];
            drawPoint(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPicker0.getValue(), canvas4);
        }
        fillShape(fillColorPicker0, canvas4, points4);
    }


    //****************************************
    // edit changecolor if you added a new word
    private double[][] rotate(double[][] points, double[][] temp_points, double cosAngle, double sinAngle, int xSpinPoint, int ySpinPoint) {
        boolean pointsWithinBounds = true;
        for (int i = 0; i < temp_points.length; i++) {
            double x = temp_points[i][0] - xSpinPoint;
            double y = temp_points[i][1] - ySpinPoint;
            double newX = x * cosAngle - y * sinAngle + xSpinPoint;
            double newY = x * sinAngle + y * cosAngle + ySpinPoint;
            if (newX < minX || newX > maxX || newY < minY || newY > maxY) {
                pointsWithinBounds = false; break;
            }
            temp_points[i] = new double[]{newX, newY};
        }
        if (!pointsWithinBounds) return points;
        return temp_points;
    }
    private void fillShape(ColorPicker fillColorPicker, Pane canvas, double[][] points) {
        Polygon Shape = new Polygon();
        for (double[] point : points) Shape.getPoints().addAll(point[0], point[1]);
        Shape.setFill(fillColorPicker.getValue());
        canvas.getChildren().add(Shape);
    }
    private void changeColor(Pane canvas, ColorPicker b, ColorPicker c, ColorPicker a, Circle d) {
        for (Node node : canvas.getChildren()) {
            if (node instanceof Polygon) { Polygon Shape = (Polygon) node; Shape.setFill(a.getValue()); }
            if (node instanceof Circle) { Circle point = (Circle) node; point.setFill(b.getValue()); }
        }
        d.setFill(c.getValue());
        redrawK();
        redrawY();
        redrawB();
        redraw0();
        redraw2();
        redraw0_1();
        redraw6();
        redraw2_1();
        redraw0_2();
        redraw0_3();
        redraw4();

    }
    private void drawPoint(double x1, double y1, double x2, double y2, int wL, Color lineColor, Pane canvas) {
        int startX = (int) x1; int startY = (int) y1;
        int endX = (int) x2; int endY = (int) y2;
        int dx = Math.abs(endX - startX); int dy = Math.abs(endY - startY);
        int sx = startX < endX ? 1 : -1; int sy = startY < endY ? 1 : -1;
        int err = dx - dy;
        while (true) {
            canvas.getChildren().add(new Circle(startX, startY, wL, lineColor));
            if (startX == endX && startY == endY) break;
            int e2 = 2 * err;
            if (e2 > -dy) { err -= dy; startX += sx; }
            if (e2 < dx) { err += dx; startY += sy; }
        }
    }


    private void DraggedPointK(MouseEvent event) {
        pointSpinK.setCenterX(event.getX());
        pointSpinK.setCenterY(event.getY());
        xSpinPointK = (int) pointSpinK.getCenterX();
        ySpinPointK = (int) pointSpinK.getCenterY();
    }
    private void DraggedPoint0(MouseEvent event) {
        pointSpin0.setCenterX(event.getX());
        pointSpin0.setCenterY(event.getY());
        xSpinPoint0 =  (int) pointSpin0.getCenterX();
        ySpinPoint0 =  (int) pointSpin0.getCenterY();
    }
}