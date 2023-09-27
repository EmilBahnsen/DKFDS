package DKFDS.komponenter

import scalatags.Text.all.*

import scala.language.postfixOps

case class Radioknapper(override val anId: String, aLegend: String, options: (String, String)*) extends Komponent2:
  override val tag: Tag = div(cls := "form-group", id := anId)(
    fieldset(role := "radiogroup")(
      legend(cls := "form-label")(aLegend)
    )(
      options.zip(Iterator.from(1)).map {
        case ((aLabel, v), i) =>
          div(cls := "form-group-radio") {
              input(
                `type` := "radio",
                id := s"$anId-opt$i",
                name := s"$anId-opt",
                cls := "form-radio radio-small",
                value := v,
                if i == 1 then checked
              )(
                label(cls := "form-label", `for` := s"$anId-opt$i")(aLabel)
              )
          }
      } toSeq
    )
  )
