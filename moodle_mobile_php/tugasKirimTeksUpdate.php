<?php
header("Content-Type: application/xml; charset=ISO-8859-1");
$timezone = "Asia/Jakarta";
date_default_timezone_set($timezone);
include "config.php";

if (isset($_REQUEST['idtgs']) && isset($_REQUEST['idu']) && isset($_REQUEST['idas']) && isset($_REQUEST['jawab'])) {
	$idtgs = $_REQUEST['idtgs'];
	$iduser = $_REQUEST['idu'];
	$id_assignment = $_REQUEST['idas'];
	$jawaban = $_REQUEST['jawab'];

	// echo date('l, j F Y, h:i A');

	$waktu = time();

	// echo $waktu;

	$q2 = "SELECT timedue from mdl_assignment where id ='$idtgs'";
	$hq2 = mysql_query($q2);
	$data2 = mysql_fetch_array($hq2);

	// echo $data2['timedue'];

	$t = $data2['timedue'];;
	$y = intval($t);
	$ts = date("Y-m-d H:i", $y);
	$te = date("Y-m-d H:i");

	$q1 = "SELECT count(*) as jum FROM mdl_assignment_submissions WHERE data1 = '$jawaban';";
	$hasil = mysql_query($q1);
	$data = mysql_fetch_array($hasil);

	if($data['jum'] > 0) {
		echo "MAAF DATA YANG ANDA MASUKAN SAMA. MOHON UNTUK LEBIH KREATIF";
	} else
	if ($te > $ts) {
		echo "MAAF WAKTU ANDA TELAH HABIS";
	}
	else {
		$query = "UPDATE mdl_assignment_submissions 
		SET timemodified = '$waktu', data1 = '$jawaban' 
		WHERE id = '$id_assignment' AND userid = '$iduser'";
	}

	$result = mysql_query($query);
	if ($result >= 1) echo 1;
	else echo 0;
}
else {
	echo 0;
}

?>