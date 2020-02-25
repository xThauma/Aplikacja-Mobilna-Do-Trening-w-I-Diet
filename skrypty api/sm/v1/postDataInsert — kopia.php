<?php  
if(isset($_GET['imie'], $_GET['nazwisko'], $_GET['pesel'], $_GET['haslo'], $_GET['email'], $_GET['numer_telefonu'])) {
	
include 'config.php';
$imie = $_GET['imie']; 
$nazwisko = $_GET['nazwisko']; 
$pesel = $_GET['pesel']; 
$haslo = $_GET['haslo'];
$email = $_GET['email']; 
$numer_telefonu = $_GET['numer_telefonu']; 
 

$stmt = $db->prepare("INSERT INTO uzytkownicy (imie, nazwisko, pesel, haslo, email, numer_telefonu) VALUES (?, ?, ?, ?, ?, ?)");
$stmt->bindParam(':imie', $imie);
$stmt->bindParam(':nazwisko', $nazwisko);
$stmt->bindParam(':pesel', $pesel);
$stmt->bindParam(':haslo', $haslo);
$stmt->bindParam(':email', $email);
$stmt->bindParam(':numer_telefonu', $numer_telefonu);
$stmt->execute();
}
?>  