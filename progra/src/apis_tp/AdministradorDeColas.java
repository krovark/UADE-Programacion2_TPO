package apis_tp;

import apis.ConjuntoTDA;
import apis.DiccionarioSimpleTDA;

public interface AdministradorDeColas {

	

	int primero();
	
	// siempre que el TDA est� inicializado

	int prioridad();

	// siempre que el TDA est� inicializado y cola no vac�as

	boolean colaVacia();
	// siempre que el TDA est� inicializado

	
	int puestoDelTurno(int idTurno);

	// siempre que el TDA est� inicializado y cola no vac�as

	DiccionarioSimpleTDA turnos();

	// siempre que el TDA est� inicializado y cola no vac�as
	
		void inicializar(int cantidad);

	// siempre que el TDA est� inicializado

	int acolar(int prioridad);

	// siempre que el TDA est� inicializado

	int desacolar(int puesto);

	// siempre que el TDA est� inicializado

	int cantPuestos();

	// siempre que el TDA est� inicializado
	
	String ObtenerNomenclatura(int idTurno, int prioridad);
	
	ConjuntoTDA Claves();
	
	// siempre que el diccionario est� inicializado
	
	void InicializarDiccionariodePrefijos();
	
	// siempre que el diccionario est� inicializado
	
	void AgregarPrefijo(int clave, String valor);
	
	// siempre que el diccionario est� inicializado
	
	void EliminarPrefijo(int clave);
	
	// siempre que el diccionario est� inicializado
	
	String RecuperarPrefijo(int clave);
	
	// siempre que el diccionario est� inicializado
	
	 
	
//
}
