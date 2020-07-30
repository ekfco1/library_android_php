<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);
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
           
                $Query = 'SELECT book_title, book_author, book_publish, book_no, book_flag from AND_201844049.bookinfo';
     
                $response = array();
     
                foreach ($Conn -> query($Query) as $result)
                {
                    $result_row["MemNo"] = ""; 
                    $result_row["BTitle"] = $result['book_title'];
                    $result_row["BAuthor"] = $result['book_author'];
                    $result_row["BPublish"] = $result['book_publish'];
                    $result_row["BNo"] = $result['book_no'];
                    $result_row["BRent"] = "";
                    $result_row["BReturn"] = "";
                    $result_row["BFlag"] = $result['book_flag'];
                    if($result['book_flag'] == 1){
                        $Query = 'SELECT bookrent.mem_no as mem_no, bookinfo.book_title as book_title, bookinfo.book_author as book_author,
                        bookinfo.book_publish as book_publish, bookinfo.book_no as book_no, bookrent.rbook_rentdate as rent, bookrent.rbook_returndate
                        as rbook_return, bookinfo.book_flag as book_flag from AND_201844049.bookinfo as bookinfo join AND_201844049.bookrent as bookrent 
                        on bookinfo.book_no = bookrent.book_no';
                        foreach ($Conn -> query($Query) as $result1)
                        {
                            $result_row["MemNo"] = $result1['mem_no']; 
                            $result_row["BTitle"] = $result1['book_title'];
                            $result_row["BAuthor"] = $result1['book_author'];
                            $result_row["BPublish"] = $result1['book_publish'];
                            $result_row["BNo"] = $result1['book_no'];
                            $result_row["BRent"] = $result1['rent'];
                            $result_row["BReturn"] = $result1['rbook_return'];
                            $result_row["BFlag"] = $result['book_flag'];
                        }
                    }
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
