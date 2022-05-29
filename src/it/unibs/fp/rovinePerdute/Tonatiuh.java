package it.unibs.fp.rovinePerdute;
//DISTANZA EUCLIDEA
public class Tonatiuh extends Squad {
    private static final String NAME = "Tonatiuh";

    public Tonatiuh() {
        super(NAME);
    }

    public void findPath() {
        //CITY PATH
        setCityPath(Path.getPathEuclideo());
        //LIST OF CITIES
        for(int i = 0; i < getCityPath().size(); i++) {
            addCity(Map.getCityByKey(getCityPath().get(i)));
        }
        //COST
        setCost(Path.getCost());
        //NUMBER OF CITIES
        setCities(getCityPath().size());
    }


}
