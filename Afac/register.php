<?php
	require 'db.php';
	$fullname=$_POST['fullname'];
	$age=$_POST['age'];
	$email=$_POST['email'];
	$gender=$_POST['gender'];
	$username=$_POST['usrname'];
	$password=$_POST['password'];

/*	$fullname="charith madusanka";
	$age=23;
	$email="charithmadusanka.cm@gmail";
	$gender="Male";
	$username="charith";
	$password="liyanage";*/

	$q1="select * from user";
	$r1=mysqli_query($conn,$q1);
	$duplicate_checker=0;
	while ($row1=mysqli_fetch_assoc($r1)) {
		if(($row1['fullname']==$fullname)&&($row1['password']==$password)){
			$duplicate_checker=1;
			break;
		}
	}
	if($duplicate_checker==1){
		//echo "duplicate value ";
		$myarray=array("duplicate_status"=>"Alredy has a Account!!");
		echo json_encode($myarray);
	}else {
		//==update user  table====
		$q2="insert into user(fullname,age,email,username,password,gender) values('$fullname','$age','$email','$username','$password','$gender')";
		if(mysqli_query($conn,$q2)){
			//echo "database is update success fullyy";
			$myarray=array("reg_status1"=>"Registration is success!!");
			echo json_encode($myarray);
		}else{
			//echo "cannot update databases!!!<br>";
			$myarray=array("reg_status2"=>"Registration is failed!!");
			echo json_encode($myarray);
		}
	}



?>