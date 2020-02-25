<?php  
if(isset($_GET['id'], $_GET['favourite'])){
	
include 'config.php';

$id = (int) $_GET['id']; 
$favourite = $_GET['favourite']; 
$stmt = $db->prepare("update suppers set favourite = :favourite where id = :id");
$stmt->bindParam(':favourite', $favourite);
$stmt->bindParam(':id', $id);
$stmt->execute();
}
?>  