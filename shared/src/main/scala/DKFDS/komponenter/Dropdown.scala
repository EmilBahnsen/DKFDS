package DKFDS.komponenter

import DKFDS.komponenter.Komponent.*
import scalatags.Text
import scalatags.Text.all.*

case class Dropdown(override val anId: String, labelText: String, someValues: Map[Any, String]) extends Komponent2:
  override val tag: Tag = div(
    label(cls := "form-label", `for` := anId)(labelText),
    select(cls := "form-select", name := anId, id := anId)(
      someValues.map(v => option(value := v._1.toString)(v._2)).toSeq
    )
  )

  def values_=(newValues: Map[Any, String])(using dt: DOMTool): Unit = dt.replaceInnerHtml(anId,
    newValues.map(v => option(value := v._1.toString)(v._2)).toSeq
  )

object Dropdown:
  def apply(anId: String, labelText: String, value: String): Dropdown =
    new Dropdown(anId, labelText, Map(0 -> value))