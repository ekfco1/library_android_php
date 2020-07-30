<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
        $MemNo = $_GET["MemNo"];
        $BNo = $_GET["BNo"];
        // $BReturn = $_GET["BReturn"];
        // $EFlag = $_GET["EFlag"];
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

            $Query = 'SELECT mem_no, book_no, rbook_returndate, extend_flag from AND_201844049.bookrent where mem_no = \''.$MemNo.'\' and book_no = \''.$BNo.'\';';
            foreach ($Conn -> query($Query) as $result)
            {              
                $response["Result"] = "Exist";
                $response["MemNo"] = $result['mem_no'];
                $response["BNo"] = $result['book_no'];
                $response["BReturn"] = $result['rbook_returndate'];
                $response["EFlag"] = $result['extend_flag'];
                if( $result['extend_flag'] == 0){
                    $Query = 'UPDATE AND_201844049.bookrent set bookrent.extend_flag = 1, rbook_returndate = date_add(rbook_returndate, INTERVAL 7 DAY) where book_no = \''.$BNo.'\';';
                    $Conn->exec($Query);
                    $response["Result"] = "Success";
                    echo json_encode($response);
                }else{
                    echo json_encode($response);
                } 
            }
        
        
          
        } catch(PDOException $ex) {
            echo $ex -> getMessage()."<br>";
        }
        $Conn = null;
?>
