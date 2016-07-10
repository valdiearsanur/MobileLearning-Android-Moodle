

<?php

header("Content-Type: application/xml; charset=ISO-8859-1");
?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
<channel>
<title>TUGAS</title>
<link>http://lepkom.gunadarma.ac.id/</link>
<description>Silahkan Klik Tugas yang Ada</description>
<language>id</language>
<managingEditor>Lepkom Mobile Learning</managingEditor>
<copyright>Copyright 2016 Lepkom Mobile Learning</copyright>
<?php
include "config.php"; 

if(isset($_GET['idc']) && isset($_GET['idu'])) {
	$idcourse = $_GET['idc'];
	$iduser = $_GET['idu'];

	$query = "SELECT a.course,b.fullname, c.username, a.userid, d.id, d.name, d.description 
	FROM `mdl_course_display` as a 
	inner join mdl_course as b on  b.id=a.course 
	inner join mdl_user as c on c.id=a.userid 
	inner join mdl_assignment as d on d.course=a.course and b.id='$idcourse' and c.id='$iduser'"; 
	$run = mysql_query($query); 
	while($b=mysql_fetch_array($run)){
	?>
	<item>
	<title><?php echo "$b[name]"; ?></title>
	<pubDate><?php echo "$b[course]"; ?></pubDate>
	<link><?php echo "$b[id]"; ?></link>
	<description><?php echo "$b[userid]"; ?></description>
	</item>
	<?php } ?>
<?php } ?>
</channel>
</rss>
<?php mysql_close(); ?>