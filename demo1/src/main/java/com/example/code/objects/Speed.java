package com.example.code.objects;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

public class Speed extends Group {
    private Rectangle skala;
    private Circle loptica;
    private Translate positionloptice;
    public Speed(double height){
        this.skala = new Rectangle(10,height-20);
        this.skala.setFill(null);
        this.skala.setStroke(Color.BLACK);
        this.skala.setStrokeWidth(2);
        this.skala.getTransforms().addAll(
                new Translate(-5,-height/2+10)
        );
        this.loptica = new Circle(5);
        this.loptica.setFill(Color.YELLOW);
        this.positionloptice = new Translate();
        this.loptica.getTransforms().addAll(positionloptice);
        super.getChildren().addAll(skala,loptica);


    }


    public void pomeriLopticu(double speed, double pravac, double maxspeed, double height){
        double oldY = this.positionloptice.getY ( );
        double newY = oldY;
        if(pravac == 1){
            newY = -speed*(height/2-10)/maxspeed;
        }
        else if(pravac == -1) {
            newY = speed*(height/2-10)/maxspeed;
        }

        this.positionloptice.setY ( newY );
    }
}
