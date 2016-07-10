<?php

header("Content-Type: application/xml; charset=ISO-8859-1");
include "config.php";
if(isset($_GET['idtgs']) && isset($_GET['idu']) && isset($_GET['jawab'])) {
    $idtgs=$_REQUEST['idtgs'];
	$iduser=$_REQUEST['idu'];
	$jawaban=$_REQUEST['jawab'];

    $timezone = "Asia/Jakarta";
	date_default_timezone_set($timezone);
	$waktu = time();
	
	$q2 = "SELECT timedue from mdl_assignment where id ='$idtgs'";
	$hq2 = mysql_query($q2);
	$data2 = mysql_fetch_array($hq2);
	
	//echo $data2['timedue'];
	
	$t = $data2['timedue']; ;
    $y = intval($t);

    $ts = date("Y-m-d H:i",$y);
    $te = date("Y-m-d H:i");
	
	$q1 = "SELECT count(*) as jum FROM mdl_assignment_submissions WHERE data1 = '$jawaban'";
	$hasil = mysql_query($q1);
	$data  = mysql_fetch_array($hasil);

	if (($data['jum'] > 0) || ($te > $ts))
	{
	echo "SAMA ATAU TELAT";
	} else
	$query = "INSERT INTO mdl_assignment_submissions(assignment,userid,timemodified,data1) VALUES('$idtgs','$iduser','$waktu','$jawaban')";
	$result = mysql_query($query);
	if ($result >= 1)
	echo 1;  

	else

	echo 0; 
} else {
	echo 0; 
}

?>
