package TASK3;

public enum ActionType {
    BASE_ATTACK("базовая атака"),
    SPECIAL_SKILL("применение особого умения или магии"),
    REST("пропуск хода для восстановления сил");
    private final String description;

    ActionType(String description) {
        this.description = description;

    }
    public String getDescription(){
        return description;

    }

}
