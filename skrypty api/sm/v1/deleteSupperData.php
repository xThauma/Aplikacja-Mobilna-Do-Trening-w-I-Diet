<?php  
if(isset($_GET['id'])){
	
include 'config.php';

$id= (int) $_GET['id']; 
$stmt = $db->prepare("delete from suppers where id = $id");
$stmt->execute();

} 
?>  