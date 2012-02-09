package lars.ntnu.helicopter;

import sheep.game.Sprite;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

public class HeliSprite extends Sprite {
	
	private Bitmap bitmap;
	private Rect sourceRect;
	private long timePerFrame = 100; // 100 ms per picture
	private long timeLastChange = 01;
	private int frames = 4;
	private int currentFrame = 0;
	
	private int spriteWidth;
	private int spriteHeight;
	
	private float x;
	private float y;
	
	private boolean frontLeft = true;
	
	public HeliSprite(Bitmap bitmap, int x, int y, int framecount){
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.spriteWidth = bitmap.getWidth() / framecount;
		this.spriteHeight = bitmap.getHeight();
		this.setShape(spriteWidth, spriteHeight);
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
	}
	
	public void update(long gameTime){
		if (gameTime > timeLastChange + timePerFrame) {
			timeLastChange = gameTime;
			// increment the frame
			if(frames > 1){
				currentFrame++;
				if (currentFrame >= frames) {
					currentFrame = 0;
				}
			}
		}
		// define the rectangle to cut out sprite
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
	}
	
	public void draw(Canvas canvas){
		// Check if front is facing the right direction
		if(getSpeed().getX() > 0 && frontLeft)
			flip();
		else if(getSpeed().getX() < 0 && !frontLeft)
			flip();
		
		// take speed in to consideration and change x and y
		setY(getY()+(getSpeed().getY()/100));
		setX(getX()+(getSpeed().getX()/100));
		// where to draw the sprite
		Rect destRect = new Rect((int)getX(), (int)getY(), (int)getX() + spriteWidth, (int)getY() + spriteHeight);
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
	}
	
	private Bitmap flip(Bitmap d)
	{
	    Matrix m = new Matrix();
	    m.preScale(-1, 1);
	    Bitmap src = d;
	    Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
	    dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
	    return new BitmapDrawable(dst).getBitmap();
	}
	
	public void flip(){
		frontLeft = !frontLeft;
		this.bitmap = flip(this.bitmap);
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float f) {
		this.y = f;
	}
	
	public String getWidth(){
		return ""+this.spriteWidth;
	}
	
	public String getHeight(){
		return ""+this.spriteHeight;
	}
	
	public Rect getRect(){
		return new Rect((int)getX(), (int)getY(), (int)getX() + spriteWidth, (int)getY() + spriteHeight);
	}
	
	public boolean frontFacingLeft(){
		return frontLeft;
	}
}
