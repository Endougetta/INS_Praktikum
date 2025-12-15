package ins;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class FeedbackErrorHandler implements ErrorHandler{
    @Override
    public void warning(SAXParseException e) throws SAXException{
        System.out.print("Warnung");
        ausgabe(e);
    }
      @Override
    public void error(SAXParseException e) throws SAXException{
        System.out.print("Fehler");
        ausgabe(e);
        throw new SAXException("DTD-Validierung fehlgeschlagen");

    }

    private void ausgabe(SAXException e){
        System.out.println("Datei:" + e.getSystemId());
        System.out.println("Zeile:" + e.getLineNumber());
        System.out.println("Spalte:" + e.getColumnNumber()); 
        System.out.println("Meldung:" + e.getMessage());
        
        
    }
}