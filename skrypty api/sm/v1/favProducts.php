<?php  
if(isset($_GET['id_users'])){
	
include 'config.php';

$id_users = (int) $_GET['id_users']; 
$stmt = $db->prepare("select distinct p.id, p.protein, p.carbohydrates, 
p.fiber, p.fat, p.saturated_fat, p.polyunsaturated_fat, p.monounsaturated_fat, p.sugar, p.kcal, p.name, p.barcode 
from breakfasts b, products p, dinners d, suppers s, lunchs l
where b.id_users = 10 and d.id_users = b.id_users and s.id_users = d.id_users and s.id_users = l.id_users 
and (b.id_products=p.id and b.favourite = 1) 
or (d.id_products=p.id and d.favourite = 1)
or (s.id_products=p.id and s.favourite = 1)
or (l.id_products=p.id and l.favourite = 1)
order by p.name ASC");
$stmt->bindParam(':id_users', $id_users);
$stmt->execute();


$myarr = array();
while($data = $stmt->fetch()){
	$myarr[]= array(
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
		'name'=>$data['name']
		);
}
echo json_encode($myarr);
}