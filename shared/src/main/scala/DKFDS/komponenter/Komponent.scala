package DKFDS.komponenter

import scalatags.Text.all.*

trait Komponent(val anId: String):
  val placeholderId: String = s"$anId-placeholder"
  val placeholder: BaseTagType = div(id := s"$anId-placeholder")

trait Komponent2:
  val anId: String
  val tag: Tag

trait DOMTag(val anId: String):
  def tag: BaseTagType

trait DOMTag2:
  def tag: BaseTagType

object Komponent:
  given Conversion[Komponent2, Tag] = _.tag

  given Conversion[DOMTag, BaseTagType] = _.tag

  given Conversion[DOMTag2, BaseTagType] = _.tag

  given Conversion[DOMTag, String] = _.tag.render

  given Conversion[DOMTag2, String] = _.tag.render

  trait DOMTool:
    def replaceTag(anId: String, tag: Frag): Unit

    def replaceInnerHtml(anId: String, tag: Frag): Unit

    def replaceInnerText(anId: String, text: String): Unit
