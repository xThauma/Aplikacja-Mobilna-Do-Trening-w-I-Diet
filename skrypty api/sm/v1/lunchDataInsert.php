<?php  
if(isset($_GET['date'],
 $_GET['id_users'], 
 $_GET['id_products'], 
 $_GET['portion'])) {
	
include 'config.php';
$date = $_GET['date']; 
$id_users = $_GET['id_users']; 
$id_products = $_GET['id_products']; 
$portion = $_GET['portion']; 

$stmt = $db->prepare("INSERT INTO lunchs 
(date, id_users, id_products, portion) values (:date, :id_users, :id_products, :portion)");
$stmt->bindParam(':date', $date);
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':id_products', $id_products);
$stmt->bindParam(':portion', $portion);
$stmt->execute();
}
?>  