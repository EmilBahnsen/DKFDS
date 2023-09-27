package DKFDS.komponenter
import scalatags.Text
import scalatags.Text.all.{tag as tagg, *}

abstract class OverflowMenu(override val anId: String) extends Komponent2:
  val buttonId: String = s"$anId-overflow-button"

object OverflowMenu:
  def noDots(anId: String, text: Tag, items: Tag*): OverflowMenu = new OverflowMenu(anId):
    override val tag: Tag = div(cls := "overflow-menu overflow-menu--open-right")(
      button(
        id := buttonId,
        cls := "button-overflow-menu js-dropdown",
        data("js-target") := this.anId,
        aria.haspopup := true,
        aria.expanded := false
      )(
        // Combine with function link when it's without dots!
        a(href := "#", cls := "function-link")(text),
        // TODO Maybe this sometimes
        /*
        tagg("svg")(cls := "icon-svg", attr("focusable") := false, aria.hidden := true)(
          tagg("use")(attr("xlink:href") := "img/all-svg-icons.svg#more-vert")
        )
        */
      ),
      div(cls := "overflow-menu-inner", id := this.anId, aria.hidden := true)(
        ul(cls := "overflow-list")(
          items.map(li(_))
        )
      )
    )