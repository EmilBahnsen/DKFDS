package DKFDS.komponenter

import scalatags.Text.all.*

trait Komponent(val anId: String):
    def tag: BaseTagType

object Komponent:
    given Conversion[Komponent, BaseTagType] = _.tag
    given Conversion[Komponent, String] = _.tag.render