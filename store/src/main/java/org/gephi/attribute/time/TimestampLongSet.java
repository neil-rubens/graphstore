package org.gephi.attribute.time;

/**
 *
 * @author mbastian
 */
public final class TimestampLongSet extends TimestampValueSet<Long> {

    private long[] values;

    public TimestampLongSet() {
        super();
        values = new long[0];
    }

    public TimestampLongSet(int capacity) {
        super(capacity);
        values = new long[capacity];
    }

    @Override
    public void put(int timestampIndex, Long value) {
        if (value == null) {
            throw new NullPointerException();
        }
        putLong(timestampIndex, value);
    }

    public void putLong(int timestampIndex, long value) {
        final int index = putInner(timestampIndex);
        if (index < values.length) {
            values[index] = value;
        } else {
            long[] newArray = new long[values.length + 1];
            System.arraycopy(values, 0, newArray, 0, index);
            System.arraycopy(values, index, newArray, index + 1, values.length - index);
            newArray[index] = value;
            values = newArray;
        }
    }

    @Override
    public void remove(int timestampIndex) {
        final int removeIndex = removeInner(timestampIndex);
        if (removeIndex > 0) {
            if (removeIndex != size) {
                System.arraycopy(values, removeIndex + 1, values, removeIndex, size - removeIndex);
            }
        }
    }

    @Override
    public Long get(int timestampIndex) {
        final int index = getIndex(timestampIndex);
        if (index >= 0) {
            return values[index];
        }
        throw new IllegalArgumentException("The element doesn't exist");
    }

    public long getLong(int timestampIndex) {
        final int index = getIndex(timestampIndex);
        if (index >= 0) {
            return values[index];
        }
        throw new IllegalArgumentException("The element doesn't exist");
    }

    @Override
    public Long[] toArray() {
        final Long[] res = new Long[size];
        for (int i = 0; i < size; i++) {
            res[i] = values[i];
        }
        return res;
    }

    public long[] toLongArray() {
        if (size < values.length - 1) {
            final long[] res = new long[size];
            System.arraycopy(values, 0, res, 0, size);
            return res;
        } else {
            return values;
        }
    }

    @Override
    public void clear() {
        super.clear();
        values = new long[0];
    }
}
