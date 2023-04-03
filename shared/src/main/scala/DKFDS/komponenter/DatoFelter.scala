package DKFDS.komponenter
import scalatags.Text.all.*

case class DatoFelter(override val anId: String, aLabel: String, hint: String = "") extends Komponent2:
  override val tag: Tag = fieldset(id := anId, aria.describedby := "")(  // TODO
    legend(cls := "form-label")(aLabel),
    if hint.nonEmpty then span(cls := "form-hint")(hint),
    div(cls := "date-group js-calendar-group mt-3")(
      // Day
      div(cls := "form-group form-group-day")(
        label(cls := "form-label", `for` := s"$anId-date-day")("Dag"),
        input(
          cls := "form-input js-calendar-day-input",
          id := s"$anId-date-day",
          value := "",
          `type` := "tel",
          data("min") := "1",
          data("max") := "31",
          maxlength := "2",
          pattern := "^[0-9]{0,2}$",
          data("input-regex") := "^[0-9]{0,2}$",
          title := "Indskriv dag på måneden som tal",
          aria.describedby := "" // TODO
        )
      ),
      // Month
      div(cls := "form-group form-group-month")(
        label(cls := "form-label", `for` := s"$anId-date-month")("Måned"),
        input(
          cls := "form-input js-calendar-month-input",
          id := s"$anId-date-month",
          value := "",
          `type` := "tel",
          data("min") := "1",
          data("max") := "21",
          maxlength := "2",
          pattern := "^[0-9]{0,2}$",
          data("input-regex") := "^[0-9]{0,2}$",
          title := "Indskriv månedens nummer",
          aria.describedby := "" // TODO
        )
      ),
      // Year
      div(cls := "form-group form-group-year")(
        label(cls := "form-label", `for` := s"$anId-date-year")("År"),
        input(
          cls := "form-input js-calendar-year-input",
          id := s"$anId-date-year",
          value := "",
          `type` := "tel",
          data("min") := "1900",
          data("max") := "3000",
          maxlength := "4",
          pattern := "^[0-9]{0,4}$",
          data("input-regex") := "^[0-9]{0,4}$",
          title := "Indskriv årstal",
          aria.describedby := "" // TODO
        )
      )
    )
  )
