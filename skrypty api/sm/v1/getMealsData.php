<?php  
if(isset($_GET['id_users'])){
	
include 'config.php';

$id_users = (int) $_GET['id_users']; 
$stmt = $db->prepare("select products, name from meals where id_users = :id_users");
$stmt->bindParam(':id_users', $id_users);
$stmt->execute();
$myarr = array();
while($data = $stmt->fetch()){
	$myarr[]= array('products'=>$data['products'] 
	, 'name'=>$data['name']);
}
echo json_encode($myarr);
}
?>  