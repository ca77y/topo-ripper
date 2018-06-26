package pl.tom.topo.ripper

import java.net.URLEncoder

import com.scalakml.io.KmlPrintWriter
import com.scalakml.kml._
import pl.tom.topo.ripper.Model.Rock

import scala.xml.PrettyPrinter


object Ripper extends App {
  private val host = "http://topo.portalgorski.pl"
  val extractor = new RockExtractor
  val rocks = extractor.extract("http://topo.portalgorski.pl/topo/json/get/id/34")
  val doc = new Document(Folder(createPlacemark(rocks)))
  val kml = new Kml(doc)
  new KmlPrintWriter(s"kmls/skaly.kml").write(kml, new PrettyPrinter(240, 4))

  def encodeWithHost(url: String) = {
    host + url.split("/").map(URLEncoder.encode).mkString("/")
  }

  def createPlacemark(rocks: List[Rock]) = {
    rocks.map(rock => {
      val data = ExtendedData(List(Data(name = Some("gx_media_links"), value = Some(encodeWithHost(rock.img)))))
      new Placemark(new Point(rock.lng, rock.lat), FeaturePart(name = Some(rock.title), description = Some
      (s"${encodeWithHost(rock.url)}")))
    })
  }
}
