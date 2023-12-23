package io.github.yvasyliev.jackson;

public class WrappableImpl implements Wrappable {
    private int id;

    @Override
    public int getId() {
        return id;
    }
}
