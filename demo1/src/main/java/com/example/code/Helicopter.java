package com.example.code;


import com.example.code.objects.Fuel;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Helicopter extends Group {
	
	private Point2D direction;
	private Rotate rotate;
	public Translate position;
	public double speed;
	public double pravac;
	private double fuelstep;
	private Ellipse cockpit;
	private Rectangle tail;

	private Rectangle e1,e2,e3;
	public Group elis;
	public boolean on;

	private Rectangle partTail;

	public double maxspeed;

	
	public Helicopter ( double width, double height) {
		this.cockpit = new Ellipse ( width/2 , width/2+7);
		Stop[] boje = new Stop[]{new Stop(0,Color.YELLOW), new Stop(1, Color.BLUE)};
		LinearGradient lg = new LinearGradient(0, 0,1,1, true, CycleMethod.NO_CYCLE,boje);
		this.cockpit.setFill (lg);
		this.cockpit.setStroke ( null );
		this.on = false;
		this.maxspeed = 300;
		this.fuelstep = 0.5;
		this.speed = 0;
		
		double tailWidth = 0.2 * width;
		double tailHeight = height - width / 2;
		double partTailWidth = tailHeight/2;
		double partTailHeight = tailWidth;
		this.partTail = new Rectangle(partTailWidth,partTailHeight);
		this.tail = new Rectangle ( tailWidth, tailHeight );
		this.tail.setFill ( Color.BLUE );
		this.partTail.setFill(Color.BLUE);
		this.tail.getTransforms ( ).addAll (
				new Translate ( -tailWidth / 2, 0 )
		);
		this.partTail.getTransforms().addAll(
				new Translate(-partTailWidth/2, tailHeight*2/3)
		);
		this.pravac = 0;

		this.e1 = new Rectangle(tailWidth, tailHeight/3*2);
		this.e2 = new Rectangle(tailWidth, tailHeight/3*2);
		this.e3 = new Rectangle(tailWidth, tailHeight/3*2);
		this.e1.setFill(Color.BLACK);
		this.e2.setFill(Color.BLACK);
		this.e3.setFill(Color.BLACK);

		e1.getTransforms().addAll(
				new Rotate(100),
				new Translate(-tailWidth/2,0)
		);

		e2.getTransforms().addAll(
				new Rotate(220),
				new Translate(-tailWidth/2,0)
		);

		e3.getTransforms().addAll(
				new Rotate(340),
				new Translate(-tailWidth/2,0)
		);

		this.elis = new Group();
		elis.getChildren().addAll(e1,e2,e3);



		
		super.getChildren ( ).addAll (  this.tail, this.partTail,this.cockpit, this.elis);
		
		this.direction = new Point2D ( 0, -1 );
		this.rotate    = new Rotate ( 0 );
		this.position  = new Translate ( );
		
		super.getTransforms ( ).addAll (
				this.position,
				this.rotate
		);
	}
	
	public boolean isWallHit (  double left, double right, double up, double down ) {
		Bounds cockpitBounds = this.cockpit.localToScene ( this.cockpit.getBoundsInLocal ( ) );
		Bounds tailBounds    = this.tail.localToScene ( this.tail.getBoundsInLocal ( ) );
		
		double cockpitMinX = cockpitBounds.getCenterX ( ) - this.cockpit.getRadiusX ( );
		double cockpitMaxX = cockpitBounds.getCenterX ( ) + this.cockpit.getRadiusX ( );
		double cockpitMinY = cockpitBounds.getCenterY ( ) - this.cockpit.getRadiusY ( );
		double cockpitMaxY = cockpitBounds.getCenterY ( ) + this.cockpit.getRadiusY ( );
		
		boolean cockpitWallHit = cockpitMinX <= left || cockpitMaxX >= right || cockpitMinY <= up || cockpitMaxY >= down;
		
		double tailMinX = tailBounds.getMinX ( );
		double tailMaxX = tailBounds.getMaxX ( );
		double tailMinY = tailBounds.getMinY ( );
		double tailMaxY = tailBounds.getMaxY ( );
		
		boolean tailWallHit = tailMinX <= left || tailMaxX >= right || tailMinY <= up || tailMaxY >= down;
		
		
		return cockpitWallHit || tailWallHit;
	}
	
	public void rotate ( double dAngle, double left, double right, double up, double down  ) {
		double oldAngle = this.rotate.getAngle ( );
		
		double newAngle = oldAngle + dAngle;
		this.rotate.setAngle ( newAngle );
		
		if ( this.isWallHit ( left, right, up, down ) ) {
			this.rotate.setAngle ( oldAngle );
		} else {
			double magnitude = this.direction.magnitude ( );
			this.direction = new Point2D (
					magnitude * Math.sin ( Math.toRadians ( newAngle ) ),
					-magnitude * Math.cos ( Math.toRadians ( newAngle ) )
			);
		}
	}
	
	public void changeSpeed ( double dSpeed, Fuel fuel) {
		this.speed = Math.max ( this.speed + dSpeed, 0 );
		this.speed = Math.min (this.speed, this.maxspeed);
		fuel.promeniStanje(this.fuelstep);
		if(this.speed == 0) this.pravac = 0;
		if(fuel.empty) {
			this.speed = 0;
			this.pravac = 0;
		}
	}
	
	public void update ( double ds, double speedDamp, double left, double right, double up, double down, Fuel fuel ) {
		double oldX = this.position.getX ( );
		double oldY = this.position.getY ( );

		double newX = oldX, newY = oldY;
		if(this.pravac == 1){
			newX = oldX + ds * this.speed * this.direction.getX ( );
			newY = oldY + ds * this.speed * this.direction.getY ( );
		}
		else if(this.pravac == -1) {
			newX = oldX - ds * this.speed * this.direction.getX();
			newY = oldY - ds * this.speed * this.direction.getY();
		}
		this.position.setX ( newX );
		this.position.setY ( newY );
		
		if ( this.isWallHit ( left, right, up, down ) ||  fuel.empty == true || this.on == false) {
			this.speed = 0;
			this.position.setX ( oldX );
			this.position.setY ( oldY );
		} else {
			this.speed *= speedDamp;
		}
		if(this.on == true) {
			this.elis.getTransforms().addAll(
					new Rotate(3)
			);
		}
	}
	public Bounds getHelicopterBounds(){
		return super.getBoundsInParent();
	}
	public void changeColorofHelicopter(Color color1, Color color2){
		Stop[] boje = new Stop[]{new Stop(0,color1), new Stop(1, color2)};
		LinearGradient lg = new LinearGradient(0, 0,1,1, true, CycleMethod.NO_CYCLE,boje);
		this.cockpit.setFill (lg);
		this.cockpit.setStroke ( null );
		this.tail.setFill (color2);
		this.partTail.setFill(color2);
	}

	public void setMaxSpeed(double max){
		this.maxspeed = max;
	}
	public void setFuelStep(double s){
		this.fuelstep = s;
	}

	public boolean handleCollision(Bounds boundsInParent) {
		return super.getBoundsInParent().intersects(boundsInParent);
	}
}
