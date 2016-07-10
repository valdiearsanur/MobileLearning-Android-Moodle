<?php
header("Content-Type: application/xml; charset=ISO-8859-1");
?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
<channel>

<title>COURSE YANG DI AMBIL</title>
<link>http://lepkom.gunadarma.ac.id/</link>
<description></description>
<language>id</language>
<managingEditor>Lepkom Mobile Learning</managingEditor>
<copyright>Copyright 2016 Lepkom Mobile Learning</copyright>
<?php
include "config.php"; 

if(isset($_GET['uname'])) {
	$query = "SELECT a.course, b.fullname, b.id, c.username, a.userid from mdl_course_display 
	  as a inner join mdl_course as b on b.id=a.course inner join mdl_user as c on c.id=a.userid and c.username='".$_GET['uname']."' "; 
	  $run = mysql_query($query); 
	while($b=mysql_fetch_array($run)){
	?>
	<item>
	<title><?php echo "$b[fullname]"; ?></title>
	<pubDate><?php echo "$b[userid]"; ?></pubDate>
	<link><?php echo "$b[id]"; ?></link>
	<description><?php echo "$b[username]"; ?></description>
	</item>
	<?php } ?>
<?php } ?>
</channel>
</rss>
<?php mysql_close(); ?>