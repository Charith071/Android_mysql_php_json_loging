<?php
	require 'db.php';

	$username=$_POST['username'];
	$password=$_POST['password'];

	$q1="select * from user";
	$r1=mysqli_query($conn,$q1);
	$log_checker=0;
	while ($row1=mysqli_fetch_assoc($r1)) {
		if(($row1['username']==$username)&&($row1['password']==$password)){
			$log_checker=1;
			break;
		}
	}

	if($log_checker==1){
		//echo "Loging Success!!<br>";
		$myarray=array("logstatus_success"=>"loging Succes!!");
		echo json_encode($myarray);
	}else{
		//echo "Loging falied!!!<br>";
		$myarray=array("logstatus_fail"=>"login falied!!");
		echo json_encode($myarray);
	}



?>