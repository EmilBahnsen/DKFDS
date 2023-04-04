package DKFDS.komponenter

import DKFDS.komponenter.Komponent.given_Conversion_Komponent2_Tag
import DKFDS.komponenter.CheckboxList.*
import DKFDS.komponenter.CheckboxList.Size.*
import scalatags.Text
import scalatags.Text.all.*

case class CheckboxList(override val anId: String, aLabel: String, checkboxes: Checkbox*) extends Komponent2:
  override val tag: Tag = div(cls := "form-group", id := anId)(
    fieldset(
      legend(cls := "form-label")(aLabel),
      ul(cls := "nobullet-list") {
        val ids = Iterator.iterate(0)(_ + 1).map(anId + "-" + _.toString)
        checkboxes.map(li(_))
      }
    )
  )

object CheckboxList:
  enum Size(aClass: String):
    val cls: Modifier = scalatags.Text.all.cls := aClass
    case Large extends Size("checkbox-large")
    case Small extends Size("")

  case class Checkbox(text: String,
                      nameValue: (String, Any),
                      size: Size,
                      private val isChecked: Boolean = false,
                      content: Seq[Tag] = Nil) extends Komponent2:
    override val anId: String = s"${nameValue._1}-${nameValue._2}"
    override val tag: Tag = div(cls := "form-group")(
      input(
        id := anId, `type` := "checkbox", name := nameValue._1, value := nameValue._2.toString, size.cls,
        cls := "form-checkbox", if isChecked then checked
      )(
        {
          if content.nonEmpty then
            Seq(
              aria.expanded := false,
              aria.controls := s"collapse-$anId",
              cls := "js-checkbox-toggle-content"
            )
          else
            Seq()
        }*
      ),
      label(`for` := anId)(text),
      div(id := s"collapse-$anId", aria.hidden := "true", cls := "checkbox-content")(  // TODO  checkbox-content-large
        // Don't space out the content so much
        content.map(_(marginTop := "5px"))*
      )
    )

    def apply(content: Tag*): Checkbox = copy(content = content)

  object Checkbox:
    def small(text: String, nameValue: (String, Any), isChecked: Boolean = false): Checkbox =
      Checkbox(text, nameValue, size = Small, isChecked)
    def large(text: String, nameValue: (String, Any), isChecked: Boolean = false): Checkbox =
      Checkbox(text, nameValue, size = Large, isChecked)