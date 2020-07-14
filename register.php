<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
    if(isset($_GET["UserID"]) && isset($_GET["UserPW"]) && isset($_GET["UserName"]) && isset($_GET["UserPhone"]) && isset($_GET["UserFlag"])) {
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
            $response["Result"] = "Fail";
            $Conn = new PDO($server, $DBID, $DBPW);
            $Conn -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $Query = 'SET NAMES utf8';
            $Conn -> query($Query);
            $Query = 'SELECT mem_id from AND_201844049.member where mem_id = \''.$UserID.'\';';
            foreach ($Conn -> query($Query) as $result)
            {   
                // 여기서 디비에서 SELECT 결과가 있으면, ID 가 중복되니까 결과는 Fail
                $response["Result"] = "DuplicatedID";
            }

            if($response["Result"] == "DuplicatedID") 
            {
                // ID가 중복된 결과라고 하면 바로 결과 화면 출력
                echo json_encode($response);   
            }
            else
            {
                // 그렇지 않으면 바로 INSERT

                $Query = 'INSERT into AND_201844049.member(mem_id, mem_passwd,mem_name,mem_phone, mem_flag) values(\''.$UserID.'\',\''.$UserPW.'\',\''.$UserName.'\',\''.$UserPhone.'\',\''.$UserFlag.'\');';
                $Conn->exec($Query); 

                // 결과 화면 출력
                // 결과 초기값이 Fail 이니까, Success 로 변경
                $response["Result"] = "Success";
                echo json_encode($response);
            }
         
   
        } catch(PDOException $ex) {
            // echo $ex -> getMessage()."<br>";
            $response["Result"] = "Fail"; // 나중에 왜 Fail 인지도 세분화해서 처리하면 개발하기 더 좋아요
            echo json_encode($response);
        }
        $Conn = null;
    }else{
        $response["Result"] = "NotEnoughParmeter";
        echo json_encode($response);
    }
?>
