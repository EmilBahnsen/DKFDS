package DKFDS.komponenter

import org.scalajs.dom.{HTMLDivElement, document}

object CardJS:
  extension (card: Card)
    def subText: HTMLDivElement = document.getElementById(card.subTextId).asInstanceOf[HTMLDivElement]
