<?php
include "config.php";

if (isset($_REQUEST['idc']) && isset($_REQUEST['un'])) {
  $idc = $_REQUEST['idc'];
  $un = $_REQUEST['un'];
  if ($idc && $un) {
    $sqlString = "SELECT a.course, b.fullname, b.id, c.username, a.userid, d.id, d.name, d.type, d.alltext, d.reference from mdl_course_display 
  as a inner join mdl_course as b on b.id=a.course inner join mdl_user as c on c.id=a.userid 
  inner join mdl_resource as d on d.course=a.course and d.type='text' and b.id='" . $_GET['idc'] . "' and d.id='" . $_GET['idr'] . "' and a.userid='" . $_GET['un'] . "'";
  }
  else {
    $sqlString = "SELECT a.course, b.fullname, b.id, c.username, a.userid, d.name, d.type, d.alltext, d.reference from mdl_course_display 
  as a inner join mdl_course as b on b.id=a.course inner join mdl_user as c on c.id=a.userid 
  inner join mdl_resource as d on d.course=a.course and d.type='text'";
  }

  $rs = mysql_query($sqlString);
  if ($rs) {
    while ($objRs = mysql_fetch_assoc($rs)) {
      $output[] = $objRs;
    }

    echo json_encode($output);
  }
}

mysql_close();
?>