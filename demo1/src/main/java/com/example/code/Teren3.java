package com.example.code;

import com.example.code.objects.*;
import com.example.code.objects.Package;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Teren3 extends Teren {
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
    public Moat moat1,moat2;
    public Lake lake;

    public Teren3(Helicopter helicopter){
        super(helicopter);
        helicopter.position.setX(-20+ WINDOW_WIDTH/2 - HELIPAD_WIDTH/2);
        helicopter.position.setY(+WINDOW_HEIGHT/2 - 20 - HELIPAD_HEIGHT/2);

        Helipad helipadss[] = {new Helipad ( HELIPAD_WIDTH,HELIPAD_HEIGHT )};
        helipadss[0].getTransforms ( ).addAll (
                new Translate( WINDOW_WIDTH/2 - 20 - HELIPAD_WIDTH, WINDOW_HEIGHT/2 -20- HELIPAD_HEIGHT)
        );

        this.helipads = helipadss;
        this.teren = new Group();

        lake = new Lake(WINDOW_WIDTH);
        lake.getTransforms().addAll(
                new Translate(-WINDOW_WIDTH/2,-WINDOW_HEIGHT/2+50)
        );
        moat1 = new Moat();
        moat2 = new Moat();

        moat1.getTransforms().addAll(
                new Rotate(90),
                new Translate(+WINDOW_HEIGHT/2-200,+WINDOW_HEIGHT/2-200 )
        );
        moat2.getTransforms().addAll(
                new Translate(WINDOW_WIDTH/2-200,WINDOW_HEIGHT/2-200)
        );

        Translate package0Position = new Translate (
                +PACKAGE_WIDTH / 2 - WINDOW_WIDTH/2 + 61,
                -PACKAGE_HEIGHT / 2 - WINDOW_HEIGHT / 2+25
        );
        Translate package1Position = new Translate (
                +PACKAGE_WIDTH / 2 - WINDOW_WIDTH/2 + 142+71,
                -PACKAGE_HEIGHT / 2 - WINDOW_HEIGHT / 2+25
        );
        Translate package2Position = new Translate (
                +PACKAGE_WIDTH / 2 - WINDOW_WIDTH/2 + 142+142+10+71,
                -PACKAGE_HEIGHT / 2 - WINDOW_HEIGHT / 2+25
        );
        Translate package3Position = new Translate (
                +PACKAGE_WIDTH / 2 - WINDOW_WIDTH/2 + 142+142+10+142+10+71,
                -PACKAGE_HEIGHT / 2 - WINDOW_HEIGHT / 2+25
        );
        Translate package4Position = new Translate (
                +PACKAGE_WIDTH / 2 - WINDOW_WIDTH/2 + 142+142+10+142+10+142+10+71,
                -PACKAGE_HEIGHT / 2 - WINDOW_HEIGHT / 2+25
        );
        Translate package5Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+100,
                -PACKAGE_HEIGHT / 2 -120
        );
        Translate package6Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+125+250,
                -PACKAGE_HEIGHT / 2 -120
        );
        Translate package7Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+150+500,
                -PACKAGE_HEIGHT / 2 -120
        );
        Translate package8Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+100,
                -PACKAGE_HEIGHT / 2
        );
        Translate package9Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+125+250,
                -PACKAGE_HEIGHT / 2
        );
        Translate package10Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+150+500,
                -PACKAGE_HEIGHT / 2
        );
        Translate package11Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+100,
                -PACKAGE_HEIGHT / 2 + 120
        );
        Translate package12Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+125+250,
                -PACKAGE_HEIGHT / 2 +120
        );
        Translate package13Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+150+500,
                -PACKAGE_HEIGHT / 2 +120
        );
        Translate package14Position = new Translate (
                -PACKAGE_WIDTH / 2 - WINDOW_WIDTH/2 +60,
                PACKAGE_HEIGHT / 2 + WINDOW_HEIGHT / 2 -60
        );
        Translate package15Position = new Translate (
                -PACKAGE_WIDTH / 2 -WINDOW_WIDTH/2+125+250,
                -PACKAGE_HEIGHT / 2 + WINDOW_HEIGHT / 2 - 100
        );
        Package packagess[] = {
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package0Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package1Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package2Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package3Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package4Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package5Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package6Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package7Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package8Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package9Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package10Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package11Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package12Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package13Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package14Position ),
                new Package( PACKAGE_WIDTH, PACKAGE_HEIGHT, package15Position )};

        packages = packagess;

        fuel = new Fuel(HELIPAD_WIDTH,HELIPAD_HEIGHT) ;
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
        Translate obstacle0Position = new Translate (
                142-WINDOW_WIDTH/2,
                10- WINDOW_HEIGHT/2
        );
        Translate obstacle1Position = new Translate (
                -80,
                -65
        );
        Translate obstacle2Position = new Translate (
                WINDOW_WIDTH/2 -304,
                10- WINDOW_HEIGHT/2
        );
        Translate obstacle3Position = new Translate (
                20-WINDOW_WIDTH/2,
                -65
        );
        Translate obstacle4Position = new Translate (
                70 - WINDOW_WIDTH/2 + 2*HELIPAD_WIDTH,
                WINDOW_HEIGHT/2 - 180
        );
        Translate obstacle5Position = new Translate (
                WINDOW_WIDTH/2 - 70 - 2*HELIPAD_WIDTH,
                WINDOW_HEIGHT/2 - 180
        );
        Translate obstacle6Position = new Translate (
                WINDOW_WIDTH/2-180,
                -65
        );
        Translate obstacle7Position = new Translate (
                20-WINDOW_WIDTH/2,
                65
        );
        Translate obstacle8Position = new Translate (
                -80,
                65
        );
        Translate obstacle9Position = new Translate (
                WINDOW_WIDTH/2-180,
                65
        );
        Translate obstacle10Position = new Translate (
                -WINDOW_WIDTH/2+294,
                10- WINDOW_HEIGHT/2
        );
        Translate obstacle11Position = new Translate (
                WINDOW_WIDTH/2 -152,
                10-WINDOW_HEIGHT/2
        );
        Translate obstacle12Position = new Translate (
                20-WINDOW_WIDTH/2,
                WINDOW_HEIGHT/2-95
        );

        Obstacle obstacless[] = {
                new Obstacle (10,30,obstacle0Position ),
                new Obstacle ( 160,10,obstacle1Position ),
                new Obstacle ( 10,30,obstacle2Position ),
                new Obstacle ( 160,10,obstacle3Position ),
                new Obstacle (10,160, obstacle4Position ),
                new Obstacle ( 10,160,obstacle5Position ),
                new Obstacle(160,10,obstacle6Position),
                new Obstacle ( 160,10,obstacle7Position ),
                new Obstacle ( 160,10,obstacle8Position ),
                new Obstacle ( 160,10,obstacle9Position ),
                new Obstacle (10,30, obstacle10Position ),
                new Obstacle ( 10,30,obstacle11Position ),
                new Obstacle(80,10,obstacle12Position)};

        this.obstacles = obstacless;
        this.rotate = new Rotate();
        this.direction = new Point2D( 0, -1 );
        this.position  = new Translate ( );

        teren.getChildren().addAll(helipads);
        teren.getChildren().addAll(lake,moat1,moat2);
        teren.getChildren ( ).addAll ( packages );
        teren.getChildren().addAll(obstacles);


        teren.getTransforms ( ).addAll (
                this.position,
                this.rotate
        );



        time.getTransforms().addAll(
                new Translate(-WINDOW_WIDTH/2+20,WINDOW_HEIGHT/2-20)
        );

        super.getChildren().addAll(teren, fuel, speed, visina, time);

    }

    @Override
    public int isHelicopterLanded(Helicopter helicopter) {
        for(int i = 0;i<helipads.length;i++){
            if(helipads[i].ifHelicopterLanded(helicopter)) return i;
        }
        return -1;
    }

    @Override
    boolean isFuelEmptyAboveWater(Helicopter helicopter) {
        if(this.lake.isitAbove(helicopter)) return true;
        else if(this.moat1.isitAbove(helicopter)) return true;
        else if(this.moat2.isitAbove(helicopter)) return true;
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

        helicopter.speed *= speedDamp;

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
