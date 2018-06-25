# meli-challange-parent
Desafío de Mercado Libre

Despliegue LOCAL

Para poder deployar los servicios de forma local se debe clonar dicho repositorio. Una vez clonado en su ordenador desde, en mi caso eclipse (se pueden usar otros), importar el proyecto meli-challange-parent y seleccionar los tres pom.xml. Si la importacion fue exitosa deberías ver el proyecto meli-challange-parent con sus dos proyectos hijos meli-challange-core y meli-challange-webapp. Finalmente hacer "mvn clean install" del proyecto y desplegar el meli-challange-webapp.
Algo muy importante a tener en cuenta es que hay que tener instalado el plugin de google cloud (google app engine) en el IDE y tener cuenta de google para poder desplegarlo de manera local. Dicho plugin se descarga desde el "eclipse marketplace". 

-------------------------------------------------------------------------------------------------------------------------------

El servicio mutant se debe ejecutar desde la aplicación de google Advanced REST client (ARC) la cual se puede descargar desde las extenciones de chrome. Una alternativa al ARC puede ser el SOAP UI.

Url de servicio mutant: http://meli-challange.appspot.com/meli/mutant

Body de ejemplo sin mutantes:
{"dna":["ATGCGA","CAGTGC","TTWTWT","AGAAGG","CCWCTA","TCACTG"]}

Body de ejemplo con mutantes:
{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}

Url del servicio stats: http://meli-challange.appspot.com/meli/stats

Ejemplo de response: {"countMutantDna":2,"countHumanDna":2,"ratio":1.0}
