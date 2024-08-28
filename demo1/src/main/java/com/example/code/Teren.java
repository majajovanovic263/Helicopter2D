package com.example.code;

import com.example.code.objects.*;
import com.example.code.objects.Package;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

abstract class Teren extends Group {

    public AnimationTimer timer;
    public Fuel fuel;
    public Speed speed;
    public Visina visina;
    public Label time;
    public Obstacle obstacles[];
    public Package packages[];
    public Helipad helipads[];
    public Translate position;
    public Point2D direction;
    public Rotate rotate;
    public Group teren;

    public Teren(Helicopter helicopter){ }
    abstract int isHelicopterLanded(Helicopter helicopter);

    abstract boolean isFuelEmptyAboveWater(Helicopter helicopter);
    abstract void update( double ds, double speedDamp, double left, double right, double up, double down, Helicopter helicopter);
    abstract void rotate ( double dAngle, double left, double right, double up, double down, Helicopter helicopter  );
}
