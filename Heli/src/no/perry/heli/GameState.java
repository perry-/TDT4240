package no.perry.heli;

import java.util.ArrayList;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Color;
import sheep.input.TouchListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.MotionEvent;


/**
 * Stores the gamestate with canvas and the different heli-objects
 * @author Perry
 *
 */
public class GameState extends State implements TouchListener{

	private ArrayList<Heli> helis = new ArrayList<Heli>();
	private int canvasHeight, canvasWidth, heliWidth, heliHeight;
	private int heliCount = 3; //Number of helicopters on screen
	private Paint paint;
	
	/**
	 * Constructor, creates helicopters
	 * @param resources Resources needed for converting the image to bitmap
	 */
	public GameState(Resources resources) {
		Bitmap bmp = BitmapFactory.decodeResource(resources, R.drawable.helisprite);

		for(int i=0; i<heliCount; i++){
			helis.add(new Heli(bmp, 100, (i*300)+50, 4));
			helis.get(i).setId(i);
			helis.get(i).setSpeed(100, 100);

		}
	}

	public void draw(Canvas canvas){
		Color c = new Color(254,0,254);
		paint = new Paint(c);
		paint.setStyle(Style.FILL);
		canvas.drawPaint(Color.BLACK);
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();

		for(int i=0; i<heliCount; i++){
			//Draw the helicopters
			helis.get(i).draw(canvas);

			//Draw the helicopter positions on canvas
			canvas.drawText("Position heli "+i+": ("+helis.get(i).getX()+","+helis.get(i).getY()+")", 0, ((i+1)*20), Font.BLUE_SANS_BOLD_20);
			
			//Draw the helicopter ID on each helicopter
			if(helis.get(i).getDirection() == 0){
				canvas.drawText(i+"", helis.get(i).getX()+65, helis.get(i).getY()+30, Font.WHITE_SANS_BOLD_20);
			}else if(helis.get(i).getDirection() == 1){
				canvas.drawText(i+"", helis.get(i).getX()+95, helis.get(i).getY()+30, Font.WHITE_SANS_BOLD_20);
			}
		}
	}


	public void update(float dt) {

		heliWidth = helis.get(0).getWidth();
		heliHeight = helis.get(0).getHeight();

		for(Heli heli : helis){
			if(heli.getX()>(canvasWidth-heliWidth) || heli.getX()<0){
				heli.setSpeed(-heli.getSpeed().getX(), heli.getSpeed().getY());
			}
			if(heli.getY()>(canvasHeight-heliHeight) || heli.getY()<0){
				heli.setSpeed(heli.getSpeed().getX(), -heli.getSpeed().getY());
			}
			for(Heli heliColl : helis){
				if(!heliColl.equals(heli)){

					if(heliColl.getHitbox().intersect(heli.getHitbox())){
						heli.setSpeed((heli.getX()-heliColl.getX())*5, (heli.getY()-heliColl.getY())*5);
					}
				}
			}
			
			if((heli.getSpeed().getX() > 0 && heli.getDirection() == 0) || (heli.getSpeed().getX() < 0 && heli.getDirection() == 1)){
				heli.flip();
			}
			
			heli.update(System.currentTimeMillis());
		}
	}

	public boolean onTouchMove(MotionEvent event) {
		if(event.getX()<(canvasWidth-heliWidth) && event.getX()>(0+heliWidth)){
			if(event.getY()<(canvasHeight-heliHeight) && event.getY()>(0+heliHeight)){
				for(Heli h : helis){
					Rect touchRect = new Rect(h.getHitbox().left+50, h.getHitbox().top+50, h.getHitbox().right+50, h.getHitbox().bottom+50);
					if(touchRect.contains((int) event.getX(), (int) event.getY())){
						h.setPosition(event.getX(), event.getY());
						
					}
				}
				return true;
			}
		}
		return false;
	}
}
