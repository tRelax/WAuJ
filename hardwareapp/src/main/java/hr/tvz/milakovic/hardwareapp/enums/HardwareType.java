package hr.tvz.milakovic.hardwareapp.enums;

public enum HardwareType {
    CPU("CPU"),
    GPU("GPU"),
    MBO("MBO"),
    RAM("RAM"),
    STORAGE("STORAGE"),
    OTHER("OTHER");

    private final String hType;

    HardwareType(String value){
        this.hType = value;
    }
    public String getHardwareType(){
        return hType;
    }
}
