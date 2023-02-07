package DKFDS.komponenter

import scalatags.Text
import scalatags.Text.all.*

import java.util.UUID

trait Accordion extends Komponent:
    val iden = s"$anId-iden"
    val tekstId = s"$anId-text"
    def +:(acc: Accordion): Accordion.AccordionList = new Accordion.AccordionList(this +: acc +: Nil)

object Accordion:
    def apply(anId: String)(title: String, rightText: Option[String] = None, text: String, expanded: Boolean = false): Accordion =
        new Accordion with Komponent(anId):
            override def tag: Text.all.BaseTagType = li(
                h2(
                    button(cls := "accordion-button", aria.expanded := expanded, aria.controls := iden),
                    title,
                    rightText.map(span(cls := "icon_text")(_))
                    // TODO Icon
                ),
                div(id := iden, aria.hidden := !expanded, cls := "accordion-content")(
                    p(id := tekstId)(text)
                )
            )
    def apply(anId: String)(title: String, text: String): Accordion = Accordion(anId)(title, None, text)

    class AccordionList(accs: Seq[Accordion]) extends Komponent(accs.map(_.anId).mkString):
        override def tag: Text.all.BaseTagType = ul(cls := "accordion accordion-multiselectable") (
            accs.map(_.tag)*
        )
        def +:(acc: Accordion): AccordionList = new AccordionList(acc +: accs)
