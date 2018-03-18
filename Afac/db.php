<?php
	$host="localhost";
	$user="root";
	$pass="";

	$conn=mysqli_connect($host,$user,$pass);
	if($conn){
		//echo "Connnection succes!!";
		$q1="use facdb";
		if(mysqli_query($conn,$q1)){
		//	echo "database is changed!!<br>";
				create_table();
		}else{
		//	echo "database is not exist!!";
			$q2="create database facdb";
			if(mysqli_query($conn,$q2)){
		//		echo "database is created<br>";
				if(mysqli_query($conn,$q1)){
		//			echo "databse is changed!!<br>";
				}
				create_table();
			}else{
		//		echo "Cannot create db<br>";
			}
		}
	}else{
	//	echo "Connection failed!!".mysqli_error($conn);
	}

	function create_table(){
		$q3="select * from user";
		if(mysqli_query($GLOBALS['conn'],$q3)){
	//		echo "table is exist<br>";
		}else{
		//	echo "Table is not exist<br>";
			$q4="create table user(id int(10) primary key auto_increment,fullname char(100),age int(10),
			email char(50),username char(50),password char(50),gender char(10))";
			if(mysqli_query($GLOBALS['conn'],$q4)){
		//		echo "Table is created!!<br>";
			}else{
		//		echo "Cannot craete Table".mysql_error($GLOBALS['conn']);
			}
		}
	}

?>