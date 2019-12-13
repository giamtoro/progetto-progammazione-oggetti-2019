# PROGETTO DI PROGAMMAZIONE AD OGGETTI

quasto prgetto maven è stato ftoo con JDK 11

## Indice

- Settare il dataset con la chiamta **GET  "/set_data"** nei paramitri vine preso Url.
- Prendre i metadata del dataset con la chimata **GET "/get_metadata"**.
- Settare il dataset filtrato con la chiamta **POST "/set_filtered"**.
- Vedere il dato filtrato esisiste con la chiamta  **POST "/filtered_visual"**
- Vedere il dataset  con la chimata **GET "/get_data/{Mode}"** in MODE si può   scegliere tutto il dataset o quello filtrato
- Vedere il modello dei dati  con la chimata **GET "/get_model/{Mode}"** in MODE si può  scegliere tutto il dato o quello filtrato, anche con paramtro Field scelgo quale attributo del metadato fare vedere

## INTRODUZIONE

### **GET  "/set_data"**

-----

quasta chiamta viene utilizata per settare il dataset con il metodo GET dando come parametri KEY ci deve essere Url.
come ritorno ridarra i metadata del dataset inserito.
se i dati già inseri gli risetta.
quata finzione è la pria ad essere chamta perchè carica i dati

> test
 localhost:8080/set_data?Url=http://data.europa.eu/euodp/data/api/3/action/package_showid=eu-cohesion-policy-historic-eu-payments-regionalised-and-modelled

{
    "NUTS2_ID": {
        "descrizione": "ID regione",
        "type": "String"
    },
    "Programming_Period": {
        "descrizione": "periodo progammato",
        "type": "int[]"
    },
    "NUTS1_ID": {
        "descrizione": "ID nazione",
        "type": "String"
    },
    "Standard_Error_of_modelled_annual_expenditure": {
        "descrizione": "Errore standard della spesa annuale modellata",
        "type": "int"
    },
    "Modelled_annual_expenditure": {
        "descrizione": "Spesa annua modellata",
        "type": "int"
    },
    "Fund": {
        "descrizione": "fondo",
        "type": "String"
    },
    "Year": {
        "descrizione": "anno",
        "type": "int"
    },
    "Standard_Deviation_of_annual_expenditure": {
        "descrizione": "Deviazione standard della spesa annuale",
        "type": "int"
    },
    "EU_Payment_annual": {
        "descrizione": "pagamrto annuale EU",
        "type": "int"
    },
    "﻿Country": {
        "descrizione": "nazione di provenienza",
        "type": "String"
    },
    "NUTS2_name": {
        "descrizione": "nome regione",
        "type": "String"
    }
}

### **GET "/get_metadata"**

-----

ritorna il metadata del dataset

> test
 localhost:8080/get_metadata

{
    "NUTS2_ID": {
        "descrizione": "ID regione",
        "type": "String"
    },
    "Programming_Period": {
        "descrizione": "periodo progammato",
        "type": "int[]"
    },
    "NUTS1_ID": {
        "descrizione": "ID nazione",
        "type": "String"
    },
    "Standard_Error_of_modelled_annual_expenditure": {
        "descrizione": "Errore standard della spesa annuale modellata",
        "type": "int"
    },
    "Modelled_annual_expenditure": {
        "descrizione": "Spesa annua modellata",
        "type": "int"
    },
    "Fund": {
        "descrizione": "fondo",
        "type": "String"
    },
    "Year": {
        "descrizione": "anno",
        "type": "int"
    },
    "Standard_Deviation_of_annual_expenditure": {
        "descrizione": "Deviazione standard della spesa annuale",
        "type": "int"
    },
    "EU_Payment_annual": {
        "descrizione": "pagamrto annuale EU",
        "type": "int"
    },
    "﻿Country": {
        "descrizione": "nazione di provenienza",
        "type": "String"
    },
    "NUTS2_name": {
        "descrizione": "nome regione",
        "type": "String"
    }
}

### **POST "/set_filtered"**

-----

