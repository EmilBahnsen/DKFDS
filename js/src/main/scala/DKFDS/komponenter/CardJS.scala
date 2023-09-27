package DKFDS.komponenter

import org.scalajs.dom.{HTMLDivElement, document}

object CardJS:
  extension (card: Card)
    def title: HTMLDivElement = card.getElementsByClassName("header-title").head.asInstanceOf[HTMLDivElement]
    def subText: HTMLDivElement = document.getElementById(card.subTextId).asInstanceOf[HTMLDivElement]
    def content: HTMLDivElement = card.getElementsByClassName("card-content").head.asInstanceOf[HTMLDivElement]
