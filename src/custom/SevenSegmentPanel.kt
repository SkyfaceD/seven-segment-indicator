package custom

import utils.*
import java.awt.*
import java.awt.geom.AffineTransform
import java.awt.geom.GeneralPath
import javax.swing.JPanel
import kotlin.math.tan

class SevenSegmentPanel(var listOfTime: Array<Int>) : JPanel() {

    private lateinit var graphic: Graphics2D

    override fun paint(g: Graphics?) {
        super.paint(g)
        graphic = g as Graphics2D
        graphic.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        )
        graphic.stroke = BasicStroke(STROKE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)

//        drawBackground()

        for (i in listOfTime.indices) {
            drawNumber(
                    Point(((SEGMENT_WIDTH + SEGMENT_HEIGHT + SEGMENT_DIVIDER) * i + 2).toInt(), 2),
                    listOfTime[i]
            )
        }
    }

    private fun drawNumber(point: Point, number: Int = 0) {
        val state: AffineTransform = graphic.transform

        graphic.translate(point.x, point.y)

        drawSegment(
                Point((SEGMENT_WIDTH / 2 + SEGMENT_HEIGHT / 2).toInt(), (SEGMENT_HEIGHT / 2).toInt()),
                (NUMBER_MAP[number]!! and Segment.A.value) > 0
        )
        drawSegment(
                Point((SEGMENT_WIDTH + SEGMENT_HEIGHT / 2).toInt(), (SEGMENT_WIDTH / 2 + SEGMENT_HEIGHT / 2).toInt()),
                (NUMBER_MAP[number]!! and Segment.B.value) > 0,
                true
        )
        drawSegment(
                Point((SEGMENT_WIDTH + SEGMENT_HEIGHT / 2).toInt(), (SEGMENT_WIDTH * 1.5 + SEGMENT_HEIGHT / 2).toInt()),
                (NUMBER_MAP[number]!! and Segment.C.value) > 0,
                true
        )
        drawSegment(
                Point((SEGMENT_WIDTH / 2 + SEGMENT_HEIGHT / 2).toInt(), (SEGMENT_WIDTH * 2 + SEGMENT_HEIGHT / 2).toInt()),
                (NUMBER_MAP[number]!! and Segment.D.value) > 0
        )
        drawSegment(
                Point((SEGMENT_HEIGHT / 2).toInt(), (SEGMENT_WIDTH * 1.5 + SEGMENT_HEIGHT / 2).toInt()),
                (NUMBER_MAP[number]!! and Segment.E.value) > 0,
                true
        )
        drawSegment(
                Point((SEGMENT_HEIGHT / 2).toInt(), (SEGMENT_WIDTH / 2 + SEGMENT_HEIGHT / 2).toInt()),
                (NUMBER_MAP[number]!! and Segment.F.value) > 0,
                true
        )
        drawSegment(
                Point((SEGMENT_WIDTH / 2 + SEGMENT_HEIGHT / 2).toInt(), (SEGMENT_WIDTH + SEGMENT_HEIGHT / 2).toInt()),
                (NUMBER_MAP[number]!! and Segment.G.value) > 0
        )

        graphic.transform = state
    }

    private fun drawSegment(
            point: Point,
            isFilled: Boolean = false,
            isRotated: Boolean = false
    ) {
        val state: AffineTransform = graphic.transform

        graphic.translate(point.x, point.y)

        val path = GeneralPath()
        path.moveTo(SEGMENT_WIDTH / -2, 0.0)
        path.lineTo(SEGMENT_WIDTH / -2 + tan(Math.toRadians(45.0)) * SEGMENT_HEIGHT / 2, SEGMENT_HEIGHT / -2)
        path.lineTo(SEGMENT_WIDTH / 2 + tan(Math.toRadians(135.0)) * SEGMENT_HEIGHT / 2, SEGMENT_HEIGHT / -2)
        path.lineTo(SEGMENT_WIDTH / 2, 0.0)
        path.lineTo(SEGMENT_WIDTH / 2 + tan(Math.toRadians(135.0)) * SEGMENT_HEIGHT / 2, SEGMENT_HEIGHT / 2)
        path.lineTo(SEGMENT_WIDTH / -2 + tan(Math.toRadians(45.0)) * SEGMENT_HEIGHT / 2, SEGMENT_HEIGHT / 2)
        path.lineTo(SEGMENT_WIDTH / -2, 0.0)
        path.closePath()

        if (isRotated) graphic.rotate(Math.toRadians(90.0))

        graphic.color = if (isFilled) ACTIVE_COLOR else INACTIVE_COLOR
        graphic.fill(path)

        graphic.color = Color(0, 0, 0, 80)
//        graphic.color = BACKGROUND
//        graphic.color = INACTIVE_COLOR
        graphic.draw(path)

        graphic.transform = state
    }

    private fun drawBackground() {
        graphic.color = BACKGROUND
        graphic.fillRect(0, 0, width, height)
    }
}