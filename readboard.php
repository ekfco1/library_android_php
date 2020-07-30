<?php
ini_set('display_errors',1);
ini_set('error_reporting',E_ALL);

    if(isset($_GET["BoardNo"])) {
        $BoardNo = $_GET["BoardNo"];
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
            $Query = 'SELECT board.board_no as board_no, board.board_title as board_title, board.board_book_title as book_title, board.board_content as board_content
            member.mem_flag, member.mem_no as mem_no from AND_201844049.board as board join AND_201844049.member as member on board.mem_no = member.mem_no where
            board_no = \''.$BoardNo.'\';';

            foreach ($Conn -> query($Query) as $result)
            { // 데이터 베이스 쿼리 결과 로우들을 반복문으로 파싱하기 결과값 출력
                $response["Result"] = "Success";
                $response["BoardNo"] = $result["board_no"];
                $response["BoardTitle"] = $result['board_title'];
                $response["BoardBTitle"] = $result["book_title"];
                $response["BoardContent"] = $result['board_content'];
                $response["MemNo"] = $result['mem_no'];
      
            }

            if($response["Result"] == "Success") 
            {
                function han ($s) { return reset(json_decode('{"s":"'.$s.'"}')); }
                function to_han ($str) { return preg_replace('/(\\\u[a-f0-9]+)+/e', "han('$0')", $str); }
                echo to_han(json_encode($response));
            }
            else
            {
                echo json_encode($response);
            }
     
            
          
        } catch(PDOException $ex) {
            echo $ex -> getMessage()."<br>";
        
        }
        $Conn = null;
    } else {
        $response["Result"] = "NotEnoughParmeter";
        echo json_encode($response);
    }
?>
