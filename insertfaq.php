<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
$Ftitle = $_GET["FTitle"];
$FContent = $_GET["FContent"];
$MemNo = $_GET["MemNo"];

    // POST 변수가 모두 입력되어 있는지 확인하기
        $DataBaseName = 'AND_201844049';
        $DBID = 'AND_201844049';
        $DBPW = 'AND_201844049';
	    $server = "mysql:host=114.71.137.109;port=3306;";
        try {
            $Conn = new PDO($server, $DBID, $DBPW);
            $Conn -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $Query = 'SET NAMES utf8';
            $Conn -> query($Query);

            // 이 부분에서 쿼리 생성해서 Array 만들기
           $Query = 'INSERT into AND_201844049.FAQ(FAQ_title,FAQ_content, mem_no) values(\''.$FTitle.'\',\''.$FContent.'\',\''.$MemNo.'\');';
           $Conn->exec($Query);
           $response["Result"] = "Success";
        echo json_encode($response);
   
        } catch(PDOException $ex) {
            $response["Result"] = "Fail";
            echo $ex -> getMessage()."<br>";
            
        }
        $Conn = null;
?>
