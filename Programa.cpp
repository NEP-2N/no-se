#include <iostream>
#include <fstream>
#include <vector>
#include <string>

#include "gestion_archivos.h"
#include "funciones.h"

using namespace std;

int main(){
	char archivo[10] = "base.bin";
	vector<empleado> base;
	leer(base, archivo);

	char opcion;
	
	while(1){
		cout<<"\n- - - - - Menu - - - - -";
		cout<<"\n1. Alta";
		cout<<"\n2. Baja";
		cout<<"\n3. Empleados";

		cout<<"\n0. Salir";

		cout<<"\n\nOpcion: ";

		cin>>opcion;
		cin.ignore();
		switch(opcion){
			case '1':
				base.push_back(alta());
			break;
			case '2':
				if(base.size() == 0){
					cout<<"\nNo hay empleados";
					cout<<"\n\nIntroduzca cualquier letra para volver";
					cin>>opcion;
					cin.ignore();
					break;
				}
				baja(base);
			break;


			case '3':
				cout<<"\n- - - - - Empleados - - - - -";
				cout<<"\n1. Buscar empleado";
				cout<<"\n2. Ver lista de empleados";
				cout<<"\n\n Opcion: ";
				cin>>opcion;
				cin.ignore();
				switch(opcion){
					case '1':
					break;

					case '2':
					break;

					default:
					break;
				}
			break;


			case '0':
				guardar(base, archivo);
				return 1;
			break;
		}
	}

	for(int i=0; i<base.size(); i++){
		cout<<endl;
		cout<<base[i].nombre<<endl;
		cout<<base[i].edad<<endl;
		cout<<base[i].direccion<<endl;
	}

}






