package DKFDS.komponenter

import scalatags.Text
import scalatags.Text.all.*
import DKFDS.komponenter.Komponent.given_Conversion_Komponent2_Tag

case class Formular(override val anId: String, submitButton: Button, content: Seq[Tag] = Nil) extends Komponent2:
  override val tag: Text.all.Tag = form(id := anId, attr("novalidate").empty)(
    content.map(_(marginBottom := "1em"))*
  )(submitButton(name := "submit"))

  def apply(moreContent: Tag*): Formular = copy(content = content ++ moreContent)

