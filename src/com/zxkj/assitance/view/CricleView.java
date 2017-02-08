package com.zxkj.assitance.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zxkj.assitance.R;

public class CricleView extends View {
	protected Paint mPaint;
	protected RectF mRectF;

	protected int mAngleWidth = 90;
	protected int state = 0;
	public CricleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint=new Paint();
		mPaint.setColor(this.getResources().getColor(R.color.main_cricle));
		mPaint.setAntiAlias(true);


	}

	public void setAngleWithAnim(final int AngleWidth) {
		final Timer mTimer = new Timer();
		state = 0;
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				switch (state) {
				case 0:
					mAngleWidth -= 10;
					if (mAngleWidth <= 0) {
						state = 1;
					}
					postInvalidate();
					break;
				case 1:
					mAngleWidth += 10;
					if (mAngleWidth >= AngleWidth) {

						mTimer.cancel();
					}
					postInvalidate();
					break;
				}

			}
		}, 100, 40);

	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		mRectF = new RectF(0, 0, width, height);
		// 将获取到布局宽高设置给当前视图
		this.setMeasuredDimension(width, height);

	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawArc(mRectF, -90, mAngleWidth, true, mPaint);
	}
}
