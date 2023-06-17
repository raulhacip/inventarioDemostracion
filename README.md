# inventarioDemostracion

En este repositorio se subió la siguiente información:

1.  Fuentes java del proyecto spring boot
2.  Imagen de diagrama de clases.



Para la revisión de la ejecución del proyecto es imporante en el Spring tools iniciar el proyecto ejecutando Run Ass :  Spring Boot App

Se ejecutan los end point con la herramienta POSTMAN

los end point son los siguientes:

LISTADO DE ELEMENTOS

METODO: GET
URL: localhost:8080/api/inventario/elemento

RESPUESTA

{
    "metadata": [
        {
            "date": "Respuesta exitosa",
            "code": "00",
            "type": "Respuesta ok"
        }
    ],
    "elementoResponse": {
        "elemento": [
            {
                "id": 1,
                "serial": "003",
                "nombre": "PC de escritorio marca lenovo 0553",
                "descripcion": "PC marca Lenovo 013",
                "fechaCompra": "2021-06-14T13:34:00.000+00:00",
                "valorCompra": 3518743.0,
                "depreciacion": 3237243.5,
                "estado": "ACTIVO"
            }
        ]
    }
}

PARA AGREGAR UN ELEMENTO Y VALIDAR EL CálCULO realizado se ejecuta


METODO: POST
URL: localhost:8080/api/inventario/elemento
BODY: RAW JSON

{
"serial":"003",
"nombre":"PC de escritorio marca lenovo 0553",
"descripcion":"PC marca Lenovo 013",
"fechaCompra":"2021-06-14T13:34:00.000",
"valorCompra":3518743,
"estado":"ACTIVO"
}


RESPUESTA:

{
    "metadata": [
        {
            "date": "Respuesta exitosa",
            "code": "00",
            "type": "Respuesta ok"
        }
    ],
    "elementoResponse": {
        "elemento": [
            {
                "id": 1,
                "serial": "003",
                "nombre": "PC de escritorio marca lenovo 0553",
                "descripcion": "PC marca Lenovo 013",
                "fechaCompra": "2021-06-14T13:34:00.000+00:00",
                "valorCompra": 3518743.0,
                "depreciacion": 3237243.5,
                "estado": "ACTIVO"
            }
        ]
    }
}











