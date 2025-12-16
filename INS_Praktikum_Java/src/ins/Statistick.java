import java.util.ArrayList;

public class Statistick{
    public  static void berechne(List<Feedback> feedbacks){
        System.out.println("\n ---STATISTICK---");
        int anzahl = feedbacks.size();

        if(anzahl == 0){
            System.out.println("Keine Feedbacks vorhanden.");
            return;
        }
        int SummeDesign = 0;

        for (Feedback f: feedbacks) {
            int note = Character.getNumericValue(f.noteDesign.charAt(0)));
            SummeDesign += note;

        }
        double durchschnittDesign = (double) SummeDesign / anzahl;
        System.out.println("Durchschnittliche Design Note: %.2f\n" + durchschnittDesign);

        int zaehlerBesuchJa = 0;
        for (Feedback f: feedbacks) {
            if (f.besuchWiederJaNein.equals("ja")) {
                zaehlerBesuchJa++;          
            
    }
    double prozentBesuchJa = ((double) zaehlerBesuchJa / anzahl) * 100;
    System.out.println("Erneuter Besuch:" + prozentBesuchJa + "%");


    int emailZaehler = 0;
    for (Feedback f: feedbacks) {
        if (!f.email.equals("") && !f.email.isEmpty()) {
            emailZaehler++;
        }
    
}
System.out.println("Emails zu senden an : " + emailZaehler + " Besucher.");
    }
}
}