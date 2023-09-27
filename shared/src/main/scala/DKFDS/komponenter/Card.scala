package DKFDS.komponenter
import scalatags.Text
import scalatags.Text.all.*

case class Card(override val anId: String)(content: Tag*) extends Komponent2:
  protected val idsItr: Iterator[String] = Iterator.from(1).map(i => s"$anId-$i")
  override val tag: Tag = div(cls := "card", id := anId)(content*)
  val subTextId: String = s"$anId-sub-text"

object Card:
  lazy val cardHeader: ConcreteHtmlTag[String] = div(cls := "card-header")
  lazy val cardTitle: ConcreteHtmlTag[String] = h2(cls := "header-title")
  lazy val cardSubHeader: ConcreteHtmlTag[String] = p(cls := "sub-header")
  lazy val cardContent: ConcreteHtmlTag[String] = div(cls := "card-content")
  lazy val cardText: ConcreteHtmlTag[String] = div(cls := "card-text")
  lazy val cardFooter: ConcreteHtmlTag[String] = div(cls := "card-footer")

  def apply(anId: String, headerText: String, subHeaderText: String, subText: String)(content: Tag*): Card = Card(anId) (
    cardHeader(
      cardTitle(headerText),
      cardSubHeader(subHeaderText)
    ),
    cardContent(backgroundColor := "oldlace")(
      content
    ),
    cardText(id := s"$anId-sub-text")(
      p(subText)
    )
  )