package com.example.code.objects;

import com.example.code.Helicopter;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Moat extends Water{

    private Rectangle r1,r2;

    public  Moat(){
        this.r1 = new Rectangle(80,200);
        this.r2 = new Rectangle(200, 80);
        Image img1 = new Image("file:///Users/majajovanovic/IdeaProjects/demo1/src/main/water.jpg");
        ImagePattern imgp1 = new ImagePattern(img1);
        r1.setFill(imgp1);
        Image img2 = new Image("file:///Users/majajovanovic/IdeaProjects/demo1/src/main/water.jpg");
        ImagePattern imgp2 = new ImagePattern(img2);
        r2.setFill(imgp2);
        r1.setFill(imgp1);
        r2.setFill(imgp2);
        super.getChildren().addAll(r1,r2);
    }
    @Override
    public boolean isitAbove(Helicopter helicopter) {
        return super.getBoundsInParent().intersects(helicopter.getHelicopterBounds());
    }
}
