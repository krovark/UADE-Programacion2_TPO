package apis_tp;

import apis.ConjuntoTDA;
import apis.DiccionarioSimpleTDA;

public interface AdministradorDeColas {

	void inicializar(int cantidad);

	// siempre que el TDA esté inicializado

	int acolar(int prioridad);

	// siempre que el TDA esté inicializado

	int desacolar(int puesto);

	// siempre que el TDA esté inicializado

	int cantPuestos();

	// siempre que el TDA esté inicializado

	int primero();
	
	// siempre que el TDA esté inicializado

	int prioridad();

	// siempre que el TDA esté inicializado y cola no vacías

	boolean colaVacia();
	// siempre que el TDA esté inicializado

	
	int puestoDelTurno(int idTurno);

	// siempre que el TDA esté inicializado y cola no vacías

	DiccionarioSimpleTDA turnos();

	// siempre que el TDA esté inicializado y cola no vacías
	
	String ObtenerNomenclatura(int idTurno, int prioridad);
	
	
	void InicializarDiccionariodePrefijos();// siempre que el diccionario esté inicializado 
	void AgregarPrefijo(int clave, String valor);// siempre que el diccionario esté inicializado 
	void EliminarPrefijo(int clave);// siempre que el diccionario esté inicializado 
	String RecuperarPrefijo(int clave); // siempre que el diccionario esté inicializado 
	ConjuntoTDA Claves(); // siempre que el diccionario esté inicializado 
	
//
}
