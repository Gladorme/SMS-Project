<!DOCTYPE html>
<html>

	<head> 
		<title> Notre serveur Apache </title> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>

	<body> 
		<p>
			keejkhbfzhbef
		</p>
		<?php
			$json_sms = fopen("/var/www/html/requete.js", "w") or die("Unable to open file!");
			$blacklist = fopen("/var/www/html/blacklist.txt", "r");
			$num_interdit = false;
			//$data=json_decode(file_get_contents('php://input'), true);
			$data=array("action"=>"send","dest"=>"0606060607","msg"=>"Mr Gautero");

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
				$contenu = json_encode(json_decode(file_get_contents("/var/www/html/requete.js"), true));
				echo $contenu;
			}
		?>

	</body>
</html>
