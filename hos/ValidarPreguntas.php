<?php

    include('./conexion.php');
    $link=conectar();	

    // RECIBE LOS DATOS DE LA APP
   // $respuesta1=$_POST['respuesta1'];
    //$respuesta2=$_POST['respuesta2'];

    // DATOS DE PRUEBA
    $respuesta1 = "";
     $respuesta2 ="";

    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($respuesta1) || empty($respuesta2)) {

        // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
        //echo "Se deben llenar los dos campos";
        echo "VACIO";

    } else {

        // CREAMOS LA CONSULTA
        $sql = "SELECT * FROM admin WHERE respuesta1='$respuesta1'AND respuesta2='$respuesta2'";
        $res=mysqli_query($link,$sql);

        // CREAMOS UN ARRAY PARA GUARDAR LOS VALORES DEL REGISTRO
        $data = array();

        // VARIABLE CON EL TOTAL DE REGISTROS OBTENIDOS
      $tam=$res->num_rows;

        //VERIFICAMOS QUE EXISTE ALGUN REGISTRO
        if($tam > 0) {
            
            // AGREGAMOS LOS VALORES AL ARRAY
            while($res2=$res->fetch_assoc()) {
                $data[] = $res2;

                // CREAMOS EL JSON Y LO MOSTRAMOS
                echo json_encode($data);
            }

        } else {
            // echo "No existe ese registro";
            echo "NO CONSULTO";
        }
    }

?>