<?php

     include('./conexion.php');
     $link=conectar();
    //RECIBE LOS DATOS DE LA APP
   $idAdmin = $_POST['id_admin'];
   $nombre = $_POST['nombre'];
   $apellido = $_POST['apellido'];
   $usuario = $_POST['usuario'];
   $clave = $_POST['clave'];		
   $fechaNac = $_POST['fecha_nac'];
   $direccion = $_POST['direccion'];
   $telefono = $_POST['telefono'];
    $respuesta1 = $_POST['respuesta1'];
   $respuesta2 = $_POST['respuesta2'];
  


     //DATOS DE PRUEBA
    //$usuario = "admin2";
    //$password = 1234;
    //$nombre = "Administrador 2";
   // $idAdmin = 21;
   //$nombre = "Benito";
   //$apellido = "Camelas";
   //$usuario = "bencam";
   //$clave = "1234";		
   //$fechaNac ='2020-06-05';
   //$direccion = "cra tal # tal";
   //$cc="10244";
	//|| empty($cc)
		//,'$cc'
	

    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($usuario) || empty($clave) || empty($apellido) || empty($nombre) || empty($fechaNac) || empty($direccion)|| empty($idAdmin) || empty($telefono)|| empty($respuesta1)|| empty($respuesta2) ) {

        // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
        //echo "Se deben llenar los dos campos";
        echo "error1";

    } else {

        // CREAMOS LA CONSULTA
        $sql = "INSERT INTO admin VALUES('$idAdmin','$nombre','$apellido','$usuario','$clave','$fechaNac','$direccion','$telefono','$respuesta1','$respuesta2')";
       $res=mysqli_query($link, $sql) or die ("Error al insertar".mysqli_error($link));
        mysqli_close($link);//cerrar conexion BD
        
	if($res === TRUE) {
		
            echo "MENSAJE";

        }
	
    }

?>