package custom

import java.awt.*
import javax.swing.border.Border

class RoundedBorder : Border {
    override fun getBorderInsets(c: Component?): Insets {
        return Insets(0, 0, 0, 0)
    }

    override fun isBorderOpaque(): Boolean {
        return false
    }

    override fun paintBorder(c: Component?, g: Graphics?, x: Int, y: Int, width: Int, height: Int) {
        val graphic = g as Graphics2D
        graphic.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        )
        graphic.stroke = BasicStroke(2f)
        c?.let {
            graphic.drawRoundRect(0, 0, c.width, c.height, 32, 32)
        }
    }
}