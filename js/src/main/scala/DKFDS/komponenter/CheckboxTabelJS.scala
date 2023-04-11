package DKFDS.komponenter

import DKFDS.TableSelectableRows
import org.scalajs.dom.*
import scalatags.Text.all.*

object CheckboxTabelJS:
  given Conversion[CheckboxTabel, HTMLTableElement] = t => document.getElementById(t.anId).asInstanceOf[HTMLTableElement]

  extension (tabel: CheckboxTabel)
    def inputs: Seq[HTMLInputElement] = 
      tabel.getElementsByTagName("tbody").head.getElementsByTagName("input").map(_.asInstanceOf[HTMLInputElement]).toSeq

    def size: Int = inputs.size
    def values: Seq[String] = inputs.map(i => Option(i.value)).filter(_.nonEmpty).map(_.get)
    def selectedRows: Seq[HTMLTableRowElement] = inputs.filter(_.checked).map(_.parentElement.parentElement.asInstanceOf[HTMLTableRowElement])
    def selectedValues: Seq[String] = inputs.filter(_.checked).map(i => Option(i.value)).filter(_.nonEmpty).map(_.get)

    def init(): Unit = new TableSelectableRows(tabel).init()

    def update(headers: Seq[String], data: Seq[Seq[Tag]], selectValues: Seq[String]): Unit =
      println("Replacing content!")
      val newTable = new CheckboxTabel(tabel.anId, headers, data, selectValues, tabel.lineHeight)
      newTable.init()
      document.getElementById(tabel.wrapperId).innerHTML = newTable.tag.render

    def update(data: Seq[TableData]): Unit =
      if data.nonEmpty then
        val rowData = data.map(_.productIterator.map(e => span(e.toString)).toSeq)
        update(data.head.productElementNames.toSeq, rowData, data.map(_.value.toString))

    def onupdated_=(f: Event => Unit): Unit = tabel.addEventListener("fds.table.selectable.updated", f)

  trait TableData extends Product:
    def value: Any
