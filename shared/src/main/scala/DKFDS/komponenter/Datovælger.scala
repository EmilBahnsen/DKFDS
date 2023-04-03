package DKFDS.komponenter
import scalatags.Text.all.*

class Datovælger(override val anId: String, aLabel: String) extends Komponent2:
  override val tag: Tag = div(cls := "from-group")(
    label(cls := "form-label", `for` := anId)(aLabel),
    div(cls := "date-picker")(
      input(cls := "input-form", required, id := anId, value := "", name := anId, `type` := "text")
    )
  )