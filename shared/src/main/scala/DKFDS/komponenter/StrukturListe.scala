package DKFDS.komponenter

import scalatags.Text.all.*

trait StrukturListe extends DOMTag

object StrukturListe:
  def apply(someId: String)(rows: (String, String)*): StrukturListe = new StrukturListe with DOMTag(someId):
    override def tag: BaseTagType = div(cls := "container", id := someId)(
      rows.map {
        case (header, content) => div(cls := "row row-bordered no-gutters")(
          div(cls := "col-md-4 weight-semibold")(header),
          div(cls := "col-md-8", id := someId + header)(content)
        )
      }
    )
