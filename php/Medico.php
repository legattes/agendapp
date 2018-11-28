<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */
include ("conexao.php");

class Medico {
    protected $conn;

    public function __construct(){
        $this->conn = new Conexao();
    }

    public static function post(){   
    }

    public static function get(){
        
    }
}    