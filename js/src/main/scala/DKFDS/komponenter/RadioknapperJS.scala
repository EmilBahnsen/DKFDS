package DKFDS.komponenter

import org.scalajs.dom.HTMLInputElement

object RadioknapperJS:
  extension (radioknapper: Radioknapper)
    def inputs: Seq[HTMLInputElement] = radioknapper.getElementsByTagName("input").map(_.asInstanceOf[HTMLInputElement]).toSeq
    def selectedValue: Option[String] = inputs.find(_.checked).map(_.value)
