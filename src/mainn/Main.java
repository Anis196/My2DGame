package mainn;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Adventure");
		
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel);
		
		window.pack();
		
		window.setLocationRelativeTo(window);
		window.setVisible(true);
		
		gamepanel.startGameThread();
		
	}
}
