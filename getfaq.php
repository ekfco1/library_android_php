<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
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

           $Query = 'SELECT FAQ.FAQ_no as FAQ_no, FAQ.FAQ_title as FAQ_title, member.mem_flag from AND_201844049.FAQ as FAQ join AND_201844049.member as 
           member on FAQ.mem_no = member.mem_no where FAQ.FAQ_flag = 0';

           $response = array();

           foreach ($Conn -> query($Query) as $result)
           {
               $result_row["FNo"] = $result['FAQ_no'];
               $result_row["FTitle"] = $result['FAQ_title'];
               array_push($response, $result_row);
           }   
        
           function han ($s) { return reset(json_decode('{"s":"'.$s.'"}')); }
           function to_han ($str) { return preg_replace('/(\\\u[a-f0-9]+)+/e', "han('$0')", $str); }
           echo to_han(json_encode($response)); 
        } catch(PDOException $ex) {
            echo $ex -> getMessage()."<br>";
        
        }
        $Conn = null;
?>
