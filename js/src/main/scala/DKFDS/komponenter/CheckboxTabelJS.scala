package DKFDS.komponenter

import DKFDS.TableSelectableRows
import org.scalajs.dom.*
import scalatags.Text.all.*

object CheckboxTabelJS:
  given Conversion[CheckboxTabel, HTMLTableElement] = t => document.getElementById(t.anId).asInstanceOf[HTMLTableElement]

  extension (tabel: CheckboxTabel)
    def inputs: Seq[HTMLInputElement] = tabel.getElementsByTagName("input").map(_.asInstanceOf[HTMLInputElement]).toSeq
    def values: Seq[String] = inputs.map(i => Option(i.value)).filter(_.nonEmpty).map(_.get)
    def selectedValues: Seq[String] = inputs.filter(_.checked).map(i => Option(i.value)).filter(_.nonEmpty).map(_.get)

    def init(): Unit = new TableSelectableRows(tabel).init()

    def update(headers: Seq[String], data: Seq[Seq[Tag]], selectValues: Seq[String]): Unit =
      document.getElementById(tabel.wrapperId).replaceWith(
        new CheckboxTabel(tabel.anId, headers, data, selectValues, tabel.lineHeight)
      )

    def update(data: Seq[TableData]): Unit =
      if data.nonEmpty then
        val rowData = data.map(_.productIterator.map(e => span(e.toString)).toSeq)
        update(data.head.productElementNames.toSeq, rowData, data.map(_.value.toString))

    def onupdated_=(f: Event => Unit): Unit = tabel.addEventListener("fds.table.selectable.updated", f)

  trait TableData extends Product:
    def value: Any
