<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    include ('./conexion.php');
     $link=conectar();
    $id=$_REQUEST['id'];
    $cla=$_REQUEST['password'];	
		
        //ejecutamos la consulta sql
 //UPDATE admin SET clave=12345 where respuesta1='goku' and respuesta2='santander';       
    $sql="UPDATE admin SET clave='$cla' where id_admin=$id";
    $res=$link->query($sql);    
        //verificar si existen los registros
    if($link->affected_rows>0){
    if($res==true){
        echo "Se actualizo la contraseña";    
        }else{
         echo "Error";
            }
    
        }else{
    
        echo "No existe el usuario con id $id";
        }
    
    
    }
    
     $link->close();
?>

    
    
    
