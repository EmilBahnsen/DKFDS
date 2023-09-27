package DKFDS.komponenter
import DKFDS.komponenter.Tabel.LineHeight
import DKFDS.komponenter.Tabel.LineHeight.Normal
import scalatags.Text.all.*

import java.util.UUID
import scala.annotation.targetName

case class Tabel(override val anId: String) extends Komponent(anId):
  val tableId: String = s"$anId-tabel"

  def apply(headers: Seq[Frag],
            data: Seq[Seq[Tag]],
            lineHeight: LineHeight = Normal,
            zebra: Boolean = false): Tabel with DOMTag2 = new Tabel(anId) with DOMTag2:
    override def tag: BaseTagType = div(cls := "table--responsive-scroll")(
      table(cls := "table", cls := (if zebra then "table--zebra" else ""), lineHeight.modifier, id := tableId)(
        thead(
          tr(
            headers.map(th(_)) *
          )
        ),
        tbody(
          data.map(row =>
            tr(
              row.map(t => if t.tag == td.tag || t.tag == th.tag then t else td(t))*
            )
          )
        )
      )
    )

  def implicitHeader(data: Seq[Seq[BaseTagType]],
                     lineHeight: LineHeight,
                     zebra: Boolean): Tabel with DOMTag2 =
    apply(data.head, data.tail, lineHeight, zebra)

  def apply(headers: Seq[String|BaseTagType],
            data: Seq[Seq[BaseTagType]],
            selectValues: Seq[String],
            lineHeight: LineHeight,
            zebra: Boolean): Tabel with DOMTag2 = new Tabel(anId) with DOMTag2:
    val rowIds: Seq[String] = Range.inclusive(1, data.length).map(i => s"$tableId-check-$i")

    override def tag: BaseTagType = div(cls := "table--responsive-scroll")(
      table(cls := "table table--selectable", cls := (if zebra then "table--zebra" else ""), lineHeight.modifier, id := tableId)(
        thead(
          tr(
            th(
              input(
                id := s"$tableId-check-all",
                `type` := "checkbox", cls := "form-checkbox",
                aria.controls := rowIds.mkString(" ")
              ),
              label(`for` := s"$tableId-check-all", aria.label := "Vælg alle rækker")(
                span(cls := "sr-only")("Vælg alle rækker")
              )
            ),
            headers match
              case x: Seq[String] => x.map(th(_))
              case x: Seq[BaseTagType] => x.map(th(_))
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

  def apply(data: Seq[Product], lineHeight: LineHeight): Tabel with DOMTag2 = if data.nonEmpty then {
    val labels = data.head.productElementNames.map(span(_)).toSeq
    val displayData = data.map(_.productIterator.map(e => span(e.toString)).toSeq)
    Tabel(UUID.randomUUID().toString)(labels, displayData, lineHeight)
  } else Tabel("")(Seq("No data"), Seq(Seq(span("No data"))), lineHeight)

object Tabel:
  enum LineHeight(val modifier: Modifier):
    case Normal extends LineHeight(cls := "")
    case Compact extends LineHeight(cls := "table--compact")
    case ExtraCompact extends LineHeight(cls := "table--extracompact")

