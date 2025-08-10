package Modelo; // Cambia por tu paquete de modelos

/**
 * JavaBean para manejar los datos del dashboard principal
 * Encapsula todas las estadísticas que se muestran en el panel principal
 */
public class DashboardData {
    
    
    int empleadosActivos;
    int clientesActivos;
    int planesActivos;

    public DashboardData(int empleadosActivos1, int clientesActivos1, int planesActivos1) {
    }

    public DashboardData(int empleadosActivos, int clientesActivos, int planesActivos, int incidenciasPendientes) {
        this.empleadosActivos = empleadosActivos;
        this.clientesActivos = clientesActivos;
        this.planesActivos = planesActivos;
    }

    public int getEmpleadosActivos() {
        return empleadosActivos;
    }

    public void setEmpleadosActivos(int empleadosActivos) {
        this.empleadosActivos = empleadosActivos;
    }

    public int getClientesActivos() {
        return clientesActivos;
    }

    public void setClientesActivos(int clientesActivos) {
        this.clientesActivos = clientesActivos;
    }

    public int getPlanesActivos() {
        return planesActivos;
    }

    public void setPlanesActivos(int planesActivos) {
        this.planesActivos = planesActivos;
    }

}