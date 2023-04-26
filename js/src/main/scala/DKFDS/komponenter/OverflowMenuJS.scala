package DKFDS.komponenter

import org.scalajs.dom.*

object OverflowMenuJS:
  extension (menu: OverflowMenu)
    def init(): Unit = new DKFDS.Dropdown(document.getElementById(menu.buttonId)).init()
