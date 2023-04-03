package DKFDS.komponenter
import scalatags.Text.all.*
import scalatags.Text.all.{tag => t}
import java.util.UUID
import scala.quoted.*

case class Tooltip(override val anId: String, text: String, icon: String = "help", triggerClick: Boolean = true) extends Komponent2:
  override val tag: Tag = button(
    id := anId,
    cls := "js-tooltip button-unstyled",
    data("tooltip") := text,
    if triggerClick then data("tooltop-trigger") := "click"
  )(
    t("svg")(cls := "icon-svg", aria.hidden := "true")(
      t("use")(attr("xlink:href") := s"img/all-svg-icons.svg#$icon"),
      span(cls := "sr-only")(text)
    )
  )

object Tooltip:
  def deleteIcon(anId: String, text: String): Tooltip = Tooltip(anId, text, "trash-can", triggerClick = false)