public enum FonteEnergia { 
        solar("solar"), eolica("eolica"), hidrica("hidrica");
    
        private String font;

        FonteEnergia (String font) {
            this.font = font;
        }

        public String getFonte() {
            return font;
        }
    
}