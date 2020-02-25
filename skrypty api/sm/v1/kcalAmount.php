<?php  
if(isset($_GET['id'])){
	
include 'config.php';

$id = (int) $_GET['id']; 
$stmt = $db->prepare("select * from users where id = :id");
$stmt->bindParam(':id', $id);
$stmt->execute();
$myarr = array();
while($data = $stmt->fetch()){
	$myarr[]= array('id'=>$data['id'], 'daily_kcal'=>$data['daily_kcal']);
}
echo json_encode($myarr);
}
?>  