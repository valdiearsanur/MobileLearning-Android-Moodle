<?php
header("Content-Type: application/xml; charset=ISO-8859-1");
 include "config.php";
 if(isset($_GET['idu']) && isset($_GET['ida'])) {
   $idu= $_REQUEST['idu'];
   $ida= $_REQUEST['ida'];
    if($idu && $ida){
       $sqlString = "SELECT a.course,b.fullname, c.username, a.userid, d.id, d.name, e.id, e.assignment, e.grade FROM `mdl_course_display` as a inner
					join mdl_course as b on  b.id=a.course inner join mdl_user as c on c.id=a.userid inner join mdl_assignment as d on 
					d.course=a.course inner join mdl_assignment_submissions as e on e.assignment=d.id 
					and a.userid='".$_GET['idu']."' 
					and e.assignment='".$_GET['ida']."' 
					and e.id='".$_GET['idsub']."'";
    }else{
       $sqlString = "SELECT a.course,b.fullname, c.username, a.userid, d.id, d.name, e.id, e.assignment, e.grade FROM `mdl_course_display` as a inner
					join mdl_course as b on  b.id=a.course inner join mdl_user as c on c.id=a.userid inner join mdl_assignment as d on 
					d.course=a.course inner join mdl_assignment_submissions as e on e.assignment=d.id";
    }
    $rs = mysql_query($sqlString);
    if($rs){
       while($objRs = mysql_fetch_assoc($rs)){
          $output[] = $objRs;
       }      
       echo json_encode($output);
    }
   }
   mysql_close();
?>