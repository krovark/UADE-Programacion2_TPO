package TP;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import apis_tp.AdministradorDeColas;
import impl_tp.AdministradorColaTP;

public class main {

	static int puestos = 9;
	//static int puestos = 16;
	
	//CAMBIAR A DISPOSICION PORQUE LA PRIMERA PARTE DICE QUE TENGA 9 puestos pero luego dice en el test que tenga 16 puestos de atención
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AdministradorDeColas adminTurnos = new AdministradorColaTP();
		adminTurnos.inicializar(puestos);
		cargarNomenclatura(adminTurnos);
		System.out.print("simula la generacion de ticket de la clinica \n");
		System.out.print("... \n");
		System.out.print("Generando cola de Tickets.. \n");
		simularCargarTurnos(adminTurnos);
		System.out.print("\n");
		System.out.print("simula la asignacion de ticket al puesto de llamado \n");
		System.out.print("... \n");
		System.out.print("\n Generador de Llamados\n");
		simularAsignarPuesto(adminTurnos);

	}

	//Cargar Nomenclatura
	public static void cargarNomenclatura(AdministradorDeColas administradorTurnos) {
		administradorTurnos.AgregarPrefijo(10, "GUA");
		administradorTurnos.AgregarPrefijo(20, "ENF");
		administradorTurnos.AgregarPrefijo(40, "ODO");
	}
	
	
	//Cargar Turnos
	public static void cargarTurnos(AdministradorDeColas adminTurnos,int prioridad) {
		int idTurno = adminTurnos.acolar(prioridad);
		String codTurno = adminTurnos.ObtenerNomenclatura(idTurno, prioridad);

		System.out.println("Ticket: " + codTurno);	
	}
	
	// Asigna el puesto - Llama al siguiente cliente de la cola
	public static void asignarPuesto(AdministradorDeColas adminTurnos, int puesto) {
		int prioridad = adminTurnos.prioridad();
		int idTurno = adminTurnos.desacolar(puesto);
		String codigoTurno = adminTurnos.ObtenerNomenclatura(idTurno, prioridad);

		System.out.println("Ticket: "+codigoTurno + "\t -> Puesto: " + puesto + "\t-> Hora:\t" + hora());
	
	}
	
	
	// TESTING
	
	// Genera el procesamiento de turnos
	public static void simularCargarTurnos(AdministradorDeColas adminTurnos) {
		int cont = 1;

		while (cont < 31) {
			int prioridad = getRandomNumber(1, 4);
			
			if (prioridad==1)
				prioridad=10;
			
			if (prioridad==2)
			prioridad=20;
			
			if (prioridad==3)
				prioridad=40;
			
			cargarTurnos(adminTurnos,prioridad);
			cont++;
		}
	}
	

	
	// Genera la simulacion de puestos cada 4s
	public static void simularAsignarPuesto(AdministradorDeColas adminTurnos) {

		while (!adminTurnos.colaVacia()) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int puesto = getRandomNumber(1, 10);
			asignarPuesto(adminTurnos, puesto);
		}
	}
	

	// COMPLEMENTARIO//
	
	
	public static String hora() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		String horario = java.time.LocalTime.now().format(formatter);
		return horario;
	}
	
	public static int getRandomNumber(int minimo, int maximo) {
		return (int) ((Math.random() * (maximo - minimo)) + minimo);
	}

	//

}
