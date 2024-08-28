package com.example.code.objects;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

public class Visina extends Group {
    public double visina;
    public double minvisina;
    public double maxvisina;

    public Rectangle popunjeno;
    private Translate position;

    private Rectangle plavo;
    private double b;
    private double windowWidth, windowHeight;
    public Visina(double windowHeight, double windowWidth){
        maxvisina = windowHeight;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        visina = 0;
        minvisina = 0;
        plavo = new Rectangle(5, windowHeight-200);
        b = windowHeight-200;
        popunjeno = new Rectangle(5, 1);
        popunjeno.setFill(Color.BLUE);
        plavo.setFill(null);
        plavo.getTransforms().addAll(
                new Translate(-windowWidth/2+10,-windowHeight/2+200)
        );
        position = new Translate();
        popunjeno.getTransforms().addAll(
                position
        );

        super.getChildren().addAll(plavo, popunjeno);
    }

    public void povecajvisinu(){
        visina+=50;
    }

    public void smanjivisinu(){
        visina-=50;
    }

    public void changeVisinu(){
        position.setX(-windowWidth/2+10);
        if(visina<maxvisina || visina>minvisina){
            popunjeno.setHeight(visina*b/maxvisina);//visina:maxvisina = x:b
            position.setY(b/2-visina*b/maxvisina+20);
        }

    }


}