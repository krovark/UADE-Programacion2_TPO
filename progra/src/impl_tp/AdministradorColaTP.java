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
	ColaPrioridadTDA cp;

	int cantPuestos;
	int indiceCola;
	int idTurno;

	class Prefijo {
		int clave;
		String valor;
	}

	Prefijo[] dsPrefijo;
	int cant;

	// Registro de atencion actual
	DiccionarioSimpleTDA dsClientes;
	//DiccionarioSimpleTDA dsPrefijo;
	DiccionarioSimpleTDA dsAtencion;

	@Override
	public void inicializar(int cantidadPuestos) {

		cantPuestos = cantidadPuestos;
		indiceCola = 0;
		idTurno = 0;

		// Inicializo la cantidad de puestos de manera secuencial 1...N
		puestos = new ConjuntoLD();
		puestos.inicializarConjunto();
		for (int i = 0; i < cantidadPuestos; i++) {
			puestos.agregar(i + 1);
		}

		// Inicializo la Cola. (Cola habilitada)
		cp = new ColaPrioridadAO();
		cp.inicializarCola();

		// Inicializo diccionario cliente.
		dsClientes = new DicSimpleA();
		dsClientes.inicializarDiccionario();

		// Inicializo diccionario Prefijo.
		InicializarDiccionariodePrefijos();

		// Inicializo diccionario Atencion.
		dsAtencion = new DicSimpleA();
		dsAtencion.inicializarDiccionario();

	}
//
	@Override
	public int acolar(int prioridad) {
		// Cliente que se suma a la cola
		indiceCola++;
		idTurno++;
		cp.acolarPrioridad(idTurno, prioridad);
		dsClientes.agregar(idTurno, prioridad);
		return idTurno;
	}

	@Override
	public int desacolar(int puesto) {
		int auxIdTurno = cp.primero();
		int prioridad = cp.prioridad();
		cp.desacolar();
		indiceCola--;
		dsAtencion.agregar(idTurno, puesto);
		// Cliente a ser atendido (EL idDelcliente es el indice de la cola, el puesto)
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
		return cp.primero();
	}

	@Override
	public int prioridad() {
		// TODO Auto-generated method stub
		return cp.prioridad();
	}

	@Override
	public boolean colaVacia() {
		// TODO Auto-generated method stub
		return cp.colaVacia();
	}

	@Override
	public int puestoDelTurno(int idTurno) {
		// TODO Auto-generated method stub
		return dsAtencion.recuperar(idTurno);
	}

	@Override
	public DiccionarioSimpleTDA turnos() {
		// TODO Auto-generated method stub
		// Clientes esperando en la cola
		return dsClientes;
	}

	public String ObtenerNomenclatura(int idTurno, int prioridad) {

		if(cant == 0) return "".concat(String.valueOf(idTurno));
		
		String ticket = RecuperarPrefijo(prioridad).concat(String.valueOf(idTurno));

		return ticket;
	}

	
	// DICCIONARIO SIMPLE MODIFICADO
	public void InicializarDiccionariodePrefijos() {
		dsPrefijo = new Prefijo[100];
		cant = 0;
	}

	public void AgregarPrefijo(int clave, String valor) {
		int i;
		for (i = 0; i < cant && dsPrefijo[i].clave != clave; i++)
			;
		if (i < cant) {
			dsPrefijo[i].valor = valor;
		} else {
			dsPrefijo[i] = new Prefijo();
			dsPrefijo[i].valor = valor;
			dsPrefijo[i].clave = clave;
			cant++;
		}
	}

	public void EliminarPrefijo(int clave) {
		int i;
		for (i = 0; i < cant && dsPrefijo[i].clave != clave; i++)
			;
		if (i < cant) {
			cant--;
			for (; i < cant; i++) {
				dsPrefijo[i] = dsPrefijo[i + 1];
			}
		}
		
	}

	public String RecuperarPrefijo(int clave) {
		int i;
		String prefijo = "";
		for (i = 0; i < cant && dsPrefijo[i].clave != clave; i++)
			;
		if (i < cant) {
			prefijo = dsPrefijo[i].valor;
		}
		return prefijo;
	}

	public ConjuntoTDA Claves() {
		
		ConjuntoTDA cl = new ConjuntoLD();
		cl.inicializarConjunto();
		for (int i = 0; i < cant; i++) {
			cl.agregar(dsPrefijo[i].clave);
		}
		return cl;
	}

}
