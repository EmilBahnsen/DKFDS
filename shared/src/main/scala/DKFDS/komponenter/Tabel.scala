package DKFDS.komponenter
import DKFDS.komponenter.Tabel.LineHeight.Normal
import scalatags.Text.all.*

trait Tabel(nRows: Int) extends Komponent:
    val tableId: String = s"$anId-tabel"
    val rowIds: Seq[String] = Range.inclusive(1,nRows).map(i => s"$tableId-check-$i")

object Tabel:
    enum LineHeight(val modifier: Modifier):
        case Normal extends LineHeight(cls := "")
        case Compact extends LineHeight(cls := "table--compact")
        case ExtraCompact extends LineHeight(cls := "table--extracompact")

    def apply(anId: String)(headers: Seq[String],
                            data: Seq[Seq[String]],
                            lineHeight: LineHeight = Normal): Tabel with Komponent = new Tabel(data.length) with Komponent(anId):
        override def tag: BaseTagType = div(cls := "table--responsive-scroll") (
            table(cls := "table", lineHeight.modifier, id := tableId) (
                thead(
                    tr(
                        headers.map(th(_))*
                    )
                ),
                tbody(
                    data.map(row =>
                        tr(
                            row.map(td(_))*
                        )
                    )
                )
            )
        )

    def apply(anId: String)(headers: Seq[String],
                            data: Seq[Seq[BaseTagType]],
                            selectValues: Seq[String],
                            lineHeight: LineHeight): Tabel with Komponent = new Tabel(data.length) with Komponent(anId):
        override def tag: BaseTagType = div(cls := "table--responsive-scroll")(
            table(cls := "table table--selectable", lineHeight.modifier, id := tableId)(
                thead(
                    tr(
                        th(
                            input(
                                id := s"$tableId-check-all",
                                `type` := "checkbox", cls := "form-checkbox",
                                aria.controls := rowIds.mkString(" ")
                            ),
                            label(`for` := s"$tableId-check-all", aria.label := "V??lg alle r??kker")(
                                span(cls := "sr-only")("V??lg alle r??kker")
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
                                        name := s"$tableId-check[]",
                                        `type` := "checkbox",
                                        cls := "form-checkbox",
                                        value := selectValue
                                    ),
                                    label(`for` := rowId, aria.label := "V??lg r??kke")(
                                        span(cls := "sr-only")("V??lg r??kke")
                                    )
                                ),
                                row.map(td(_))
                            )
                    }
                )
            )
        )