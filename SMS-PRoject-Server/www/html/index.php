<?php session_start(); ?>
<!DOCTYPE html>
<html>

	<head> 
		<title> Notre serveur Apache </title> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>

	<body> 
		<a href="historique.php">Voir l'historique</a><br />
		<?php
			if (isset($_GET['json'])) {
				
				$json_sms = fopen("requete.js", "w") or die("Unable to open file!");
				$blacklist = fopen("blacklist.txt", "r");
				$num_interdit = false;
				//$data=json_decode($_GET['json'], true);
				//var_dump($data);
				$data = json_decode($_GET['json'], true);
				//echo $res['msg'];
				//$data=array("action"=>"send","dest"=>"0606060607","msg"=>"Mr Gautero");

				while(!feof($blacklist) and $num_interdit == false){

					if ((int)$data["dest"] === (int)fgets($blacklist)){
						$num_interdit = true;
						echo "num interdit : ",$data["dest"]," ";
					}
				}
				if ($num_interdit === false){
					if ($data != null){
						fwrite($json_sms, json_encode($data));
					}

					fclose($json_sms);
					$contenu = json_encode(json_decode(file_get_contents("requete.js"), true));
					echo $contenu;;
					$test = json_decode($contenu, true);
					//echo $test['msg']; //contient le message uniquement
					$date = date("D d F Y à H:i:s");
					
					$arrayName = array('date' => $date, 'destinataire' => $test['dest'], 'message' => $test['msg']);
					//$_SESSION['historique'][]["date"] = $date;
					//$_SESSION['historique'][]["message"] = $contenu;
					$_SESSION['historique'][] = $arrayName;

				}

				/*var_dump($_SESSION);
				foreach ($_SESSION['historique'] as $key => $value) {
					echo $key . ' => ' . $value . '<br />';
				}*/

			}else{
				echo "Problème d'url.";
			}
		?>

	</body>
</html>
