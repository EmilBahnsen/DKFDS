package DKFDS.komponenter

import java.time.LocalDate
import scala.language.postfixOps
import scala.util.Try

object DatovælgerJS:
  extension (dato: Datovælger)
    def date: Option[LocalDate] = Try {
      LocalDate.parse(
        DKFDS.datePicker.getDatePickerContext(dato).internalInputEl.value.toString
      )
    } toOption
