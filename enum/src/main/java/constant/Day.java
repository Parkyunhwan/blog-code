package constant;

public enum Day {
    MONDAY, TUESDAY;
}

class DayStatic {
    private final static DayStatic MONDAY = new DayStatic();
    private final static DayStatic TUESDAY = new DayStatic();
}
