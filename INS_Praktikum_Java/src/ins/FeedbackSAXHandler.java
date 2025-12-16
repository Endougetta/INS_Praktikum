package ins;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public class FeedbackSAXHandler extends DefaultHandler{
    private List<Feedback> feedbackList = new ArrayList<>();
    private Feedback aktuellesFeedback ;
    private String aktuellesElement = ;
    private StringBuilder textbuffer = new StringBuilder();

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        aktuellesElement = qName;
        if(qName.equals("feedback")){
            aktuellesFeedback = new Feedback();

        }
    }  
@Override
    public void characters(char[] ch, int start, int length){
        textbuffer.append(ch, start, length);
    }
@Override
    public void endElement(String uri, String localName, String qName){
        String text = textbuffer.toString().trim();

        if (aktuellesFeedback!=null){
            switch (qName){
                case "anrede":
                    aktuellesFeedback.anrede = text;
                    break;
                case "vorname":
                    aktuellesFeedback.vorname = text;
                    break;
                case "name":
                    aktuellesFeedback.name = text;
                    break;
                case "email":
                    aktuellesFeedback.email = text;
                    break;
                case "telefon":
                    aktuellesFeedback.telefon = text;
                    break;
                case "url":
                    aktuellesFeedback.url = text;
                    break;
                case "alter":
                    aktuellesFeedback.alter = Integer.parseInt(text);
                    break;
                case "aufmerksamkeit":
                    aktuellesFeedback.aufmerksamkeit = text;
                    break;
                case "noteInhalt":
                    aktuellesFeedback.noteInhalt = text;
                    break;
                case "noteDesign":
                    aktuellesFeedback.noteDesign = text;
                    break;
                case "verbesserung":
                    aktuellesFeedback.verbesserung = text;
                    break;
                case "besuch":
                    aktuellesFeedback.besuch = text;
                    break;
                case "zeitpunkt":
                    aktuellesFeedback.zeitpunkt = text;
                    break;
                case "feedback":
                    feedbackList.add(aktuellesFeedback);
                    aktuellesFeedback = null;
                    break;
                
            }
        }

    }
    
}