import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.scalajs.dom.*

package object DKFDS:
    @js.native
    @JSImport("dkfds", "init")
    def init(): Unit = js.native

    @js.native
    @JSImport("dkfds", "Tooltip")
    class Tooltip(e: Element) extends js.Any:
        def init(): Unit = js.native

    object datePicker:
        @js.native
        @JSImport("dkfds", "datePicker.getDatePickerContext")
        def getDatePickerContext(e: Element): js.Dynamic = js.native

        @js.native
        @JSImport("dkfds", "datePicker.validateDateInput")
        def validateDateInput(e: Element): Unit = js.native