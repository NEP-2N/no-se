using namespace std;

empleado alta(){
	empleado nuevo;

	cout<<"\n- - - - - - - - - -";
	cout<<"\nRegistrar usuario";

	cout<<"\nNombre: ";
	cin.getline(nuevo.nombre, 100);

	cout<<"Edad: ";
	cin>>nuevo.edad;
	cin.ignore();

	cout<<"Direccion: ";
	cin.getline(nuevo.direccion, 200);

	cout<<"- - - - - - - - - -";

	return nuevo;
}

void baja(vector<empleado> &base){
	unsigned short int indice;
	
	cout<<"\n- - - - - - - - - -";
	cout<<"\nLista de empleados";

	for(int i=0; i<base.size(); i++){
		cout<<"\n"<<i+1<<". "<<base[i].nombre;

	}
	cout<<"\n\nElija el empleado a dar de baja: ";
	cin>>indice;

	base.erase(base.begin() + indice - 1);
	cout<<"\n- - - - - - - - - -";
}

void consultar_empleado(){

}

void consultar_empleados(){

}

void modificar(){

}
