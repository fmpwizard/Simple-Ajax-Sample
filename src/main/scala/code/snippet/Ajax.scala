package code
package snippet 

import net.liftweb._
import http._
import js.JsCmds._
import js.JE._
import js.JsCmd
import common._
import util._
import Helpers._
import scala.xml._
import java.util.Date
import code.lib._
import Helpers._

class HelloWorld extends Logger {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> date.map(_.toString)

  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */



   def exec = "#tag_input" #> SHtml.onSubmit(str => { 
      prepend("lift-tag-prepend", <div id={str+"tag"} class="tag"><span>{str+" (Tag)"}</span><span onclick={SHtml.ajaxCall(Str(str), removeTag _)._2.toJsCmd.toString+"; return false;"}>X</span></div>) &
      SetValById("tag_input", "")
    })

   def removeTag(tag: String): JsCmd = {
    info("Remove Tag: "+tag)
    Noop
   }
   
   def prepend(id: String, node: Elem): JsCmd = JsRaw("$('#"+id+"').prepend('"+node+"');")
   
}

