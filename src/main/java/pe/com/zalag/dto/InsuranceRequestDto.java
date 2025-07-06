package pe.com.zalag.dto;

public record InsuranceRequestDto(
        String policeNumber,
        String holderNumber,
        Double coverage,
        Boolean active,
        String type
) {
}
