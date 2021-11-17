package ru.hwru.integration.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequestMapping("test")
public class TestParseController {

    private final String currencyUrl = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002";


    @GetMapping
    public String index() throws IOException, ParserConfigurationException, SAXException, JAXBException {


        URL url = new URL(currencyUrl);
        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");

        InputStream xml = connection.getInputStream();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xml);

        System.out.println(doc.getElementsByTagName("ValCurs").item(0).getAttributes().getNamedItem("Date").getTextContent());


//        NodeList nodeList = doc.getElementsByTagName("Valute");
//
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                Element element = (Element) node;
//                for (int i1 = 0; i1 < element.getChildNodes().getLength(); i1++) {
//                   String val = element.getElementsByTagName(element.getChildNodes().item(i1).getNodeName())
//                            .item(0).getTextContent();
//                    System.out.println(val);
//                }
//            }
//        }





//        System.out.println(doc.getElementsByTagName("Valute").item(0).getChildNodes().item(1).getNodeValue());

        return "error";
    }
}
