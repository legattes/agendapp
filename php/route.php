<?php

/**
 * author legates (Lucas)
 * email lucas.arantes55@gmail.com
 */

class Route{
    public static function handle(){
        $url = explode("/", $_SERVER['REQUEST_URI']);
        $method = $_SERVER["REQUEST_METHOD"];
        $args = [];
        unset($url[0]);        
        unset($url[1]);
        
        $controller = array_shift($url);
        
        if(count($url) > 0){
            foreach($url as $argument){
                array_push($args, $argument);
            }
        }
        
        if(class_exists($controller)){
            $controller = new $controller;
        }
        
        if(is_object($controller)){
            $controller->$method($args);
        } else {
            echo "Não suportado";
        }  
    }
}