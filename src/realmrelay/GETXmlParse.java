package realmrelay;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import realmrelay.data.GroundData;
import realmrelay.data.ItemData;
import realmrelay.data.ObjectData;

import java.util.HashMap;
import java.util.Map;

public class GETXmlParse {

	public static final Map<String, Object> itemMap = new HashMap<String, Object>();
	public static final Map<String, Object> objectMap = new HashMap<String, Object>();
	public static final Map<String, Object> tileMap = new HashMap<String, Object>();
	public static final Map<String, Object> packetMap = new HashMap<String, Object>();
	
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final int XML_ITEMS = 0;
	private static final int XML_OBJECTS = 1;
	private static final int XML_PACKETS = 2;
	private static final int XML_TILES = 3;

	public static void parseXMLData() throws Exception {
		parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/XML/Objects.xml", objectMap, "Object", XML_OBJECTS);
		parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/XML/Tile.xml", tileMap, "Ground", XML_TILES);
		parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/XML/Packets.xml", packetMap, "Packet", XML_PACKETS);
		parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/XML/Items.xml", itemMap, "Object", XML_ITEMS);
	}

	private static void parseXMLtoMap(String url, Map<String, Object> map, String elementTagName, int xmlType) {
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
			xmlToMap(nodeList, map, xmlType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void xmlToMap(NodeList node, Map<String, Object> map, int xmlType) {
		for (int j = 0; j < node.getLength(); j++) {
			Element el = (Element) node.item(j);
			// convert names with lowercase letters and spaces to the correct format
			String idtemp = el.getAttribute("id").replace(" ", "").toUpperCase();
			if(xmlType == XML_TILES) {
				GroundData groundData = new GroundData();
				groundData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;
				if ((nodeList = el.getElementsByTagName("MaxDamage")).getLength() > 0) {
					groundData.maxDamage = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MinDamage")).getLength() > 0) {
					groundData.minDamage = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if (el.getElementsByTagName("NoWalk").getLength() > 0) {
					groundData.noWalk = true;
				}
				if (el.getElementsByTagName("Push").getLength() > 0) {
					groundData.push = true;
				}
				if (el.getElementsByTagName("Sink").getLength() > 0) {
					groundData.sink = true;
				}
				if ((nodeList = el.getElementsByTagName("Speed")).getLength() > 0){
					groundData.speed = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				tileMap.put(idtemp, groundData);
			} else if (xmlType == XML_ITEMS) {
				ItemData itemData = new ItemData();
				itemData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;
				if ((nodeList = el.getElementsByTagName("SlotType")).getLength() > 0) {
					itemData.slotType = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Tier")).getLength() > 0) {
					itemData.tier = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("PetFamily")).getLength() > 0) {
					itemData.petFamily = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Rarity")).getLength() > 0) {
					itemData.rarity = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Activate")).getLength() > 0) {
					itemData.activate = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Consumable")).getLength() > 0) {
					itemData.consumable = true;
				}
				if ((nodeList = el.getElementsByTagName("Soulbound")).getLength() > 0) {
					itemData.soulbound = true;
				}
				if ((nodeList = el.getElementsByTagName("Usable")).getLength() > 0) {
					itemData.usable = true;
				}
				if ((nodeList = el.getElementsByTagName("BagType")).getLength() > 0) {
					itemData.bagType = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("FeedPower")).getLength() > 0) {
					itemData.feedPower = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("RateOfFire")).getLength() > 0) {
					itemData.rateOfFire = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("FameBonus")).getLength() > 0) {
					itemData.fameBonus = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MpCost")).getLength() > 0) {
					itemData.mpCost = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MpEndCost")).getLength() > 0) {
					itemData.mpEndCost = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MultiPhase")).getLength() > 0) {
					itemData.multiPhase = true;
				}
				if ((nodeList = el.getElementsByTagName("NumProjectiles")).getLength() > 0) {
					itemData.numProjectiles = Integer.parseInt(nodeList.item(0).getTextContent());
				}
			} else if (xmlType == XML_OBJECTS) {
				ObjectData objectData = new ObjectData();
				objectData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;
				if ((nodeList = el.getElementsByTagName("MaxHitPoints")).getLength() > 0) {
					objectData.maxHitPoints = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MaxSize")).getLength() > 0) {
					objectData.maxSize = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MinSize")).getLength() > 0) {
					objectData.minSize = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Size")).getLength() > 0) {
					objectData.size = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("SizeStep")).getLength() > 0) {
					objectData.sizeStep = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("ShadowSize")).getLength() > 0) {
					objectData.shadowSize = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Color")).getLength() > 0) {
					objectData.color = Integer.decode(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("XpMult")).getLength() > 0) {
					objectData.xpMult = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Rotation")).getLength() > 0) {
					objectData.rotation = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				if (el.getElementsByTagName("DrawOnGround").getLength() > 0) {
					objectData.drawOnGround = true;
				}
				if (el.getElementsByTagName("Enemy").getLength() > 0) {
					objectData.enemy = true;
				}
				if (el.getElementsByTagName("FullOccupy").getLength() > 0) {
					objectData.fullOccupy = true;
				}
				if (el.getElementsByTagName("OccupySquare").getLength() > 0) {
					objectData.occupySquare = true;
				}
				if (el.getElementsByTagName("EnemyOccupySquare").getLength() > 0) {
					objectData.enemyOccupySquare = true;
				}
				if (el.getElementsByTagName("BlocksSight").getLength() > 0) {
					objectData.blocksSight = true;
				}
				if (el.getElementsByTagName("NoMiniMap").getLength() > 0) {
					objectData.noMiniMap = true;
				}
				if (el.getElementsByTagName("StasisImmune").getLength() > 0) {
					objectData.stasisImmune = true;
				}
				if (el.getElementsByTagName("ProtectFromGroundDamage").getLength() > 0) {
					objectData.protectFromGroundDamage = true;
				}
				if (el.getElementsByTagName("ProtectFromSink").getLength() > 0) {
					objectData.protectFromSink = true;
				}
				if (el.getElementsByTagName("Connects").getLength() > 0) {
					objectData.connects = true;
				}
				if ((nodeList = el.getElementsByTagName("Z")).getLength() > 0) {
					objectData.z = Float.parseFloat(nodeList.item(0).getTextContent());
				}
			} else if (xmlType == XML_PACKETS) {
				String typetemp = el.getAttribute("type");
				int ParsedTileType = Integer.decode(typetemp);
				map.put(idtemp, ParsedTileType);
			}
		}
	}
	
}