<?php  
if(isset($_GET['id_users'], $_GET['date'])){
	
include 'config.php';

$id_users = (int) $_GET['id_users']; 
$date = $_GET['date']; 
$stmt = $db->prepare("select * from dinners where id_users = :id_users and date = :date");
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':date', $date);
$stmt->execute();





$myarr3 =array();
while($data = $stmt->fetch()){
	$myarr = array();
	$myarr['id']=$data['id'];
	$myarr['date']=$data['date'];
	$myarr['id_users']=$data['id_users'];
	$myarr['portion']=$data['portion'];
	$myarr['favourite']=$data['favourite'];
	$myarr['id_products']=array();

	$stmt2 = $db->prepare("select * from products where id = :id_products");
	$stmt2->bindParam(':id_products', $data['id_products']);
	$stmt2->execute();

	while($data2 = $stmt2->fetch()){
		$myarr2 = array();
		$myarr2['id']=$data2['id'];
		$myarr2['protein']=$data2['protein'];
		$myarr2['carbohydrates']=$data2['carbohydrates'];
		$myarr2['fiber']=$data2['fiber'];
		$myarr2['fat']=$data2['fat'];
		$myarr2['saturated_fat']=$data2['saturated_fat'];
		$myarr2['polyunsaturated_fat']=$data2['polyunsaturated_fat'];
		$myarr2['monounsaturated_fat']=$data2['monounsaturated_fat'];
		$myarr2['sugar']=$data2['sugar'];
		$myarr2['kcal']=$data2['kcal'];
		$myarr2['name']=$data2['name'];
		array_push($myarr['id_products'],$myarr2);
	}
	array_push($myarr3,$myarr);
}

$jsonData = json_encode($myarr3, JSON_PRETTY_PRINT);

echo $jsonData; 

} 
?>  