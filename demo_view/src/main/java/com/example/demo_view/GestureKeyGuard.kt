package com.example.demo_view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.common.util.DemoLog
import java.util.*
import kotlin.collections.ArrayList

class GestureKeyGuard : View {

    private var row = 3
    private var col = 3
    private var radius = 0

    private lateinit var points:Array<Array<KeyGuardPoint>>
    private var keyMap = TreeMap<Int, Int>()
    private var selectedPoints = ArrayList<KeyGuardPoint>()

    //private var path = Path()
    private val lastEventPoint = Point()

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
        linePaint.color = Color.BLUE
        linePaint.style = Paint.Style.STROKE

        this.points = Array(row){it -> Array(col) { it1 -> KeyGuardPoint(it * row + it1) } }
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
                        val short = Math.min(widthSize, heightSize)
                        newHeight = heightSize
                        newWidth = short
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
                cy += radius * 4
            }
            cy = radius * 2
            cx += radius * 4
        }

//        points.forEachIndexed { i, arrayOfKeyGuardPoints ->
//            arrayOfKeyGuardPoints.forEachIndexed { j, keyGuardPoint ->
//                DemoLog.viewlog_keyguard(",i="+i+",j="+j+",id="+keyGuardPoint.id+",keyGuardPoint.(x,y)=("+keyGuardPoint.x+","+keyGuardPoint.y+")")
//            }
//        }

        val path = Path()
        selectedPoints.forEach {
            if (path.isEmpty) {
                path.moveTo(it.x.toFloat(), it.y.toFloat())
            } else {
                path.lineTo(it.x.toFloat(), it.y.toFloat())
            }
        }
        DemoLog.viewlog_keyguard("ondraw lastEvent.x="+lastEventPoint.x+"  lastEvent.y="+lastEventPoint.y)
        if (!path.isEmpty)
            path.lineTo(lastEventPoint.x.toFloat(), lastEventPoint.y.toFloat())

        canvas.drawPath(path, linePaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?: return false
        var point = findEventInPoint(event)
        point?.let {
            if (!selectedPoints.contains(point)) {
                selectedPoints.add(point)
                keyMap[keyMap.size+1] = point.id
            }
            //DemoLog.viewlog_keyguard("point.x="+point.x+"  point.y="+point.y)
            //DemoLog.viewlog_keyguard("event.x="+event.x+"  event.y="+event.y)
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                var lastPoint = selectedPoints.lastOrNull()
                lastPoint?.let {
                    lastEventPoint.x = event.x.toInt()
                    lastEventPoint.y = event.y.toInt()
                    invalidate()
                }
                DemoLog.viewlog_keyguard("onTouchEvent lastEvent.x="+lastEventPoint.x+"  lastEvent.y="+lastEventPoint.y)
            }
            MotionEvent.ACTION_UP -> {
                selectedPoints.clear()
                keyMap.clear()
                //path.reset()
                invalidate()
            }
            else -> {
            }
        }
        return false
    }

    //找到手落在哪个点，不在圈里返回null
    private fun findEventInPoint(event: MotionEvent): KeyGuardPoint? {
        var x = findRangX(event.x, points, 0, points.size - 1)
        var y = findRangY(event.y, points, 0, points[0].size - 1)
        DemoLog.viewlog_keyguard("points x=$x y=$y")
        if (x == -1 || y == -1) {
            return null
        }
        return points[x][y]
    }

    //通过二分查找
    private fun findRangX(x: Float, points: Array<Array<KeyGuardPoint>>, start: Int, end: Int): Int {
        var mid = (end - start) / 2 + start
        if(start >= end) {
            return if (start == end && Math.abs(x - points[start][0].x) < radius) {
                start
            } else {
                -1
            }
        }
        return when {
            //判断手势点是否在圈的范围内
            Math.abs(x - points[mid][0].x) < radius -> mid
            x > points[mid][0].x -> findRangX(x, points, mid + 1, end)
            x < points[mid][0].x -> findRangX(x, points, start, mid - 1 )
            else -> mid
        }
    }

    private fun findRangY(y: Float, points: Array<Array<KeyGuardPoint>>, start: Int, end: Int): Int {
        var mid = (end - start) / 2 + start
        if(start >= end) {
            return if(start == end && Math.abs(y - points[0][start].y) < radius) {
                start
            } else {
                -1
            }
        }
        return when {
            Math.abs(y - points[0][mid].y) < radius -> mid
            y > points[0][mid].y -> findRangY(y, points, mid + 1, end)
            y < points[0][mid].y -> findRangY(y, points, start, mid - 1 )
            else -> mid
        }
    }

    class KeyGuardPoint(var id:Int): Point() {
        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }
    }
}