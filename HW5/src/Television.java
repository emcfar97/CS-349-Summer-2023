// The purpose of this class is to model a television
// Ethan McFarland
// 21/06/2023
public class Television {

    private String MANUFACTURER;
    private int SCREEN_SIZE;
    private boolean powerOn;
    private int channel;
    private int volume;

    /**Contstructor with parameters*/
    public Television(String brand, int size) {
        this.MANUFACTURER = brand;
        this.SCREEN_SIZE = size;
        this.powerOn = false;
        this.volume = 20;
        this.channel = 2;
    }
    /** mutator or setter for channel field */
    public void setChannel(int station) {
        this.channel = station;
    }
    /** mutator or setter for power field */
    public void power() {
        this.powerOn = !this.powerOn;
    }
    /** mutator or incrementor for volume field */
    public void increaseVolume() {
        this.volume += 1;
    }
    /** mutator or decrementor for volume field */
    public void decreaseVolume() {
        this.volume -= 1;
    }
    /** accessor for channel field */
    public int getChannel() {
        return this.channel;
    }
    /** accessor for volume field */
    public int getVolume() {
        return this.volume;
    }
    /** accessor for manufacturer field */
    public String getManufacturer() {
        return this.MANUFACTURER;        
    }
    /** accessor for screen size field */
    public int getScreenSize() {
        return this.SCREEN_SIZE;
    }
}