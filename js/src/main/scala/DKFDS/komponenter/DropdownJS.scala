package DKFDS.komponenter

object DropdownJS:

  import org.scalajs.dom.*

  given Conversion[Dropdown, HTMLSelectElement] =
    d => document.getElementById(d.anId).asInstanceOf[HTMLSelectElement]
