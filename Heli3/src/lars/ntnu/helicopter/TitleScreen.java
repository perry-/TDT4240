package lars.ntnu.helicopter;

import java.util.ArrayList;

import sheep.graphics.Color;
import sheep.graphics.Image;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TitleScreen extends sheep.game.State implements OnTouchListener {

	private Image heliImage = new Image(R.drawable.heli1_east);
	private int width;
	private int height;
	private ArrayList<HeliSprite> heliList = new ArrayList<HeliSprite>();
	private int heliCount = 3;
				
	public TitleScreen(Resources res) {
		for (int i = 0; i<heliCount; i++){
			heliList.add(new HeliSprite(BitmapFactory.decodeResource(res, R.drawable.heli),200,(i*100)+100,4));	
			heliList.get(i).setSpeed(100, 100);
		}
	
		this.addTouchListener(this);
		}
	
	public void draw(android.graphics.Canvas canvas){
		width = canvas.getWidth();
		height = canvas.getHeight();
		Paint paint = new Paint(new Color(254, 0, 254));
		paint.setStyle(Style.FILL);
		canvas.drawPaint(paint);
		for (int i = 0; i<heliCount; i++){
		    heliList.get(i).draw(canvas);
		    canvas.drawText("X:" + heliList.get(i).getX() , 15, (i*15)+20, Color.WHITE);
//		    canvas.drawText("Y:" + heli.getY() , 15, 25, Color.WHITE);
			
		}
		Log.i("LARS", ""+heliList.get(2));
		
	    }
	
	public void update(float dt) {
		for(HeliSprite heli : heliList){
			if (heli.getX()<=0){
				heli.setSpeed(-heli.getSpeed().getX(), heli.getSpeed().getY());
			}
			else if (heli.getY()<=0){
				heli.setSpeed(heli.getSpeed().getX(), -heli.getSpeed().getY());
			}
			else if(heli.getX()>=width-heliImage.getWidth()){
				heli.setSpeed(-heli.getSpeed().getX(), heli.getSpeed().getY());
			}
			else if(heli.getY()>=height-heliImage.getHeight()){
				heli.setSpeed(heli.getSpeed().getX(), -heli.getSpeed().getY());
			}
			
			heli.update(System.currentTimeMillis());
		}
		

		 
		
		

}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean onTouchDown(MotionEvent event) {
	//	heliSprite.setSpeed(event.getX() - heliSprite.getX(), event.getY() - heliSprite.getY());
		return true;
	}
	
	public boolean onTouchUp(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean onTouchMove(MotionEvent event) {
		// TODO Auto-generated method stub
		//heliSprite.setSpeed(event.getX() - heliSprite.getX(), event.getY() - heliSprite.getY());
		return true;
	}
}
