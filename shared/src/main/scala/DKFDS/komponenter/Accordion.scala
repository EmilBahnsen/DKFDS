package DKFDS.komponenter

import scalatags.Text
import scalatags.Text.all.*

import java.util.UUID

case class Accordion(override val anId: String, content: Seq[(String, Seq[Tag])] = Seq.empty) extends Komponent2:
  def add(title: String, newContent: Tag*): Accordion = copy(content = content :+ (title, newContent))
  override val tag: Tag = ul(cls := "accordion")(
    content map {
      case (title, content) =>
        val controlsId = UUID.randomUUID().toString
        li(
          h2(
            button(cls := "accordion-button", aria.expanded := false, aria.controls := controlsId)(
              title
            )
          ),
          div(id := controlsId, aria.hidden := true, cls := "accordion-content")(
            content*
          )
        )
    }
  )
