package DKFDS.komponenter

import scalatags.Text
import scalatags.Text.all.*

trait Tekstområde extends Komponent:
    val formId = s"$anId-form"
    val areaId = s"$anId-area"
    val errorId = s"$anId-error"

object Tekstområde:
    def apply(anId: String)
             (labelText: String, errorText: String = "", rowsNum: Int, widthClass: String, fontFamilyName: String): Tekstområde =
        new Tekstområde with Komponent(anId):
            override def tag: BaseTagType =
                div(cls := "form-group", id := formId)(
                    label(cls := "form-label", `for` := areaId)(labelText),
                    span(cls := "form-error-message", id := errorId)(
                        span(cls := "sr-only")("Fejl:"),
                        errorText
                    ),
                    textarea(
                        cls := "form-input " + widthClass,
                        rows := rowsNum,
                        id := areaId,
                        name := areaId,
                        aria.describedby := errorId,
                        fontFamily := fontFamilyName
                    )
                )
