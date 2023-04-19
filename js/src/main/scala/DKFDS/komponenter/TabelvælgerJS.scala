package DKFDS.komponenter

import CheckboxTabelJS.*
import DKFDS.Modal
import org.scalajs.dom.{HTMLAnchorElement, document}

import scala.collection.immutable.{AbstractSeq, LinearSeq}

object TabelvælgerJS:
  given Conversion[Tabelvælger, Modal] = t => new Modal(document.getElementById(t.modalId))
  extension (vælger: Tabelvælger)
    def vælgerButton: HTMLAnchorElement = document.getElementById(vælger.anId).asInstanceOf[HTMLAnchorElement]
    def init(): Unit = {
      vælger.table.init()
      vælgerButton.onclick = e => {
        vælger.show()
      }
      vælger.table.onupdated_=(e => {
        // Map all the data as text and display it
        val values = vælger.table.selectedRows.map(_.innerText.replace("Vælg række", ""))  // TODO
        vælgerButton.innerText = values match
          case Nil => vælger.defaultText
          case _ => values.mkString(",\n")
      })
    }
