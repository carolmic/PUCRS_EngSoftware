public enum Combustivel {
    petroleo("petroleo"), carvao("carvao"), nuclear("nuclear");

    private String comb;

    Combustivel(String comb) {
         this.comb= comb;
    }

    public String getCombustivel() {
        return comb;
    }
}
