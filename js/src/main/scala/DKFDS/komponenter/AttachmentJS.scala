package DKFDS.komponenter

object AttachmentJS:

  import org.scalajs.dom.*

  given Conversion[Attachment, HTMLInputElement] =
    d => document.getElementById(d.anId).asInstanceOf[HTMLInputElement]
