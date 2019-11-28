public enum Enum {

   DATA("data.data");

    private String pars;

    Enum (String parsdata){
        this.pars=parsdata;
    }

    public String toString(){
        return pars;
    }


}
