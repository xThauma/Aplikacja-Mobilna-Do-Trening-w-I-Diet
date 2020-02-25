<?php  
if(isset($_GET['title'], $_GET['date'], $_GET['content'], $_GET['id_users'])) {
	
include 'config.php';
$title = $_GET['title']; 
$date = $_GET['date']; 
$content = $_GET['content']; 
$id_users = (int) $_GET['id_users']; 

$stmt = $db->prepare("INSERT INTO posts (title, date, content, id_users) values (:title,:date,:content,:id_users)");
$stmt->bindParam(':title', $title);
$stmt->bindParam(':date', $date);
$stmt->bindParam(':content', $content);
$stmt->bindParam(':id_users', $id_users);
$stmt->execute();
}
?>  