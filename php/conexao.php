<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

class Conexao{
    public function connect(){    
        $server = 'localhost';
        $user = 'u112785488_zion';
        $pass = '3Uj7zy98@';
        $db = 'u112785488_agapp';

        return mysqli_connect($server, $user, $pass, $db);
    }
}