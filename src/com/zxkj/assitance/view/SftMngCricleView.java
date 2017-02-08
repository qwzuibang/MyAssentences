package com.zxkj.assitance.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zxkj.assitance.R;
public class SftMngCricleView extends View {
	protected Paint mPaint;
	protected RectF mRectF;
	protected int innerSweepAngle = 180;

	public SftMngCricleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// ª≠‘≤ª°±≥æ∞
		mPaint.setColor(this.getResources().getColor(R.color.main_cricle));
		canvas.drawArc(mRectF, -90, 360, true, mPaint);
		// ª≠ƒ⁄÷√ƒ⁄¥Ê‘≤ª°
		mPaint.setColor(Color.BLUE);
		canvas.drawArc(mRectF, -90, innerSweepAngle, true, mPaint);
		// ª≠Õ‚÷√ƒ⁄¥Ê‘≤ª°
		mPaint.setColor(Color.GREEN);
		canvas.drawArc(mRectF, -90 + innerSweepAngle, 360 - innerSweepAngle,
				true, mPaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		mRectF = new RectF(0, 0, width, height);
		this.setMeasuredDimension(width, height);
	}


}
