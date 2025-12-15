import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Main{
    public static void main(String[] args){
        File xmlOrdner = new File(xml_input);
          
        
        if(!xmlOrdner.exists()|| !xmlOrdner.isDirectory()){
              System.out.println("XML-Ordner nicht gefunden");
            return;
        }
        File[] dateien = xmlOrdner.listFiles((dir,name)-> name.endsWith(".xml"));
        if (dateien == null || dateien==0) {
              System.out.println("XML-dateien nicht gefunden");
            return;
            
        }
        try { 
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(false);
            
            SAXParser parser = factory.newSAXParser();

            parser.getXMLReader().setErrorHandler(new FeedbackErrorHandler());

            FeedbackSAXHandler handler = new FeedbackSAXHandler();

            for (File xmlDatei : dateien){
                System.out.print("Prüfe Datei" + xmlDatei.getName());
                parser.parse(xmlDatei,handler);
            }
              System.out.println("Alle XML-Datein Sind gültig");

            
            
        } catch (Exception e) {
  System.out.println("Programm abgebrochen /n Grund ist: " + e.getMessage());
              }
    }
}