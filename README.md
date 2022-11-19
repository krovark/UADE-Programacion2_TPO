# UADE-Programacion2_TPO


Desarrollar un programa para gestionar los turnos para atención en un consultorio médico MEDIUADE que dispone de 9 puestos de atención. El cliente solicita un turno de atención mediante un ticket (identificador) con una nomenclatura descripta más abajo. El programa irá llamando a los clientes indicando ticket y puesto de atención.
Nomenclaturas: Prefijo + numero
Prefijos:
¿ Guardia: ¿GUA¿. Prioridad: 10
¿ ENFERMERIA: ¿ENF¿. Prioridad: 20
¿ ODONTOLOGIA: ¿ODO¿. Prioridad: 40
Ejemplo: ODONTOLOGÍA ODO994 .
El programa debe poder:
- ingresar la cantidad de puestos de atención
- Ingresar las solicitudes de turnos según nomenclatura.
- Una vez ingresadas las solicitudes:
- Ir llamando cada 4 s los turnos imprimiendo:
- Ticket (identificador del turno)
- Puesto en el que se atenderá.
- Realizar el test con 16 puestos de atención y 30 clientes , que cubra los distintos tipos imprimiendo un listado final con :
- Identificador ¿ hora de atención - puesto
