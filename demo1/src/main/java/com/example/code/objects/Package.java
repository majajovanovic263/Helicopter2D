package com.example.code.objects;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

public class Package extends Rectangle {
	
	public Package ( double width, double height, Translate position ) {
		super ( width, height );
		Image img = new Image("file:///Users/majajovanovic/IdeaProjects/demo1/src/bow.jpg");
		ImagePattern imgp = new ImagePattern(img);
		super.setFill(imgp);

		
		super.getTransforms ( ).addAll ( position );
	}
	
	public boolean handleCollision ( Bounds helicopterBounds ) {
		return super.getBoundsInParent ( ).intersects ( helicopterBounds );
	}
}
