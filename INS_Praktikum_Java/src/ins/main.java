package ins;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ================================
        // 1️⃣ XML-Eingabeordner festlegen
        // ================================
        File xmlOrdner = new File("xml_input");

        if (!xmlOrdner.exists() || !xmlOrdner.isDirectory()) {
            System.out.println("❌ XML-Ordner nicht gefunden");
            return;
        }

        // ================================
        // 2️⃣ XML-Dateien suchen
        // ================================
        File[] dateien = xmlOrdner.listFiles(
                (dir, name) -> name.toLowerCase().endsWith(".xml")
        );

        if (dateien == null || dateien.length == 0) {
            System.out.println("❌ Keine XML-Dateien gefunden");
            return;
        }

        try {
            // ================================
            // 3️⃣ SAX-Parser konfigurieren (7.2)
            // ================================
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);          // DTD-Validierung AKTIV
            factory.setNamespaceAware(false);

            SAXParser parser = factory.newSAXParser();
            parser.getXMLReader().setErrorHandler(
                    new FeedbackErrorHandler()
            );

            // Handler sammelt die Daten (7.3)
            FeedbackSAXHandler handler = new FeedbackSAXHandler();

            // ================================
            // 4️⃣ Alle XML-Dateien einlesen
            // ================================
            for (File xmlDatei : dateien) {
                System.out.println("Prüfe Datei: " + xmlDatei.getName());
                parser.parse(xmlDatei, handler);
            }

            System.out.println("✔ Alle XML-Dateien sind gültig");

            // ================================
            // 5️⃣ Daten aus dem RAM holen
            // ================================
            List<Feedback> feedbacks = handler.getFeedbackListe();

            // ================================
            // 6️⃣ Aufgabe 7.4 – Statistik
            // ================================
            Statistik.berechne(feedbacks);

            // ================================
            // 7️⃣ Aufgabe 7.5 – Gesamt-XML schreiben
            // ================================
            FeedbackXMLWriter.schreibeXML(feedbacks);

            // ================================
            // 8️⃣ Aufgabe 7.7 – erzeugte XML erneut validieren
            // ================================
            System.out.println("Validiere feedback_gesamt.xml ...");

            parser.parse(
                    new File("feedback_gesamt.xml"),
                    new DefaultHandler() // kein Eventhandler nötig
            );

            System.out.println("✔ feedback_gesamt.xml ist DTD-gültig");

        } catch (Exception e) {
            System.out.println("❌ Programm abgebrochen");
            System.out.println("Grund: " + e.getMessage());
        }
    }
}
