<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    include ('./conexion.php');
     $link=conectar();
    $id=$_REQUEST['idadmin'];
    $nom=$_REQUEST['nomadmin'];
      $ape=$_REQUEST['apadmin'];
      $dir=$_REQUEST['diradmin'];
      $usu=$_REQUEST['user'];
      $cla=$_REQUEST['clavadmin'];
      $fec=$_REQUEST['fechaadmin'];
      $tel=$_REQUEST['teladamin'];  
//UPDATE admin SET nombre='andres', apellido='martinez' where id_admin=1030;

// UPDATE admin SET nombre='$nom',apellido='$ape',direccion='$dir',usuario='$usu',clave='$cla',fecha_nac='2002-03-03',telefono=12345 where id_paciente=33;

    $sql="UPDATE admin SET 
id_admin=$id, nombre='$nom',apellido='$ape',direccion='$dir',clave='$cla',fecha_nac='$fec',telefono=$tel where usuario='$usu'";
    $res=$link->query($sql);    
        //verificar si existen los registros
    if($link->affected_rows>0){
    if($res==true){
        echo "se actualizo el curso";    
        }else{
         echo "Error";
            }
    
        }else{
    
        echo "No existe el admin con el usuario $usu";
        }
    
    
    }
    
     $link->close();
?>

    
    
    
