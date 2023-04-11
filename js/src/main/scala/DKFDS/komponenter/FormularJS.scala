package DKFDS.komponenter

object FormularJS:
  import org.scalajs.dom.*
  given Conversion[Formular, HTMLFormElement] = f => document.getElementById(f.anId).asInstanceOf[HTMLFormElement]

  extension (formular: Formular)
    def formData: FormData = new FormData(formular.convert)

    def restore(): Unit = {
      val params = new URLSearchParams(window.location.search)

      // Checkboxes
      val inputs: HTMLCollection[Element] = formular.getElementsByTagName("input")
      println(s"Restoring ${inputs.size} elements.")
      inputs.map(_.asInstanceOf[HTMLInputElement]).foreach(e => {
        e.getAttribute("type") match
          case "checkbox" =>
            if (!e.checked && params.has(e.name) && params.getAll(e.name).contains(e.value)) ||
               (e.checked && !params.has(e.name) && params.getAll(e.name).contains(e.value)) then
              e.click() // We have to click it for the DKFDS-script to do its thing
          case "text" =>
            if e.name.nonEmpty && params.has(e.name) then
              // Date pickers are a special case
              if e.classList.contains("date-picker__internal-input") then
                val p = "(.*)-(.*)-(.*)".r
                val value = params.get(e.name)
                DKFDS.datePicker.getDatePickerContext(e).internalInputEl.value = value
                DKFDS.datePicker.getDatePickerContext(e).externalInputEl.value = value match
                  case p(y,m,d) => s"$d/$m/$y"
                  case _ => ""
              else
                e.value = params.get(e.name)
      })

      // Dropdowns
      val selects: HTMLCollection[Element] = formular.getElementsByTagName("select")
      selects.map(_.asInstanceOf[HTMLSelectElement]).foreach(e => {
        if params.has(e.name) then
          e.value = params.get(e.name)
      })
    }
