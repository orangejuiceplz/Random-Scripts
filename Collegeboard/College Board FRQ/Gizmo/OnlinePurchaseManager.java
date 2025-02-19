public class OnlinePurchaseManager {


    private ArrayList<Gizmo> purchases;


    public int countElectronicsByMaker(String maker) {
        int count = 0;

        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).isElectronic()) {
                if (purchases.get(i).getMaker().equals(maker)) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean hasAdjacentEqualPair() {
        boolean tracker;

        for (int i = 0; i < purchase.size() - 1; i++) {
            if (purhases.get(i).equals(other)) {
                tracker = true;
            }
        }

        return tracker;
    }


}