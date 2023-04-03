package DKFDS.komponenter

import DKFDS.komponenter.CheckboxList.Checkbox

object CheckboxJS:
  extension (box: Checkbox)
    def checkedAnd(pred: => Boolean): Boolean = box.checked && pred
