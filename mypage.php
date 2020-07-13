<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
    if(isset($_GET["MemNo"])) {
        $MemNo = $_GET["MemNo"];
        $DataBaseName = 'AND_201844049';
        $DBID = 'AND_201844049';
        $DBPW = 'AND_201844049';
	    $server = "mysql:host=114.71.137.109;port=3306;";
        try {
            $response["Result"] = "Success";
            $Conn = new PDO($server, $DBID, $DBPW);
            $Conn -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $Query = 'SET NAMES utf8';
            $Conn -> query($Query);

            $Query = 'SELECT rent.mem_no as mem_no, book.book_title as book_title, rent.rbook_rentdate as rent, rent.rbook_returndate as returndate, rent.book_no as book_no
            from AND_201844049.bookrent as rent join AND_201844049.bookinfo as book on rent.book_no = book.book_no where rent.mem_no = \''.$MemNo.'\';';

            $response = array();
            foreach ($Conn -> query($Query) as $result)
            {
                $result_row["MemNo"] = $result['mem_no']; 
                $result_row["BTitle"] = $result['book_title'];
                $result_row["BRent"] = $result['rent'];
                $result_row["BReturn"] = $result['returndate'];
                $result_row["BNo"] = $result['book_no'];
                array_push($response, $result_row);
            }   
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
