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
 
        public static final Map<String, Integer> ObjMap = new HashMap<String, Integer>();
        public static final Map<String, Integer> TileMap = new HashMap<String, Integer>();
        public static final Map<String, Integer> PacketMap = new HashMap<String, Integer>();
        private static final String USER_AGENT = "Mozilla/5.0";
 
        public static void parseXML() throws Exception {
                System.out.println("Testing 1 - Send Http GET request");
                parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/objects.xml", ObjMap, "Object");
                parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/tiles.xml", TileMap, "Ground");
                parseXMLtoMap("https://raw.github.com/DeVoidCoder/Realm-Relay/master/packets.xml", PacketMap, "Packet");
 
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
 
                        int responseCode = con.getResponseCode();
                        System.out.println("\nSending 'GET' request to URL : " + url);
                        System.out.println("Response Code : " + responseCode);
 
                        InputStream in = con.getInputStream();
                        Document doc = dBuilder.parse(in);
                        in.close();
 
                        doc.getDocumentElement().normalize();
 
                        NodeList nodeList = doc.getElementsByTagName(elementTagName);
 
                        if (nodeList != null) {                        	
                            XmltoMap("type", "id", nodeList, map);
                    }
 
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
 
        private static void XmltoMap(String type, String id, NodeList node, Map<String, Integer> map) {
                if (node != null) {
                        if (node.getLength() > 0) {                        	
                                for (int j = 0; j < node.getLength(); j++) {                                	
                                        Element el = (org.w3c.dom.Element) node.item(j);
                                        if (el.hasAttribute(id)) {                                        	
                                                String typetemp = el.getAttribute(type);
                                                String idtemp = el.getAttribute(id);
                                                int ParsedTileType = Integer.decode(typetemp);
                                                map.put(idtemp, ParsedTileType);
                                                System.out.println(idtemp);
                                        }
                                }
                        }
                }
        }
}