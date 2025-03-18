package co.feip.fefu2025

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class FlexBoxLayout : ViewGroup {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        var totalHeight = 0
        var lineWidth = 0
        var maxLineHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            if (lineWidth + childWidth > width) {
                totalHeight += maxLineHeight
                lineWidth = childWidth
                maxLineHeight = childHeight
            } else {
                lineWidth += childWidth
                maxLineHeight = maxOf(maxLineHeight, childHeight)
            }
        }

        totalHeight += maxLineHeight
        setMeasuredDimension(width, totalHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val width = right - left
        var lineWidth = 0
        var lineHeight = 0
        var currentTop = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                val childWidth = child.measuredWidth
                val childHeight = child.measuredHeight

                if (lineWidth + childWidth > width) {
                    currentTop += lineHeight
                    lineWidth = 0
                    lineHeight = 0
                }

                child.layout(lineWidth, currentTop, lineWidth + childWidth, currentTop + childHeight)
                lineWidth += childWidth
                lineHeight = maxOf(lineHeight, childHeight)
            }
        }
    }

    fun addFlexItem(view: View) {
        addView(view)
    }
}