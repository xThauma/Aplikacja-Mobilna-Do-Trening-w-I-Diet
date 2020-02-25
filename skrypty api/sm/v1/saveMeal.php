<?php  
if(isset($_GET['id_users'], $_GET['products'], $_GET['name'])) {
	
include 'config.php';
$id_users = $_GET['id_users']; 
$products = $_GET['products']; 
$name = $_GET['name']; 
$stmt = $db->prepare("INSERT INTO meals (products, id_users, name) values (:products, :id_users, :name)");
$stmt->bindParam(':products', $products);
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':name', $name);
$stmt->execute();
}
?>  