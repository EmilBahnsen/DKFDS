package DKFDS.komponenter

import scalatags.Text.all.*

trait StrukturListe extends Komponent

object StrukturListe:
    def apply(anId: String)(rows: (String, String)*): StrukturListe with Komponent = new StrukturListe with Komponent(anId):
        override def tag: BaseTagType = div(cls := "container")(
            rows.map {
                case (header, content) => div(cls := "row row-bordered no-gutters") (
                    div(cls := "col-md-4 weight-semibold")(header),
                    div(cls := "col-md-8")(content)
                )
            }
        )
