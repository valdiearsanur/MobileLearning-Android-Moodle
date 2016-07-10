<?php 

header("Content-Type: application/xml; charset=ISO-8859-1");
?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
<channel>
<title>BOOK CHAPTERS</title>
<link>http://lepkom.gunadarma.ac.id/</link>
<description>Silahkan Klik Topik yang Ada</description>
<language>id</language>
<managingEditor>Lepkom Mobile Learning</managingEditor>
<copyright>Copyright 2016 Lepkom Mobile Learning</copyright>
<?php
include "config.php"; 
if(isset($_GET['idb'])) {
	$idbook = $_GET['idb'];
	$query = "SELECT a.id, a.bookid, a.pagenum, a.title, a.content
	from mdl_book_chapters  as a 
	where a.bookid = '$idbook'
	order by a.pagenum asc"; 

	$run = mysql_query($query); 
	while($b=mysql_fetch_array($run)){
	?>
	<item>
	<title><?php echo "$b[title]"; ?></title>
	<link><?php echo "$b[id]"; ?></link>
	<pubDate><?php echo "$b[pagenum]"; ?></pubDate>
	<category><?php echo "$b[pagenum]"; ?></category>
	<description><?php echo "$b[content]"; ?></description>
	</item>	

	<?php } ?>
<?php } ?>
</channel>
</rss>
<?php mysql_close(); ?>