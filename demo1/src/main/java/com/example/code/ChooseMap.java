package com.example.code;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

public class ChooseMap extends Group {
    public Rectangle r1,r2,r3;

    public ChooseMap(double width, double height){

        this.r1 = new Rectangle(width/3-10,width/3-10);
        this.r2 = new Rectangle(width/3-10,width/3-10);
        this.r3 = new Rectangle(width/3-10,width/3-10);
        this.r1.setFill(Color.WHITE);
        this.r2.setFill(Color.WHITE);
        this.r3.setFill(Color.WHITE);
        this.r1.getTransforms().addAll(
                new Translate(10, width/3 + 5)
        );
        this.r2.getTransforms().addAll(
                new Translate(width/3 + 5   ,width/3 +5)
        );
        this.r3.getTransforms().addAll(
                new Translate(2*width/3, width/3+5)
        );

        this.r1.setStrokeWidth(5);
        this.r2.setStrokeWidth(5);
        this.r3.setStrokeWidth(5);
        Label l1 = new Label("EASY");
        Label l2 = new Label("MEDIUM");
        Label l3 = new Label("HARD");

        l1.setTextFill(Color.BLACK);
        l2.setTextFill(Color.BLACK);
        l3.setTextFill(Color.BLACK);

        l1.getTransforms().addAll(
                new Translate(width/6-10, width/2)
        );
        l2.getTransforms().addAll(
                new Translate(width/2 - 20,width/2)
        );
        l3.getTransforms().addAll(
                new Translate(5*width/6-20, width/2)
        );

        super.getChildren().addAll(r1,r2,r3,l1,l2,l3);

    }

}
