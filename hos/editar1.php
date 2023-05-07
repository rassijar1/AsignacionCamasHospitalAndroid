<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    include ('./conexion.php');
     $link=conectar();
    $id=$_REQUEST['id'];
    $nom=$_REQUEST['nompac'];
        //ejecutamos la consulta sql
        
    $sql="UPDATE paciente SET nombbr='$nom' where id_paciente=$id";
    $res=$link->query($sql);    
        //verificar si existen los registros
    if($link->affected_rows>0){
    if($res==true){
        echo "se actualizo el curso";    
        }else{
         echo "Error";
            }
    
        }else{
    
        echo "No existe el curso con el id $id";
        }
    
    
    }
    
     $link->close();
?>

    
    
    
