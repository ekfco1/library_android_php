<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
        $MemNo = $_GET["MemNo"];
        $BNo = $_GET["BNo"];
        $RMemNo = $_GET["rMemNo"];
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

            $Query = 'UPDATE AND_201844049.bookinfo SET bookinfo.book_flag = 1, bookinfo.mem_no = \''.$MemNo.'\' where bookinfo.book_no =\''.$BNo.'\';' ;
            $response["UpdateResult"] = "Success";
            $result_count = $Conn->prepare($Query);
            $result_count -> execute();
            
            if($result_count->rowCount() != 0){
                $Query = 'INSERT INTO AND_201844049.bookrent(book_no, mem_no, rbook_rentdate, rbook_returndate) 
                VALUES(\''.$BNo.'\', \''.$RMemNo.'\', Current_Date, date_add(Current_date, INTERVAL 7 DAY))';
                $response["Result"] = "Success";
                $Conn->exec($Query);
            }else{
                $response["Result"]="Fail";
            }
            function han ($s) { return reset(json_decode('{"s":"'.$s.'"}')); }
            function to_han ($str) { return preg_replace('/(\\\u[a-f0-9]+)+/e', "han('$0')", $str); }
            echo to_han(json_encode($response)); 
        } catch(PDOException $ex) {
            echo $ex -> getMessage()."<br>";
        }
        $Conn = null;
?>
