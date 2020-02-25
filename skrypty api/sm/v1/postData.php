<?php  
if(isset($_GET['id_users'], $_GET['date'])){
	
include 'config.php';

$id_users = (int) $_GET['id_users']; 
$date = $_GET['date']; 
$stmt = $db->prepare("select * from posts where id_users = :id_users  and date = :date order by date desc");
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':date', $date);
$stmt->execute();
$myarr = array();
while($data = $stmt->fetch()){
	$myarr[]= array('id'=>$data['id'], 'title'=>$data['title'], 'date'=>$data['date'], 'content'=>$data['content'], 'id_users'=>$data['id_users']);
}
echo json_encode($myarr);
}
?>  