package desafio.transacao_api.controller.dtos;



public record EstatisticasResponseDTO(Double sum, Double avg, Double max, Double min, Long count) {

    public EstatisticasResponseDTO(double d, double e, double f, double g, int i) {
        this(d, e, f, g, (long) i);
    }
    
        

}