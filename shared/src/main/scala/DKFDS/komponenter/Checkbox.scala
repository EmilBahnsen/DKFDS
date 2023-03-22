package DKFDS.komponenter

import DKFDS.komponenter.Checkbox.*
import DKFDS.komponenter.Checkbox.Size.*
import scalatags.Text.all.*

case class Checkbox(anId: String):
  def apply(text: String, aValue: String, size: Size = Large) = new Checkbox(anId) with DOMTag2:
    override def tag: BaseTagType = li(
      input(id := anId, `type` := "checkbox", size.name, value := aValue, cls := "form-checkbox", size.cls),
      label(`for` := anId)(text)
    )

object Checkbox:
  enum Size(aName: String, aClass: String):
    val name: Modifier = scalatags.Text.all.name := aName
    val cls: Modifier = scalatags.Text.all.cls := aClass
    case Large extends Size("checkbox-large[]", "checkbox-large")
    case Small extends Size("checkbox-small[]", "")