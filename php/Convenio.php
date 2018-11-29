<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

class Convenio {
    public function post(){          
        if(!isset($_POST['convenio_nome']) 
        || !isset($_POST['convenio_dia_mes_fatura']) 
        || ($_POST['convenio_nome']) == '' 
        || ($_POST['convenio_dia_mes_fatura']) == ''){
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
            die();
        }
        
        $convenioNome = $_POST['convenio_nome'];
        $convenioDiaMesFatura = $_POST['convenio_dia_mes_fatura'];
                
        $conn = (new Connection())->connect();

        $query = "INSERT INTO Convenio (convenio_nome, convenio_dia_mes_fatura)
        values ('{$convenioNome}','{$convenioDiaMesFatura}')";


        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');            
            header('Location: index.php');
        } else {
            header('HTTP/1.1 500 FAIL');
            header('Location: index.php');
        } 
    }

    public function get(){        
        $result['convenios'] = [];

        $query = "SELECT * FROM Convenio";
        $conn = (new Connection())->connect();
        
        if(!($queryResult = mysqli_query($conn, $query))){
            header('HTTP/1.1 500 FAIL');
            die();
        }

        while($row = mysqli_fetch_assoc($queryResult)){
            $item = [
                'id' => $row['convenio_id'],
                'nome' => $row['convenio_nome'],        
                'dia_mes_fatura' => $row['convenio_dia_mes_fatura'],
            ];
        
            array_push($result['convenios'], $item);
        }    

        echo json_encode($result);
    }
}    