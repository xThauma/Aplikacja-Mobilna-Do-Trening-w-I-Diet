<?php  
if(isset($_GET['id'])){
	
include 'config.php';

$id = (int) $_GET['id']; 
$stmt = $db->prepare("select * from users where id = :id");
$stmt->bindParam(':id', $id);
$stmt->execute();
$myarr = array();
while($data = $stmt->fetch()){
	$myarr[]= array('id'=>$data['id']
	, 'username'=>$data['username']
	, 'email'=>$data['email']
	, 'sex'=>$data['sex']
	, 'height'=>$data['height']
	, 'weight'=>$data['weight']
	, 'target_weight'=>$data['target_weight']
	, 'weight_lose'=>$data['weight_lose']
	, 'daily_kcal'=>$data['daily_kcal']
	, 'kcals_left'=>$data['kcals_left']);
}
echo json_encode($myarr);
}
?>  