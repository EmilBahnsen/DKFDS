package DKFDS.komponenter

import org.scalajs.dom.{HTMLTableElement, document}
import scalatags.Text.all.*

object TabelJS:
  extension (tabel: Tabel)
    def tsv: String = tabel.tHead.getElementsByTagName("th").map(_.innerText.clean).mkString("\t") + "\n" + tabel.tBodies.map(
      _.getElementsByTagName("tr").map(
        _.getElementsByTagName("td").map(_.innerText.clean).mkString("\t")
      ).mkString("\n")
    ).mkString("\n")

    def exportTsv(headers: String*): String = {
      val data = tabel.tBodies.flatMap(_.getElementsByTagName("tr")).map(row =>
        row.querySelectorAll("[data-export]").map(d =>
          d.getAttribute("data-export") -> d.innerText.replace('\n', ';')
        ).toMap
      )

      val values = data.map(row =>
        headers.map(key => row.getOrElse(key, ""))
      )

      headers.mkString("\t") + "\n" + values.map(_.mkString("\t")).mkString("\n")
    }

  extension (str: String)
    protected def clean: String = str.replace("\n","").replace("\t","")

  val exp: Attr = data("export")
