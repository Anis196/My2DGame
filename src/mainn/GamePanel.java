package mainn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	//FPS
	int FPS = 60;

	keyHandler keyH = new keyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	
	// Set Players default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
//	public void run() {
//		//has game-loop
//		double drawInterval = 1000000000/FPS; // 0.01666 seconds screen is drawn
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//			
////			long currentTime = System.nanoTime(); // this is more precise
////			System.out.println("Current time is :"+currentTime);
////			long currentTime2 = System.currentTimeMillis()
//;			// 1 UPDATE: update information such as character positions
//			update();
//			//there was no interval between each update and repaint so we now assign somw interval 
//			//even during the short key touch the update() gets called so many times and add 4 speed to playerY each time
//			
//			//2 DRAW: draw the screen with the input updated information
//			repaint();
//						
//			try {
//				
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//
//				Thread.sleep((long)remainingTime);
//				
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				
//				e.printStackTrace();
//			}
//		}
//	}
//	

	// 2nd METHOD(MORE PRECISE AS THE thread.sleep() method creates a delay of some milli-seconds SO
	// ****************WE WILL USE DELTA METHOD*************************
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000 ) {
				System.out.println("FPS:"+drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	private void update() {
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); // used to color and fill the graphic
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
	}
}
