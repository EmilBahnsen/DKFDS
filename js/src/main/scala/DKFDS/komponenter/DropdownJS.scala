package DKFDS.komponenter

object DropdownJS:

  import org.scalajs.dom.*

  given Conversion[Dropdown, HTMLSelectElement] =
    d => document.getElementById(d.anId).asInstanceOf[HTMLSelectElement]

  extension (drop: Dropdown)
    def hide(): Unit = document.getElementById(drop.groupId).setAttribute("hidden", "")
    def unhide(): Unit = document.getElementById(drop.groupId).removeAttribute("hidden")
    def valueFromURL: String = new URLSearchParams(window.location.search).get(drop.name)
      
    