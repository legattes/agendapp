<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */
include ("conexao.php");

class Convenio { 
    protected $conn;

    public function __construct(){
        $this->conn = new Conexao();
    }

    public function get(){
        echo 'oi';
        die();
        $query = "SELECT * FROM Convenio";

        $conn = (new Conexao())->connect();

        if(!($queryResult = mysqli_query($conn, $query))){
            header('HTTP/1.1 500 FAIL');
            die();
        }

        $result['convenios'] = [];

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

    public static function post(){   
        $conn = (new Conexao())->connect();

        if(!isset($_POST['convenio_nome']) 
        || !isset($_POST['convenio_dia_mes_fatura']) 
        || ($_POST['convenio_nome']) == '' 
        || ($_POST['convenio_dia_mes_fatura']) == ''){
            header('HTTP/1.1 500 FAIL');
            die();;
        }

        $convenioNome = $_POST['convenio_nome'];
        $convenioDiaMesFatura = $_POST['convenio_dia_mes_fatura'];

        $query = "INSERT INTO Convenio (convenio_nome, convenio_dia_mes_fatura)
        values ('{$convenioNome}','{$convenioDiaMesFatura}')";

        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');
        } else {
            header('HTTP/1.1 500 FAIL');
        }
  
    }

    public static function delete($id){
        $conn = (new Conexao())->connect();

        print_r("hasdhas " . $id);

        /*if(!isset($_POST['convenio_id']) 
        || ($_POST['convenio_id']) == ''){
            header('HTTP/1.1 500 FAIL');
            die();
        }

        $_DELETE

        $convenioId = $_POST['convenio_id'];*/
        $convenioId = $id;

        //$query = "DELETE FROM Convenio where convenio_id = '{$convenioId}'";

        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');
        } else {
            header('HTTP/1.1 500 FAIL');
        }
    }

    public static function put(){
        $conn = (new Conexao())->connect();

        if(!isset($_POST['convenio_id'])
        || !isset($_POST['convenio_nome']) 
        || !isset($_POST['convenio_dia_mes_fatura'])
        || ($_POST['convenio_id']) == ''
        || ($_POST['convenio_nome']) == ''
        || ($_POST['convenio_dia_mes_fatura']) == ''){
            header('HTTP/1.1 500 FAIL');
            die();
        }

        $convenioId = $_POST['convenio_id'];
        $convenioNome = $_POST['convenio_nome'];
        $convenioDiaMesFatura = $_POST['convenio_dia_mes_fatura'];

        $query = "UPDATE Convenio SET convenio_nome = '$convenioNome',convenio_dia_mes_fatura = '$convenioDiaMesFatura' WHERE convenio_id = '$convenioId';";

        if(mysqli_query($conn, $query)){
            header('HTTP/1.1 200 OK');
        } else {
            header('HTTP/1.1 500 FAIL');
        }
    }
}    