<?php  
if(isset($_GET['id_users'],
 $_GET['date'], 
 $_GET['id'], 
 $_GET['portion'])) {
	
include 'config.php';
$date = $_GET['date']; 
$id = $_GET['id']; 
$id_users = $_GET['id_users']; 
$portion = $_GET['portion']; 

$stmt = $db->prepare("UPDATE lunchs set portion = :portion where id_users = :id_users and id = :id and date = :date");
$stmt->bindParam(':date', $date);
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':id', $id);
$stmt->bindParam(':portion', $portion);
$stmt->execute();
}
?>  

