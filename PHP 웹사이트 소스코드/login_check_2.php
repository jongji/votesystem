<?php
    header("Content-Type: text/html;charset=UTF-8");
    $conn = mysqli_connect("127.0.0.1","root","jongji","votingsystem");    
    $data_stream = "'".$_GET['user_id']."','".$_GET['user_pass']."'";
    //$query = "insert into user(`Id`, `Password`, `Name`, `PhoneNum`, `Email`) values (".$data_stream.")";
    $result = mysqli_query($conn, $query);
	echo $data_stream;
     
    if($result)
      echo "1";
    else
      echo "-1";
     
    mysqli_close($conn);
?>