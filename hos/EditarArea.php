<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    include ('./conexion.php');
     $link=conectar();
    $id=$_REQUEST['id'];
    $nom=$_REQUEST['nomarea'];
        //ejecutamos la consulta sql
      $ape=$_REQUEST['numhab'];
      
//UPDATE admin SET nombre='andres', apellido='martinez' where id_admin=1030;

// UPDATE area SET nombre='$nom',='$ape' where id_paciente=33;

    $sql="UPDATE area SET nombre='$nom',num_habitaciones='$ape' where id_area=$id";
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

    
    
    
