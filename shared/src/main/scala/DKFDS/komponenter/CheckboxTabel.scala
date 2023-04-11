package DKFDS.komponenter
import DKFDS.komponenter.Tabel.LineHeight
import scalatags.Text.all.*

class CheckboxTabel(override val anId: String,
                    headers: Seq[String],
                    data: Seq[Seq[Tag]],
                    selectValues: Seq[String],
                    val lineHeight: LineHeight) extends Komponent2:
  private val rowIds: Seq[String] = Range.inclusive(1, data.length).map(i => s"$anId-check-$i")
  val wrapperId: String = s"$anId-wrapper"
  override val tag: Tag = div(cls := "table--responsive-scroll", id := wrapperId)(
    table(cls := "table table--selectable", lineHeight.modifier, id := anId)(
      thead(
        tr(
          th(
            input(
              id := s"$anId-check-all",
              `type` := "checkbox", cls := "form-checkbox",
              aria.controls := rowIds.mkString(" ")
            ),
            label(`for` := s"$anId-check-all", aria.label := "Vælg alle rækker")(
              span(cls := "sr-only")("Vælg alle rækker")
            )
          ),
          headers.map(th(_))
        )
      ),
      tbody(
        data.zip(selectValues).zip(rowIds).map {
          case ((row, selectValue), rowId) =>
            tr(
              td(
                input(
                  id := rowId,
                  name := s"$anId-check",
                  `type` := "checkbox",
                  cls := "form-checkbox",
                  value := selectValue
                ),
                label(`for` := rowId, aria.label := "Vælg række")(
                  span(cls := "sr-only")("Vælg række")
                )
              ),
              row.map(td(_))
            )
        }
      )
    )
  )

object CheckboxTabel:
  def loading(anId: String): CheckboxTabel = CheckboxTabel(anId, Seq("Loader..."), Seq(Seq(span("Loader..."))), Seq("Loader..."), LineHeight.ExtraCompact)