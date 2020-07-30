<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
$UserID = $_GET["UserID"];
$UserPW = $_GET["UserPW"];
$UserName = $_GET["UserName"];
$UserFlag = $_GET["UserFlag"];
$UserPhone = $_GET["UserPhone"];
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
           $Query = 'INSERT into AND_201844049.member(mem_id, mem_passwd,mem_name,mem_phone, mem_flag) values(\''.$UserID.'\',\''.$UserPW.'\',\''.$UserName.'\',\''.$UserPhone.'\',\''.$UserFlag.'\');';
           $Conn->exec($Query);
           $Conn->commit();

            $response["Result"] = "Success";
            echo json_encode($response);
   
        } catch(PDOException $ex) {
            echo $ex -> getMessage()."<br>";
        
        }
        $Conn = null;
?>
