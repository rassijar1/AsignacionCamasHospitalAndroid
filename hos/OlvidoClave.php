<?php

    include('./conexion.php');
    $link=conectar();	

    // RECIBE LOS DATOS DE LA APP
    //$usuario = $_POST['usuario'];
    

    // DATOS DE PRUEBA
    $usuario ="sancas";
     

    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($usuario)) {

        echo "VACIO";

    } else {

        // CREAMOS LA CONSULTA
        $sql = "SELECT * FROM admin WHERE usuario='$usuario'";
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
            echo "NOCONSULTO";
        }
    }

?>