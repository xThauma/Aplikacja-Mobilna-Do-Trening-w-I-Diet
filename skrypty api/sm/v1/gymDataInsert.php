<?php  
if(isset($_GET['id_users'], $_GET['date'], $_GET['name'])) {
	
include 'config.php';
$id_users = $_GET['id_users']; 
$date = $_GET['date']; 
$name = $_GET['name']; 
$stmt = $db->prepare("INSERT INTO gyms (id_users, date, name) values (:id_users,:date,:name)");
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':date', $date);
$stmt->bindParam(':name', $name);
$stmt->execute();
}
?>  