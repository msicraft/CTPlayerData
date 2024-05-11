package me.msicraft.ctplayerdata.PlayerData.aCommon;

public class TagData {

    private final String sectionPath;
    private Object value;

    public TagData(String sectionPath, Object value) {
        this.sectionPath = sectionPath;
        this.value = value;
    }

    public TagData(Object value) {
        this.sectionPath = null;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getSectionPath() {
        return sectionPath;
    }

}
