<?php  
if(isset($_GET['id'])){
	
include 'config.php';

$id= (int) $_GET['id']; 
$stmt = $db->prepare("delete from lunchs where id = $id");
$stmt->execute();

} 
?>  