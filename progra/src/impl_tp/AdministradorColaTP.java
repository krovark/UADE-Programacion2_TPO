package impl_tp;

import apis.ColaPrioridadTDA;
import impl.ColaPrioridadAO;
import apis.ConjuntoTDA;
import impl.ConjuntoLD;
import apis.DiccionarioSimpleTDA;
import impl.DicSimpleA;
import apis_tp.AdministradorDeColas;

public class AdministradorColaTP implements AdministradorDeColas {

	ConjuntoTDA puestos;
	ColaPrioridadTDA colapaciente;

	int cantPuestos;
	int idCola;
	int idTurno;

	class Prefijo {
		int clave;
		String valor;
	}

	Prefijo[] dicSPrefijo;
	int cantidad;

	// Registro de atención
	DiccionarioSimpleTDA dicSClientes;
	//DiccionarioSimpleTDA dsPrefijo;
	DiccionarioSimpleTDA dicSAtencion;

	@Override
	public void inicializar(int cantidadPuestos) {

		cantPuestos = cantidadPuestos;
		idCola = 0;
		idTurno = 0;

		// Inicializo cantidad de puestos -> 1...N
		puestos = new ConjuntoLD();
		puestos.inicializarConjunto();
		for (int i = 0; i < cantidadPuestos; i++) {
			puestos.agregar(i + 1);
		}

		// Inicializo la Cola
		colapaciente = new ColaPrioridadAO();
		colapaciente.inicializarCola();

		// Inicializo diccionario cliente
		dicSClientes = new DicSimpleA();
		dicSClientes.inicializarDiccionario();

		// Inicializo diccionario Prefijo
		InicializarDiccionariodePrefijos();

		// Inicializo diccionario Atencion
		dicSAtencion = new DicSimpleA();
		dicSAtencion.inicializarDiccionario();

	}
//
	@Override
	public int acolar(int prioridad) {
		// Cliente que se suma a la cola
		idCola++;
		idTurno++;
		colapaciente.acolarPrioridad(idTurno, prioridad);
		dicSClientes.agregar(idTurno, prioridad);
		return idTurno;
	}

	@Override
	public int desacolar(int puesto) {
		int auxIdTurno = colapaciente.primero();
		int prioridad = colapaciente.prioridad();
		colapaciente.desacolar();
		idCola--;
		dicSAtencion.agregar(idTurno, puesto);
		
		
		return auxIdTurno;
	}

	@Override
	public int cantPuestos() {
		// TODO Auto-generated method stub
		return cantPuestos;
	}

	@Override
	public int primero() {
		// TODO Auto-generated method stub
		return colapaciente.primero();
	}

	@Override
	public int prioridad() {
		// TODO Auto-generated method stub
		return colapaciente.prioridad();
	}

	@Override
	public boolean colaVacia() {
		// TODO Auto-generated method stub
		return colapaciente.colaVacia();
	}

	@Override
	public int puestoDelTurno(int idTurno) {
		// TODO Auto-generated method stub
		return dicSAtencion.recuperar(idTurno);
	}

	@Override
	public DiccionarioSimpleTDA turnos() {
		// TODO Auto-generated method stub
		// Clientes esperando en la cola
		return dicSClientes;
	}

	public String ObtenerNomenclatura(int idTurno, int prioridad) {

		if(cantidad == 0) return "".concat(String.valueOf(idTurno));
		
		String ticket = RecuperarPrefijo(prioridad).concat(String.valueOf(idTurno));

		return ticket;
	}

	
	// DICCIONARIO SIMPLE MODIFICADO
	public void InicializarDiccionariodePrefijos() {
		dicSPrefijo = new Prefijo[100];
		cantidad = 0;
	}

	public void AgregarPrefijo(int clave, String valor) {
		//Agrega los prefijos a la cola de turnos
		int i;
		for (i = 0; i < cantidad && dicSPrefijo[i].clave != clave; i++)
			;
		if (i < cantidad) {
			dicSPrefijo[i].valor = valor;
		} else {
			dicSPrefijo[i] = new Prefijo();
			dicSPrefijo[i].valor = valor;
			dicSPrefijo[i].clave = clave;
			cantidad++;
		}
	}

	public void EliminarPrefijo(int clave) {
		// Elimina el prefijo de la cola de turnos
		int i;
		for (i = 0; i < cantidad && dicSPrefijo[i].clave != clave; i++)
			;
		if (i < cantidad) {
			cantidad--;
			for (; i < cantidad; i++) {
				dicSPrefijo[i] = dicSPrefijo[i + 1];
			}
		}
		
	}

	public String RecuperarPrefijo(int clave) {
		// Recupera el prefijo del diccionario de claves
		int i;
		String prefijo = "";
		for (i = 0; i < cantidad && dicSPrefijo[i].clave != clave; i++)
			;
		if (i < cantidad) {
			prefijo = dicSPrefijo[i].valor;
		}
		return prefijo;
	}

	public ConjuntoTDA Claves() {
		// Genera conjunto del diccionario de Claves 
		ConjuntoTDA clav = new ConjuntoLD();
		clav.inicializarConjunto();
		for (int i = 0; i < cantidad; i++) {
			clav.agregar(dicSPrefijo[i].clave);
		}
		return clav;
	}

}
