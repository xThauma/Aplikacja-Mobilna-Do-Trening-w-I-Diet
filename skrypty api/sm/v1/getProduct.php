<?php  
if(isset($_GET['id'])){
	
include 'config.php';



$myarray = explode(",", $_GET["id"]);
$query_str = "select * from products where id IN (";
for($i = 0; $i < count($myarray); ++$i) {
    if($i > 0)
        $query_str .= ', ';
    $query_str .= $myarray[$i];
}
$query_str .= ")";
$stmt = $db->prepare($query_str);
$stmt->execute();
$myarr1 = array();
while($data2 = $stmt->fetch()){
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
		array_push($myarr1,$myarr2);
}
		$jsonData = json_encode($myarr1, JSON_PRETTY_PRINT);

		echo $jsonData; 

}
?>  