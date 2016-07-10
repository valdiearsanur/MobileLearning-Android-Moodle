<?php
header("Content-Type: application/xml; charset=ISO-8859-1");?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
<channel>
<title>BERITA</title>
<description></description>
<language>id</language>
<managingEditor>Mobile Learning</managingEditor>
<copyright>Copyright 2011 Mobile Learning</copyright>

<?php
include "config.php"; 
$query = "SELECT subject, message from mdl_forum_posts "; 
$run = mysql_query($query); 

while($b=mysql_fetch_array($run)){

?>
<item>
<title><?php echo "$b[subject]"; ?></title>
<description><?php echo "$b[message]"; ?></description>


</item>

<?php } ?>
</channel>
</rss>
<?php mysql_close(); ?>

