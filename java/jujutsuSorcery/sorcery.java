public class sorcery {
    public static void main(String[] args) {

        jujutsuSorcerer gojo = new jujutsuSorcerer("satoru gojo", true, "limitless", 100);
        jujutsuSorcerer maki = new jujutsuSorcerer("maki zenin", false, "cursed object mastery", 80);

        gojo.introduceYourself();
        System.out.println();
        maki.introduceYourself();
        System.out.println();
        
        gojo.setTechnique("six eyes");
        maki.setTechnique("ultimate weapon wielder");

        System.out.println(gojo);
        System.out.println();
        System.out.println(maki);
    }
}
