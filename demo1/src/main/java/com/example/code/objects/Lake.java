package com.example.code.objects;

import com.example.code.Helicopter;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Lake extends Water{

    private Rectangle lake;
    public Lake(double width){
        lake = new Rectangle(width,150);
        Image img = new Image("file:///Users/majajovanovic/IdeaProjects/demo1/src/main/water.jpg");
        ImagePattern imgp = new ImagePattern(img);
        lake.setFill(imgp);
        super.getChildren().addAll(lake);
    }

    @Override
    public boolean isitAbove(Helicopter helicopter) {
        return super.getBoundsInParent().intersects(helicopter.getHelicopterBounds());
    }
}
