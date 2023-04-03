package DKFDS.komponenter

object TooltipJS:
  import org.scalajs.dom.*
  given Conversion[Tooltip, HTMLButtonElement] = tt => document.getElementById(tt.anId).asInstanceOf[HTMLButtonElement]
  extension (tooltip: Tooltip)
    def init(): Unit = new DKFDS.Tooltip(document.getElementById(tooltip.anId)).init()