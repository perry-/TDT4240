package no.perry.pong;


import sheep.game.State;
import sheep.game.World;
import sheep.graphics.Font;
import sheep.graphics.Color;
import sheep.input.TouchListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.Display;
import android.view.MotionEvent;


/**
 * @author Perry
 * Git test
 */
public class GameOverState extends State implements TouchListener{

	private Paint paint;
	private World world = new World();
	private Font font;
	private int winner;
	private Display display;
	
	/**
	 * Constructor, add paddles and a ball to the canvas
	 * @param display Display data, for better scaling
	 */
	public GameOverState(int winner, Display display) {
		this.winner = winner;
		this.display = display;
		Color c = new Color(0,0,0);
		paint = new Paint(c);
		paint.setStyle(Style.FILL);
		font = new Font(255, 255, 255, 30, Typeface.SERIF, Typeface.NORMAL);
	}

	
	public void draw(Canvas canvas){
		canvas.drawPaint(paint);
		font.setTextAlign(Align.CENTER);
		canvas.drawText("Game over", getGame().getWidth()/2, getGame().getHeight()/2, font);
		canvas.drawText("Player "+winner+" won!", getGame().getWidth()/2, (getGame().getHeight()/2)+30, font);
		canvas.drawText("Touch the screen to start a new game", getGame().getWidth()/2, (getGame().getHeight()/2)+100, font);
		
		world.draw(canvas);
	}


	public void update(float dt) {
		world.update(dt);
	}

	public boolean onTouchMove(MotionEvent event) {
		getGame().popState();
		getGame().pushState(new GameState(display));
		return true;
	}
}
