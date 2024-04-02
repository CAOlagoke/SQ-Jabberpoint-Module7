import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextItemTest {
    @Test
    public void testCreateTextItem() {
        TextItem textItem = new TextItem(1, "Hello World");

        assertEquals(1, textItem.getLevel());
        assertEquals("Hello World", textItem.getText());
    }

    @Test
    public void testCreateEmptyTextItem() {
        TextItem textItem = new TextItem();

        assertEquals(0, textItem.getLevel());
        assertEquals("No Text Given", textItem.getText());
    }
}
