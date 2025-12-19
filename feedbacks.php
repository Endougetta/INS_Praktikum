<?php
// XML-Datei laden
$xml = simplexml_load_file("feedback_gesamt.xml");

if ($xml === false) {
    die("Fehler beim Laden der XML-Datei");
}
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Auswertung Feedbacks</title>
    <link rel="stylesheet" href="feedbacks.css">
</head>
<body>

<table class="feedback-table">

    <!-- Erste Zeile: Tabellenbeschreibung -->
    <tr class="headline">
        <th colspan="7">Alle Feedbacks</th>
    </tr>

    <!-- Zweite Zeile: Spaltenüberschrift -->
    <tr class="header">
        <th>Anrede</th>
        <th>Name</th>
        <th>Alter</th>
        <th>Note Inhalt</th>
        <th>Note Design</th>
        <th>Erneuter Besuch</th>
        <th>Datum</th>
    </tr>

    <!-- Dynamische Feedback-Zeilen -->
    <?php foreach ($xml->feedback as $fb): ?>

        <?php
            $noteInhalt = (string)$fb->bewertung['note_inhalt'];
            $noteDesign = (string)$fb->bewertung['note_aussehen'];

            // Schlechte Bewertung erkennen
            $kritischInhalt = in_array($noteInhalt, ["mangelhaft", "ungenuegend"]);
            $kritischDesign = in_array($noteDesign, ["5", "6"]);
        ?>

        <tr>
            <td><?= $fb->besucher['anrede'] ?></td>
            <td><?= $fb->besucher['vorname'] . " " . $fb->besucher['nachname'] ?></td>
            <td><?= $fb->besucher->alter ?></td>

            <td class="<?= $kritischInhalt ? 'kritisch' : '' ?>">
                <?= $noteInhalt ?>
            </td>

            <td class="<?= $kritischDesign ? 'kritisch' : '' ?>">
                <?= $noteDesign ?>
            </td>

            <td><?= $fb->bewertung['erneuter_besuch'] ?></td>
            <td><?= $fb->info->datum ?></td>
        </tr>

    <?php endforeach; ?>

</table>

</body>
</html>
