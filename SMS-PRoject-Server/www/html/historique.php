<?php
session_start();
?><a href="index.php">Retourner en arrière</a><br /><?php
foreach($_SESSION['historique'] as $ligne){
    // Lecture de chaque tableau de chaque ligne
	foreach($ligne as $cle=>$valeur){
                // Affichage
		echo $cle.': '.$valeur.'<br>';
	}
	echo "<hr>";
}
?>