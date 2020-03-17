package app;

public abstract class Card{

    private Integer value;

    public Card(Integer value){
        this.value = value;
    }

    public Card(){}

    public Integer getValue(){
        return this.value;
    }

    public Card withValue(Integer value){
        this.value = value;
        return this;
    }

}