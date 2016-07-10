<?php
// framework 

header("Content-Type: application/xml; charset=ISO-8859-1");
require_once("../moodle/config.php");
// server config
include "config.php";

if(isset($_REQUEST['username']) && isset($_REQUEST['password'])) {
	$username = $_REQUEST['username'];
	$password = $_REQUEST['password'];
	$username = "student01";
	$password = "Super.secure436";

	// cek kesesuaian password
	if (authenticate_user_login($username, $password))
		echo 1;  
	else
		echo 0; 
} else {
	echo 0; 
}

?>