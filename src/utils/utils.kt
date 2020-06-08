package utils

import java.awt.Color

const val SEGMENT_WIDTH = 60.0
const val SEGMENT_HEIGHT = 30.0
const val SEGMENT_DIVIDER = 20.0
const val STROKE_WIDTH = 5.0f

val BACKGROUND = Color(0x111111)
val ACTIVE_COLOR = Color(0xEF9D3E)
val INACTIVE_COLOR = Color(0x222222)

val NUMBER_MAP = hashMapOf(
        0 to (Segment.A.value or Segment.B.value or Segment.C.value or Segment.D.value or Segment.E.value or Segment.F.value),
        1 to (Segment.B.value or Segment.C.value),
        2 to (Segment.A.value or Segment.B.value or Segment.D.value or Segment.E.value or Segment.G.value),
        3 to (Segment.A.value or Segment.B.value or Segment.C.value or Segment.D.value or Segment.G.value),
        4 to (Segment.B.value or Segment.C.value or Segment.F.value or Segment.G.value),
        5 to (Segment.A.value or Segment.C.value or Segment.D.value or Segment.F.value or Segment.G.value),
        6 to (Segment.A.value or Segment.C.value or Segment.D.value or Segment.E.value or Segment.F.value or Segment.G.value),
        7 to (Segment.A.value or Segment.B.value or Segment.C.value),
        8 to (Segment.A.value or Segment.B.value or Segment.C.value or Segment.D.value or Segment.E.value or Segment.F.value or Segment.G.value),
        9 to (Segment.A.value or Segment.B.value or Segment.C.value or Segment.D.value or Segment.F.value or Segment.G.value)
)