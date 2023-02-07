import scala.scalajs.js
import scala.scalajs.js.annotation.*

package object DKFDS:
    @js.native
    @JSImport("dkfds", "init")
    def init(): Unit = js.native
