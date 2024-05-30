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
}
