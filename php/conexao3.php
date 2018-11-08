<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

class Conexao{
    public function connect(){    
        $server = 'localhost';
        $user = 'id7728892_root';
        $pass = '8759836a';
        $db = 'id7728892_agenda';

        return $conn = mysqli_connect($server, $user, $pass, $db);
    }
}

?>