using namespace std;

struct empleado{
	int edad;
	char nombre[100];
	char direccion[200];
};

void guardar(vector<empleado> guardar, char archivo[]){
	FILE *f = fopen(archivo, "wb");

	if(!f) return;
	for(int i = 0; i<guardar.size(); i++){
		fwrite(&guardar[i], sizeof(empleado), 1, f);
	}

	fclose(f);
	return;
}

void leer(vector<empleado> &cargar, char archivo[]){
	empleado origen;

	FILE *f = fopen(archivo, "rb");
	if(!f) return;

	while(fread(&origen, sizeof(empleado), 1, f)) cargar.push_back(origen);

	fclose(f);
	return;
}