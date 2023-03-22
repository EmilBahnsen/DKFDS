package DKFDS.komponenter

import DKFDS.komponenter.Komponent.*
import scalatags.Text
import scalatags.Text.all.*

case class Attachment(override val anId: String, labelText: String) extends Komponent2:
    override val tag: Tag = div(
        label(cls := "form-label", `for` := anId, id := s"$anId-label")(labelText),
        input(`type` := "file", id := anId, name := "file", multiple, attr("directory").empty, attr("webkitdirectory").empty)
    )

    def label_=(newText: String)(using dt: DOMTool): Unit = dt.replaceInnerText(s"$anId-label", newText)
