import controller.MainController;

public class Main {
    public static void main(String[] args){
//        MainController mc = new MainController();
//        mc.run();

        //VacationPackService vs = new VacationPackService();
        /*ArrayList<VacationPack> packs = vs.getFilteredPacks(true, "500.0", true,
                "Maldives", true, "01-04-2022",
                true, "10-05-2022");*/
//        ArrayList<VacationPack> packs = vs.getAvailablePacks();
//        JTable t = vs.getPacksTable(packs);
//
//        UserPage ui = new UserPage();
//        ui.initialize();
//        UserController uc = new UserController();
//        uc.initializeUserPageListeners(ui);
        //ui.addTableToPane(t);

//        for(VacationPack p : packs) {
//            System.out.println("sttaus " + p.getStatus() + " name " + p.getName() + " start date " + p.getStartDate());
//        }

        //AgencyService as = new AgencyService();
//        AgencyController ac = new AgencyController();
//        ac.run();
        //as.insertDestination("Brasov");
        //AgencyController ac = new AgencyController();
        //ac.run();

        //DestinationRepo dr = new DestinationRepo();
        //dr.deleteDestination("Brasov");

        //AgencyController ac = new AgencyController();
        //ac.run();

        //UserController uc = new UserController();
        //uc.run();

        MainController mc = new MainController();
        mc.run();
    }
}
