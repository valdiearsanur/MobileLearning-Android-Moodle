<?php 
header("Content-Type: application/xml; charset=ISO-8859-1");?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
<channel>
<title>DOWNLOAD FILE</title>
<link>http://lepkom.gunadarma.ac.id/</link>
<description>Silahkan Klik Untuk Menuju Lnk Download</description>
<language>id</language>
<managingEditor>Lepkom Mobile Learning</managingEditor>
<copyright>Copyright 2016 Lepkom Mobile Learning</copyright>
<?php
include "config.php"; 
 if(isset($_REQUEST['idc']) && isset($_REQUEST['idu'])) {
	$query = "SELECT a.course, b.fullname, b.id, c.username, a.userid, d.name, d.type, d.alltext, d.reference from mdl_course_display 
	  as a inner join mdl_course as b on b.id=a.course inner join mdl_user as c on c.id=a.userid 
	  inner join mdl_resource as d on d.course=a.course and d.type='file' and b.id='".$_GET['idc']."' and c.id='".$_GET['idu']."' ";
	$run = mysql_query($query); 
	while($b=mysql_fetch_array($run)){
	?>
	<item>
	<title><?php echo "$b[name]"; ?></title>
	<link><?php echo "$b[id]"; ?></link>
	<description><?php echo "$b[reference]"; ?></description>
	</item>
<?php } ?>
<?php } ?>
</channel>
</rss>
<?php mysql_close(); ?>