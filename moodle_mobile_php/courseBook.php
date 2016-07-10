<?php 

header("Content-Type: application/xml; charset=ISO-8859-1");
?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
<channel>
<title>BOOK COURSE</title>
<link>http://lepkom.gunadarma.ac.id/</link>
<description>Silahkan Klik Topik yang Ada</description>
<language>id</language>
<managingEditor>Lepkom Mobile Learning</managingEditor>
<copyright>Copyright 2016 Lepkom Mobile Learning</copyright>
<?php
include "config.php"; 
if(isset($_GET['idc'])) {
	$idcourse = $_GET['idc'];
	$query = "SELECT a.id, a.course, a.name, a.summary
	from mdl_book  as a 
	where a.course = '$idcourse'"; 

	$run = mysql_query($query); 
	while($b=mysql_fetch_array($run)){
	?>
	<item>
	<title><?php echo "$b[name]"; ?></title>
	<link><?php echo "$b[id]"; ?></link>
	<pubDate><?php echo "$b[course]"; ?></pubDate>
	<category><?php echo "$b[course]"; ?></category>
	<description><?php echo "$b[summary]"; ?></description>
	</item>	

	<?php } ?>
<?php } ?>
</channel>
</rss>
<?php mysql_close(); ?>