package com.example.timelytextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

	
	public class NumberView extends View {
		 
	    
		private final Interpolator mInterpolator;
	    private final Paint mPaint;
	    private final Path mPath;
	    
	 
	    // Numbers currently shown.
	    private int mCurrent = 0;
	    private int mNext = 1;
	    private int numCounter; 
	    
	    
	    private String animationType ="loop";
	    private String countType ="up";
	 
	    // Frame of transition between current and next frames.
	    private int mFrame = 0;
	     
	    private  float[][][] mPoints = null;
	    private float[][][] mControlPoint1 = null;
	    private  float[][][] mControlPoint2 = null;
	    
	    // The 5 end points. (Note: The last end point is the first end point of the next segment.
	    private final float[][][] mPointsUp = {
	        {{44.5f, 100}, {100, 18}, {156, 100}, {100, 180}, {44.5f, 100}}, // 0
	        {{77, 20.5f}, {104.5f, 20.5f}, {104.5f, 181},{104.5f, 181},{104.5f, 181}}, //1
	        {{56, 60}, {144.5f, 61}, {108,122}, {57, 177}, {147,177}}, //2
    		{{63.25f, 54}, {99.5f, 18}, {99.5f, 96}, {100, 180}, {56.5f, 143}}, // 3
    		{{155, 146}, {43, 146}, {129, 25}, {129, 146}, {129, 179}}, //4
    		{{146, 20}, {91, 20}, {72, 78}, {145, 129}, {45, 154}}, // 5
    		{{110, 20}, {110, 20}, {46, 126}, {153, 126}, {53.5f, 100}}, // 6
    		{{47, 21}, {158, 21}, {120.67f, 73.34f}, {83.34f, 126.67f}, {46, 181}},  // 7
    		{{101, 96}, {101, 19}, {101, 96}, {101, 179}, {101, 96}}, // 8
    		{{146.5f, 100}, {47, 74}, {154, 74}, {90, 180}, {90, 180}} // 9
	    };
	 
	    // The set of the "first" control points of each segment.
	    private final float[][][] mControlPointUp1 = {
	    		{{44.5f, 60}, {133, 18}, {156 , 140}, {67, 180}}, // 0
	    		{{77, 20.5f}, {104.5f, 20.5f}, {104.5f, 181}, {104.5f, 181}}, // 1
	    		{{59, 2}, {144.5f, 78}, {94, 138}, {57, 177}}, // 2
	    		{{63, 27}, {156, 18}, {158, 96}, {54, 180}}, // 3
	    		{{155, 146}, {43, 146}, {129, 25}, {129, 146}}, // 4
	    		{{91, 20}, {72, 78}, {97, 66}, {140, 183}}, // 5
	    		{{110, 20}, {71, 79}, {52, 208}, {146, 66}}, // 6
	    		{{47, 21}, {158, 21}, {120.67f, 73.34f}, {83.34f, 126.67f}}, // 7
	    		{{44, 95}, {154, 19}, {44, 96}, {154, 179}}, // 8
	    		{{124, 136}, {42, 8}, {152, 108}, {90, 180}} // 9
	    
	    };
	 
	    // The set of the "second" control points of each segment.
	    private final float[][][] mControlPointUp2 = { 
	    		
	    		{{67, 18}, {156, 60}, {133, 180}, {44.5f, 140}}, // 0
	    		{{104.5f, 20.5f}, {104.5f, 181}, {104.5f, 181}, {104.5f, 181}}, // 1
	    		{{143, 4}, {130, 98}, {74, 155}, {147, 177}}, // 2
	    		{{86, 18}, {146, 96}, {150, 180}, {56, 150}}, // 3
	    		{{43, 146}, {129, 25}, {129, 146}, {129, 179}}, // 4
	    		{{91, 20}, {72, 78}, {145, 85}, {68, 198}}, // 5
	    		{{110, 20}, {48, 92}, {158, 192}, {76, 64}}, // 6
	    		{{158, 21}, {120.67f, 73.34f}, {83.34f, 126.67f}, {46, 181}}, // 7
	    		{{44, 19}, {154, 96}, {36, 179}, {154, 96}}, // 8
	    		{{54, 134}, {148, -8}, {129, 121}, {90, 180}} // 9
	    };
	     
	    
	    private final float[][][] mPointsDown = {

	    	{{146.5f, 100}, {47, 74}, {154, 74}, {90, 180}, {90, 180}}, // 9
    		{{101, 96}, {101, 19}, {101, 96}, {101, 179}, {101, 96}}, // 8
    		{{47, 21}, {158, 21}, {120.67f, 73.34f}, {83.34f, 126.67f}, {46, 181}},  // 7
    		{{110, 20}, {110, 20}, {46, 126}, {153, 126}, {53.5f, 100}}, // 6
    		{{146, 20}, {91, 20}, {72, 78}, {145, 129}, {45, 154}}, // 5
    		{{155, 146}, {43, 146}, {129, 25}, {129, 146}, {129, 179}}, //4
    		{{63.25f, 54}, {99.5f, 18}, {99.5f, 96}, {100, 180}, {56.5f, 143}}, // 3
    		{{56, 60}, {144.5f, 61}, {108,122}, {57, 177}, {147,177}}, //2
    		{{77, 20.5f}, {104.5f, 20.5f}, {104.5f, 181},{104.5f, 181},{104.5f, 181}}, //1
    		{{44.5f, 100}, {100, 18}, {156, 100}, {100, 180}, {44.5f, 100}} // 0
	    };
	 
	    private final float[][][] mControlPointDown1 = {

	    		{{124, 136}, {42, 8}, {152, 108}, {90, 180}}, // 9
	    		{{44, 95}, {154, 19}, {44, 96}, {154, 179}}, // 8
	    		{{47, 21}, {158, 21}, {120.67f, 73.34f}, {83.34f, 126.67f}}, // 7
	    		{{110, 20}, {71, 79}, {52, 208}, {146, 66}}, // 6
	    		{{91, 20}, {72, 78}, {97, 66}, {140, 183}}, // 5
	    		{{155, 146}, {43, 146}, {129, 25}, {129, 146}}, // 4
	    		{{63, 27}, {156, 18}, {158, 96}, {54, 180}}, // 3
	    		{{59, 2}, {144.5f, 78}, {94, 138}, {57, 177}}, // 2
	    		{{77, 20.5f}, {104.5f, 20.5f}, {104.5f, 181}, {104.5f, 181}}, // 1
	    		{{44.5f, 60}, {133, 18}, {156 , 140}, {67, 180}} // 0
	    
	    };
	 
	    private final float[][][] mControlPointDown2 = { 
	    		
	    		{{54, 134}, {148, -8}, {129, 121}, {90, 180}}, // 9
	    		{{44, 19}, {154, 96}, {36, 179}, {154, 96}}, // 8
	    		{{158, 21}, {120.67f, 73.34f}, {83.34f, 126.67f}, {46, 181}}, // 7
	    		{{110, 20}, {48, 92}, {158, 192}, {76, 64}}, // 6
	    		{{91, 20}, {72, 78}, {145, 85}, {68, 198}}, // 5
	    		{{43, 146}, {129, 25}, {129, 146}, {129, 179}}, // 4
	    		{{86, 18}, {146, 96}, {150, 180}, {56, 150}}, // 3
	    		{{143, 4}, {130, 98}, {74, 155}, {147, 177}}, // 2
	    		{{104.5f, 20.5f}, {104.5f, 181}, {104.5f, 181}, {104.5f, 181}}, // 1
	    		{{67, 18}, {156, 60}, {133, 180}, {44.5f, 140}} // 0
	    		
	    };
	     
	    
	   
	    
	    public NumberView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	         
	        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);

	        String animType = a.getString(R.styleable.MyCustomView_animate);
	        String counter = a.getString(R.styleable.MyCustomView_count);

	        animationType = animType;
	        countType = counter;
	        
	        
	        if(countType.equalsIgnoreCase("up"))
	        {
	        	mPoints = mPointsUp;
	        	mControlPoint1 = mControlPointUp1;
	        	mControlPoint2 = mControlPointUp2;
	        }else
	        {
	        	mPoints = mPointsDown;
	        	mControlPoint1 = mControlPointDown1;
	        	mControlPoint2 = mControlPointDown2;
	        }

	        a.recycle();
	        
	        setWillNotDraw(false);
	        mInterpolator = new AccelerateDecelerateInterpolator();
	         
	        // A new paint with the style as stroke.
	        mPaint = new Paint();
	        mPaint.setAntiAlias(true);
	        mPaint.setColor(Color.BLACK);
	        mPaint.setStrokeWidth(5.0f);
	        mPaint.setStyle(Paint.Style.STROKE);
	        mPath = new Path();
	    }
	     
	    @Override
	    public void onDraw(Canvas canvas) {
	        int count = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,    
	                Canvas.MATRIX_SAVE_FLAG |
	                Canvas.CLIP_SAVE_FLAG |
	                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
	                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
	                Canvas.CLIP_TO_LAYER_SAVE_FLAG);
	 
	        super.onDraw(canvas);
	 
	      
	        final int currentFrame;
	        if (mFrame < 2) {
	            currentFrame = 0;
	        } else if (mFrame > 8) {
	            currentFrame = 6;
	        } else {
	            currentFrame = mFrame - 2;
	        }
	         
	        // A factor of the difference between current
	        // and next frame based on interpolation.
	        // Only 6 frames are used between the transition.
	        final float factor = mInterpolator.getInterpolation(currentFrame / 6.0f);
	         
	        // Reset the path.
	        mPath.reset();
	 
	        final float[][] current = mPoints[mCurrent];
	        final float[][] next = mPoints[mNext];
	 
	        final float[][] curr1 = mControlPoint1[mCurrent];
	        final float[][] next1 = mControlPoint1[mNext];
	 
	        final float[][] curr2 = mControlPoint2[mCurrent];
	        final float[][] next2 = mControlPoint2[mNext];
	         
	        // First point.
	        mPath.moveTo(current[0][0] + ((next[0][0] - current[0][0]) * factor),
	                     current[0][1] + ((next[0][1] - current[0][1]) * factor));
	         
	        // Rest of the points connected as bezier curve.
	        for (int i = 1; i < 5; i++) {
	            mPath.cubicTo(curr1[i-1][0] + ((next1[i-1][0] - curr1[i-1][0]) * factor),
	                          curr1[i-1][1] + ((next1[i-1][1] - curr1[i-1][1]) * factor),
	                          curr2[i-1][0] + ((next2[i-1][0] - curr2[i-1][0]) * factor),
	                          curr2[i-1][1] + ((next2[i-1][1] - curr2[i-1][1]) * factor),
	                          current[i][0] + ((next[i][0] - current[i][0]) * factor),
	                          current[i][1] + ((next[i][1] - current[i][1]) * factor));
	        }
	 
	        // Draw the path.
	        canvas.drawPath(mPath, mPaint);
	 
	        canvas.restoreToCount(count);
	         
	        // Next frame.
	        mFrame++;
	 
	        // Each number change has 10 frames. Reset.
	        if (mFrame == 10) {
	        	numCounter++;
	            // Reset to zero.
	            mFrame = 0;
	 
	            mCurrent = mNext;
	            mNext++;
	 
	            // Reset to zero.
	            if (mNext == 10) {
	                mNext = 0;
	            }
	        }
	        
	        if(numCounter == 9 && animationType.equalsIgnoreCase("once")){
	        	
	        }else{
	        // Callback for the next frame.
	        postInvalidateDelayed(85);
	        }
	    }
	    
	    public void setAnimationType(String animType)
	    {
	    	animationType  = animType;
	    }
	    
	    
	     public void setCountType(String type)
	    {
	    	countType = type;
	    }
	    
	}

