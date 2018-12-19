package com.example.demo_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.common.util.DemoLog

class GestureKeyGuard : View {

    private var row = 5
    private var col = 5
    private var radius = 0

    lateinit var points:Array<Array<Point>>

    private val cyclePaint:Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val linePaint:Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()

    }

    private fun initView() {
        cyclePaint.color = Color.GRAY
        linePaint.color = Color.DKGRAY

        points = Array(row){ _ -> Array(col) { Point() } }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var newWidth = 0
        var newHeight = 0

        when(widthMode) {
            MeasureSpec.AT_MOST -> {
                when (heightMode) {
                    MeasureSpec.AT_MOST -> {
//                        newWidth = ((radius + gap) * col * 2).toInt()
//                        newHeight = newWidth
                    }
                    MeasureSpec.EXACTLY -> {
                        val short = Math.min(widthSize, heightSize)
                        newHeight = heightSize
                        newWidth = short
                    }
                    MeasureSpec.UNSPECIFIED -> {}
                }
            }
            MeasureSpec.EXACTLY -> {
                newWidth = widthSize
                when (heightMode) {
                    MeasureSpec.AT_MOST -> {
                        newHeight = newWidth
                    }
                    MeasureSpec.EXACTLY -> {
                        newHeight = heightSize
                    }
                    MeasureSpec.UNSPECIFIED -> {
                        //
                    }
                }
            }
            MeasureSpec.UNSPECIFIED -> {
                //
            }
        }

        newWidth = resolveSize(newWidth, widthMeasureSpec)
        newHeight = resolveSize(newHeight, heightMeasureSpec)

        DemoLog.viewlog_keyguard("newWidth = $newWidth,newHeight = $newHeight")
        setMeasuredDimension(newWidth, newHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?: return
        radius = width / (4 * col)
        var cx = 2 * radius
        var cy = cx
        for (i in 0 until row) {
            for (j in 0 until col) {
                canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), cyclePaint)
                points[i][j].x = cx
                points[i][j].y = cy
                cx += radius * 4
            }
            cx = radius * 2
            cy += radius * 4
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?: return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                var nearest = findNearestPoint(event)
                nearest?.let {
                    
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                var nearest = findNearestPoint(event)
            }
            else -> {
            }
        }
        return false
    }

    private fun findNearestPoint(event: MotionEvent): Point? {
        var x = findRangX(event.x, points, 0, points.size - 1)
        var y = findRangY(event.y, points, 0, points.size - 1)
        DemoLog.viewlog_keyguard("points x=$x y=$y")
        if (x == -1 || y == -1) {
            return null
        }
        return points[x][y]
    }

    private fun findRangX(x: Float, points: Array<Array<Point>>, start: Int, end: Int): Int {
        var mid = (end - start) / 2 + start
        if(start >= end) {
            return if (start == end && Math.abs(x - points[0][start].x) < radius) {
                start
            } else {
                -1
            }
        }
        return when {
            Math.abs(x - points[0][mid].x) < radius -> mid
            x > points[0][mid].x -> findRangX(x, points, mid + 1, end)
            x < points[0][mid].x -> findRangX(x, points, start, mid - 1 )
            else -> mid
        }
    }

    private fun findRangY(y: Float, points: Array<Array<Point>>, start: Int, end: Int): Int {
        var mid = (end - start) / 2 + start
        if(start >= end) {
            return if(start == end && Math.abs(y - points[start][0].y) < radius) {
                start
            } else {
                -1
            }
        }
        return when {
            Math.abs(y - points[mid][0].y) < radius -> mid
            y > points[mid][0].y -> findRangY(y, points, mid + 1, end)
            y < points[mid][0].y -> findRangY(y, points, start, mid - 1 )
            else -> mid
        }
    }
}