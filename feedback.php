<?php

$debug = false;


$anrede  = $_POST["anrede"] ?? "";
$vorname = $_POST["vorname"] ?? "";
$name    = $_POST["name"]  ?? "";
$KopieMail = isset($_POST["KopieMail"]);
$email_Input = $_POST["email_Input"] ?? "";
$rueckfragen = isset($_POST["rückfragen"]);
$telefon = $_POST["telefonnummer"] ?? "";
$uRLLink = $_POST["uRLLink"] ?? "";
$alter = $_POST["alter"] ?? "";
$besuch = isset($_POST["besuch"]);
$verbesserung = $_POST["verbesserung"] ?? "";
$aufmerksam = $_POST["aufmerksam"] ?? "";
$a = $_POST["a"] ?? "";
$b = $_POST["b"] ?? "";
$password = $_POST["password"] ?? "";
$keineFehler = true;
if($debug){

    echo "<h2> Ausgabe der formularwerte</h2>";

    echo "<p>Die Anrede war: $anrede</p>";

    echo "<p>Der Nachname war: $name</p>";

    if ($KopieMail){
        echo "<p>Die Checkbox 'Kopie an meine Mailbox senden' war ausgewählt</p>";
    }
    else {
        echo "<p>Die Checkbox 'Kopie an meine Mailbox senden' war nicht ausgewählt.</p>";
    }

    echo "<p>Aufmerksamkeit durch: $aufmerksam</p>";

    echo "<p>Die angegebene Emailadresse war: $email_Input</p>";

    if ($rueckfragen){
        echo "<p>Checkbox 'Rückfragen': ausgewählt</p>";
    }
    else{
        echo "<p>Checkbox 'Rückfragen': nicht ausgewählt</p>";
    }

    echo "<p>Angegebene Telefonnummer war: $telefon</p>";


    echo "<p>Angegebene URL war: $uRLLink</p>";

    echo "<p>Angegebenes Alter war: $alter</p>";

    if($besuch){
        echo "<p>Die Checkbox 'Wieder Besuchen' war ausgewählt</p>";
    }
    else{
        echo "<p>Die Checkbox 'Wieder Besuchen' war nicht ausgewählt</p>";
    }

    echo "<p>Angegebene Verbesserung war: $verbesserung</p>";

    echo "<p>Die Aufmerksamkeit auf die Webseite wurde hervorgerufen durch: $aufmerksam</p>";

    echo "<p>Note für Inhalt: $a</p>";

    echo "<p>Note für Design: $b</p>";

    echo "<p>Passwort: $password</p>";
}
    echo "<p>Vielen Dank $anrede $vorname $name, dass Sie meine Webseite besucht haben.</p>";

if ($password !== "Internetsprachen") {
    echo "<p>Falsches Passwort! Keine weiteren Prüfungen werden ausgeführt.</p>";
    $keineFehler =  false;
    return; // Script abbrechen → KEINE weiteren Aufgaben mehr
}
if ($KopieMail && empty($email_Input)) {
    echo "<p>Warnung: Sie haben Kopie-Mail gewählt, aber keine Email angegeben.</p>";
    $keineFehler = false;
}
if ($rueckfragen) {

    if (empty($telefon) && empty($uRLLink) && empty($email_Input)) {
        echo "<p>Warnung: Keine Kontaktmöglichkeit angegeben!</p>";
        $keineFehler =false;
    }
}
$noteInhalt = (int)$a[0];

if ($noteInhalt === 1 || $noteInhalt === 2) {
    echo "<p>Ich freue mich, dass Ihnen der Inhalt gefallen hat!</p>";
} elseif ($noteInhalt === 3) {
    echo "<p>Ich werde den Inhalt weiter verbessern.</p>";
} else {
    echo "<p>Danke für Ihre ehrliche Bewertung.</p>";
}
$noteDesign = (int)$b[0];

if ($noteDesign <= 2) {
    echo "<p>Danke! Schön, dass das Design gefällt.</p>";
} elseif ($noteDesign === 3) {
    echo "<p>Ich arbeite weiter am Design.</p>";
} else {
    echo "<p>Danke! Ich werde das Layout weiter verbessern.</p>";
}
$now = new DateTime();
echo "<p>Ihre Feedbackwerte wurden am ".$now->format("d.m.Y")." um ".$now->format("H:i")." Uhr angenommen.</p>";


if ($alter < 18 || $alter > 99) {
    echo "<p>Warnung: Das Alter scheint nicht realistisch.</p>";
    $keineFehler = false;
}
if ($keineFehler) {

    // Datei-Name mit Zeitstempel
    $filename = "feedback_" . date("Ymd_His") . ".xml";

    // XML-Datei öffnen
    $xml = new XMLWriter();
    $xml->openURI($filename);

    // Dokument starten
    $xml->startDocument('1.0', 'UTF-8');
    $xml->writeDTD('feedback',null,'meineFeedback.dtd');
    $xml->setIndent(true);

    // Wurzel-Element
    $xml->startElement("feedback");

    // Daten speichern
    $xml->writeElement("anrede", $anrede);
    $xml->writeElement("vorname", $vorname);
    $xml->writeElement("name", $name);
    $xml->writeElement("email", $email_Input);
    $xml->writeElement("telefon", $telefon);
    $xml->writeElement("url", $uRLLink);
    $xml->writeElement("alter", $alter);
    $xml->writeElement("aufmerksam", $aufmerksam);
    $xml->writeElement("note_inhalt", $a);
    $xml->writeElement("note_design", $b);
    $xml->writeElement("verbesserung", $verbesserung);
    $xml->writeElement("besuch", $besuch ? "ja" : "nein");

    // Zeit hinzufügen
    $xml->writeElement("zeitpunkt", date("d.m.Y H:i:s"));

    $xml->endElement(); // </feedback>
    $xml->endDocument();
    $xml->flush();

    echo "<p>✔ Feedback erfolgreich in <strong>$filename</strong> gespeichert.</p>";
}

?>
