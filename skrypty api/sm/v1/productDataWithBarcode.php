<?php  
if(isset($_GET['barcode'])){
	
include 'config.php';

$barcode =  $_GET['barcode']; 
$stmt = $db->prepare("select * from products where barcode = :barcode");
$stmt->bindParam(':barcode', $barcode);
$stmt->execute();
$myarr = array();
while($data = $stmt->fetch()){
	$myarr[]= array('id'=>$data['id']);
}
echo json_encode($myarr);
}
?>  