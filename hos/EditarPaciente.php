<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    include ('./conexion.php');
     $link=conectar();
    $id=$_REQUEST['id'];
    $nom=$_REQUEST['nompac'];
        //ejecutamos la consulta sql
      $ape=$_REQUEST['appac'];
      $dir=$_REQUEST['dirpac'];
      $usu=$_REQUEST['uspac'];
      $cla=$_REQUEST['clapac'];
      $fec=$_REQUEST['fechapac'];
      $tel=$_REQUEST['telpac'];  
//UPDATE admin SET nombre='andres', apellido='martinez' where id_admin=1030;

// UPDATE paciente SET nombre='$nom',apellido='$ape',direccion='$dir',usuario='$usu',clave='$cla',fecha_nac='2002-03-03',telefono=12345 where id_paciente=33;

    $sql="UPDATE paciente SET nombre='$nom',apellido='$ape',direccion='$dir',usuario='$usu',clave='$cla',fecha_nac='$fec',telefono=$tel where id_paciente=$id";
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


    
    
    