questa chiamta setta il dato filtrato con un body json ridando il numero di elemti filtrati , ma il dataset principale deve essere settato e il dato filtrato deve essere maggiore di 1 e  invece se quei parametri non corrispondono al modello  ridarra l'errore "non foud".

>test
{
    "year": 0,
    "country": "IT",
    "nuts2_ID": "",
    "nuts1_ID": "",
    "nuts2_name": "",
    "fund": "",
    "programming_Period": [
        0,
        0
    ],
    "eu_Payment_annual": 0,
    "modelled_annual_expenditure": 0,
    "standard_Error_of_modelled_annual_expenditure": 0,
    "standard_Deviation_of_annual_expenditure": 0
}

2702

### **POST "/filtered_visual"**

-----

quasta funzione si utiliza per provare con un body json dei se il filtrodel dataset se il dataset è vuoto e il body json parametri sono tutti vuoti del modello ridarra l'errore "non foud" e  invece se quei parametri non corrispondono al modello e anoo un valore diverso dal vuoto ridarra l'errore "non foud".

>test
{
    "year": 0,
    "country": "IT",
    "nuts2_ID": "",
    "nuts1_ID": "",
    "nuts2_name": "",
    "fund": "",
    "programming_Period": [
        0,
        0
    ],
    "eu_Payment_annual": 0,
    "modelled_annual_expenditure": 0,
    "standard_Error_of_modelled_annual_expenditure": 0,
    "standard_Deviation_of_annual_expenditure": 0
}

2702

### **GET "/get_data/{Mode}"**

-----

questa chiamta fa vedere il dataset dove Mode
>Mode

| Mode     | visulizione data        |           path                 |
| ---------|-------------------------|--------------------------------|
| All      | tutto il data set       |           "/get_data/All"      |
| Filtered | il dataset filtrato     |       "/get_data/Filtered"     |

> test localhost:8080/get_data/All

[
    {
        "year": 1994,
        "country": "AT",
        "eu_Payment_annual": 0,
        "modelled_annual_expenditure": 715213,
        "programming_Period": [
            1994,
            1999
        ],
        "nuts2_ID": "AT11",
        "nuts1_ID": "AT1",
        "nuts2_name": "Burgenland",
        "fund": "EAFRD",
        "standard_Deviation_of_annual_expenditure": 93168,
        "standard_Error_of_modelled_annual_expenditure": 2948
    },

ritorna  tutti i dati

> test localhost:8080/get_model/Filtered

[
    {
        "year": 2011,
        "country": "IT",
        "nuts2_name": "Piemonte",
        "fund": "ESF",
        "nuts1_ID": "ITC",
        "nuts2_ID": "ITC1",
        "standard_Error_of_modelled_annual_expenditure": 0,
        "standard_Deviation_of_annual_expenditure": 0,
        "eu_Payment_annual": 23719805,
        "programming_Period": [
            2000,
            2006
        ],
        "modelled_annual_expenditure": 0
    },

ritorna i dati filtrati

### **GET "/get_model/{Mode}"**

-----

questa chiamta fa vedere il modello dove Mode
> Mode

| Mode     | visulizione data        |           path                 |
| ---------|-------------------------|--------------------------------|
| All      | tutto il data set       |           "/get_data/All"      |
| Filtered | il dataset filtrato     |       "/get_data/Filtered"     |

con la possibilità di scgliere la statistica da  vedere tramite il parametro
> Field

| Field                 | visulizione della statistca                        |
| ----------------------|----------------------------------------------------|
| ""                    |  modelo di tutti metadati del dato                 |
| parametro del modello |modello del paramtro scelto del metadati del dato   |

> test localhost:8080/get_model/All?Field=

ritorna le statitiche di tutti i dati

> test localhost:8080/get_model/Filtered?Field=

ritorna le statitiche di tutti i dati

> test localhost:8080/get_model/All?Field=Year

{
    "Year": {
        "std": 14,
        "min": 1986,
        "max": 2016,
        "mean": 2004,
        "count": 28372
    }
}

> test localhost:8080/get_model/Filtered?Field=Year

{
    "Year": {
        "std": 14,
        "min": 1986,
        "max": 2016,
        "mean": 2004,
        "count": 28372
    }
}
