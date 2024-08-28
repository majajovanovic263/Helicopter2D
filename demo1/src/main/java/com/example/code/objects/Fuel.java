package com.example.code.objects;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Fuel extends Group {
    private Rectangle skala;
    private Arc luk;
    private Arc crveniluk;
    private Rotate rotationskale;

    private double capacity, stanje;
    public boolean empty;

    public Fuel(double width,double height){
        this.skala = new Rectangle(width/2,5);
        this.skala.setFill(Color.BLACK);
        this.rotationskale = new Rotate();
        this.skala.getTransforms().addAll(
                rotationskale

        );
        this.luk = new Arc(0,0,width/2,width/2,0,180);
        this.luk.setStrokeWidth(5);
        this.luk.setFill(null);
        this.luk.setStroke(Color.BLACK);
        this.crveniluk = new Arc(0,0,width/2,width/2,135,45);
        this.crveniluk.setStrokeWidth(5);
        this.crveniluk.setFill(null);
        this.crveniluk.setStroke(Color.RED);
        this.capacity = 200;
        this.stanje = this.capacity;
        this.empty = false;

        super.getChildren().addAll(luk,skala,crveniluk);

    }

    public void promeniStanje(double step){
        this.stanje = Math.max(0,this.stanje-step);
        this.stanje = Math.min(this.stanje, this.capacity);
        if(this.stanje == 0) this.empty = true;
    }

    public void rotirajSkalu(){
        double newAngle = this.stanje*180/capacity - 180;
        this.rotationskale.setAngle ( newAngle );
    }

    public void setCapacity(double c){
        this.capacity = c;
        this.stanje = c;
    }
}
