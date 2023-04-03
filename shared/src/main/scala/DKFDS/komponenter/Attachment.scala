package DKFDS.komponenter

import DKFDS.komponenter.Komponent.*
import scalatags.Text
import scalatags.Text.all.*

case class Attachment(override val anId: String, aLabel: Tag) extends Komponent2:
    override val tag: Tag = div(
        label(cls := "form-label", `for` := anId, id := s"$anId-label")(aLabel),
        input(`type` := "file", id := anId, name := "file", accept := ".json")
    )

    def label_=(newText: String)(using dt: DOMTool): Unit = dt.replaceInnerText(s"$anId-label", newText)

object Attachment:
    def apply(anId: String, aLabel: String): Attachment =
        Attachment(anId, span(aLabel))