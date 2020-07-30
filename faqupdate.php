<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
    if(isset($_GET["FNo"])) {
        $FNo = $_GET["FNo"];
        $FTitle = $_GET["FTitle"];
        $FContent = $_GET["FContent"];
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
            $Query = 'UPDATE AND_201844049.FAQ SET FAQ_title = \''.$FTitle.'\', FAQ_content = \''.$FContent.'\' WHERE FAQ.FAQ_no = \''.$FNo.'\';';
            $response["Result"] = "Success";
            $Conn->exec($Query);
            function han ($s) { return reset(json_decode('{"s":"'.$s.'"}')); }
            function to_han ($str) { return preg_replace('/(\\\u[a-f0-9]+)+/e', "han('$0')", $str); }
            echo to_han(json_encode($response));       
          
        } catch(PDOException $ex) {
            echo $ex -> getMessage()."<br>";
        
        }
        $Conn = null;
    } else {
        $response["Result"] = "NotEnoughParmeter";
        echo json_encode($response);
    }
?>
