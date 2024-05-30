import org.example.entrainement_sae_01_02.modele.Position;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder( MethodOrderer.OrderAnnotation.class) public class TestPosition {

    @Test @Order(1)
    void equals(){
        System.out.println("Test méthode equals()");
        assertEquals(true,new Position(0,0).equals(new Position(0,0)));
        assertEquals(false,new Position(0,0).equals(new Position(0,1)));
        assertEquals(false,new Position(0,0).equals(new Position(1,0)));
    }

    @Test @Order(2)
    void deplacementUneCase(){
        System.out.println("Test méthode deplacementUneCase()");
        Position positionOrigineA=new Position(0,0);
        Position positionDestinatieA=new Position(1,0);
        positionOrigineA.deplacementUneCase(positionDestinatieA);
        assertEquals(true,positionOrigineA.equals(positionDestinatieA));

        Position positionOrigineB=new Position(0,0);
        Position positionDestinatieB=new Position(1,0);
        positionOrigineB.deplacementUneCase(positionDestinatieB);
        assertEquals(true,positionOrigineB.equals(positionDestinatieB));

        Position positionOrigineC=new Position(0,0);
        Position positionDestinatieC=new Position(1,0);
        positionOrigineC.deplacementUneCase(positionDestinatieC);
        assertEquals(true,positionOrigineC.equals(positionDestinatieC));

        Position positionOrigineD=new Position(0,0);
        Position positionDestinatieD=new Position(1,0);
        positionOrigineD.deplacementUneCase(positionDestinatieD);
        assertEquals(true,positionOrigineD.equals(positionDestinatieD));
    }
}
