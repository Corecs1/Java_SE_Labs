import com.corecs.javase.buildings.Flat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlatTest {
    private static Flat flat;


    @Test
    public void newDefaultConstructorShouldHaveDefaultArea() {
        flat = new Flat();
        Assertions.assertEquals(50, flat.getArea());
    }

    @Test
    public void newDefaultConstructorShouldHaveDefaultAmountOfRooms() {
        flat = new Flat();
        Assertions.assertEquals(2, flat.getAmountOfRooms());
    }

    @Test
    public void newConstructorWithAreaShouldHaveDefaultAmountOfRooms() {
        flat = new Flat(70);
        Assertions.assertEquals(2, flat.getAmountOfRooms());
    }
}
