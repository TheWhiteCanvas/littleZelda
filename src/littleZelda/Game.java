package littleZelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener { //implements Runnable and KeyListener for the controllers, add implements from Game.

	public static int WIDTH=1920, HEIGHT=1080; //Criando WIDTH e HEIGHT aqui, pode-se acessa-los depois de "qualquer lugar";
	
	public Player player; //referencing and accessing to the Player class.
	public World world; //referencing and accessing World Class.
	public List<Enemy> enemies = new ArrayList<Enemy>();
	public List<EliteGuardian> eliteguardians = new ArrayList<EliteGuardian>();
	public Boss boss;
	
	public int framesarrows = 0;
	
	public boolean soldiersAreDead = false;
	
	public Game()
	{
		this.addKeyListener(this); //to access keyboard methods from this same class;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		new Spritesheet();
		new Sprites();
		
		player = new Player(72, 72); //instancing new player; 
		world = new World(); //instancing new World.
		
		enemies.add(new Enemy(328, 528));
		enemies.add(new Enemy(700, 600));
		enemies.add(new Enemy(1300, 900));
		enemies.add(new Enemy(1670, 600));
		enemies.add(new Enemy(1530, 100));
		enemies.add(new Enemy(1450, 625));
		enemies.add(new Enemy(1180, 150));
		enemies.add(new Enemy(1720, 435));
		enemies.add(new Enemy(1360, 220));
		enemies.add(new Enemy(1490, 390));
		
		eliteguardians.add(new EliteGuardian( 525, 300));
		eliteguardians.add(new EliteGuardian( 600, 300));
		eliteguardians.add(new EliteGuardian( 675, 300));
		eliteguardians.add(new EliteGuardian( 750, 300));
		eliteguardians.add(new EliteGuardian( 825, 300));
		eliteguardians.add(new EliteGuardian( 900, 300));
		
		boss = new Boss(715, 200);
	}
	
	public void tick() //método presente em todo jogo, checa calculos todo frame;
	{
		player.tick(); //calling method tick from the Player class;
		
		for(int i=0; i < enemies.size(); i++)
		{
			enemies.get(i).tick();
		}
		
		for(int i=0; i < eliteguardians.size(); i++)
		{
			eliteguardians.get(i).tick();
			
			if(enemies.isEmpty())
			{
				eliteguardians.get(i).spd = 2;
			}
		}
		
		if(enemies.isEmpty())
		{
			soldiersAreDead = true;
		}
		
		if(eliteguardians.isEmpty())
		{
			boss.spd = 2;
		}
		boss.tick();
	}
	
	public void render() //onde vão ser renderizados nossos elementos gráficos;
	{
		BufferStrategy buffer = this.getBufferStrategy(); //importar Buffer Strategy;
		
		if (buffer == null)
		{
			this.createBufferStrategy(3); //failcheck pra ver se buffer existe, valor de int pode ser 2 ou 3 (é otimização gráfica - (?) )
			return;
		}
		
		Graphics g = buffer.getDrawGraphics(); //importar Graphics
		
	    g.setColor(Color.darkGray); //import color para preencher o background com retângulo da cor definida pra evitar tela de ficar piscando.
		g.fillRect(0, 0, WIDTH, HEIGHT); //método que cria o retângulo para preencher o background. (pos x, pos y, width, height). 
		
		/* g.setColor(Color.black); //import Color
		g.fillRect(10, 10, 50, 100); //método para criar um retângulo na tela, os parâmetros são (pos x, pos y, width, height). */
		
		g.drawImage(Sprites.tileLevel, 0, -20, 1882, 1058, null);
		
		world.render(g);
		player.render(g); //calling the render(g) method to render the player from the Player class;
		 
		for(int i=0; i < enemies.size(); i++)
		{
			enemies.get(i).render(g);
		}
		
		for(int i=0; i < eliteguardians.size(); i++)
		{
			eliteguardians.get(i).render(g);
		}
		
		boss.render(g);
		
		buffer.show();
	}
	
	public static void main(String[] args)
	{
		Game littleZelda = new Game();
		JFrame window = new JFrame();
		
		window.add(littleZelda); 
		window.setTitle("The Little Zelda"); //Título do jogo
		window.pack(); //"Empacotar todo o código e calcular o tamanho certo da janela"... Sei não.
		window.setLocationRelativeTo(null); //Para deixar a janela centralizada; Deve vir após o .pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Para que quando fechar a janela, o programa seja fechado para não ficar ocupando memória.	
		window.setVisible(true); //Código para vermos a janela;
	
		//Agora que temos a janela, é preciso criar um game loop pra ficar chamando "infinitamente" as ações e renderizando na tela; 
	
		new Thread(littleZelda).start(); //Processo começa e vai executando;
	}
	
	@Override
	public void run() 
	{ 
	  //método principal que vai rodar nosso jogo;
	  // TODO Auto-generated method stub
		
		while(true)
		{
			
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			/*Para forçar o jogo a ser executado a 60fps; É preciso colocar dentro de um try/catch, pois se a execução falhasse, "nada acontece" exceto
			reproduzir em outro framerate.Sem isso, o código apresenta erro. */
			
			System.out.println("Game Looping Updating!"); //Debugging Thread(littleZelda) Update.
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		// Below the movement input keys:
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			player.left = false;
			player.up = false;
			player.down = false;
			player.right = true;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			player.right = false;
			player.down = false;
			player.up = false;
			player.left = true;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			player.up = false;
			player.right = false;
			player.left = false;
			player.down = true;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			player.down = false;
			player.right = false;
			player.left = false;
			player.up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player.left = false;
			player.up = false;
			player.down = false;
			player.right = true;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			player.right = false;
			player.down = false;
			player.up = false;
			player.left = true;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player.up = false;
			player.right = false;
			player.left = false;
			player.down = true;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			player.down = false;
			player.right = false;
			player.left = false;
			player.up = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			player.shoot = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		// Below canceling movement when releasing key
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			player.left = false;
			player.up = false;
			player.down = false;
			player.right = false;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			player.right = false;
			player.down = false;
			player.up = false;
			player.left = false;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			player.up = false;
			player.right = false;
			player.left = false;
			player.down = false;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			player.down = false;
			player.right = false;
			player.left = false;
			player.up = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player.left = false;
			player.up = false;
			player.down = false;
			player.right = false;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			player.right = false;
			player.down = false;
			player.up = false;
			player.left = false;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player.up = false;
			player.right = false;
			player.left = false;
			player.down = false;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			player.down = false;
			player.right = false;
			player.left = false;
			player.up = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			player.shoot = false;
		}
	}

}
