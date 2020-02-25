<?php  
if(isset($_GET['id_users'], $_GET['date'], $_GET['steps'])) {
	
include 'config.php';
$id_users = $_GET['id_users']; 
$date = $_GET['date']; 
$steps = $_GET['steps']; 

$stmt = $db->prepare("INSERT INTO steps (id_users, date, steps) values (:id_users,:date,:steps)");
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':date', $date);
$stmt->bindParam(':steps', $steps);
$stmt->execute();
}
?>  