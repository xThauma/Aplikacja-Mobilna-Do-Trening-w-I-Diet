<?php 

	class DbOperations{

		private $con; 

		function __construct(){

			require_once dirname(__FILE__).'/DbConnect.php';

			$db = new DbConnect();

			$this->con = $db->connect();

		}

		/*CRUD -> C -> CREATE */

		public function createUser($username, $email, $pass, $sex, $height, $weight, $target_weight, $weight_lose, $daily_kcal){
			if($this->isUserExist($username,$email)){
				return 0; 
			}else{
				$password = md5($pass);
				$stmt = $this->con->prepare("INSERT INTO users (username, email, password, sex, height, weight, target_weight, weight_lose, daily_kcal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				$stmt->bind_param("sssssssss",$username,$email,$password, $sex, $height, $weight, $target_weight, $weight_lose, $daily_kcal);

				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			}
		}
		
		public function createPost($title, $date, $content, $id_users){
				$password = md5($pass);
				$stmt = $this->con->prepare("INSERT INTO posts (title, date, content, id_users) VALUES (?, ?, ?, ?)");
				$stmt->bind_param("ssss",$title,$date,$content, $id_users);

				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}


		public function userLogin($username, $pass){
			$password = md5($pass);
			$stmt = $this->con->prepare("SELECT id FROM users WHERE username = ? AND password = ?");
			$stmt->bind_param("ss",$username,$password);
			$stmt->execute();
			$stmt->store_result(); 
			return $stmt->num_rows > 0; 
		}

		public function getUserByUsername($username){
			$stmt = $this->con->prepare("SELECT * FROM users WHERE username = ?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}
		

		private function isUserExist($username, $email){
			$stmt = $this->con->prepare("SELECT id FROM users WHERE username = ? OR email = ?");
			$stmt->bind_param("ss", $username, $email);
			$stmt->execute(); 
			$stmt->store_result(); 
			return $stmt->num_rows > 0; 
		}
		
		private function postData($id_users){
			$stmt = $this->con->prepare("SELECT id FROM posts WHERE id_users = ?");
			$stmt->bind_param("s", $id_users);
			$stmt->execute(); 
			$stmt->store_result(); 
			return $stmt->num_rows > 0; 
		}

	}