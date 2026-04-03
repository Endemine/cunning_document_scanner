package biz.cunning.cunning_document_scanner.fallback.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import biz.cunning.cunning_document_scanner.R

/**
 * This class creates a circular button by modifying an image button. This is used for the
 * add new document button and retake photo button.
 *
 * @param context image button context
 * @param attrs image button attributes
 * @constructor creates circle button
 */
class CircleButton(
    context: Context,
    attrs: AttributeSet
): AppCompatImageButton(context, attrs) {
    /**
     * @property ring the button's outer ring
     */
    private val ring = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * @property fill the button's dark background fill
     */
    private val fill = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        // default ring style (TCGrail gold)
        ring.color = Color.parseColor("#FFD700")
        ring.style = Paint.Style.STROKE
        ring.strokeWidth = resources.getDimension(R.dimen.small_button_ring_thickness)

        // dark background fill
        fill.color = ContextCompat.getColor(context, R.color.tcgrail_dark_surface)
        fill.style = Paint.Style.FILL
    }

    /**
     * This gets called repeatedly. We use it to draw the button
     *
     * @param canvas the image button canvas
     */
    override fun onDraw(canvas: Canvas) {
        // update ring color based on button ID (retake gets red-ish border)
        if (id == R.id.retake_photo_button) {
            ring.color = ContextCompat.getColor(context, R.color.tcgrail_retake_border)
        } else {
            ring.color = Color.parseColor("#FFD700")
        }

        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()
        val outerRadius = (width.toFloat() - ring.strokeWidth) / 2

        // draw dark circular background
        canvas.drawCircle(centerX, centerY, outerRadius, fill)

        // draw outer ring
        canvas.drawCircle(centerX, centerY, outerRadius, ring)

        super.onDraw(canvas)
    }
}
