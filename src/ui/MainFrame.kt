package ui

import custom.RoundedBorder
import custom.SevenSegmentPanel
import utils.SEGMENT_DIVIDER
import utils.SEGMENT_HEIGHT
import utils.SEGMENT_WIDTH
import utils.STROKE_WIDTH
import java.awt.Color
import java.awt.Rectangle
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class MainFrame : JFrame() {

    private var pX = 0
    private var pY = 0
    private var timeFormat = true

    init {
        val w = ((SEGMENT_WIDTH + SEGMENT_HEIGHT + STROKE_WIDTH) * 6 + (SEGMENT_DIVIDER - STROKE_WIDTH) * 5).toInt()
        val h = ((SEGMENT_WIDTH * 2) + (SEGMENT_HEIGHT * 2) / 2 + STROKE_WIDTH).toInt()

        val container = JPanel()
        container.layout = null
        container.isOpaque = false

        val panel = SevenSegmentPanel(Array(6) { 0 })
        panel.bounds = Rectangle(0, 0, w, h)
        panel.isOpaque = false

        drag(panel)

        val btnSettings = JButton()
        btnSettings.bounds = Rectangle(w - 32, 0, 32, 32)
        btnSettings.isOpaque = false
        btnSettings.background = Color(0x00000000, true)
        btnSettings.border = RoundedBorder()
        btnSettings.icon = ImageIcon("H:\\Docs\\Kotlin\\seven-segment-indicator\\src\\images\\gear.png")
        btnSettings.isContentAreaFilled = false
        btnSettings.isVisible = false
        btnSettings.addActionListener {
            SettingsDialog()
        }

        panel.addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent?) {
                btnSettings.isVisible = true
            }

            override fun mouseExited(e: MouseEvent?) {
                btnSettings.isVisible = false
            }
        })

        btnSettings.addMouseListener(object: MouseAdapter(){
            override fun mouseEntered(e: MouseEvent?) {
                btnSettings.isVisible = true
            }

            override fun mouseExited(e: MouseEvent?) {
                btnSettings.isVisible = false
            }
        })

        setSize(w, h)

        contentPane = container
        isUndecorated = true
        background = Color(0x00000000, true)

        setLocationRelativeTo(container)
        container.add(btnSettings)
        container.add(panel)

        isVisible = true

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val time = SimpleDateFormat(if (timeFormat) "HHmmss" else "hhmmss").format(Date(System.currentTimeMillis()))
                val listOfTime = Array(time.length) { time[it].toString().toInt() }

                panel.listOfTime = listOfTime
                panel.repaint()
            }
        }, 0L, 1000L)
    }

    private fun drag(panel: SevenSegmentPanel) {
        panel.addMouseListener(object : MouseAdapter() {
            override fun mousePressed(event: MouseEvent) {
                pX = event.x
                pY = event.y
            }

            override fun mouseDragged(event: MouseEvent) {
                setLocation(location.x + event.x - pX, location.y + event.y - pY)
            }
        })

        panel.addMouseMotionListener(object : MouseMotionAdapter() {
            override fun mouseDragged(event: MouseEvent) {
                setLocation(location.x + event.x - pX, location.y + event.y - pY)
            }
        })
    }
}