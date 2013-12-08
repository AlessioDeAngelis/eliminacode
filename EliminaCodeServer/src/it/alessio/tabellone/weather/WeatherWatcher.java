package it.alessio.tabellone.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WeatherWatcher {

	public static Map<String, String> getWeather(double latitude, double longitude) {

		Map<String, String> weather = new HashMap<String, String>();

		try {
			String urlString = "http://api.wunderground.com/auto/wui/geo/WXCurrentObXML/index.xml?query=" + latitude
					+ "," + longitude;
			URL url = new URL(urlString);

			url.getFile();
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String xmlString = "";
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				xmlString += inputLine;

			in.close();

			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);

			doc.normalize();

			Element element = doc.getDocumentElement();

			NodeList childrenElements = element.getChildNodes();

			for (int i = 0; i < childrenElements.getLength(); ++i) {
				Node node = childrenElements.item(i);
				if (node.getNodeName().equals("weather"))
					weather.put("weather", node.getTextContent());
				if (node.getNodeName().equals("temp_c"))
					weather.put("temperature", node.getTextContent());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return weather;

	}

}
