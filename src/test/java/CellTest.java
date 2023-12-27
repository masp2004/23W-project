
import static org.junit.Assert.assertEquals;

public class CellTest {

    @org.junit.Test
    public void cellTest() {
        util.Cell cell = new util.Cell(1, 2);
        assertEquals(1, cell.getX());
        assertEquals(2, cell.getY());
    }
}