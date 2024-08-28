package com.example.code.objects;

import com.example.code.Helicopter;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

public class Obstacle extends Group {
    private Rectangle ob;
    public Obstacle(double width, double height,Translate position){
        ob = new Rectangle(width, height);
        Image img = new Image("file:///Users/majajovanovic/IdeaProjects/demo1/src/main/wood.jpg");
        ImagePattern imgp = new ImagePattern(img);
        ob.setFill(imgp);
        super.getTransforms().addAll(position);
        super.getChildren().addAll(ob);
    }

    public boolean ifObIsHit(Helicopter helicopter){
        return super.getBoundsInParent().intersects(helicopter.getHelicopterBounds());
    }


}
