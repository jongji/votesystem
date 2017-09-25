<?php
session_start();
session_destroy();
echo "<script>alert('로그아웃 되었습니다..'); document.location.href='http://votingapp.iptime.org/web/index.php' </script>";
?>