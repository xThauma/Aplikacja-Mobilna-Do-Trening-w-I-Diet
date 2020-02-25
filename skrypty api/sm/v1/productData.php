<?php  
if(isset($_GET['name'])){
	
include 'config.php';

$name = $_GET['name']; 
$stmt = $db->prepare("select * from products where name LIKE '%$name%'");
$stmt->execute();
$myarr2 = array();
while($data = $stmt->fetch()){
		$myarr2[]= array(
		'id'=>$data['id'], 
		'protein'=>$data['protein'], 
		'carbohydrates'=>$data['carbohydrates'], 
		'fiber'=>$data['fiber'],
		'fat'=>$data['fat'],
		'saturated_fat'=>$data['saturated_fat'],
		'polyunsaturated_fat'=>$data['polyunsaturated_fat'],
		'monounsaturated_fat'=>$data['monounsaturated_fat'],
		'sugar'=>$data['sugar'],
		'kcal'=>$data['kcal'],
		'name'=>$data['name']);

}
$jsonData = json_encode($myarr2, JSON_PRETTY_PRINT);

echo $jsonData; 

}
?>  