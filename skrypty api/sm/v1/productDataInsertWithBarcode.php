<?php  
if(isset($_GET['protein'],
 $_GET['carbohydrates'], 
 $_GET['fiber'], 
 $_GET['fat'], 
 $_GET['saturated_fat'], 
 $_GET['polyunsaturated_fat'], 
 $_GET['monounsaturated_fat'], 
 $_GET['sugar'], 
 $_GET['kcal'], 
 $_GET['name'],
 $_GET['barcode'])) {
	
include 'config.php';
$protein = $_GET['protein']; 
$carbohydrates = $_GET['carbohydrates']; 
$fiber = $_GET['fiber']; 
$fat =  $_GET['fat']; 
$saturated_fat = $_GET['saturated_fat']; 
$polyunsaturated_fat = $_GET['polyunsaturated_fat']; 
$monounsaturated_fat = $_GET['monounsaturated_fat']; 
$sugar = $_GET['sugar']; 
$kcal = $_GET['kcal']; 
$name =  $_GET['name']; 
$barcode =  $_GET['barcode']; 

$stmt = $db->prepare("INSERT INTO products 
(protein, carbohydrates, fiber, fat, saturated_fat, polyunsaturated_fat, monounsaturated_fat, sugar, kcal, name, barcode) values
 (:protein, :carbohydrates, :fiber, :fat, :saturated_fat, :polyunsaturated_fat, :monounsaturated_fat, :sugar, :kcal, :name, :barcode)");
$stmt->bindParam(':protein', $protein);
$stmt->bindParam(':carbohydrates', $carbohydrates);
$stmt->bindParam(':fiber', $fiber);
$stmt->bindParam(':fat', $fat);
$stmt->bindParam(':saturated_fat', $saturated_fat);
$stmt->bindParam(':polyunsaturated_fat', $polyunsaturated_fat);
$stmt->bindParam(':monounsaturated_fat', $monounsaturated_fat);
$stmt->bindParam(':sugar', $sugar);
$stmt->bindParam(':kcal', $kcal);
$stmt->bindParam(':name', $name);
$stmt->bindParam(':barcode', $barcode);
$stmt->execute();
}
?>  