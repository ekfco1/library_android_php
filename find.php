<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
$UserName = $_GET["UserName"];
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

            // 이 부분에서 쿼리 생성해서 Array 만들기
           $Query = 'SELECT mem_id, mem_passwd from AND_201844049.member WHERE mem_name = \''.$UserName.'\' and mem_phone = \''.$UserPhone.'\';';
           foreach ($Conn -> query($Query) as $result)
           { // 데이터 베이스 쿼리 결과 로우들을 반복문으로 파싱하기 결과값 출력
               $response["Result"] = "Success";
               $response["MemberId"] = $result['mem_id'];
               $response["MemberPw"] = $result['mem_passwd'];
           }

           if($response["Result"] == "Success") 
           {
               echo json_encode($response);
           }
           else
           {
               echo json_encode($response);
           }
        } catch(PDOException $ex) {
            $response["Result"] = "NotEnoughParmeter";
            echo $ex -> getMessage()."<br>";
        
        }
        $Conn = null;
?>
