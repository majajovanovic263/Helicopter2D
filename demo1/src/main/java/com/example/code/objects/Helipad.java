package com.example.code.objects;

import com.example.code.Helicopter;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Helipad extends Group {

	public Rectangle rect,line1,line2;
	private Circle circle;
	public double visited;
	public Helipad ( double width, double height ) {

		this.rect = new Rectangle(width,height);
		rect.setFill(Color.GRAY);
		rect.setStrokeWidth(5);
		rect.setStroke(Color.YELLOW);
		this.circle = new Circle(width/2);
		this.circle.setStrokeWidth(3);
		this.circle.setStroke(Color.WHITE);
		this.circle.setFill(null);
		this.circle.getTransforms().addAll(
				new Translate(width/2,height/2)
		);
		this.visited = 0;
		this.line1 = new Rectangle(Math.sqrt(width * width + height * height)-1, 3);
		this.line2 = new Rectangle(Math.sqrt(width * width + height * height)-1, 3);

		this.line1.setFill(Color.WHITE);
		this.line2.setFill(Color.WHITE);

		this.line1.getTransforms().addAll(
				new Rotate(45)
		);

		this.line2.getTransforms().addAll(
				new Translate(width,1),
				new Rotate(135)
		);


		super.getChildren().addAll(rect,circle,line1,line2);
	}

	public boolean ifHelicopterLanded(Helicopter helicopter){
		return super.getBoundsInParent().intersects(helicopter.getHelicopterBounds());
	}

	public void unmarkHelipad(){
		this.rect.setStroke(null);
		this.rect.setStrokeWidth(0);
	}

}
