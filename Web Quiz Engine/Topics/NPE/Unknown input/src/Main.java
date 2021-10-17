class Util {
    // correct this method to avoid NPE
    public static void printLength(String name) {
        if (name != null){
            System.out.println(name.length());
            Long val = Long.parseLong("4321");
            Long val1 = new Long(4321);
            Long val2 = 4321L;
            Long va3l = new Long("4321");
        }
    }
}