package ins;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.List;

public class FeedbackXMLWriter {
    public static void schreibeFeedbacksInXML(List<Feedback> feedbacks, String dateiPfad) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter xml = factory.createXMLStreamWriter(
                new FileOutputStream("feedback_gesamt.xml"), "UTF-8");

            xml.writeStartDocument("UTF-8", "1.0");

            xmll.writeStartDocument("<!DOCTYPE feedbacks SYSTEM \"feedbacks.dtd\">");
            for Feedback
            xml.writeStartElement("feedbacks");
            
            xml.writeStartElement("besucher");
            xml.writeAttribute("anrede", mapAnrede(f.anrede));
            xml.writeAttribute("nachname", f.name);

            xml.writeStartElement("Alter");
            xml.writeCharacters(f.alter);
            xml.writeEndElement();

            xml.writeStartElement("kontakt");
            xml.writeAttribute("rueckfrage_erlaubt",
                f.rueckfrageErlaubt ? "ja" : "nein"
            );

            if (!f.email.isEmpty()) {
                xml.writeStartElement("email");
                xml.writeCharacters(f.email);
                xml.writeEndElement();
            }
            if (!f.telefon.isEmpty()) {
                xml.writeStartElement("telefon");
                xml.writeCharacters(f.telefon);
                xml.writeEndElement();
            }
            if (!f.url.isEmpty()) {
                xml.writeStartElement("url");
                xml.writeCharacters(f.url);
                xml.writeEndElement();
            }
            xml.writeEndElement(); // kontakt  
            xml.writeStartElement("bewertung");
           xml.writeAttribute("erneuter_besuch",
                f.besuch.equals("ja") ? "Ja" :nein);
            xml.writeAttribute("note_inhalt",
                noteInhaltToText(f.noteInhalt));
            xml.writeAttribute("note_darstellung",
                String.valueOf(f.noteDarstellung));
            

           if (!f.verbesserung.isEmpty()) {
                    xml.writeStartElement("vorschlag");
                    xml.writeCharacters(f.verbesserung);
                    xml.writeEndElement();
                }

                xml.writeEndElement(); // bewertung

                // -------- INFO --------
                xml.writeStartElement("info");

                xml.writeStartElement("email-gesendet");
                xml.writeCharacters(f.email.isEmpty() ? "nein" : "ja");
                xml.writeEndElement();

                xml.writeStartElement("datum");
                xml.writeCharacters(f.datum);
                xml.writeEndElement();

                xml.writeStartElement("uhrzeit");
                xml.writeCharacters(f.uhrzeit);
                xml.writeEndElement();

                xml.writeEndElement(); // info
                xml.writeEndElement(); // feedback
            }

            xml.writeStartElement("entwickler_parser");
            xml.writeCharacters("Endougetta SAX Parser");
            xml.writeEndElement();

            xml.writeEndElement(); // feedbackdatenbank
            xml.writeEndDocument();
            xml.close();

            System.out.println("✔ feedback_gesamt.xml erfolgreich erstellt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- HELFER ----------------

    private static String mapAnrede(String a) {
        switch (a) {
            case "Dr.": return "Doktor";
            case "Prof.": return "Professor";
            default: return a;
        }
    }

    private static String noteInhaltToText(String n) {
        int wert = Character.getNumericValue(n.charAt(0));
        switch (wert) {
            case 1: return "sehr_gut";
            case 2: return "gut";
            case 3: return "befriedigend";
            case 4: return "ausreichend";
            case 5: return "mangelhaft";
            default: return "ungenuegend";
        }
 
           
    }
}