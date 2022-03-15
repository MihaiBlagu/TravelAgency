package controller;

import view.MainPage;

public class MainController {
    private final AgencyController ac;
    private final UserController uc;

    public MainController() {
        this.ac = new AgencyController();
        this.uc = new UserController();
    }

    public void run() {
        MainPage mainPage = new MainPage();
        mainPage.initialize();

        initializeListeners(mainPage);
    }

    private void initializeListeners(MainPage mainPage) {
        mainPage.getClientButton().addActionListener(a -> {
            uc.run();
        });

        mainPage.getAgencyButton().addActionListener( a -> {
            ac.run();
        });
    }


}
