package org.wahlzeit.model;

public class CreateEnergyDrinkPhotoException extends Exception{
    private static final long serialVersionUID = 1L;
    
    public CreateEnergyDrinkPhotoException(String message){
        super(message);
    }

    public CreateEnergyDrinkPhotoException(IllegalStateException e){
        super(e);
    }
}
