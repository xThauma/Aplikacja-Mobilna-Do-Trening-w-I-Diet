<?php  
if(isset($_GET['id'])){
	
include 'config.php';

$id= $_GET['id']; 
$stmt = $db->prepare("delete from posts where id = :id");
$stmt->bindParam(':id', $id);
$stmt->execute();

} 
?>  