package net.groupfive.murderdesk;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.groupfive.murderdesk.gui.MurderDeskPanel;
import net.groupfive.murderdesk.gui.MurderDeskScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static Font ftCamcorder, ftMinecraftia, ftTitle1, ftTitle2, ftRegular, ftSmall;
	
	/**
	 * Start the game, either just the game in a single window, or as part of the full GUI.
	 * Comment or uncomment as needed. Note that executing both functions will throw a NullPointerException
	 */
	public static void main(String[] args) {
		
		//runAsSingleWindow();
		
		initGUI();
        
	}
	
	public static void runAsSingleWindow(){
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = MurderDesk.TITLE + " " + MurderDesk.VERSION;
		cfg.useGL20 = true;
		cfg.width = 640;
		cfg.height = 480;
		
		new LwjglApplication(new MurderDesk(), cfg);
	}
	
	/**
	 * Very long and messy coded function that sets up the GUI
	 */
	public static void initGUI(){
		// add fonts globally
		try {
			InputStream s1 = Main.class.getResourceAsStream("/fonts/CAMCORDER_REG.otf");
			InputStream s2 = Main.class.getResourceAsStream("/fonts/Minecraftia.ttf");
			ftCamcorder = Font.createFont(Font.TRUETYPE_FONT, s1);
			ftMinecraftia = Font.createFont(Font.TRUETYPE_FONT,  s2);
			ftTitle1 = ftCamcorder.deriveFont(Font.PLAIN, 24);
			ftTitle2 = ftCamcorder.deriveFont(Font.PLAIN, 16);
			ftRegular = ftMinecraftia.deriveFont(Font.PLAIN, 11);
			ftSmall = ftMinecraftia.deriveFont(Font.PLAIN, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// create 3 windows for the game
		final MurderDeskScreen screen1 = new MurderDeskScreen("Map");
		final MurderDeskScreen screen2 = new MurderDeskScreen("Active View");
		final MurderDeskScreen screen3 = new MurderDeskScreen("Main");
		
		initScreen1(screen1);
		initScreen2(screen2);
		initScreen3(screen3);
		
		// pack
		screen1.pack();
		screen2.pack();
		screen3.pack();
		
		// show
        screen1.setVisible(true);
        screen2.setVisible(true);
        screen3.setVisible(true);
	}
	
	private static void initScreen1(MurderDeskScreen s){
		
	}
	
	private static void initScreen2(MurderDeskScreen s){
		MurderDeskPanel pCamera = new MurderDeskPanel("Camera");
		MurderDeskPanel pSound = new MurderDeskPanel("Sound");
		MurderDeskPanel pTraps = new MurderDeskPanel("Traps");
		MurderDeskPanel pConsole = new MurderDeskPanel("Console");

		pCamera.setBounds(0, 0, 640, 400);
		pSound.setBounds(640, 0, 140, 400);
		pTraps.setBounds(0, 400, 300, 156);
		pConsole.setBounds(300, 400, 480, 156);
		
		/*
		 * Currently uses a seperate JFrame, instead of the JPanel in the second screen, but the behaviour is the same
		 */
		// unused configuration
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.useGL20 = true;
		cfg.vSyncEnabled = true;
		// create the frame
		JFrame test = new JFrame();
		// create a canvas
		LwjglAWTCanvas gameCanvas = new LwjglAWTCanvas(new MurderDesk(), true);
		gameCanvas.getCanvas().setSize(640, 400);
		test.setSize(800, 600);
		// add and set visible
		test.getContentPane().add(gameCanvas.getCanvas());
		test.setVisible(true);
		//pCamera.add(gameCanvas.getCanvas());

		s.content.add(pCamera);
		s.content.add(pSound);
		s.content.add(pTraps);
		s.content.add(pConsole);
	}

	private static void initScreen3(MurderDeskScreen s){
		MurderDeskPanel pSubject = new MurderDeskPanel("Subject");
		MurderDeskPanel pObjectives = new MurderDeskPanel("Objectives");
		MurderDeskPanel pBalance = new MurderDeskPanel("Balance");
		
		pSubject.setBounds(0, 0, 500, 556);
		pObjectives.setBounds(500, 0, 280, 400);
		pBalance.setBounds(500, 400, 280, 156);
		
		s.content.add(pSubject);
		s.content.add(pObjectives);
		s.content.add(pBalance);
	}
	
}