/**
 * Created by Alex on 23/04/2015.
 */
public class Geometria {

    private String tipo;
    private double[] coordenadas;

    public Geometria(String tipo, double[] coordenadas){
        this.tipo = tipo;
        this.coordenadas = coordenadas;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(double[] coordenadas) {
        this.coordenadas = coordenadas;
    }


}
