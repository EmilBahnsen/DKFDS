package DKFDS.komponenter
import scalatags.Text.all.*

case class Inputfelt(override val anId: String, aLabel: String) extends Komponent2:
  override val tag: Tag = div(cls := "form-group")(
    label(cls := "form-label", `for` := anId)(aLabel),
    input(`type` := "text", id := anId, name := anId, cls := "form-input", required)
  )
