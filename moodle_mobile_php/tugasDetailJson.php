<?php
include "config.php";

if (isset($_REQUEST['idc']) && isset($_REQUEST['idu']) && isset($_REQUEST['ida'])) {
  $idcourse = $_GET['idc'];
  $iduser = $_GET['idu'];
  $idassignment = $_GET['ida'];
  $sqlString = "";

  if ($idcourse && $iduser) {
    $q1 = "SELECT count(*) as jum FROM mdl_assignment_submissions WHERE userid = '$iduser' AND assignment='$idassignment';";
    $hasil = mysql_query($q1);
    $data = mysql_fetch_array($hasil);

    // pernah jawab
    if ($data['jum'] > 0) {
      $sqlString = "SELECT a.course, b.fullname, b.id, c.username, a.userid, d.id, d.name, d.description, e.data1
      from mdl_course_display as a 
      inner join mdl_course as b on b.id=a.course 
      inner join mdl_user as c on c.id=a.userid 
      inner join mdl_assignment d on d.course=a.course and b.id='$idcourse' and d.id='$idassignment' and a.userid='$iduser'
      inner join mdl_assignment_submissions as e on e.userid=a.userid and e.assignment=d.id";
    } else {
      $sqlString = "SELECT a.course, b.fullname, b.id, c.username, a.userid, d.id, d.name, d.description
      from mdl_course_display as a 
      inner join mdl_course as b on b.id=a.course 
      inner join mdl_user as c on c.id=a.userid 
      inner join mdl_assignment d on d.course=a.course and b.id='$idcourse' and d.id='$idassignment' and a.userid='$iduser'";
    }
  }
  else {
    $sqlString = "SELECT a.course, b.fullname, b.id, c.username, a.userid, d.id, d.name, d.description
    from mdl_course_display as a 
    inner join mdl_course as b on b.id=a.course 
    inner join mdl_user as c on c.id=a.userid 
    inner join mdl_assignment d on d.course=a.course ";
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