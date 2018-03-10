<?php
if (isset($_POST['login']) && isset($_POST['mdp']) && $_POST['login']=='boss' && $_POST['mdp']=='boss'){
	$jour=date('d,m,y',time(NULL));
	echo "bonjour ".$_POST['login']."!Nous sommes le ".$jour;
}
else {
	echo "Tu dois être authentifié pour connaître la date ! Retourne au <a href='index.html'>formulaire</a>";
}

?>