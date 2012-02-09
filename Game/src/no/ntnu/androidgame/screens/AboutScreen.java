package no.ntnu.androidgame.screens;

import no.ntnu.androidgame.Constants;
import android.graphics.Canvas;
import android.graphics.Paint;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * The About screen presents the credits. Information about the developers. The background is an image,
 * but the text is coded in the class.
 * 
 * @author rodsjo
 * @see MenuScreen
 */
public class AboutScreen extends State implements WidgetListener {
	
	private Image background = Constants.BACKGROUND_WITH_LOGO;
	
	private String about1;
	private String about2;
	private String about3;
	
	private String aboutVegard;
	private String aboutHaakon;
	private String aboutBorge;
	private String aboutFernando;
		
	private TextButton backBtn;
	
	/**
	 * Constructor that creates the texts and the backbutton on the screen
	 * 
	 * @author rodsjo
	 */
	public AboutScreen() {
		about1 = "This game has been developed in the course ";
		about2 = "TDT4240 Software Architecture, spring 2010 ";
		about3 = "at NTNU, Norway.";
		
		aboutVegard = "Vegard Egeland - vegare@stud.ntnu.no";
		aboutHaakon = "Haakon Raudsandmoen - raudsand@stud.ntnu.no";
		aboutBorge = "Boerge Roedsjoe - rodsjo@stud.ntnu.no";
		aboutFernando = "Fernando Rodriguez - thenanox@gmail.com";
		
		
		backBtn = new TextButton(Constants.WINDOW_WIDTH*0.8f, Constants.WINDOW_HEIGHT*0.85f, "back", Constants.buttonPaint);
		addTouchListener(backBtn);
		backBtn.addWidgetListener(this);
	}
	/**
	 * Draws buttons, background and text to the canvas, at hardcoded positions
	 * 
	 * @author rodsjo
	 * @param canvas - The Canvas to draw on
	 */
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		background.draw(canvas, 0, 0);
		
		Paint paint = new Paint();
		paint.setColor(Constants.HISTORYTEXTCOLOR);
		
		canvas.drawText(about1, 25f, (100f + 14*0), paint);
		canvas.drawText(about2, 25f, (100f + 14*1), paint);
		canvas.drawText(about3, 25f, (100f + 14*2), paint);
		
		canvas.drawText(aboutVegard, 25f, (180f + 14*0), paint);
		canvas.drawText(aboutHaakon, 25f, (180f + 14*1), paint);
		canvas.drawText(aboutBorge, 25f, (180f + 14*2), paint);
		canvas.drawText(aboutFernando, 25f, (180f + 14*3), paint);
		
		backBtn.draw(canvas);
	}
	/**
	 * Method that is invoked when an action has been performed with an widget
	 * on the screen. If an action has hit'd the back button, this screen is popped away
	 * from the stack, and the menu screen is showed.
	 * 
	 * @author rodsjo
	 * @param action - WidgetActino that has taken place
	 */
	@Override
	public void actionPerformed(WidgetAction action) {
		if (action.getSource() == backBtn) {
			getGame().popState(); // Pops this state, beneath there should be a MenuScreen
		}
	}
}
