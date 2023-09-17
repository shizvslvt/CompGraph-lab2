package com.example.compgraphlab2;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.control.ColorPicker;

public class HelloController {
    @FXML
    private void initialize() {
        initK();
        init0();
    }

    //****************************************
    //              Word K
    //****************************************

    @FXML
    private Pane canvasK;
    @FXML
    private Slider widthSliderK, xWordSliderK, yWordSliderK, widthLineSliderK, spinPointSliderK, xPointK, yPointK;
    @FXML
    private ColorPicker fillColorPickerK, lineColorPickerK, spinColorPickerK;
    private double xSpinPointK;
    private double ySpinPointK;
    @FXML
    private Circle pointSpinK;
    private void initK() {
        pointSpinK.setOnMouseDragged(this::DraggedPointK);
        xSpinPointK = pointSpinK.getCenterX();
        ySpinPointK = pointSpinK.getCenterY();
        xPointK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        yPointK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        spinPointSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        fillColorPickerK.setValue(Color.rgb(43, 110, 98, 0.5));
        lineColorPickerK.setValue(Color.WHITE);
        spinColorPickerK.setValue(Color.RED);
        widthSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        xWordSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        yWordSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        widthLineSliderK.valueProperty().addListener((observable, oldValue, newValue) -> redrawK());
        fillColorPickerK.setOnAction(event -> changeColorK());
        lineColorPickerK.setOnAction(event -> changeColorK());
        spinColorPickerK.setOnAction(event -> changeColorK());
        redrawK();
    }
    private void drawLineK(double x1, double y1, double x2, double y2, int wL, Color lineColor) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStroke(lineColor);
        line.setStrokeWidth(wL);
        canvasK.getChildren().add(line);
    }
    private void redrawK() {
        canvasK.getChildren().clear();
        int wK = (int) widthSliderK.getValue();
        double xK = xWordSliderK.getValue() * wK / 10.0;
        double yK = yWordSliderK.getValue() * wK / 10.0;
        int wL = (int) widthLineSliderK.getValue();
        double angle = spinPointSliderK.getValue();

        double initialX = yPointK.getValue() / xK;
        double initialY = xPointK.getValue() / yK;

        double angleRadians = Math.toRadians(angle);
        double cosAngle = Math.cos(angleRadians);
        double sinAngle = Math.sin(angleRadians);

        double[][] points = new double[][]{
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

        for (int i = 0; i < points.length; i++) {
            double x = points[i][0] - xSpinPointK;
            double y = points[i][1] - ySpinPointK;
            points[i] = new double[]{x * cosAngle - y * sinAngle + xSpinPointK, x * sinAngle + y * cosAngle + ySpinPointK};
        }

        for (int i = 1; i < points.length; i++) {
            double[] currentPoint = points[i - 1];
            double[] nextPoint = points[i];
            drawLineK(currentPoint[0], currentPoint[1], nextPoint[0], nextPoint[1], wL, lineColorPickerK.getValue());
        }

        Polygon kShape = new Polygon();
        for (double[] point : points) kShape.getPoints().addAll(point[0], point[1]);
        kShape.setFill(fillColorPickerK.getValue());
        canvasK.getChildren().add(kShape);
    }
    private void changeColorK() {
        for (Node node : canvasK.getChildren()) {
            if (node instanceof Polygon) {
                Polygon kShape = (Polygon) node;
                kShape.setFill(fillColorPickerK.getValue());
            }
            if (node instanceof Line) {
                Line line = (Line) node;
                line.setStroke(lineColorPickerK.getValue());
            }
        }
        pointSpinK.setFill(spinColorPickerK.getValue());
    }
    private void DraggedPointK(MouseEvent event) {
        pointSpinK.setCenterX(event.getX());
        pointSpinK.setCenterY(event.getY());
        xSpinPointK = pointSpinK.getCenterX();
        ySpinPointK = pointSpinK.getCenterY();

    }

    //****************************************
    //              Chislo 0
    //****************************************

    @FXML
    private Pane canvas0;
    @FXML
    private Slider widthSlider0, xWordSlider0, yWordSlider0, widthLineSlider0, spinPointSlider0, xPoint0, yPoint0;
    @FXML
    private ColorPicker fillColorPicker0, lineColorPicker0, spinColorPicker0;
    private double xSpinPoint0;
    private double ySpinPoint0;
    @FXML
    private Circle pointSpin0;
    private void init0() {
        pointSpin0.setOnMouseDragged(this::DraggedPoint0);
        xSpinPoint0 = pointSpin0.getCenterX();
        ySpinPoint0 = pointSpin0.getCenterY();
        xPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        yPoint0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        spinPointSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        fillColorPicker0.setValue(Color.rgb(43, 110, 98, 0.5));
        lineColorPicker0.setValue(Color.WHITE);
        spinColorPicker0.setValue(Color.BLUE);
        widthSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        xWordSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        yWordSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        widthLineSlider0.valueProperty().addListener((observable, oldValue, newValue) -> redraw0());
        fillColorPicker0.setOnAction(event -> changeColor0());
        lineColorPicker0.setOnAction(event -> changeColor0());
        spinColorPicker0.setOnAction(event -> changeColor0());
        redraw0();
    }
    private void drawLine0(double x1, double y1, double x2, double y2, int wL, Color lineColor) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStroke(lineColor);
        line.setStrokeWidth(wL);
        canvas0.getChildren().add(line);
    }
    private void redraw0() {
        canvas0.getChildren().clear();
        int w0 = (int) widthSlider0.getValue();
        double x0 = xWordSlider0.getValue() * w0 / 10.0;
        double y0 = yWordSlider0.getValue() * w0 / 10.0;
        int wL0 = (int) widthLineSlider0.getValue();
        double angle0 = spinPointSlider0.getValue();

        double initialX0 = yPoint0.getValue() / x0;
        double initialY0 = xPoint0.getValue() / y0;
        double angleRadians0 = Math.toRadians(angle0);
        double cosAngle0 = Math.cos(angleRadians0);
        double sinAngle0 = Math.sin(angleRadians0);

        double[][] points1 = new double[][] {
                {(initialY0 + 2) * y0, (initialX0 + 0) * x0},
                {(initialY0 + 0) * y0, (initialX0 + 2) * x0},
                {(initialY0 + 0) * y0, (initialX0 + 8) * x0},
                {(initialY0 + 2) * y0, (initialX0 + 10) * x0},
                {(initialY0 + 5) * y0, (initialX0 + 10) * x0},
                {(initialY0 + 7) * y0, (initialX0 + 8) * x0},
                {(initialY0 + 7) * y0, (initialX0 + 2) * x0},
                {(initialY0 + 5) * y0, (initialX0 + 0) * x0},
                {(initialY0 + 2) * y0, (initialX0 + 0) * x0},
        };
        double[][] points2 = new double[][] {
                {(initialY0 + 2) * y0, (initialX0 + 3) * x0},
                {(initialY0 + 2) * y0, (initialX0 + 7) * x0},
                {(initialY0 + 3) * y0, (initialX0 + 8) * x0},
                {(initialY0 + 4) * y0, (initialX0 + 8) * x0},
                {(initialY0 + 5) * y0, (initialX0 + 7) * x0},
                {(initialY0 + 5) * y0, (initialX0 + 3) * x0},
                {(initialY0 + 4) * y0, (initialX0 + 2) * x0},
                {(initialY0 + 3) * y0, (initialX0 + 2) * x0},
                {(initialY0 + 2) * y0, (initialX0 + 3) * x0},
        };

        for (int i = 0; i < points1.length; i++) {
            double x1 = points1[i][0] - xSpinPoint0;
            double y1 = points1[i][1] - ySpinPoint0;
            double x2 = points2[i][0] - xSpinPoint0;
            double y2 = points2[i][1] - ySpinPoint0;
            points1[i] = new double[]{
                    x1 * cosAngle0 - y1 * sinAngle0 + xSpinPoint0,
                    x1 * sinAngle0 + y1 * cosAngle0 + ySpinPoint0
            };
            points2[i] = new double[]{
                    x2 * cosAngle0 - y2 * sinAngle0 + xSpinPoint0,
                    x2 * sinAngle0 + y2 * cosAngle0 + ySpinPoint0
            };
        }
        for (int i = 1; i < points1.length; i++) {
            double[] currentPoint10 = points1[i - 1];
            double[] nextPoint10 = points1[i];
            drawLine0(currentPoint10[0], currentPoint10[1], nextPoint10[0], nextPoint10[1], wL0, lineColorPicker0.getValue());
            double[] currentPoint20 = points2[i - 1];
            double[] nextPoint20 = points2[i];
            drawLine0(currentPoint20[0], currentPoint20[1], nextPoint20[0], nextPoint20[1], wL0, lineColorPicker0.getValue());
        }

        Polygon kShape = new Polygon();
        for (double[] point : points1) kShape.getPoints().addAll(point[0], point[1]);
        for (int i = points2.length - 1; i >= 0; i--)  kShape.getPoints().addAll(points2[i][0], points2[i][1]);
        kShape.setFill(fillColorPicker0.getValue());
        canvas0.getChildren().add(kShape);
    }
    private void changeColor0 () {
        for (Node node : canvas0.getChildren()) {
            if (node instanceof Polygon) {
                Polygon kShape = (Polygon) node;
                kShape.setFill(fillColorPicker0.getValue());
            }
            if (node instanceof Line) {
                Line line = (Line) node;
                line.setStroke(lineColorPicker0.getValue());
            }
        }
        pointSpin0.setFill(spinColorPicker0.getValue());
    }
    private void DraggedPoint0(MouseEvent event) {
        pointSpin0.setCenterX(event.getX());
        pointSpin0.setCenterY(event.getY());
        xSpinPoint0 = pointSpin0.getCenterX();
        ySpinPoint0 = pointSpin0.getCenterY();
    }
}