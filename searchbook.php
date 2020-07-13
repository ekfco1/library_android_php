<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
    // POST 변수가 모두 입력되어 있는지 확인하기
    $BTitle = $_GET["BTitle"];
    $DataBaseName = 'AND_201844049';
    $DBID = 'AND_201844049';
    $DBPW = 'AND_201844049';
    $server = "mysql:host=114.71.137.109;port=3306;";
    try {
        $Conn = new PDO($server, $DBID, $DBPW);
        $Conn -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $Query = 'SET NAMES utf8';
        $Conn -> query($Query);
        $result_row["Result"] = "Fail";
        $Query = 'SELECT book_title, book_author, book_publish, book_no, book_flag from AND_201844049.bookinfo where book_title = \''.$BTitle.'\';';

        $response = array();
        
        foreach ($Conn -> query($Query) as $result)
        {
            $result_row["Result"] = "Success";
            $result_row["BTitle"] = $result['book_title'];
            $result_row["BAuthor"] = $result['book_author'];
            $result_row["BPublish"] = $result['book_publish'];
            $result_row["BNo"] = $result['book_no'];
            $result_row["BFlag"] = $result['book_flag'];
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
