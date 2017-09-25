<?php  
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
 
mysqli_set_charset($con,"utf8");
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}
$studentid = $_POST['studentid'];
$suggest_content = $_POST['suggest_content'];  


$result = mysqli_query($con,"insert into suggestbox (studentid, suggest_content) values ('$studentid', '$suggest_content')");  
 
 if($result){  
    echo 'success';  
  }  
  else{
	
    echo 'failed';  
  }  

  
mysqli_close($con);  
?>  