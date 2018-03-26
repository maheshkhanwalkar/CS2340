package edu.gatech.buzzshelter.model.control;

import java.util.List;

/**
 * Created by Kyle on 3/24/2018.
 */

public class DataServiceFacade {

    private static DataServiceFacade INSTANCE = new DataServiceFacade();
    public static DataServiceFacade getInstance(){return INSTANCE;}

    private DataManager dataManager;

    private DataServiceFacade(){
        dataManager = new DataManager();
    }



    public List<DataElement> getData() {
        return dataManager.getData();
    }
}
