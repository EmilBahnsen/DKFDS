package DKFDS.komponenter

import org.scalajs.dom.{HTMLInputElement, HTMLTextAreaElement, document}

object TekstområdeJS:
  given område2element: Conversion[Tekstområde, HTMLTextAreaElement] = f => document.getElementById(f.areaId).asInstanceOf[HTMLTextAreaElement]
  given område2string: Conversion[Tekstområde, String] = f => f.value
