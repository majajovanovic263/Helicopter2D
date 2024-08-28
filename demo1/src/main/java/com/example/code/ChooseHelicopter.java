package com.example.code;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

public class ChooseHelicopter extends Group {
    private Helicopter h1,h2,h3;
    public Rectangle r1,r2,r3, logo;

    public ChooseHelicopter(double width, double height, double hwidth, double hheight){
        this.h1 = new Helicopter(hwidth,hheight);
        this.h2 = new Helicopter(hwidth,hheight);
        this.h3 = new Helicopter(hwidth,hheight);
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

        this.h2.changeColorofHelicopter(Color.YELLOW,Color.GREEN);
        this.h3.changeColorofHelicopter(Color.RED,Color.ORANGE);
        this.h1.getTransforms().addAll(
                new Translate(width/6 + 5, width/2)
        );
        this.h2.getTransforms().addAll(
                new Translate(width/2,width/2)
        );
        this.h3.getTransforms().addAll(
                new Translate(5*width/6-5, width/2)
        );

        super.getChildren().addAll(r1,r2,r3,h1,h2,h3);

    }


}
