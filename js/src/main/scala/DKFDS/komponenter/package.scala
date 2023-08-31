package DKFDS

import DKFDS.komponenter.CheckboxList.Checkbox
import DKFDS.komponenter.Komponent.DOMTool
import org.scalajs.dom.*
import scalatags.Text
import scalatags.Text.all.Frag


package object komponenter:
    export DKFDS.komponenter.DropdownJS.given_Conversion_Dropdown_HTMLSelectElement
    export DKFDS.komponenter.AttachmentJS.given_Conversion_Attachment_HTMLInputElement
    export DKFDS.komponenter.TooltipJS.*
    export DKFDS.komponenter.TooltipJS.given_Conversion_Tooltip_HTMLButtonElement
    export DKFDS.komponenter.FormularJS.given_Conversion_Formular_HTMLFormElement
    export DKFDS.komponenter.Komponent.given_Conversion_Komponent2_Tag

    given Conversion[Komponent2, Element] = k => document.getElementById(k.anId)

    given DOMTool = new DOMTool:
        override def replaceTag(anId: String, tag: Frag): Unit = ???

        override def replaceInnerHtml(anId: String, tag: Frag): Unit =
            document.getElementById(anId).innerHTML = tag.render

        override def addClass(anId: ByteString, text: ByteString): Unit =
            document.getElementById(anId).classList.add(text)

        override def removeClass(anId: ByteString, text: ByteString): Unit =
            document.getElementById(anId).classList.remove(text)

        override def replaceInnerText(anId: String, text: String): Unit =
            document.getElementById(anId).innerText = text

    extension (komponent: Komponent with DOMTag2)
        def updatePlaceholder(): Unit = document.getElementById(komponent.anId).innerHTML = komponent

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
        def initSelectableRows(): Unit = new TableSelectableRows(document.getElementById(table.tableId)).init()
        def selectableRows: Seq[HTMLInputElement] = table.getElementsByTagName("input").toSeq.map(_.asInstanceOf[HTMLInputElement])
        def selected: Seq[HTMLInputElement] = {
            selectableRows.filter(_.parentElement.parentElement.classList.contains("table-row-selected"))
        }
        def selectedValues: Seq[String] = selected.map(_.value)
        def selectedRows: Seq[HTMLTableRowElement] = {
            selectableRows
              .map(_.parentElement.parentElement.asInstanceOf[HTMLTableRowElement])
              .filter(_.classList.contains("table-row-selected"))
        }
        def selected_=(values: Seq[String]): Unit = {
            selectableRows.filter(row => values.contains(row.value)) // TODO Donøt work with the value!
            ???
        }

    extension (rows: Seq[HTMLTableRowElement])
        def getCol(col: Int): Seq[String] = {
            rows.map(_.getElementsByTagName("td")(col).innerText)
        }

    extension (strukturliste: StrukturListe)
        def setValue(header: String, value: String): Unit = {
            Option(document.getElementById(strukturliste.anId + header)).foreach(_.innerText = value)
        }

    given Conversion[Button, HTMLButtonElement] = b => document.getElementById(b.anId).asInstanceOf[HTMLButtonElement]

    given Conversion[Checkbox, HTMLInputElement] = b => document.getElementById(b.anId).asInstanceOf[HTMLInputElement]
