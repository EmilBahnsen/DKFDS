package DKFDS.komponenter
import scalatags.Text.all.*

case class Inputfelt(override val anId: String, aLabel: String,
                     aValue: String = "",
                     isRequired: Boolean = false,
                     isDisabled: Boolean = false,
                     isReadonly: Boolean = false,
                     aPattern: String = "") extends Komponent2:
  private val inputMods = {if isRequired then Seq(required) else Seq.empty} ++
    {if isDisabled then Seq(disabled) else Seq.empty} ++
    {if isReadonly then Seq(readonly) else Seq.empty} ++
    {if aPattern.nonEmpty then Seq(pattern := aPattern) else Seq.empty}
  override val tag: Tag = div(cls := "form-group")(
    label(cls := "form-label", `for` := anId)(aLabel),
    input(`type` := "text", id := anId, name := anId, cls := "form-input", value := aValue)(
      inputMods*
    )
  )
