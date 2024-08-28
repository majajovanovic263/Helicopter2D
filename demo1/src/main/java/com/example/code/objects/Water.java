package com.example.code.objects;

import com.example.code.Helicopter;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

abstract class Water extends Group {

    public Water(){}

    abstract boolean isitAbove(Helicopter helicopter);
}
