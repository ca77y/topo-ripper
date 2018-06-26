package pl.tom.topo.ripper

import com.softwaremill.sttp._
import com.typesafe.scalalogging.Logger
import org.json4s.JsonAST.{JField, JString}
import org.json4s.native.Serialization
import org.json4s.{CustomSerializer, JObject, NoTypeHints}
import pl.tom.topo.ripper.Model.Rock

class RockExtractor {
  private val logger = Logger[RockExtractor]

  private implicit val formats = Serialization.formats(NoTypeHints) + new RockSerializer
  private implicit val sttpBackend = HttpURLConnectionBackend()

  def extract(url: String) = {
    val res = sttp.get(uri"$url").response(json4s.asJson[JObject]).send()
    val rocks: List[Rock] = res.body match {
      case Left(error) =>
        logger.error(error)
        List()
      case Right(body) =>
        body \ "results" filter {
          case JObject(o) => o.exists(p => p._1 == "type" && p._2.extract[String] == "rock")
          case _ => false
        } map (_.extract[Rock])
    }
    rocks.filter(_.lat > 0).sortBy(_.title)
  }

  private def extractRegion(url: String) = {
    url.split(",").takeRight(4).head
  }

  class RockSerializer extends CustomSerializer[Rock](formats => ( {
    case JObject(JField("id", JString(id)) :: JField("lat", JString(lat)) :: JField("lng", JString(lng)) :: JField
      ("title", JString(title)) :: JField("description", JString(description)) :: JField("type", _) :: JField("url",
    JString(url)) :: JField("img", JString(img)) :: Nil) => Rock(id.toInt, lat.toDouble, lng.toDouble, title,
      description, url, img.replaceAll("60x60", "438x300").replaceAll("/item/", "/scale/").replace("" +
        ".png", "_JPG"))
  }, {
    case Rock(id, lat, lng, title, description, url, img) => JObject(JField("id", JString(id.toString)) :: JField("lat",
      JString(lat.toString)) :: JField("lng", JString(lng.toString)) :: JField("title", JString(title)) :: JField
    ("description", JString(description)) :: JField("type", JString("rock")) :: JField("url", JString(url)) :: JField
    ("img",
      JString(img)) :: Nil)
  }))

}
