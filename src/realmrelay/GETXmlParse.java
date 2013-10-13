
package realmrelay;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public class GETXmlParse {

	public static final Map<String, Integer> objectMap = new HashMap<String, Integer>();
	public static final Map<String, Integer> tileMap = new HashMap<String, Integer>();
	public static final Map<String, Integer> packetMap = new HashMap<String, Integer>();
	
	private static final String USER_AGENT = "Mozilla/5.0";

	public static void parseXMLData() throws Exception {
		parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/objects.xml", objectMap, "Object");
		parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/tiles.xml", tileMap, "Ground");
		parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/packets.xml", packetMap, "Packet");
	}

	private static void parseXMLtoMap(String url, Map<String, Integer> map, String elementTagName) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			/*int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);*/

			InputStream in = con.getInputStream();
			Document doc = dBuilder.parse(in);
			in.close();
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName(elementTagName);
			if (nodeList != null) {
				xmlToMap("type", "id", nodeList, map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void xmlToMap(String type, String id, NodeList node, Map<String, Integer> map) {
		if (node != null) {
			if (node.getLength() > 0) {
				for (int j = 0; j < node.getLength(); j++) {
					Element el = (Element) node.item(j);
					if (el.hasAttribute(id)) {
						String typetemp = el.getAttribute(type);
						String idtemp = el.getAttribute(id);
						
						// convert names with lowercase letters and spaces to the correct format
						idtemp = idtemp.replace(" ", "").toUpperCase();
						
						int ParsedTileType = Integer.decode(typetemp);
						map.put(idtemp, ParsedTileType);
						// System.out.println(idtemp);
					}
				}
			}
		}
	}
	
}