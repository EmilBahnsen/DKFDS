package DKFDS.komponenter

import DKFDS.komponenter.Komponent.*
import scalatags.Text
import scalatags.Text.all.*

case class Dropdown(override val anId: String, labelText: String, someValues: Map[Any, String], isDisabled: Boolean) extends Komponent2:
  val groupId: String = s"$anId-group"
  private val selectMods = if isDisabled then Seq(disabled) else Seq.empty
  override val tag: Tag = div(cls := "form-group", id := s"$anId-group")(
    label(cls := "form-label", `for` := anId)(labelText),
    select(cls := "form-select", name := anId, id := anId)(selectMods*)(
      someValues.map(v => option(value := v._1.toString)(v._2)).toSeq
    )
  )

  def values_=(newValues: Seq[(Any, String)])(using dt: DOMTool): Unit = dt.replaceInnerHtml(anId,
    newValues.map(v => option(value := v._1.toString)(v._2)).toSeq
  )

object Dropdown:
  def apply(anId: String, labelText: String, value: String, isDisabled: Boolean = false): Dropdown =
    new Dropdown(anId, labelText, Map(0 -> value), isDisabled)