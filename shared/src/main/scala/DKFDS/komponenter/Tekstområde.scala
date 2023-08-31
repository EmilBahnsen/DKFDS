package DKFDS.komponenter

import scalatags.Text
import scalatags.Text.all.*

trait Tekstområde extends DOMTag:
  val formId = s"$anId-form"
  val areaId = s"$anId-area"
  val errorId = s"$anId-error"

object Tekstområde:
    def apply(anId: String)
             (labelText: String,
              rowsNum: Int,
              errorText: String = "",
              widthClass: String = "",
              fontFamilyName: String = "inherit", isDisabled: Boolean = false): Tekstområde =
      new Tekstområde with DOMTag(anId):
        private val areaMods = if isDisabled then Seq(disabled) else Seq.empty
        override def tag: BaseTagType =
          div(cls := "form-group", id := formId)(
            label(cls := "form-label", `for` := areaId)(labelText),
            span(cls := "form-error-message", id := errorId)(
              span(cls := "sr-only")("Fejl:"),
              errorText
            ),
            textarea(areaMods*)(
              cls := "form-input " + widthClass,
              rows := rowsNum,
                        id := areaId,
                        name := areaId,
                        aria.describedby := errorId,
                        fontFamily := fontFamilyName
                    )
                )
