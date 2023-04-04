package DKFDS.komponenter

import CheckboxTabelJS.*
import DKFDS.Modal
import org.scalajs.dom.{HTMLAnchorElement, document}

object TabelvælgerJS:
  given Conversion[Tabelvælger, Modal] = t => new Modal(document.getElementById(t.modalId))
  extension (vælger: Tabelvælger)
    def vælgerButton: HTMLAnchorElement = document.getElementById(vælger.anId).asInstanceOf[HTMLAnchorElement]
    def init(): Unit = {
      CheckboxTabelJS.init(vælger.table)()
      vælgerButton.onclick = e => {
        vælger.show()
      }
      vælger.table.onupdated_=(e => {
        val values = vælger.table.selectedValues
        if values.nonEmpty then
          vælgerButton.innerText = values.mkString(", ")
        else
          vælgerButton.innerText = vælger.defaultText
      })
    }
