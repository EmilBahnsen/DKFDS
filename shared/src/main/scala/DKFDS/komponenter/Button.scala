package DKFDS.komponenter

import DKFDS.komponenter.Button.ButtonVariant
import DKFDS.komponenter.Button.ButtonVariant.*
import scalatags.Text.all.*
import scalatags.text.Builder
import scalatags.{Text, generic}

case class Button(override val anId: String, variant: ButtonVariant, text: String) extends Komponent2:
  override val tag: Tag = button(id := anId, cls := "button", variant.cls)(text)


object Button:
  def primary(anId: String, text: String): Button = Button(anId, Primary, text)
  def secondary(anId: String, text: String): Button = Button(anId, Secondary, text)
  def tertiary(anId: String, text: String): Button = Button(anId, Tertiary, text)
  def back(anId: String, text: String): Button = Button(anId, Back, text)

  enum ButtonVariant(clsName: String):
    val cls = scalatags.Text.all.cls := clsName
    case Primary extends ButtonVariant("button-primary")
    case Secondary extends ButtonVariant("button-secondary")
    case Tertiary extends ButtonVariant("button-tertiary")
    case Back extends ButtonVariant("button-quaternary")
