/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dictionaryapplication;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author LENOVO
 */
public class XMLUtil {
    public static void readWordsFromFile(Map<String, Meaning> map, File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(file);
            
            document.getDocumentElement().normalize();
            
            NodeList records = document.getElementsByTagName("record");
            
            for(int i = 0; i < records.getLength(); i++){
                Element record = (Element) records.item(i);
                
                Element wordEl = (Element) record.getElementsByTagName("word").item(0);
                String wordStr = wordEl.getTextContent();
                Meaning meaning;
                String meanStr = record.getElementsByTagName("meaning").item(0).getTextContent();
                if(record.getAttribute("favorite").equals("true")) {
                    meaning = new Meaning(meanStr, true);
                } else {
                    meaning = new Meaning(meanStr, false);
                }
                
                
                map.put(wordStr.toLowerCase(), meaning);
                
                
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void readLog(List<SearchLog> logs, File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(file);
            
            document.getDocumentElement().normalize();
            
            NodeList searchs = document.getElementsByTagName("search");
            
            for(int i = 0; i < searchs.getLength(); i++){
                Element record = (Element) searchs.item(i);
                
                Element timestampEl = (Element) record.getElementsByTagName("timestamp").item(0);
                long timestamp = Long.parseLong(timestampEl.getTextContent());
                String word = record.getElementsByTagName("word").item(0).getTextContent();
                
                logs.add(new SearchLog(new Date(timestamp), word));
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addWord(Map<String, Meaning> map, File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(file);
            
            Element root = document.createElement("dictionary");
            
           
            
            map.forEach((word, meaning) -> {
                Element newEl = document.createElement("record");
                if(meaning.isFavoriate()) {
                    newEl.setAttribute("favorite", "true");
                }
            
                Element wordEl = document.createElement("word");
                wordEl.setTextContent(word);
                Element meaningEl = document.createElement("meaning");
                meaningEl.setTextContent(meaning.getMeaning());

                newEl.appendChild(wordEl);
                newEl.appendChild(meaningEl);

                root.appendChild(newEl);
            });
            
            writeFile(root, file);
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addLog(List<SearchLog> list, File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(file);
            
            Element root = document.createElement("log");
            
            list.forEach(log -> {
                Element newEl = document.createElement("search");
            
            
                Element wordEl = document.createElement("word");
                wordEl.setTextContent(log.getWord());
                Element timestampEl = document.createElement("timestamp");
                timestampEl.setTextContent(String.valueOf(log.getTimestamp().getTime()));

                newEl.appendChild(wordEl);
                newEl.appendChild(timestampEl);

                root.appendChild(newEl);
            });
            
            writeFile(root, file);
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void removeWord(String word, File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(file);
            
            String path = "//record[word=\'" + word + "\']"; 
            XPathExpression xPath =   XPathFactory.newInstance().newXPath().compile(path);
            
            Node record = (Node) xPath.evaluate(document, XPathConstants.NODE);
            
            if(record != null) {
                document.getDocumentElement().removeChild(record);
                writeFile(document.getDocumentElement(), file);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateFavoriteWord(String word, boolean isFavorite, File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(file);
            
            String path = "//record[word=\'" + word + "\']"; 
            XPathExpression xPath =   XPathFactory.newInstance().newXPath().compile(path);
            
            Element record = (Element) xPath.evaluate(document, XPathConstants.NODE);
            
            if(record != null) {
                if(isFavorite) {
                    record.setAttribute("favorite", "true");
                } else {
                    record.removeAttribute("favorite");
                }
                writeFile(document.getDocumentElement(), file);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void writeFile(Element elemnent, File file) {
        TransformerFactory factory = TransformerFactory.newDefaultInstance();
        try {
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(elemnent);
            StreamResult result = new StreamResult(file);
            
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
