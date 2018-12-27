package com.example.demo_view.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.common.util.DemoLog

class StatickItemDecoration(private var callback: GroupInfoCallBack) : RecyclerView.ItemDecoration() {

    private val headerHeight = 50
    private val dividerHeight = 1

    private val mPaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private val textPaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    init {
        mPaint.color = Color.YELLOW
        textPaint.color = Color.RED
        textPaint.textSize = 50F
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val positon = parent.getChildAdapterPosition(view)
        DemoLog.viewlog_decoration("positon=$positon")
        val groupInfo = callback.getGroupInfo(positon)
        if (groupInfo.isFirstViewInGroup()) {
            outRect.top = headerHeight
        } else{
            outRect.top = dividerHeight
        }

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(view)

            val groupInfo = callback.getGroupInfo(index)
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            if (i != 0) {
                if (groupInfo.isFirstViewInGroup()) {
                    val top = view.top - headerHeight
                    val bottom = view.top
                    drawHeaderRect(c, groupInfo, left, top, right, bottom)
                }
            } else{
                //当 ItemView 是屏幕上第一个可见的View 时，不管它是不是组内第一个View
                //它都需要绘制它对应的 StickyHeader。

                // 还要判断当前的 ItemView 是不是它组内的最后一个 View
                var top = parent.paddingTop

                if (groupInfo.isLastViewInGroup()) {
                    val suggesttop = view.bottom - headerHeight
                    if (suggesttop < top) {
                        top = suggesttop
                    }
                }

                val bottom = top + headerHeight
                drawHeaderRect(c, groupInfo, left, top, right, bottom)
            }
        }
    }

    private fun drawHeaderRect(c: Canvas, groupInfo: GroupInfo, left: Int, top: Int, right: Int, bottom: Int) {
        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
        c.drawText(groupInfo.title, left.toFloat(), bottom.toFloat(), textPaint)
    }

//    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDraw(c, parent, state)
//        DemoLog.viewlog_decoration("ondraw")
//        val childCount = parent.childCount
//        for (i in 0 until childCount) {
//            val view = parent.getChildAt(i)
//            //刚开始没有加下面这句，用i获取groupInfo
//            //getChildAdapterPosition(View child)
//            //getChildLayoutPosition(View child) //布局完成时的位置
//            //getChildPosition(View child) 废弃了
//            val index = parent.getChildAdapterPosition(view)
//            val groupInfo = callback.getGroupInfo(index)
//
//            DemoLog.viewlog_decoration("index=$index")
//            if (groupInfo.isFirstViewInGroup()) {
//                val left = parent.paddingLeft
//                val top = view.top - headerHeight
//                val right = parent.width - parent.paddingRight
//                val bottom = view.top
//                DemoLog.viewlog_decoration("left=$left,top=$top,right=$right,bottom=$bottom")
//                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
//
//                c.drawText(groupInfo.title, left.toFloat(), bottom.toFloat(), textPaint)
//            }
//
//        }
//    }

    interface GroupInfoCallBack {
        fun getGroupInfo(position: Int): GroupInfo
    }
}