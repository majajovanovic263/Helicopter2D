package com.example.code;

import com.example.code.objects.*;
import com.example.code.objects.Package;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
	private static final double WINDOW_WIDTH  = 750;
	private static final double WINDOW_HEIGHT = 750;
	private int space = 0;
	
	private static final double HELICOPTER_WIDTH          = 0.03 * Main.WINDOW_WIDTH;
	private static final double HELICOPTER_HEIGHT         = 0.07 * Main.WINDOW_HEIGHT;
	private static final double HELICOPTER_SPEED_STEP     = 10;
	private static final double HELICOPTER_DIRECTION_STEP = 5;

	private static final double FUEL_CAPACITY = 100;
	private static final double HELICOPTER_DAMP           = 0.995;
	private double r;
	
	private static final double HELIPAD_WIDTH  = 0.1 * Main.WINDOW_WIDTH;
	private static final double HELIPAD_HEIGHT = 0.1 * Main.WINDOW_HEIGHT;
	
	private static final double PACKAGE_WIDTH  = 0.02 * Main.WINDOW_WIDTH;
	private static final double PACKAGE_HEIGHT = 0.02 * Main.WINDOW_HEIGHT;

	private static final double MAX_SPEED = 50;
	private double choosenhelicopter, choosenMap;

	private Teren t;
	private Group root;
	private Scene scene;
	
	@Override
	public void start ( Stage stage ) throws IOException {
		r = 1;
		root = new Group();

		ChooseHelicopter cp = new ChooseHelicopter(WINDOW_WIDTH,WINDOW_HEIGHT,HELICOPTER_WIDTH,HELICOPTER_HEIGHT);

		Scene chooseHelicopter = new Scene(cp,WINDOW_WIDTH,WINDOW_HEIGHT);
		chooseHelicopter.setFill(Color.BLACK);


		ChooseMap cm = new ChooseMap(WINDOW_WIDTH,WINDOW_HEIGHT);
		Scene chooseMap = new Scene(cm,WINDOW_WIDTH,WINDOW_HEIGHT);
		chooseMap.setFill(Color.BLACK);

		choosenhelicopter = 1;
		choosenMap = 1;
		stage.setScene(chooseHelicopter);
		cp.r1.setStroke(Color.RED);
		cm.r1.setStroke(Color.RED);

		chooseHelicopter.addEventHandler ( KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode().equals(KeyCode.SPACE)){
				stage.setScene (chooseMap);

			} else if (event.getCode().equals(KeyCode.LEFT)) {
				if(choosenhelicopter == 1) {
					choosenhelicopter = 3;
					cp.r3.setStroke(Color.RED);
					cp.r1.setStroke(null);
				}
				else if(choosenhelicopter == 2){
					choosenhelicopter = 1;
					cp.r1.setStroke(Color.RED);
					cp.r2.setStroke(null);
				}
				else if(choosenhelicopter == 3){
					choosenhelicopter = 2;
					cp.r2.setStroke(Color.RED);
					cp.r3.setStroke(null);
				}
			}
			else if(event.getCode().equals(KeyCode.RIGHT)){
				if(choosenhelicopter == 1) {
					choosenhelicopter = 2;
					cp.r2.setStroke(Color.RED);
					cp.r1.setStroke(null);
				}
				else if(choosenhelicopter == 2){
					choosenhelicopter = 3;
					cp.r3.setStroke(Color.RED);
					cp.r2.setStroke(null);
				}
				else if(choosenhelicopter == 3){
					choosenhelicopter = 1;
					cp.r1.setStroke(Color.RED);
					cp.r3.setStroke(null);
				}
			}

		});
		Helicopter helicopter = new Helicopter(HELICOPTER_WIDTH,HELICOPTER_HEIGHT);

		chooseMap.addEventHandler ( KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode().equals(KeyCode.SPACE)){

				if(choosenMap == 1){
					t = new Teren1(helicopter);
				}
				else if(choosenMap == 2){
					t = new Teren2(helicopter);
				}
				else{
					t = new Teren3(helicopter);
				}
				if(choosenhelicopter == 2){
					helicopter.changeColorofHelicopter(Color.YELLOW,Color.GREEN);
					helicopter.setMaxSpeed(400);
					t.fuel.setCapacity(600);
					helicopter.setFuelStep(1);
				}
				else if(choosenhelicopter == 3){
					helicopter.changeColorofHelicopter(Color.RED,Color.ORANGE);
					helicopter.setMaxSpeed(500);
					t.fuel.setCapacity(800);
					helicopter.setFuelStep(1.5);
				}


				root.getChildren().addAll(t, helicopter);
				root.getTransforms ( ).addAll (
						new Translate ( Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT / 2 )
				);
				scene = new Scene(root,Main.WINDOW_WIDTH,Main.WINDOW_HEIGHT);
				scene.addEventHandler ( KeyEvent.KEY_PRESSED, event1 -> {
					if(!t.fuel.empty) {
						if(event1.getCode().equals(KeyCode.Q)){
							r = 1;
						}
						else if(event1.getCode().equals(KeyCode.W)){
							r = 2;
						}
						if (event1.getCode().equals(KeyCode.SPACE)){
							if(helicopter.on == true) {
								helicopter.on = false;
								space = 0;
								if(t.isHelicopterLanded(helicopter) >= 0 && t.helipads[t.isHelicopterLanded(helicopter)].visited == 0){
									t.fuel.promeniStanje(-FUEL_CAPACITY);
									t.helipads[t.isHelicopterLanded(helicopter)].unmarkHelipad();
									t.helipads[t.isHelicopterLanded(helicopter)].visited = 1;
								}
							}
							else {
								helicopter.on = true;
								space = 1;
							}
						}
						if(helicopter.on == true) {
							if(r==1) {
								if (event1.getCode().equals(KeyCode.UP)) {
									if (helicopter.pravac == 1)
										helicopter.changeSpeed(Main.HELICOPTER_SPEED_STEP, t.fuel);
									else if (helicopter.pravac == 0) {
										helicopter.changeSpeed(Main.HELICOPTER_SPEED_STEP, t.fuel);
										helicopter.pravac = 1;
									} else if (helicopter.pravac == -1)
										helicopter.changeSpeed(-Main.HELICOPTER_SPEED_STEP, t.fuel);
								} else if (event1.getCode().equals(KeyCode.DOWN)) {
									if (helicopter.pravac == 1)
										helicopter.changeSpeed(-Main.HELICOPTER_SPEED_STEP, t.fuel);
									else if (helicopter.pravac == 0) {
										helicopter.changeSpeed(Main.HELICOPTER_SPEED_STEP, t.fuel);
										helicopter.pravac = -1;
									} else if (helicopter.pravac == -1)
										helicopter.changeSpeed(Main.HELICOPTER_SPEED_STEP, t.fuel);
								}

								if (event1.getCode().equals(KeyCode.LEFT)) {
									helicopter.rotate(
											-Main.HELICOPTER_DIRECTION_STEP,
											0,
											Main.WINDOW_WIDTH,
											0,
											Main.WINDOW_HEIGHT
									);
								} else if (event1.getCode().equals(KeyCode.RIGHT)) {
									helicopter.rotate(
											Main.HELICOPTER_DIRECTION_STEP,
											0,
											Main.WINDOW_WIDTH,
											0,
											Main.WINDOW_HEIGHT
									);
								}
							} else if (r == 2) {
								if (event1.getCode().equals(KeyCode.UP)) {
									if (helicopter.pravac == 1)
										helicopter.changeSpeed(Main.HELICOPTER_SPEED_STEP, t.fuel);
									else if (helicopter.pravac == 0) {
										helicopter.changeSpeed(Main.HELICOPTER_SPEED_STEP, t.fuel);
										helicopter.pravac = 1;
									} else if (helicopter.pravac == -1)
										helicopter.changeSpeed(-Main.HELICOPTER_SPEED_STEP, t.fuel);
								} else if (event1.getCode().equals(KeyCode.DOWN)) {
									if (helicopter.pravac == 1)
										helicopter.changeSpeed(-Main.HELICOPTER_SPEED_STEP, t.fuel);
									else if (helicopter.pravac == 0) {
										helicopter.changeSpeed(Main.HELICOPTER_SPEED_STEP, t.fuel);
										helicopter.pravac = -1;
									} else if (helicopter.pravac == -1)
										helicopter.changeSpeed(Main.HELICOPTER_SPEED_STEP, t.fuel);
								}

								if (event1.getCode().equals(KeyCode.LEFT)) {
									t.rotate(
											-Main.HELICOPTER_DIRECTION_STEP,
											0,
											Main.WINDOW_WIDTH,
											0,
											Main.WINDOW_HEIGHT,
											helicopter
									);
								} else if (event1.getCode().equals(KeyCode.RIGHT)) {
									t.rotate(
											Main.HELICOPTER_DIRECTION_STEP,
											0,
											Main.WINDOW_WIDTH,
											0,
											Main.WINDOW_HEIGHT,
											helicopter
									);
								}

							}
						}
					}
					else{
						helicopter.on = false;
						helicopter.pravac = 0;
					}
				} );

				MyTimer.IUpdatable helicopterWrapper = ds -> {
					if(r == 1){
						helicopter.update (
							ds,
							Main.HELICOPTER_DAMP,
							0,
							Main.WINDOW_WIDTH,
							0,
							Main.WINDOW_HEIGHT,
							t.fuel
						);
						for ( int i = 0; i < t.packages.length; ++i ) {
							if ( t.packages[i] != null && t.packages[i].handleCollision ( helicopter.getBoundsInParent ( ) ) ) {
								t.teren.getChildren ( ).remove ( t.packages[i] );

								t.packages[i] = null;
							}
						}
					} else if (r == 2) {
						t.update(
								ds,
								Main.HELICOPTER_DAMP,
								0,
								Main.WINDOW_WIDTH,
								0,
								Main.WINDOW_HEIGHT,
								helicopter
						);
						for ( int i = 0; i < t.packages.length; ++i ) {
							if ( t.packages[i] != null && helicopter.handleCollision ( t.packages[i].getBoundsInParent ( ) ) ) {
								t.teren.getChildren ( ).remove ( t.packages[i] );

								t.packages[i] = null;
							}
						}
					}

					t.speed.pomeriLopticu(helicopter.speed,helicopter.pravac,helicopter.maxspeed,WINDOW_HEIGHT);
					t.fuel.rotirajSkalu();



					double p  = 0;
					for(int i = 0;i<t.packages.length;i++){
						if(t.packages[i] != null) p = 1;
					}
					if(p == 0){
						Group won = new Group();
						Rectangle wonmessage = new Rectangle(400,200);
						Image imgwon = new Image("file:///Users/majajovanovic/IdeaProjects/demo1/src/main/winner.jpg");
						ImagePattern imglp = new ImagePattern(imgwon);
						wonmessage.setFill(imglp);
						wonmessage.getTransforms().addAll(
								new Translate(WINDOW_WIDTH/2-200,WINDOW_HEIGHT/2-100)
						);
						won.getChildren().addAll(wonmessage);
						Scene w= new Scene(won,WINDOW_WIDTH,WINDOW_HEIGHT);
						w.setFill(Color.BLACK);
						stage.setScene(w);
					}

					if(space == 0){
						if(t.visina.visina>t.visina.minvisina){
							t.visina.smanjivisinu();
							t.visina.changeVisinu();
						}
					}
					else{
						if(t.visina.visina<t.visina.maxvisina){
							t.visina.povecajvisinu();
							t.visina.changeVisinu();
						}
					}
					for ( int i = 0; i < t.obstacles.length; ++i ) {
						if ( t.obstacles[i] != null && t.obstacles[i].ifObIsHit(helicopter) ) {
							helicopter.speed = 0;
						}
					}

					if(t.fuel.empty && t.isFuelEmptyAboveWater(helicopter)) root.getChildren().remove(helicopter);

					if(t.fuel.empty){
						Group lost = new Group();
						Rectangle lostmessage = new Rectangle(400,200);
						Image imglost = new Image("file:///Users/majajovanovic/IdeaProjects/demo1/src/main/gameover.jpg");
						ImagePattern imglp = new ImagePattern(imglost);
						lostmessage.setFill(imglp);
						lostmessage.getTransforms().addAll(
								new Translate(WINDOW_WIDTH/2-200,WINDOW_HEIGHT/2-100)
						);
						lost.getChildren().addAll(lostmessage);
						Scene l= new Scene(lost,WINDOW_WIDTH,WINDOW_HEIGHT);
						l.setFill(Color.BLACK);
						stage.setScene(l);

					}

					t.timer.start ();

				};

				MyTimer myTimer = new MyTimer ( helicopterWrapper );
				myTimer.start ( );
				Image trava = new Image("file:///Users/majajovanovic/IdeaProjects/demo1/src/grass.jpg");
				ImagePattern travapattern = new ImagePattern(trava);
				scene.setFill (travapattern);
				stage.setTitle ( "Helicopter" );
				stage.setScene (scene);

			} else if (event.getCode().equals(KeyCode.LEFT)) {
				if(choosenMap == 1) {
					choosenMap = 3;
					cm.r3.setStroke(Color.RED);
					cm.r1.setStroke(null);
				}
				else if(choosenMap == 2){
					choosenMap = 1;
					cm.r1.setStroke(Color.RED);
					cm.r2.setStroke(null);
				}
				else if(choosenMap == 3){
					choosenMap = 2;
					cm.r2.setStroke(Color.RED);
					cm.r3.setStroke(null);
				}

			}
			else if(event.getCode().equals(KeyCode.RIGHT)){
				if(choosenMap == 1) {
					choosenMap = 2;
					cm.r2.setStroke(Color.RED);
					cm.r1.setStroke(null);
				}
				else if(choosenMap == 2){
					choosenMap = 3;
					cm.r3.setStroke(Color.RED);
					cm.r2.setStroke(null);
				}
				else if(choosenMap == 3){
					choosenMap = 1;
					cm.r1.setStroke(Color.RED);
					cm.r3.setStroke(null);
				}
			}

		});
		stage.setResizable ( false );
		stage.show ( );
	}
	
	public static void main ( String[] args ) {
		launch ( );
	}
}