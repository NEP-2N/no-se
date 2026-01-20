#include <iostream>
#include <fstream>
#include <vector>

#include <string>
#include <string.h>

using namespace std;

struct empleado{
	char nombre[50];
};

void guardar(vector<empleado> guardar, char archivo[]){

    FILE *f = fopen(archivo, "wb"); // write binary
    if (!f) return;
	
	for(unsigned int i=0; i<guardar.size(); i++){
		fwrite(&guardar[i], sizeof(empleado), 1, f);
	}

	fclose(f);

	return;
}

void leer(vector<empleado> &abrir, char archivo[]){
	
	empleado origen;

    FILE *f = fopen(archivo, "rb"); // read binary
    if (!f) return;

	while(fread(&origen, sizeof(empleado), 1, f)){
		abrir.push_back(origen);
	}
    
	fclose(f);

	return;	
}

#define archivo "prueba.bin"

int main(){

	vector<empleado> save;
	vector<empleado> test;

	empleado temp;

	strcpy(temp.nombre, "ivan");

	save.push_back(temp);

	strcpy(temp.nombre, "bolas");

	save.push_back(temp);

	guardar(save, archivo);

	leer(test, archivo);

	for(unsigned int i=0; i<test.size(); i++){
		cout<<"\n\n"<<test[i].nombre<<"\n\n";
	}


}