package DKFDS.komponenter

import org.scalajs.dom.{HTMLInputElement, document}

object InputfeltJS:
  given Conversion[Inputfelt, HTMLInputElement] = f => document.getElementById(f.anId).asInstanceOf[HTMLInputElement]
  given Conversion[Inputfelt, String] = f => f.value
