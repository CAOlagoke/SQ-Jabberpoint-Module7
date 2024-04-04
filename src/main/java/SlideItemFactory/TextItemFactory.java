package SlideItemFactory;

public class TextItemFactory extends SlideItemFactory{
    @Override
    SlideItem createSlideItem(int level, String value) {
        return new TextItem(level, value);
    }
}
