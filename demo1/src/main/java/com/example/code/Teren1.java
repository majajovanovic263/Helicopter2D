package com.example.code;

import com.example.code.objects.*;
import com.example.code.objects.Package;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Teren1 extends Teren {
    private static final double WINDOW_WIDTH  = 750;
    private static final double WINDOW_HEIGHT = 750;
    private int space = 0;

    private static final double HELICOPTER_WIDTH          = 0.03 * WINDOW_WIDTH;
    private static final double HELICOPTER_HEIGHT         = 0.07 * WINDOW_HEIGHT;
    private static final double HELICOPTER_SPEED_STEP     = 5;
    private static final double HELICOPTER_DIRECTION_STEP = 5;
    private static final double HELICOPTER_FUEL_STEP = 0.5;
    private static final double FUEL_CAPACITY = 100;
    private static final double HELICOPTER_DAMP           = 0.995;


    private static final double HELIPAD_WIDTH  = 0.1 * WINDOW_WIDTH;
    private static final double HELIPAD_HEIGHT = 0.1 * WINDOW_HEIGHT;

    private static final double PACKAGE_WIDTH  = 0.02 * WINDOW_WIDTH;
    private static final double PACKAGE_HEIGHT = 0.02 * WINDOW_HEIGHT;

    private static final double MAX_SPEED = 50;

    public Lake lake;

    public Teren1(Helicopter helicopter){
        super(helicopter);

        teren = new Group();

        Helipad helipadss[] = {
                new Helipad(HELIPAD_WIDTH,HELIPAD_HEIGHT),
                new Helipad(HELIPAD_WIDTH,HELIPAD_HEIGHT),
                new Helipad(HELIPAD_WIDTH,HELIPAD_HEIGHT),
                new Helipad(HELIPAD_WIDTH,HELIPAD_HEIGHT)
        };
        helipadss[0].getTransforms ( ).addAll (
                new Translate( 20 - WINDOW_WIDTH/2, 20- WINDOW_HEIGHT/2)
        );
        helipadss[1].getTransforms ( ).addAll (
                new Translate( 20- WINDOW_WIDTH/2, WINDOW_HEIGHT/2 - 20-HELIPAD_HEIGHT)
        );
        helipadss[2].getTransforms ( ).addAll (
                new Translate( + WINDOW_WIDTH/2 - 20 - HELIPAD_WIDTH,   - WINDOW_HEIGHT/2 + 20 )
        );
        helipadss[3].getTransforms ( ).addAll (
                new Translate( WINDOW_WIDTH/2 - 20 - HELIPAD_WIDTH, WINDOW_HEIGHT/2 -20- HELIPAD_HEIGHT)
        );

        this.helipads = helipadss;

        helicopter.position.setX(20- WINDOW_WIDTH/2 + HELIPAD_WIDTH/2);
        helicopter.position.setY(WINDOW_HEIGHT/2 - 20-HELIPAD_HEIGHT + HELIPAD_HEIGHT/2);

        lake = new Lake(WINDOW_WIDTH);
        lake.getTransforms().addAll(
                new Translate(-WINDOW_WIDTH/2,-75)
        );

        Translate package0Position = new Translate (
                -PACKAGE_WIDTH / 2,
                -PACKAGE_HEIGHT / 2 + 20 - WINDOW_HEIGHT/2 + HELIPAD_HEIGHT/2
        );
        Translate package1Position = new Translate (
                -PACKAGE_WIDTH / 2 + 20 - WINDOW_WIDTH/2 + HELIPAD_WIDTH/2,
                -PACKAGE_HEIGHT / 2 + 20- WINDOW_HEIGHT/2 + 2*HELIPAD_HEIGHT
        );
        Translate package2Position = new Translate (
                -PACKAGE_WIDTH / 2 - 20 + WINDOW_WIDTH/2 -HELIPAD_WIDTH/2,
                -PACKAGE_HEIGHT / 2 + 20- WINDOW_HEIGHT/2 + 2*HELIPAD_HEIGHT
        );
        Translate package3Position = new Translate (
                PACKAGE_WIDTH / 2 + 20 - WINDOW_WIDTH/2 + HELIPAD_WIDTH/2,
                PACKAGE_HEIGHT / 2 - 20 + WINDOW_HEIGHT/2 - 2*HELIPAD_HEIGHT
        );
        Translate package4Position = new Translate (
                -PACKAGE_WIDTH / 2 - 20 + WINDOW_WIDTH/2 -HELIPAD_WIDTH/2,
                PACKAGE_HEIGHT / 2 - 20 + WINDOW_HEIGHT/2 - 2*HELIPAD_HEIGHT
        );
        Translate package5Position = new Translate (
                -PACKAGE_WIDTH / 2,
                PACKAGE_HEIGHT / 2 - 20 + WINDOW_HEIGHT/2 - HELIPAD_HEIGHT/2
        );
        Package packagess[] = {
                new Package ( PACKAGE_WIDTH, PACKAGE_HEIGHT, package0Position ),
                new Package ( PACKAGE_WIDTH, PACKAGE_HEIGHT, package1Position ),
                new Package ( PACKAGE_WIDTH, PACKAGE_HEIGHT, package2Position ),
                new Package ( PACKAGE_WIDTH, PACKAGE_HEIGHT, package3Position ),
                new Package ( PACKAGE_WIDTH, PACKAGE_HEIGHT, package4Position ),
                new Package ( PACKAGE_WIDTH, PACKAGE_HEIGHT, package5Position )};

        packages = packagess;

        fuel = new Fuel(HELIPAD_WIDTH,HELIPAD_HEIGHT);
        fuel.getTransforms().addAll(
                new Translate(-WINDOW_WIDTH/2+HELIPAD_WIDTH/2+20,-WINDOW_HEIGHT/2+HELIPAD_HEIGHT/2+20)
        );

        speed = new Speed(WINDOW_HEIGHT);
        speed.getTransforms().addAll(
                new Translate(WINDOW_WIDTH/2-15, 0)
        );

        visina = new Visina(WINDOW_HEIGHT, WINDOW_WIDTH);
        time = new Label();
        timer = new AnimationTimer() {
            int startTimesec = 0;
            int startTimeMin = 0;

            @Override
            public void handle(long l) {
                startTimesec++;
                if(startTimesec==60){
                    startTimesec = 0;
                    startTimeMin++;
                }
                time.setText(startTimeMin +":"+ startTimesec);
            }
        };

        time.getTransforms().addAll(
                new Translate(-WINDOW_WIDTH/2+20,WINDOW_HEIGHT/2-20)
        );

        Translate obstacle0Position = new Translate (
                40 - WINDOW_WIDTH/2 + 2*HELIPAD_WIDTH,
                20- WINDOW_HEIGHT/2
        );
        Translate obstacle1Position = new Translate (
                -50,
                -PACKAGE_HEIGHT / 2 + 20- WINDOW_HEIGHT/2 + 2*HELIPAD_HEIGHT
        );
        Translate obstacle2Position = new Translate (
                WINDOW_WIDTH/2 - 40 - 2*HELIPAD_WIDTH,
                20- WINDOW_HEIGHT/2
        );
        Translate obstacle3Position = new Translate (
                -50,
                PACKAGE_HEIGHT / 2 - 20 + WINDOW_HEIGHT/2 - 2*HELIPAD_HEIGHT
        );
        Translate obstacle4Position = new Translate (
                40 - WINDOW_WIDTH/2 + 2*HELIPAD_WIDTH,
                -20 +WINDOW_HEIGHT/2 - 100
        );
        Translate obstacle5Position = new Translate (
                WINDOW_WIDTH/2 - 40 - 2*HELIPAD_WIDTH,
                -20 +WINDOW_HEIGHT/2 - 100
        );

        Obstacle obstacless[] = {
                new Obstacle (10,100,obstacle0Position ),
                new Obstacle ( 100,10,obstacle1Position ),
                new Obstacle ( 10,100,obstacle2Position ),
                new Obstacle ( 100,10,obstacle3Position ),
                new Obstacle (10,100, obstacle4Position ),
                new Obstacle ( 10,100,obstacle5Position )};

        this.obstacles = obstacless;
        this.rotate    = new Rotate (  );
        this.direction = new Point2D( 0, -1 );
        this.position  = new Translate ( );

        teren.getChildren().addAll(helipads);
        teren.getChildren().addAll(lake);
        teren.getChildren ( ).addAll ( packages );
        teren.getChildren().addAll(obstacles);


        teren.getTransforms ( ).addAll (
                this.position,
                this.rotate
        );

        super.getChildren().addAll(teren, fuel, speed, visina, time);

    }

    @Override
    public int isHelicopterLanded(Helicopter helicopter){
        for(int i = 0;i<helipads.length;i++){
            if(helipads[i].ifHelicopterLanded(helicopter)) return i;
        }
        return -1;
    }

    @Override
    public boolean isFuelEmptyAboveWater(Helicopter helicopter) {
        if(this.lake.isitAbove(helicopter)) return true;
        return false;
    }

    @Override
    void update(double ds, double speedDamp, double left, double right, double up, double down, Helicopter helicopter) {
        double oldX = this.position.getX ( );
        double oldY = this.position.getY ( );

        double newX = oldX, newY = oldY;
        if(helicopter.pravac == 1){
            newX = oldX - ds * helicopter.speed * this.direction.getX ( );
            newY = oldY - ds * helicopter.speed * this.direction.getY ( );
        }
        else if(helicopter.pravac == -1) {
            newX = oldX + ds * helicopter.speed * this.direction.getX();
            newY = oldY + ds * helicopter.speed * this.direction.getY();
        }
        this.position.setX ( newX );
        this.position.setY ( newY );

        if ( fuel.empty == true || helicopter.on == false) {
            helicopter.speed = 0;
            this.position.setX ( oldX );
            this.position.setY ( oldY );
        } else {
            helicopter.speed *= speedDamp;
        }

        if(helicopter.on == true) {
            helicopter.elis.getTransforms().addAll(
                    new Rotate(3)
            );
        }
    }

    @Override
    void rotate(double dAngle, double left, double right, double up, double down, Helicopter helicopter) {
        double oldAngle = this.rotate.getAngle ( );

        double newAngle = oldAngle - dAngle;
        this.rotate.setAngle ( newAngle );

        double magnitude = this.direction.magnitude ( );
        this.direction = new Point2D (
                magnitude * Math.sin ( Math.toRadians ( newAngle ) ),
                -magnitude * Math.cos ( Math.toRadians ( newAngle ) )
        );
    }


}
