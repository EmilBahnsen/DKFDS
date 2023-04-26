package DKFDS.komponenter

import DKFDS.komponenter.CheckboxList.Checkbox
import org.scalajs.dom.*

object CheckboxJS:
  given Conversion[Checkbox, Boolean] = c => document.getElementById(c.anId).asInstanceOf[HTMLInputElement].checked
  extension (box: Checkbox)
    def checkedAnd(pred: => Boolean): Boolean = box.checked && pred
