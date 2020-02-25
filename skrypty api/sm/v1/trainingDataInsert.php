<?php  
if(isset($_GET['id_users'], $_GET['date'], $_GET['name'], $_GET['reps'], $_GET['series'])) {
	
include 'config.php';
$id_users = $_GET['id_users']; 
$date = $_GET['date']; 
$name = $_GET['name']; 
$reps = $_GET['reps'];
$series = $_GET['series'];  
$stmt = $db->prepare("INSERT INTO trainings (id_users, date, name, reps, series) values (:id_users,:date,:name,:reps,:series)");
$stmt->bindParam(':id_users', $id_users);
$stmt->bindParam(':date', $date);
$stmt->bindParam(':name', $name);
$stmt->bindParam(':reps', $reps);
$stmt->bindParam(':series', $series);
$stmt->execute();
}
?>  