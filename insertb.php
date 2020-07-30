<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
$BTitle = $_GET["BTitle"];
$BAuthor = $_GET["BAuthor"];
$BPublish = $_GET["BPublish"];
$BDate = $_GET["BDate"];
$BFlag = $_GET["BFlag"];
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
           $Query = 'INSERT into AND_201844049.bookinfo(book_title, book_author, book_publish, book_date, book_flag, mem_no) values(\''.$BTitle.'\',\''.$BAuthor.'\',\''.$BPublish.'\',\''.$BDate.'\',\''.$BFlag.'\',\''.$MemNo.'\');';
           $Conn->exec($Query);
           $response["Result"] = "Success";
        echo json_encode($response);
   
        } catch(PDOException $ex) {
            $response["Result"] = "Fail";
            echo $ex -> getMessage()."<br>";
            
        }
        $Conn = null;
?>
