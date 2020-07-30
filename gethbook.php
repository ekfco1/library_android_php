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

           $Query = 'SELECT hbook_no, hbook_title, hbook_author, hbook_publish from AND_201844049.hopebook';

           $response = array();

           foreach ($Conn -> query($Query) as $result)
           {
               $result_row["HTitle"] = $result['hbook_title'];
               $result_row["HAuthor"] = $result['hbook_author'];
               $result_row["HPublish"] = $result['hbook_publish'];

               array_push($response, $result_row);
           }   
        
           echo json_encode($response);           
        } catch(PDOException $ex) {
            echo $ex -> getMessage()."<br>";
        
        }
        $Conn = null;
?>
