<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
<body>
<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
$UserID = $_POST["UserID"];
// $UserPW = $_GET["UserPW"];
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
           $Query = 'SELECT mem_no FROM AND_201844049.member';
            -- WHERE mem_id = \''.$UserID.'\';';
            foreach ($Conn -> query($Query) as $result) { // 데이터 베이스 쿼리 결과 로우들을 반복문으로 파싱하기 결과값 출력
                $response["MemberNo"] = $result['mem_no'];
            }
            
            // 안드로이드가 알아들을 수 있게 인코딩            
            // function han ($s) { return reset(json_decode('{"s":"'.$s.'"}')); } 
            // function to_han ($str) { return preg_replace('/(\\\u[a-f0-9]+)+/e', "han('$0')", $str); }
            // echo to_han(json_encode($response));
            echo json_encode($response);
        } catch(PDOException $ex) {
            echo $ex -> getMessage()."<br>";
            /*$response['RESULT'] = 'DB Conn Error';
            echo json_encode($response);*/
        }
        $Conn = null;
?>
</body>
</html>