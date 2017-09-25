<?php  
 
function unistr_to_xnstr($str){ 
    return preg_replace('/\\\u([a-z0-9]{4})/i', "&#x\\1;", $str); 
} 
 
$con=mysqli_connect("127.0.0.1","root","jongji","votingsystem");  
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}  
 
 
mysqli_set_charset($con,"utf8");  
 
 
$res = mysqli_query($con,"select survey_id, survey_name, survey_short_content from admin_survey");  
   
$result = array();
   
while($row = mysqli_fetch_array($res)){  
  array_push($result,  
    array('survey_id'=>$row[0],'survey_name'=>$row[1],'survey_short_content'=>$row[2]  
    ));  
}  
   
 
$json = json_encode(array("result"=>$result));
echo $json;
 
   
mysqli_close($con);  
   
?>