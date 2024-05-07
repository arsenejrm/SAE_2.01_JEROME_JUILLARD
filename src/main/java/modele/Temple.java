package modele;

public class Temple {
    private Position positionTemple;
    private final int couleurTemple;
    private int contenuTemple = 0;
    public Temple(Position positionTemple, int couleurTemple) {
        this.positionTemple = positionTemple;
        this.couleurTemple = couleurTemple;
    }

    public Position getPositionTemple() { return positionTemple; }

    public int getCouleurTemple() { return couleurTemple; }

    public int getContenuTemple() { return contenuTemple; }

    public void setPositionTemple(Position positionTemple) { this.positionTemple = positionTemple; }

    public void setContenuTemple(int contenuTemple) { this.contenuTemple = contenuTemple; }

    public String toString() {return "Temple de couleur " + couleurTemple + " : Position = " + positionTemple + ", Contenu = " + contenuTemple;}
}
