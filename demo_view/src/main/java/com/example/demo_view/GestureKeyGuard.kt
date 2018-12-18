package com.example.demo_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.common.util.DemoLog
import com.pdog.dimension.dp
import com.pdog.dimension.sp
import java.util.*

class GestureKeyGuard : View {

    var row = 3
    var col = 3
    var radius = 5f.sp
    var gap = 10f.sp

    lateinit var cyclePaint: Paint
    lateinit var linePaint: Paint

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    fun initView() {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var newWidth = reSizeWidth(widthMeasureSpec)
        var newHeight = reSizeHeight(heightMeasureSpec)

        setMeasuredDimension(newWidth, newHeight)
    }

    private fun reSizeWidth(widthMeasureSpec: Int): Int {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var newSize = 0
        when(widthMode){
            MeasureSpec.UNSPECIFIED -> {}
            MeasureSpec.AT_MOST -> {}
            MeasureSpec.EXACTLY -> {}
        }
        return resolveSize(newSize, widthMeasureSpec)
    }

    private fun reSizeHeight(heightMeasureSpec: Int): Int {
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var newSize = 0
        when(heightMode){
            MeasureSpec.UNSPECIFIED -> {}
            MeasureSpec.AT_MOST -> {}
            MeasureSpec.EXACTLY -> {}
        }
        return resolveSize(newSize, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}