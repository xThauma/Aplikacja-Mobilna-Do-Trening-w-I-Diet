<?php  
if(isset($_GET['id_users'], $_GET['date'])){
	
include 'config.php';

$id_users = (int) $_GET['id_users']; 
$date = $_GET['date']; 
$stmt = $db->prepare("select * from trainings where id_users = :id_users and date = :date");
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':date', $date);
$stmt->execute();
$myarr = array();
while($data = $stmt->fetch()){
	$myarr[]= array('id'=>$data['id']
	, 'id_users'=>$data['id_users']
	, 'reps'=>$data['reps']
	, 'series'=>$data['series']
	, 'date'=>$data['date']
	, 'name'=>$data['name']);
}
echo json_encode($myarr);
}
?>  