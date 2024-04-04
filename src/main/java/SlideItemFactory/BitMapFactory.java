package SlideItemFactory;

public class BitMapFactory extends SlideItemFactory{
    @Override
    public SlideItem createSlideItem(int level, String value) {
        return new BitmapItem(level, value);
    }
}
