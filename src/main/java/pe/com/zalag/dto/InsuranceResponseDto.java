package pe.com.zalag.dto;

public record InsuranceResponseDto(
        String policeNumber,
        String holderNumber,
        Double coverage,
        Boolean active,
        String type
) {
}
