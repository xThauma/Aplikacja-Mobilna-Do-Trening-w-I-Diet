<?php  
if(isset($_GET['id'], $_GET['daily_kcal'])){
	
include 'config.php';

$id = (int) $_GET['id']; 
$daily_kcal = $_GET['daily_kcal']; 
$stmt = $db->prepare("update users set kcals_left = :daily_kcal where id = :id");
$stmt->bindParam(':daily_kcal', $daily_kcal);
$stmt->bindParam(':id', $id);
$stmt->execute();
}
?>  