<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
include ('./conexion.php');
 $link=conectar();
$id=$_REQUEST['cod'];

    //ejecutamos la consulta sql
    
$sql="DELETE FROM area where id_area=$id";
$res=$link->query($sql);    
    //verificar
if($link->affected_rows>0){
if($res==true){
    echo "se elimino el area";    
    }else{
     echo "Error";
        }

    }else{

    echo "No existe el area con el id $id";
    }


}

 $link->close();
    
    
    
?>