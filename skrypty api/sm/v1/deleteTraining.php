<?php  
if(isset($_GET['id_users'], $_GET['name'], $_GET['date'])){
	
include 'config.php';

$id_users= $_GET['id_users']; 
$name= $_GET['name']; 
$date=  $_GET['date']; 
$stmt = $db->prepare("delete from trainings where id_users = :id_users and name = :name and date = :date");
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':name', $name);
$stmt->bindParam(':date', $date);
$stmt->execute();

} 
?>  