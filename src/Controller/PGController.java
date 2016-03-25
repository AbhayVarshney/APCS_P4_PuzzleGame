package Controller;

import Model.PGModel;
import View.PGView;

public class PGController {
    PGView view;
    PGModel model;

    public PGController() { // default constructor
        view = new PGView(this);
        model = new PGModel();
    }
}
