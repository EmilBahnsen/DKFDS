package DKFDS

import org.scalajs.dom.*

package object komponenter:
    extension (acc: Accordion)
        def textPara: HTMLParagraphElement = document.getElementById(acc.tekstId).asInstanceOf[HTMLParagraphElement]
        def text: String = textPara.innerText
        def text_=(value: String): Unit = textPara.innerText = value

    extension (tekstområde: Tekstområde)
        def form: HTMLDivElement = document.getElementById(tekstområde.formId).asInstanceOf[HTMLDivElement]
        def error: HTMLSpanElement = document.getElementById(tekstområde.errorId).asInstanceOf[HTMLSpanElement]
        def area: HTMLInputElement = document.getElementById(tekstområde.areaId).asInstanceOf[HTMLInputElement]
        def errorText: String = error.innerText
        def errorText_=(value: String): Unit = {
            if value.isEmpty then
                form.classList.remove("form-error")
                error.innerText = ""
            else
                form.classList.add("form-error")
                error.innerText = value
        }

    given Conversion[Tabel, HTMLTableElement] = t => document.getElementById(t.tableId).asInstanceOf[HTMLTableElement]
    given Conversion[Tabel, TableSelectableRows] = t => DKFDS.TableSelectableRows(document.getElementById(t.tableId))
    extension (table: Tabel)
        def selectableRows: Seq[HTMLInputElement] = table.rowIds.map(document.getElementById).map(_.asInstanceOf[HTMLInputElement])
        def selected: Seq[HTMLInputElement] = {
            selectableRows.filter(_.parentElement.parentElement.classList.contains("table-row-selected"))
        }
        def selectedRows: Seq[HTMLTableRowElement] = {
            selectableRows
                    .map(_.parentElement.parentElement.asInstanceOf[HTMLTableRowElement])
                    .filter(_.classList.contains("table-row-selected"))
        }
        def selected_=(values: Seq[String]): Unit = {
            selectableRows.filter(row => values.contains(row.value))  // TODO Donøt work with the value!
            ???
        }

    extension (rows: Seq[HTMLTableRowElement])
        def getCol(col: Int): Seq[String] = {
            rows.map(_.getElementsByTagName("td")(col).innerText)
        }
