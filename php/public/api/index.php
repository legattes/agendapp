<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */


include "../../../agendapp/Paciente.php";


$url = explode("/", $_SERVER['REQUEST_URI']);
$method = $_SERVER["REQUEST_METHOD"];
$arg = '';

$controller = $url[1];
if(count($url) > 2){
    $arg = $url[2];
}

$controller = new $controller;

$controller->$method($arg);
